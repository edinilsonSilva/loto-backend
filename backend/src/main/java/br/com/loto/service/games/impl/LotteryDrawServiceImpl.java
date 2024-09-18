package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.request.CreateContestRequest;
import br.com.loto.api.mappers.LotteryDrawMapper;
import br.com.loto.client.ICaixaFeign;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import br.com.loto.domain.repository.ILotteryDrawRepository;
import br.com.loto.domain.specification.LotteryDrawSpecification;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.ILotteryDrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class LotteryDrawServiceImpl implements ILotteryDrawService {

    private final ILotteryDrawRepository lotteryDrawRepository;

    private final ICaixaFeign caixaFeign;

    @Override
    public Page<LotteryDraw> findAllByParams(ContestQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        return lotteryDrawRepository.findAll(LotteryDrawSpecification.search(query), pageRequest);
    }

    @Override
    @Transactional
    public CustomResponse<LotteryDraw> createAndUpdate(CreateContestRequest request) {
        return CustomResponse.<LotteryDraw>builder()
                .status(201)
                .message("Concurso cadastrado.")
                .content(null)
                .build();
    }

    @Override
    @Transactional
    public LotteryDraw save(LotteryDraw contest) {
        return lotteryDrawRepository.save(contest);
    }

    @Override
    public LotteryDraw findByIdWithThrow(Long contestId) {
        return lotteryDrawRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Concurso não encontrado."));
    }

    public Optional<LotteryDraw> findByGameTypeAndNumber(TypeGame typeGame, int number) {
        return lotteryDrawRepository.findByGameTypeAndNumber(typeGame, number);
    }

    @Override
    public LotteryDraw generateGame(TypeGame typeGame) {

        if (typeGame == null)
            return null;

        LotteryDraw cxLotteryDraw = LotteryDrawMapper.toLotteryDraw(caixaFeign.getResults(typeGame.getPathName()));

        Optional<LotteryDraw> lotteryDrawFound = findByGameTypeAndNumber(typeGame, cxLotteryDraw.getNumber());

        if (lotteryDrawFound.isPresent()) {

            LotteryDraw ldTempUpdate = cxLotteryDraw;
            ldTempUpdate.setId(lotteryDrawFound.get().getId());
            return lotteryDrawRepository.save(ldTempUpdate);
        }

        return lotteryDrawRepository.save(cxLotteryDraw);
    }

    @Override
    public void checkResults() {

        for (TypeGame typeGame : TypeGame.values()) {
            generateGame(typeGame);
            log.info("Atualização jogo " + typeGame.getDescription() + " as " + LocalDateTime.now());
        }
    }
}

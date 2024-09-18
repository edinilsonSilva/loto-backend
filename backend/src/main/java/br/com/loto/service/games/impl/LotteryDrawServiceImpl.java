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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public CustomResponse<LotteryDraw> create(CreateContestRequest request) {
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

    public Optional<LotteryDraw> findByGameTypeAndNumber (TypeGame typeGame, int number) {
        return lotteryDrawRepository.findByGameTypeAndNumber(typeGame, number);
    }

    @Override
    public LotteryDraw generateGame(TypeGame typeGame) {

        if (typeGame.equals(TypeGame.MEGA_SENA))
            return lotteryDrawRepository.save(LotteryDrawMapper.toLotteryDraw(caixaFeign.getMegaSenaResults()));

        if (typeGame.equals(TypeGame.DUPLA_SENA))
            return lotteryDrawRepository.save(LotteryDrawMapper.toLotteryDraw(caixaFeign.getDuplasenaResults()));

        if (typeGame.equals(TypeGame. ))
            return lotteryDrawRepository.save(LotteryDrawMapper.toLotteryDraw(caixaFeign.getLotofacilResults()));

        if (typeGame.equals(TypeGame.QUINA))
            return lotteryDrawRepository.save(LotteryDrawMapper.toLotteryDraw(caixaFeign.getQuinaResults()));


        return null;
    }
}

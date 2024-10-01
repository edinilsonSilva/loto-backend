package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.SharesQuery;
import br.com.loto.api.dto.game.response.SharesResponse;
import br.com.loto.domain.entity.Shares;
import br.com.loto.domain.repository.ISharesRepository;
import br.com.loto.exceptions.BetException;
import br.com.loto.service.games.ISharesConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class SharesConsultServiceImpl implements ISharesConsultService {

    private final ISharesRepository sharesRepository;

    @Override
    public Page<SharesResponse> findAllByParams(SharesQuery query) {
        return null;
    }

    @Override
    public Page<SharesResponse> findAllByParamsPublic(SharesQuery query) {
        return null;
    }

    @Override
    public Shares findByIdWithThow(Long sharesId) {
        return sharesRepository.findById(sharesId)
                .orElseThrow(() -> new BetException("Cota não encontrada.", 4004));
    }
}

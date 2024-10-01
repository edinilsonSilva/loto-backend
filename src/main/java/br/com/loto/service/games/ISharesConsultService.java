package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.SharesQuery;
import br.com.loto.api.dto.game.response.SharesResponse;
import br.com.loto.domain.entity.Shares;
import org.springframework.data.domain.Page;

public interface ISharesConsultService {

    Page<SharesResponse> findAllByParams(SharesQuery query);

    Page<SharesResponse> findAllByParamsPublic(SharesQuery query);

    Shares findByIdWithThow(Long sharesId);
}

package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.request.CreateContestRequest;
import br.com.loto.domain.entity.Contest;
import br.com.loto.exceptions.CustomResponse;
import org.springframework.data.domain.Page;

public interface IContestService {

    Page<Contest> findAllByParams(ContestQuery query);

    CustomResponse<Contest> create(CreateContestRequest request);

    Contest save(Contest contest);

    Contest findByIdWithThrow (Long contestId);
}

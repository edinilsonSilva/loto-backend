package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.GameQuery;
import br.com.loto.api.dto.game.request.CreateGameRequest;
import br.com.loto.domain.entity.Game;
import br.com.loto.exceptions.CustomResponse;
import org.springframework.data.domain.Page;

public interface IGameService {

    Page<Game> findAllByParams(GameQuery query);

    CustomResponse<Game> create(CreateGameRequest request);

    Game save(Game game);

    Game findByIdWithThrow (Long id);
}

package br.com.loto.service.games;

import br.com.loto.api.dto.game.request.CreateSharesRequest;
import br.com.loto.api.dto.game.request.UpdateBetRequest;
import br.com.loto.domain.entity.Shares;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISharesService {

    List<Shares> create(CreateSharesRequest request, List<MultipartFile> files);

    Shares update(UpdateBetRequest request, Long sharesId);

    void deleteById(Long betId);

    Shares save(Shares shares);
}

package br.com.loto.api.mappers;

import br.com.loto.api.dto.game.request.CreateSharesRequest;
import br.com.loto.api.dto.game.response.SharesResponse;
import br.com.loto.domain.entity.Shares;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * <p>
 * DATA CRIACAO: 10/09/2024
 */

@Component
@AllArgsConstructor(onConstructor_ = @Lazy)
public class SharesMapper {

    private final ModelMapper modelMapper;

    public SharesResponse convertEntityToResponse(Shares shares) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SharesResponse response = modelMapper.map(shares, SharesResponse.class);
        response.setName(shares.getSharesFile().getName());
        response.setContentType(shares.getSharesFile().getContentType());
        response.setSize(shares.getSharesFile().getSize());
        response.setBase64(shares.getSharesFile().getBase64());
        return response;
    }

    @SneakyThrows
    public CreateSharesRequest convertRequest(String request) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(request, CreateSharesRequest.class);
    }
}

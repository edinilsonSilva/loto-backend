package br.com.loto.api.mappers;

import br.com.loto.api.dto.responses.AccountResponse;
import br.com.loto.domain.entity.Account;
import br.com.loto.enums.TypeContact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Edinilson Silva - edinilson@nestec.com.br
 * <p>
 * DATA CRIACAO: 02/03/2022
 */

@Component
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountResponse convertEntityToResponse(Account account) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AccountResponse response = modelMapper.map(account, AccountResponse.class);

        response.setName(account.getPersonal().getName());
        response.setCpf(account.getPersonal().getCpf());
        response.setContact(account.getContacts().stream().filter(c -> c.getValidatedAt() != null)
                .filter(c -> c.getType().equals(TypeContact.EMAIL))
                .map(c -> c.getValue())
                .findFirst()
                .orElse(null));

        return response;
    }
}

package br.com.loto.api.mappers;

import br.com.loto.api.dto.account.responses.AccountResponse;
import br.com.loto.api.dto.account.responses.AccountWalletResponse;
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

        response.setName(account.getName());
        response.setCpf(account.getCpf());
        response.setEmail(account.getContacts().stream().filter(c -> c.getValidatedAt() != null)
                .filter(c -> c.getType().equals(TypeContact.EMAIL))
                .map(c -> c.getValue())
                .findFirst()
                .orElse(null));

        response.setPhone(account.getContacts().stream().filter(c -> c.getValidatedAt() != null)
                .filter(c -> c.getType().equals(TypeContact.MOBILE_PHONE))
                .map(c -> c.getValue())
                .findFirst()
                .orElse(null));

        response.setAdminPermissions(account.getAccountAdmin() == null ? null :
                account.getAccountAdmin().getPermissions()
                        .stream()
                        .map(p -> p.name())
                        .toList());

        response.setLotteryPermissions(account.getAccountLottery() == null ? null :
                account.getAccountLottery().getPermissions()
                        .stream()
                        .map(p -> p.name())
                        .toList());

        response.setWallets(account.getAccountLottery() == null ? null :
                account.getAccountLottery().getWallets().isEmpty() ? null :
                        account.getAccountLottery().getWallets().stream()
                                .map(w -> AccountWalletResponse.builder()
                                        .id(w.getId())
                                        .balance(w.getBalance())
                                        .currency(w.getTypeCurrency().name())
                                        .build())
                                .toList());

        return response;
    }
}

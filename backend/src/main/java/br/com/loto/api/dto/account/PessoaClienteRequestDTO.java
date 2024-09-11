package br.com.loto.api.dto.account;

import br.com.loto.domain.entity.Account;
import org.springframework.beans.BeanUtils;

import lombok.Data;


@Data
public class PessoaClienteRequestDTO {
    
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String cep;

    public Account converter(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Account account = new Account();
        BeanUtils.copyProperties(pessoaClienteRequestDTO, account);
        return account;
    }
}

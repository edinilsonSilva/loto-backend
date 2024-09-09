package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IAccountRepository extends PagingAndSortingRepository<Account, Long>, JpaSpecificationExecutor<Account>, JpaRepository<Account, Long> {

    Optional<Account> findByCpf(String cpf);
}

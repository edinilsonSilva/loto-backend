package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountContactRepository extends JpaRepository<AccountContact, Long>{

}

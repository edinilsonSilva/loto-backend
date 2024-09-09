package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountAdminRepository extends JpaRepository<AccountAdmin, Long> {

}

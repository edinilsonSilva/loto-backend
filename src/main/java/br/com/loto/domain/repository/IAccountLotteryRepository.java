package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountLottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountLotteryRepository extends JpaRepository<AccountLottery, Long> {

}

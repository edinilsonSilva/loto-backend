package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountLotteryWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountLotteryWalletRepository extends JpaRepository<AccountLotteryWallet, Long> {

}

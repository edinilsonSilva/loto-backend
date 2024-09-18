package br.com.loto.domain.repository;

import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ILotteryDrawRepository extends PagingAndSortingRepository<LotteryDraw, Long>, JpaSpecificationExecutor<LotteryDraw>, JpaRepository<LotteryDraw, Long> {

    Optional<LotteryDraw> findByGameTypeAndNumber (TypeGame typeGame, int number);
}

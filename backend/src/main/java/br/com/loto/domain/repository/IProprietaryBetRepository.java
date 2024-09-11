package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.entity.ProprietaryBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProprietaryBetRepository extends JpaRepository<ProprietaryBet, Long> {

}

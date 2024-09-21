package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findByParticipantId(Long participantId);

}

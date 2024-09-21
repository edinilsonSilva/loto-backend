package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParticipantRepository extends JpaRepository<Participant, Long> {

}

package br.com.loto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entidade que representa uma aposta criada pelo administrador
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "bet_chosen_numbers")
    @JoinColumn(name = "bet_id")
    @Column(name = "chosen_numbers")
    private List<Integer> chosenNumbers; // Números escolhidos (Mega-Sena tem 6 números)

    private BigDecimal amount; // Valor da aposta

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    @JsonIgnoreProperties({"bets"})
    private Pool pool;
}

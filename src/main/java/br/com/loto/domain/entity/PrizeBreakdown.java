package br.com.loto.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um jogo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prize_breakdown")
public class PrizeBreakdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prizeTierDescription;

    private int tier;

    private int numberOfWinners;

    private String prizeAmount;
}

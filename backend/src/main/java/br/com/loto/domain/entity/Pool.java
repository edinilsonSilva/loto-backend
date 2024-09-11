package br.com.loto.domain.entity;

import br.com.loto.enums.PoolStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa os bolões
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pools")
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;

    private String name;

    @JoinColumn(name = "total_amount")
    private BigDecimal totalAmount; // Total arrecadado

    @Enumerated(EnumType.STRING)
    private PoolStatus status; // Status do bolão (ABERTO, FECHADO, FINALIZADO)

    @JoinColumn(name = "number_of_draws")
    private int numberOfDraws; // Número de sorteios que este bolão participará

    @OneToMany(mappedBy = "pool")
    private List<Participant> participants;

    @OneToMany(mappedBy = "pool")
    private List<Bet> bets;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}

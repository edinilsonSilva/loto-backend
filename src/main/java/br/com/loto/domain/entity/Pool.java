package br.com.loto.domain.entity;

import br.com.loto.domain.enums.PoolStatus;
import br.com.loto.domain.enums.TypeProbability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @Enumerated(EnumType.STRING)
    private PoolStatus status; // Status do bolão (ABERTO, FECHADO, FINALIZADO)

    private String code;

    private Integer drawNumber;  // Número do concurso onde este balão foi executado

    private BigDecimal entryFee;  // Valor da entrada no bolão

    @Enumerated(EnumType.STRING)
    private TypeProbability probability;

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private List<Participant> participants;

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("pool")
    private List<Bet> bets;

    @ManyToOne
    @JoinColumn(name = "lottery_draw_id", nullable = false)
    @JsonIgnoreProperties("pools")
    private LotteryDraw lotteryDraw;
}

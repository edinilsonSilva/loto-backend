package br.com.loto.domain.entity;

import br.com.loto.enums.PoolStatus;
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

    private String name;

    @Enumerated(EnumType.STRING)
    private PoolStatus status; // Status do bolão (ABERTO, FECHADO, FINALIZADO)

    @Column(nullable = false)
    private BigDecimal entryFee;  // Valor da entrada no bolão

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private List<Participant> participants;

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private List<Bet> bets;

    @ManyToOne
    @JoinColumn(name = "contest_id", nullable = false)
    @JsonIgnoreProperties("pools")
    private Contest contest;
}

package br.com.loto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa um concurso
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contests")
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    @JsonIgnoreProperties("contests")
    private Game game;

    @Column(nullable = false)
    private Integer contestNumber;  // Número do concurso

    @Column(nullable = false)
    private BigDecimal prizeAmount;  // Valor acumulado do prêmio

    @Column(nullable = false)
    private LocalDate drawDate;  // Data do sorteio

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pool> pools;  // Lista de bolões associados ao concurso
}

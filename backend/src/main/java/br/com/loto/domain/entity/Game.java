package br.com.loto.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa um jogo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

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

    private String name; // Nome do jogo (Mega-Sena, Lotofácil, etc.)

    @Column(name = "min_numbers")
    private int minNumbers; // Quantidade mínima de números que podem ser escolhidos

    @Column(name = "max_numbers")
    private int maxNumbers; // Quantidade máxima de números que podem ser escolhidos

    @Column(name = "max_number_value")
    private int maxNumberValue; // Valor máximo do número (ex: 60 para Mega-Sena)

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Pool> pools;
}

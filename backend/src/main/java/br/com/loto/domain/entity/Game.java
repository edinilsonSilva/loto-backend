package br.com.loto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "min_numbers")
    private int minNumbers; // Quantidade mínima de números que podem ser escolhidos

    @Column(name = "max_numbers")
    private int maxNumbers; // Quantidade máxima de números que podem ser escolhidos

    @Column(name = "max_number_value")
    private int maxNumberValue; // Valor máximo do número (ex: 60 para Mega-Sena)

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("game")
    private List<Contest> contests;
}

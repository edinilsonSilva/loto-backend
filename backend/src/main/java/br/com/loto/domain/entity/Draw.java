package br.com.loto.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "draws")
public class Draw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime drawDate;

    @ElementCollection
    @CollectionTable(name = "winning_numbers", joinColumns = @JoinColumn(name = "draw_id"))
    @Column(name = "numero")
    private List<Integer> winningNumbers;

//    @OneToMany(mappedBy = "sorteio")
//    private List<Premio> premios;
}

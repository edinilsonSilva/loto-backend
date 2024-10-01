package br.com.loto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade que representa uma aposta criada pelo administrador
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shares")
public class Shares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToOne
    @JoinColumn(name = "shares_file_id")
    private SharesFile sharesFile;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    @JsonIgnoreProperties({"bets"})
    private Pool pool;
}

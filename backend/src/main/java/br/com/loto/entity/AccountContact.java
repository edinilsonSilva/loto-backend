package br.com.loto.entity;

import br.com.loto.enums.TypeContact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts_contacts")
public class AccountContact implements Serializable {

    private static final long serialVersionUID = 3647125436201199490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "validated_at")
    private LocalDateTime validatedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeContact type;

    private String value;

    private String code;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnoreProperties("contacts")
    private Account account;

}

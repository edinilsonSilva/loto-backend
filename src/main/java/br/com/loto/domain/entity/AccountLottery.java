package br.com.loto.domain.entity;

import br.com.loto.domain.enums.LotteryPermission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts_lotteries")
public class AccountLottery implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "accountLottery", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("accountLottery")
    @Fetch(FetchMode.SUBSELECT)
    private List<AccountLotteryWallet> wallets;

    @ElementCollection(targetClass = LotteryPermission.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "accounts_lotteries_permissions")
    @JoinColumn(name = "account_lottery_id")
    @Column(name = "permission")
    private Set<LotteryPermission> permissions;
}

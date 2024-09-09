package br.com.loto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

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

    @Column(name = "activated_at")
    private LocalDateTime activatedAt;

    private String name;

    private String cpf;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private AccountAdmin accountAdmin;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lottery_id", referencedColumnName = "id")
    private AccountLottery accountLottery;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "config_id", referencedColumnName = "id")
    private AccountConfig config;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    @Fetch(FetchMode.SUBSELECT)
    private List<AccountContact> contacts;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    @Fetch(FetchMode.SUBSELECT)
    private List<AccountPassword> passwords;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    @Fetch(FetchMode.SUBSELECT)
    private List<AccountPhoto> photos;

}

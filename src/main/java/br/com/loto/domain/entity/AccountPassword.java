package br.com.loto.domain.entity;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts_passwords")
public class AccountPassword implements Serializable {

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

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;
	
	private boolean active;
	
	@Column(name = "create_password_next_login")
	private boolean createPasswordNextLogin;
	
	@Column(name = "same_password_limit")
	private Long samePasswordLimit;
	
	private String password;
	
	@Column(name = "token_forget_password")
	private String tokenForgetPassword;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	@JsonIgnoreProperties("passwords")
	private Account account;

}

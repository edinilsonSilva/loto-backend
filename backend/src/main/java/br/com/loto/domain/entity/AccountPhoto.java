package br.com.loto.domain.entity;

import br.com.loto.enums.TypePhoto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts_photos")
public class AccountPhoto implements Serializable {

	private static final long serialVersionUID = 4049150557660462734L;

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

	@Column(name = "base_64", length = 10000000)
	private String base64;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "content_type")
	private String contentType;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "size")
	private Long size;

	@Column(name = "type_photo")
	@Enumerated(EnumType.STRING)
	private TypePhoto typePhoto;
	
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	@JsonIgnoreProperties("photos")
	private Account account;

}

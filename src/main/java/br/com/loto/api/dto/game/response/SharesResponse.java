package br.com.loto.api.dto.game.response;

import br.com.loto.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SharesResponse {

    private Long id;

    private String createdAt;

    private String createdBy;

    private String base64;

    private String name;

    private String description;

    private String contentType;

    private Long size;
}
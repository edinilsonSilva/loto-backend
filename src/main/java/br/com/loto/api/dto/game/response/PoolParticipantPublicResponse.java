package br.com.loto.api.dto.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PoolParticipantPublicResponse {

    private Long id;

    private String createdAt;

    private String createdBy;

}
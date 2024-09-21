package br.com.loto.api.controller.publics;

import br.com.loto.api.dto.account.requests.CreateAccountRequest;
import br.com.loto.api.dto.account.requests.ResetPasswordPublicRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.account.IAccountPublicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/accounts")
@Tag(name = "Rotas públicas para contas")
public class AccountPublicController {

    private final IAccountPublicService accountPubService;

    @Operation(
            summary = "Cadastrar uma nova conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/create-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Account>> create(@RequestBody @Valid CreateAccountRequest request) {
        return new ResponseEntity<>(accountPubService.create(request), HttpStatus.OK);
    }

    @Operation(
            summary = "Alterar senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/reset-password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse> resetPassword(@RequestBody @Valid ResetPasswordPublicRequest request) {
        accountPubService.resetPassword(request);
        return new ResponseEntity<>(CustomResponse.builder()
                .status(200)
                .message("Se o seu cpf existir em nossa plataforma, será enviado um e-mail para seu e-mail cadastrado para contunuar a troca de sua senha.")
                .build(), HttpStatus.OK);
    }
}
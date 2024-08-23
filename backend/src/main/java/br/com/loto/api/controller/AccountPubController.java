package br.com.loto.api.controller;

import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.IAccountPubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts/p")
@Tag(name = "Rotas públicas")
public class AccountPubController {

    private final IAccountPubService accountPubService;

    @Operation(
            summary = "Cadastrar uma nova conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping("/create-account")
    public ResponseEntity<CustomResponse<Account>> create(@RequestBody @Valid CreateAccountRequest request) {
        return new ResponseEntity<>(accountPubService.create(request), HttpStatus.OK);
    }

    @PostMapping("/senha-codigo")
    public String recuperarCodigo(@RequestBody Account account) {
        return accountPubService.solicitarCodigo(account.getUsername());
    }

    @PostMapping("/senha-alterar")
    public String alterarSenha(@RequestBody Account account) {
        return accountPubService.alterarSenha(account);
    }

}
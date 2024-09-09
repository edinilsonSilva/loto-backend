package br.com.loto.api.controller;

import br.com.loto.api.dto.query.AccountQuery;
import br.com.loto.api.dto.requests.ChangePasswordRequest;
import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins/accounts")
@Tag(name = "Rotas para o gerenciamento das contas administrativas")
public class AccountAdminController {

    private final IAccountService accountService;

    @Operation(
            summary = "Lista todas as contas admins",
            description = "É possível utilizar 3 parametros simutâneos sendo o [name],[cpf] e [active]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Account.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Account>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "active", required = false) boolean active) {

        AccountQuery accountQuery = AccountQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .name(name)
                .active(active)
                .cpf(cpf)
                .build();

        return new ResponseEntity<>(accountService.findAllByParams(accountQuery), HttpStatus.OK);
    }

    @Operation(
            summary = "Cadastrar uma nova conta admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Account>> create(@RequestBody @Valid CreateAccountRequest request) {
        return new ResponseEntity<>(CustomResponse.<Account>builder().build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Atualizar conta admin senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Void>> update(@RequestBody @Valid ChangePasswordRequest request) {
        return new ResponseEntity<>(CustomResponse.<Void>builder().build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Remover conta admin senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Void>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(CustomResponse.<Void>builder().build(), HttpStatus.OK);
    }
}
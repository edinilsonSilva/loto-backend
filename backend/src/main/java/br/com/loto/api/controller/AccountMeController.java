package br.com.loto.api.controller;

import br.com.loto.api.dto.account.requests.ChangePasswordRequest;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.account.IAccountService;
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
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts/me")
@Tag(name = "Rotas públicas")
public class AccountMeController {

    private final IAccountService accountService;

    @Operation(
            summary = "Alterar senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/change-my-password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Void>> changePassword() {
        return new ResponseEntity<>(CustomResponse.<Void>builder().build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Alterar foto perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PutMapping(value = "/change-photo", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Void>> change(@RequestBody @Valid ChangePasswordRequest request) {
        return new ResponseEntity<>(CustomResponse.<Void>builder().build(), HttpStatus.OK);
    }
}
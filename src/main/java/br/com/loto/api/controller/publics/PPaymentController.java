package br.com.loto.api.controller.publics;

import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.api.dto.efi.EfiPayResponse;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.efi.IEfiService;
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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/payment")
@Tag(name = "Rotas públicas para pagamentos")
public class PPaymentController {

    private final IEfiService efiService;

    @Operation(
            summary = "Pagar com cartão de crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/by-credit-card", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<EfiPayResponse>> create(@RequestBody @Valid EfiPayPaymentCreateRequet request) {
        return new ResponseEntity<>(CustomResponse.<EfiPayResponse>builder()
                .status(200)
                .message("Pagamento realizado com sucesso.")
                .content(efiService.paymentByCreditCard(request))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Ver pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/{chargeId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<EfiPayResponse>> create(@PathVariable Long chargeId) {
        return new ResponseEntity<>(CustomResponse.<EfiPayResponse>builder()
                .status(200)
                .content(efiService.getCharges(chargeId))
                .build(), HttpStatus.OK);
    }
}
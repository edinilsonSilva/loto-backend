package br.com.loto.api.controller.admin;

import br.com.loto.api.dto.game.queries.BetQuery;
import br.com.loto.api.dto.game.request.CreateBetRequest;
import br.com.loto.api.dto.game.request.UpdateBetRequest;
import br.com.loto.api.dto.game.response.BetResponse;
import br.com.loto.api.dto.game.response.PoolResponse;
import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.entity.Pool;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IBetConsultService;
import br.com.loto.service.games.IBetService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bets")
@Tag(name = "Rotas para o gerenciamento das apostas")
public class BetController {

    private final IBetService betService;
    private final IBetConsultService betConsultService;

    @Operation(
            summary = "Lista todas as apostas de um jogo",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PoolResponse.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BetResponse>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "pool-id", required = true) Long poolId) {

        return new ResponseEntity<>(betConsultService.findAllByParams(BetQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .poolId(poolId)
                .build()), HttpStatus.OK);
    }

    @Operation(
            summary = "Cadastrar uma nova aposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Bet>> create(@RequestBody @Valid CreateBetRequest request) {
        return new ResponseEntity<>(CustomResponse.<Bet>builder()
                .status(201)
                .message("Bolão cadastrado.")
                .content(betService.create(request))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Atualiza aposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PutMapping(value = "/{poolId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Bet>> update(@RequestBody @Valid UpdateBetRequest request, @PathVariable Long poolId) {
        return new ResponseEntity<>(CustomResponse.<Bet>builder()
                .status(201)
                .message("Bolão atualizado.")
                .content(betService.update(request, poolId))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Remove uma aposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @DeleteMapping(value = "/{betId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> delete(@PathVariable Long betId) {
        betService.deleteById(betId);
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(201)
                .message("Aposta removido.")
                .content(null)
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Consultar uma aposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/{betId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Bet>> findById(@PathVariable Long betId) {
        return new ResponseEntity<>(CustomResponse.<Bet>builder()
                .status(200)
                .message("")
                .content(betConsultService.findByIdWithThow(betId))
                .build(), HttpStatus.OK);
    }
}
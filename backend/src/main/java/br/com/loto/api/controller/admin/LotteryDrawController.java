package br.com.loto.api.controller.admin;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.response.LotteryDrawReduced01Response;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.ILotteryDrawConsultService;
import br.com.loto.service.games.ILotteryDrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lotteries")
@Tag(name = "Rotas para o gerenciamento das loterias")
public class LotteryDrawController {

    private final ILotteryDrawService lotteryDrawService;
    private final ILotteryDrawConsultService lotteryDrawConsultService;

    @Operation(
            summary = "Gerar jogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LotteryDraw.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/update-games", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<LotteryDraw>> updateGames() {
        lotteryDrawService.updateGameResults();
        return new ResponseEntity<>(CustomResponse.<LotteryDraw>builder()
                .status(201)
                .message("Atualizado com sucesso.")
                .content(null)
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Lista todos os concursos cadastrados",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LotteryDraw.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<LotteryDraw>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "number", required = false) Integer contestNumber,
            @RequestParam(value = "game-id", required = false) Long gameId) {

        return new ResponseEntity<>(lotteryDrawConsultService.findAllByParams(ContestQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .contestNumber(contestNumber)
                .gameId(gameId)
                .build()), HttpStatus.OK);
    }

    @Operation(
            summary = "Lista todos os concursos cadastrados com a quantidade de campos reduzidos",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LotteryDrawReduced01Response.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/reduced-01", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<LotteryDrawReduced01Response>> findAllReduced01(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "number", required = false) Integer contestNumber,
            @RequestParam(value = "name", required = false) Long gameId) {

        return new ResponseEntity<>(lotteryDrawConsultService.findAllReduced01(ContestQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .contestNumber(contestNumber)
                .gameId(gameId)
                .build()), HttpStatus.OK);
    }
}
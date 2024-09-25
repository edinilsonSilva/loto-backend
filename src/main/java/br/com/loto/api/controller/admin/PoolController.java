package br.com.loto.api.controller.admin;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.api.dto.game.request.UpdatePoolRequest;
import br.com.loto.api.dto.game.response.PoolResponse;
import br.com.loto.domain.entity.Pool;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IPoolConsultService;
import br.com.loto.service.games.IPoolService;
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
@RequestMapping("/api/v1/pools")
@Tag(name = "Rotas para o gerenciamento dos bolões")
public class PoolController {

    private final IPoolService poolService;
    private final IPoolConsultService poolConsultService;

    @Operation(
            summary = "Lista todos os jogos cadastrados",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PoolResponse.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<PoolResponse>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "name", required = false) String name) {

        return new ResponseEntity<>(poolConsultService.findAllByParams(PoolQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .name(name)
                .build()), HttpStatus.OK);
    }

    @Operation(
            summary = "Cadastrar um novo bolão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> create(@RequestBody @Valid CreatePoolRequest request) {
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(201)
                .message("Bolão cadastrado.")
                .content(poolService.create(request))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Atualiza bolão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PutMapping(value = "/{poolId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> update(@RequestBody @Valid UpdatePoolRequest request, @PathVariable Long poolId) {
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(201)
                .message("Bolão atualizado.")
                .content(poolService.update(request, poolId))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Remove bolão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @DeleteMapping(value = "/{poolId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> delete(@PathVariable Long poolId) {
        poolService.deleteById(poolId);
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(201)
                .message("Bolão removido.")
                .content(null)
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Consultar um bolão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/{pooId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> findById(@PathVariable Long pooId) {
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(200)
                .message("")
                .content(poolConsultService.findByIdWithThow(pooId))
                .build(), HttpStatus.OK);
    }
}
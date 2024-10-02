package br.com.loto.api.controller.admin;

import br.com.loto.api.dto.game.queries.SharesQuery;
import br.com.loto.api.dto.game.response.SharesResponse;
import br.com.loto.api.mappers.SharesMapper;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.entity.Shares;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.ISharesConsultService;
import br.com.loto.service.games.ISharesService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pools/shares")
@Tag(name = "Rotas para o gerenciamento das cotas de bolões")
public class PoolSharesController {

    private final ISharesService sharesService;
    private final ISharesConsultService sharesConsultService;
    private final SharesMapper sharesMapper;

    @Operation(
            summary = "Lista todos as cotas cadastradas",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SharesResponse.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<SharesResponse>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction,
            @RequestParam(value = "poolId", required = true) Long poolId) {

        return new ResponseEntity<>(sharesConsultService.findAllByParams(SharesQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .poolId(poolId)
                .build()), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar uma nova cota.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomResponse> create(
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "request", required = true) String request) throws IOException {
        return new ResponseEntity<>(CustomResponse.<List<Shares>>builder()
                .status(201)
                .message("Cota cadastrada.")
                .content(sharesService.create(sharesMapper.convertRequest(request), files))
                .build(), HttpStatus.OK);
    }


    @Operation(
            summary = "Remove cota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @DeleteMapping(value = "/{sharesId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Pool>> delete(@PathVariable Long sharesId) {
        sharesService.deleteById(sharesId);
        return new ResponseEntity<>(CustomResponse.<Pool>builder()
                .status(201)
                .message("Cota removida")
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
    @GetMapping(value = "/{sharesId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Shares>> findById(@PathVariable Long sharesId) {
        return new ResponseEntity<>(CustomResponse.<Shares>builder()
                .status(200)
                .message("")
                .content(sharesConsultService.findByIdWithThow(sharesId))
                .build(), HttpStatus.OK);
    }
}
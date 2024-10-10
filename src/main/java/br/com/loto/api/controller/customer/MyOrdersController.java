package br.com.loto.api.controller.customer;

import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.api.dto.game.queries.OrderQuery;
import br.com.loto.api.dto.game.response.OrderResponse;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.entity.Order;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IMyOrderConsultService;
import br.com.loto.service.games.IMyOrderService;
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
@RequestMapping("/api/v1/orders")
@Tag(name = "Rotas para compras e pedidos.")
public class MyOrdersController {

    private final IMyOrderService myOrderService;
    private final IMyOrderConsultService myOrderConsultService;

    @Operation(
            summary = "Comprar um bolão com cartão de crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @PostMapping(value = "/buy-pool-with-credit-card", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomResponse<Order>> buyAGamePoolWithCreditCard(@RequestBody @Valid EfiPayPaymentCreateRequet request) {
        return new ResponseEntity<>(CustomResponse.<Order>builder()
                .status(200)
                .message("Pagamento realizado com sucesso.")
                .content(myOrderService.buyAGamePoolWithCreditCard(request))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Consultar os pedidos da conta logada",
            description = "É possível utilizar parametros [name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LotteryDraw.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)})
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<OrderResponse>> findAllByParams(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "24", required = false) Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false) String direction) {

        return new ResponseEntity<>(myOrderConsultService.findAllByParams(OrderQuery.builder()
                .orderBy(orderBy)
                .direction(direction)
                .page(page)
                .limit(limit)
                .build()), HttpStatus.OK);
    }
}
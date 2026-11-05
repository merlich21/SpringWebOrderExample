package ru.skillfactory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.model.MyOrder;
import ru.skillfactory.repository.OrderService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Products", description = "API для управления заказами")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Получить список всех заказов",
    description = "Возвращает список всех доступных заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =
            "Успешно получен список заказов"),
            @ApiResponse(responseCode = "401", description =
            "Неавторизованный доступ"),
            @ApiResponse(responseCode = "404", description =
            "Заказы не найдены")
    })
    @GetMapping
    public List<MyOrder> getAllOrders() {
        return orderService.findAll();
    }


    @Operation(summary = "Получить заказ по id",
            description = "Возвращает заказ с указанным id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =
                    "Успешно получен заказ"),
            @ApiResponse(responseCode = "401", description =
                    "Неавторизованный доступ"),
            @ApiResponse(responseCode = "404", description =
                    "Заказ не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MyOrder> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Создать новый заказ",
            description = "Создаёт новый заказ и возвращает его")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =
                    "Заказ успешно создан"),
            @ApiResponse(responseCode = "501", description =
                    "Заказ не был создан")
    })
    @PostMapping
    public ResponseEntity<MyOrder> createOrder(@RequestBody MyOrder order) {
        MyOrder createdOrder = orderService.save(order);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + createdOrder.getId()))
                .body(createdOrder);
    }
}

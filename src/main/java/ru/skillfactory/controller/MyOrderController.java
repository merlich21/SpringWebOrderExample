//package ru.skillfactory.controller;
//
//import org.springframework.web.bind.annotation.*;
//import ru.skillfactory.model.Order;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Создайте контроллер для управления заказами (Order) с эндпоинтами:
// *
// * GET /api/v1/orders — получение всех заказов.
// * GET /api/v1/orders/{id} — получение заказа по ID.
// * POST /api/v1/orders — создание нового заказа.
// *
// */
//@RestController
//@RequestMapping("/api/v1/orders")
//public class MyOrderController {
//
//    private List<Order> orders = new ArrayList<>();
//
//    @GetMapping
//    public List<Order> getAllOrders() {
//        return orders;
//    }
//
//
//    @GetMapping("/{id}")
//    public Order getOrderById(@PathVariable Long id) {
//
//        Order order;
//
//        if (id >= orders.size()) {
//            order = new Order();
//        }
//        else {
//            order = orders.get(Math.toIntExact(id));
//        }
//
//        return order;
//    }
//
//
//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//
//        orders.add(order);
//        return order;
//    }
//
//}

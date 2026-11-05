package ru.skillfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Создайте контроллер для управления заказами (Order) с эндпоинтами:
 *
 * GET /api/v1/orders — получение всех заказов.
 * GET /api/v1/orders/{id} — получение заказа по ID.
 * POST /api/v1/orders — создание нового заказа.
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class,args);
    }
}

package ru.skillfactory.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@CrossOrigin
public class UserController {

    // Публичный ресурс, доступен без аутентификации (см. .permitAll() в конфиге)
    @GetMapping("/public/hello")
    public String hello() {
        return "Привет, гость! Для доступа к закрытым ресурсам требуется вход.";
    }

    // Закрытый ресурс, доступен только ADMIN (см. hasRole("ADMIN") в конфиге)
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Секретная админская информация!";
    }

    // Альтернативно, то же самое можно было ограничить на уровне метода:
     @PreAuthorize("hasRole('ADMIN')")  // "hasRole('ADMIN')" -- SpEL (Spring Expression Language)
     @GetMapping("/admin/newdashboard")
     public String adminNewDashboard() { return "Секретная new админская информация!"; }
}

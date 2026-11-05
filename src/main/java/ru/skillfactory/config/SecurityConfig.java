package ru.skillfactory.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // включаем @PreAuthorize и др
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager users(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    @Bean
    public CorsConfigurationSource configurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // Разрешаем только определенный домен, методы GET/POST, и стандартные заголовки Auth & Content-Type
        config.setAllowedOrigins(List.of("https://example.com"));
        config.setAllowedMethods(List.of("GET", "POST"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

// Когда приложение защищено Spring Security, появляется дополнительный уровень фильтрации запросов. Важно помнить: браузерный preflight (OPTIONS) не содержит аутентификационных данных (ни cookie, ни токена), поэтому, если фильтры безопасности обрабатывают его как обычный запрос, они могут отклонить его (подумав, что это неаутентифицированный запрос). Чтобы этого не произошло, нужно настроить Spring Security так, чтобы он пропускал и учитывал CORS-запросы.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(configurationSource()))
                .csrf(csrf -> csrf.disable())  // отключаем CSRF для простоты примера (не делайте так в продакшене!)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/api/public/**").permitAll()
                        .requestMatchers("/api/admin").hasRole("ADMIN")  // только ADMIN
                        .anyRequest().authenticated()  // остальные - авторизованные
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/oauth2/authorization/github"))  // включает OAuth2 Login для github
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home", true)// страница входа (например, контроллер отдает шаблон)
//                        .permitAll()
//
//                )  // разрешаем всем видеть страницу логина
//                .logout(logout-> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")  // после выхода отправляем на главную публичную
//                )
        ;

        return http.build();
    }

}







//     @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOrigins(List.of("http://localhost:3000"));
//        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//
//    }

























//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/api/public/**").permitAll()  // страницы /public доступны всем
//                        .anyRequest().authenticated()  // остальные требуют авторизации
//                )
//                .httpBasic(Customizer.withDefaults());  // включаем базовую HTTP-авторизацию
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService users() {
//
//        UserDetails user = User.withUsername("john")
//                .password(passwordEncoder().encode("secret123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}



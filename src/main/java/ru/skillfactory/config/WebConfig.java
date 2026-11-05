package ru.skillfactory.config;

import ch.qos.logback.core.joran.spi.HttpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://exmaple.com")
                .allowedMethods("GET", "POST")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")  // любые пути API ("/**") — то есть весь наш бэкенд;
//                .allowedOrigins("http://localhost:3000")  // запросы с origin http://localhost:3000 (можно перечислить несколько адресов);
//                .allowedMethods("*")  // любые методы (* — GET, POST, PUT, DELETE и т. д.);
//                .allowedHeaders("*")  // любые заголовки от клиента (*);
//                .allowCredentials(true);  // отправку креденшелов (cookies) — allowCredentials(true).
//    }

}

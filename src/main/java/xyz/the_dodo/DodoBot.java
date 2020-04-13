package xyz.the_dodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class DodoBot {
    public static void main(String[] args) {
        SpringApplication.run(DodoBot.class, args);
    }
}

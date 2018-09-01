package xyz.the_dodo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"xyz.the_dodo.database.interfaces.repos"})
@EnableTransactionManagement
@EnableJpaAuditing
public class ServiceConfig {

}

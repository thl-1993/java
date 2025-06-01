package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.services.UserServiceImpl;
import school21.spring.service.services.UsersService;

@Configuration
@ComponentScan("school21.spring.service.services")
public class TestApplicationConfig {
    @Bean
    public EmbeddedDatabase EmbeddedDatabaseDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Bean
    public UsersRepository usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(EmbeddedDatabaseDataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UserServiceImpl(usersRepositoryJdbc());
    }
}
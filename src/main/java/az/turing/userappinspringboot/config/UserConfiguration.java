package az.turing.userappinspringboot.config;

import az.turing.userappinspringboot.domain.repository.UserRepository;
import az.turing.userappinspringboot.domain.repository.UserRepositoryInMemory;
import az.turing.userappinspringboot.domain.repository.UserRepositoryInPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/user-demo-app");
        dataSource.setUsername("root");
        dataSource.setPassword("root1234");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public UserRepository userRepository() {
       //  return new UserRepositoryInPostgres(jdbcTemplate(dataSource()));
      return new UserRepositoryInMemory();
    }

}

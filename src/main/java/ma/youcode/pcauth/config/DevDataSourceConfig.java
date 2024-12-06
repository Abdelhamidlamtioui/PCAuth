package ma.youcode.pcauth.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev") // Active only when the dev profile is enabled
public class DevDataSourceConfig {

    @Bean
    public DataSource devDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://dev-db:3306/db_dev") // Matches dev-db service
                .username("dev_user")
                .password("dev_password")
                .build();
    }
}

package io.chagchagchag.oauth2client.oauth2_client_example.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Profile("h2")
@EnableR2dbcRepositories(basePackages = {
    "io.chagchagchag.oauth2client.oauth2_client_example.member.entity"
})
@Configuration
public class H2R2dbcConfig extends AbstractR2dbcConfiguration {

  @Override
  public ConnectionFactory connectionFactory() {
    return new H2ConnectionFactory(
        H2ConnectionConfiguration.builder()
            .inMemory("test")
            .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
            .username("sa")
            .build()
    );
  }

  @Bean
  public ConnectionFactoryInitializer h2DbInitializer(){
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//    resourceDatabasePopulator.addScript(new ClassPathResource("/sql/schema.sql"));
//    resourceDatabasePopulator.addScript(new ClassPathResource("/sql/data.sql"));
//    initializer.setDatabasePopulator(resourceDatabasePopulator);
    initializer.setConnectionFactory(connectionFactory());
    return initializer;
  }

}

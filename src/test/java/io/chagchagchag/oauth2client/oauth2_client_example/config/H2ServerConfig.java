package io.chagchagchag.oauth2client.oauth2_client_example.config;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("h2")
@Component
public class H2ServerConfig {
  private static final Logger logger = LoggerFactory.getLogger(H2ServerConfig.class);

  @Value("${h2.console.port}")
  private Integer port;
  private Server h2Server;

  @EventListener(ContextRefreshedEvent.class)
  public void start() throws SQLException {
    logger.info("start h2 console at port {}", port);
    this.h2Server = Server.createWebServer("-webPort", port.toString());
    this.h2Server.start();
  }

  @EventListener(ContextClosedEvent.class)
  public void stop(){
    logger.info("stop h2 console at port {}", port);
    this.h2Server.stop();
  }
}

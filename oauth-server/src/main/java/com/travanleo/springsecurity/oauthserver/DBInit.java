package com.travanleo.springsecurity.oauthserver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * Initializing DDL and sample data.
 * 
 *
 */
@Configuration
public class DBInit {

  @Value("classpath:schema.sql")
  private Resource schemaScript;

  @Value("classpath:oauth_client_details.sql")
  private Resource dataScript;

  @Value("classpath:user_authority_deatils.sql")
  private Resource dataScript2;

  @Autowired
  DataSource dataSource;

  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    final DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator());
    return initializer;
  }

  private DatabasePopulator databasePopulator() {
    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(schemaScript);
    populator.addScript(dataScript);
    populator.addScript(dataScript2);
    return populator;
  }

}

package com.opsDashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
class TestConfig
{
    @Bean
    @Primary
    @Profile("!integration")
    DataSource testDataSource()
    {
        var result = new DriverManagerDataSource("jdbc:h2:mem:databaseForTest;DB_CLOSE_DELAY=-1");
        result.setDriverClassName("org.h2.Driver");

        return result;
    }
}

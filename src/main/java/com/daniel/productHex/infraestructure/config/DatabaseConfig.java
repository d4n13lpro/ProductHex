package com.daniel.productHex.infraestructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    /**
     * Configura y devuelve un bean de JdbcTemplate.
     * JdbcTemplate es una clase de Spring que simplifica el trabajo con JDBC.
     *
     * @param dataSource El DataSource configurado automáticamente por Spring Boot.
     * @return Una instancia de JdbcTemplate.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Bean para probar la conexión a la base de datos al inicio de la aplicación.
     *
     * @param jdbcTemplate El JdbcTemplate configurado.
     * @return CommandLineRunner que ejecuta la prueba de conexión.
     */
    @Bean
    public CommandLineRunner testDatabaseConnection(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Prueba básica de conexión
                jdbcTemplate.query("SELECT 1", rs -> {
                    logger.info("✅ Conexión a la base de datos exitosa!");
                });

                // Prueba de acceso a la tabla products
                Integer productCount = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM products", Integer.class);
                logger.info("📊 Número de productos en la base de datos: {}", productCount);

            } catch (Exception e) {
                logger.error("❌ Error al conectar con la base de datos: {}", e.getMessage());
                throw e;
            }
        };
    }
}
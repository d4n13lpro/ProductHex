package com.daniel.productHex.infraestructure.repository;

import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.domain.ports.ProductOutputPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductOutputPort {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");  // La columna "id" es auto-generada
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity"),
                rs.getObject("category_id", Long.class),
                rs.getObject("brand_id", Long.class),
                rs.getObject("supplier_id", Long.class),
                rs.getObject("created_at", LocalDateTime.class)));
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"),
                    rs.getObject("category_id", Long.class),
                    rs.getObject("brand_id", Long.class),
                    rs.getObject("supplier_id", Long.class),
                    rs.getObject("created_at", LocalDateTime.class)));
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Product save(Product product) {
        // Se omite "created_at" porque se maneja en la base de datos
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("description", product.getDescription() != null ? product.getDescription() : "");
        parameters.put("price", product.getPrice());
        parameters.put("quantity", product.getQuantity());
        parameters.put("category_id", product.getCategoryId());
        parameters.put("brand_id", product.getBrandId());
        parameters.put("supplier_id", product.getSupplierId());

        // Insertamos el producto sin "created_at"
        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        // Devolvemos el "created_at" después de la inserción
        String sql = "SELECT created_at FROM products WHERE id = ?";
        LocalDateTime createdAt = jdbcTemplate.queryForObject(sql, new Object[]{generatedId}, LocalDateTime.class);

        return new Product(
                generatedId.longValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategoryId(),
                product.getBrandId(),
                product.getSupplierId(),
                createdAt);  // Aquí se asigna la fecha correcta obtenida de la base de datos
    }

    @Override
    public Optional<Product> findByNameAndBrandIdAndCategoryId(String name, Long brandId, Long categoryId) {
        String sql = "SELECT * FROM products WHERE name = ? AND brand_id = ? AND category_id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{name, brandId, categoryId},
                    (rs, rowNum) -> new Product(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price"),
                            rs.getInt("quantity"),
                            rs.getObject("category_id", Long.class),
                            rs.getObject("brand_id", Long.class),
                            rs.getObject("supplier_id", Long.class),
                            rs.getObject("created_at", LocalDateTime.class))
            );
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

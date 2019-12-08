package example.etag.service.data;

import example.etag.service.data.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Product data access layer.
 */
@Component
public class ProductDao {
    private static final Logger LOG = LoggerFactory.getLogger(ProductDao.class);

    private final DataSource dataSource;

    @Autowired
    public ProductDao(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets product information for a specific product id.
     *
     * @param id product identifier
     * @return the {@link Product} information if found; otherwise <code>null</code>
     */
    public Product getProduct(String id) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "SELECT * FROM products WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Product.from(rs);
                    }

                    // Product not found
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Error retrieving product from database [productId: '%s']", id));
        }
    }
}

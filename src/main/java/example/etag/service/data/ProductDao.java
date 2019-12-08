package example.etag.service.data;

import example.etag.service.data.model.Gender;
import example.etag.service.data.model.Prices;
import example.etag.service.data.model.Product;
import example.etag.service.data.model.ProductType;
import example.etag.service.data.model.Sku;
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
            final String sql = "SELECT p.*, ps.id AS sku_id, ps.active AS sku_active, ps.colorway_id, ps.colorway, ps.size, sp.usd_list, sp.usd_msrp, sp.usd_sale, sp.cad_list, sp.cad_msrp, sp.cad_sale " +
                                "FROM products p " +
                                "JOIN product_skus ps on p.id = ps.product_id " +
                                "JOIN sku_prices sp on ps.id = sp.sku_id " +
                                "WHERE p.id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Product product = new Product();
                        product.setProductId(rs.getString("id"));
                        product.setProductType(ProductType.get(rs.getInt("type")));
                        product.setActive(rs.getBoolean("active"));
                        product.setStartTime(rs.getTimestamp("start_time"));
                        product.setEndTime(rs.getTimestamp("end_time"));
                        product.setShortName(rs.getString("short_name"));
                        product.setLongName(rs.getString("long_name"));
                        product.setDescription(rs.getString("description"));
                        product.setGender(Gender.get(rs.getInt("gender")));

                        // Handle first SKU record
                        Sku sku = new Sku();
                        sku.setSkuId(rs.getString("sku_id"));
                        sku.setActive(rs.getBoolean("sku_active"));
                        sku.setColorwayId(rs.getString("colorway_id"));
                        sku.setColorway(rs.getString("colorway"));
                        sku.setSize(rs.getString("size"));

                        Prices pricesUsd = new Prices();
                        pricesUsd.setCurrency("USD");
                        pricesUsd.setList(rs.getDouble("usd_list"));
                        pricesUsd.setMsrp(rs.getDouble("usd_msrp"));
                        pricesUsd.setSale(rs.getDouble("usd_sale"));

                        Prices pricesCad = new Prices();
                        pricesCad.setCurrency("CAD");
                        pricesCad.setList(rs.getDouble("cad_list"));
                        pricesCad.setMsrp(rs.getDouble("cad_msrp"));
                        pricesCad.setSale(rs.getDouble("cad_sale"));

                        sku.getPrices().putIfAbsent(pricesUsd.getCurrency(), pricesUsd);
                        sku.getPrices().putIfAbsent(pricesCad.getCurrency(), pricesCad);

                        product.getSkus().add(sku);

                        // Handle remaining SKU records
                        while (rs.next()) {
                            Sku sku2 = new Sku();
                            sku2.setSkuId(rs.getString("sku_id"));
                            sku2.setActive(rs.getBoolean("sku_active"));
                            sku2.setColorwayId(rs.getString("colorway_id"));
                            sku2.setColorway(rs.getString("colorway"));
                            sku2.setSize(rs.getString("size"));

                            Prices pricesUsd2 = new Prices();
                            pricesUsd2.setCurrency("USD");
                            pricesUsd2.setList(rs.getDouble("usd_list"));
                            pricesUsd2.setMsrp(rs.getDouble("usd_msrp"));
                            pricesUsd2.setSale(rs.getDouble("usd_sale"));

                            Prices pricesCad2 = new Prices();
                            pricesCad2.setCurrency("CAD");
                            pricesCad2.setList(rs.getDouble("cad_list"));
                            pricesCad2.setMsrp(rs.getDouble("cad_msrp"));
                            pricesCad2.setSale(rs.getDouble("cad_sale"));

                            sku2.getPrices().putIfAbsent(pricesUsd2.getCurrency(), pricesUsd2);
                            sku2.getPrices().putIfAbsent(pricesCad2.getCurrency(), pricesCad2);

                            product.getSkus().add(sku2);
                        }

                        return product;
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

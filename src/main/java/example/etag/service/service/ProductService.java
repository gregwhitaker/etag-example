package example.etag.service.service;

import example.etag.service.data.ProductDao;
import example.etag.service.data.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Service for querying and modifying product information.
 */
@Service
public class ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Gets the product information for a specific product identifier.
     *
     * @param id product identifier
     * @return the product information if found; otherwise <code>null</code>
     */
    public Product getProduct(String id) {
        if (StringUtils.isEmpty(id)) {
            LOG.warn("Received request to retrieve product with empty id");
            return null;
        }

        return productDao.getProduct(id);
    }

    /**
     * Sets the product to the active state.
     *
     * @param id product identifier
     * @return the product information if found; otherwise <code>null</code>
     */
    public Product activateProduct(String id) {
        if (StringUtils.isEmpty(id)) {
            LOG.warn("Received request to retrieve product with empty id");
            return null;
        }

        return productDao.setProductState(id, true);
    }

    /**
     * Sets the product to the inactive state.
     *
     * @param id product identifier
     * @return the product information if found; otherwise <code>null</code>
     */
    public Product deactivateProduct(String id) {
        if (StringUtils.isEmpty(id)) {
            LOG.warn("Received request to retrieve product with empty id");
            return null;
        }

        return productDao.setProductState(id, false);
    }
}

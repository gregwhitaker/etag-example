package example.etag.service.controller;

import example.etag.service.controller.model.GetProductResponse;
import example.etag.service.data.model.Product;
import example.etag.service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for querying and modifying products.
 */
@RestController
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        LOG.debug("Retrieving information for product [id: '{}']", id);

        Product product = productService.getProduct(id);

        if (product != null) {
            return ResponseEntity.ok(GetProductResponse.from(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/products/{id}/active")
    public ResponseEntity<?> activateProduct(@PathVariable("id") String id) {
        LOG.debug("Activating product [id: '{}'", id);

        Product product = productService.activateProduct(id);

        if (product != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/products/{id}/active",
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deactivateProduct(@PathVariable("id") String id) {
        LOG.debug("Deactivating product [id: '{}'", id);

        Product product = productService.deactivateProduct(id);

        if (product != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

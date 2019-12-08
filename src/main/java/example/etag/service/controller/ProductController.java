package example.etag.service.controller;

import example.etag.service.controller.model.GetProductResponse;
import example.etag.service.data.model.Product;
import example.etag.service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        LOG.debug("Retrieving information for product [productId: '{}']", id);

        Product product = productService.getProduct(id);

        if (product != null) {
            return ResponseEntity.ok()
                    .body(GetProductResponse.from(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

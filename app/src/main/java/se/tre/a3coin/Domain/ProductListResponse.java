package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.List;

public class ProductListResponse implements Serializable {
 private List<ProductList> productLists;

    public ProductListResponse(List<ProductList> productLists) {
        this.productLists = productLists;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }

    @Override
    public String toString() {
        return "ProductListResponse{" +
                "productLists=" + productLists +
                '}';
    }
}

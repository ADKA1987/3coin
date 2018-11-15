package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.List;

public class ProductsOffersList  implements Serializable {
    private List<Product> productList;

    public ProductsOffersList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "ProductsOffersList{" +
                "productList=" + productList +
                '}';
    }
}

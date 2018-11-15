package se.tre.a3coin.Domain;

import java.io.Serializable;

public class Product  implements Serializable {
    private String requiredCoins;
    private String availabelProductId;
    private String productDescription;

    public Product(String requiredCoins, String availabelProductId, String productDescription) {
        this.requiredCoins = requiredCoins;
        this.availabelProductId = availabelProductId;
        this.productDescription = productDescription;
    }

    public String getRequiredCoins() {
        return requiredCoins;
    }

    public String getAvailabelProductId() {
        return availabelProductId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "requiredCoins='" + requiredCoins + '\'' +
                ", availabelProductId='" + availabelProductId + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}

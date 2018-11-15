package se.tre.a3coin.Domain;

import java.io.Serializable;

public class Usage  implements Serializable {
    private String usageEventId;
    private String usageEventDate;
    private String usedCoins;
    private String purchasedProductId;
    private String productDescription;

    public Usage(String usageEventId, String usageEventDate, String usedCoins, String purchasedProductId, String productDescription) {
        this.usageEventId = usageEventId;
        this.usageEventDate = usageEventDate;
        this.usedCoins = usedCoins;
        this.purchasedProductId = purchasedProductId;
        this.productDescription = productDescription;
    }

    public String getUsageEventId() {
        return usageEventId;
    }

    public String getUsageEventDate() {
        return usageEventDate;
    }

    public String getUsedCoins() {
        return usedCoins;
    }

    public String getPurchasedProductId() {
        return purchasedProductId;
    }

    public String getProductDescription() {
        return productDescription;
    }
}

package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.List;

public class My3CoinResponse implements Serializable {
    private String coins;
    private String expiryDate;
    private  List<Credit> creditHistoryList;
    private  List<Usage> usageList;
    private  List<Product> productList;
    private String[] personalId;
    public  My3CoinResponse(String available3Coins, String expiryDate, String[] personalId){
        this.coins = available3Coins;
        this.expiryDate = expiryDate;
        this.personalId= personalId;
    }

    public String getCoins() {
        return coins;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public List<Credit> getCreditHistoryList() {
        return creditHistoryList;
    }

    public List<Usage> getUsageList() {
        return usageList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "My3CoinResponse{" +
                "coins='" + coins + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", creditHistoryList=" + creditHistoryList +
                ", usageList=" + usageList +
                ", productList=" + productList +
                '}';
    }
}

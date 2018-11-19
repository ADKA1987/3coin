package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.List;

public class My3CoinResponse implements Serializable {
    private String coins;
    private String expiryDate;
    private CreditHistoryResponse creditHistoryListArray;
    private  List<Usage> usageList;
    private  ProductListResponse productListResponse;
    private String[] personalId;
    public  My3CoinResponse(String available3Coins,CreditHistoryResponse creditHistoryListArray,ProductListResponse productListResponse, String expiryDate, String[] personalId){
        this.coins = available3Coins;
        this.expiryDate = expiryDate;
        this.personalId= personalId;
        this.creditHistoryListArray = creditHistoryListArray;
        this.productListResponse = productListResponse;
    }

    public String getCoins() {
        return coins;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public CreditHistoryResponse getCreditHistoryList() {
        return creditHistoryListArray;
    }

    public List<Usage> getUsageList() {
        return usageList;
    }

    public ProductListResponse getProductList() {
        return productListResponse;
    }

    @Override
    public String toString() {
        return "My3CoinResponse{" +
                "coins='" + coins + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", creditHistoryList=" + creditHistoryListArray +
                ", usageList=" + usageList +
                ", productList=" + productListResponse +
                '}';
    }
}

package se.tre.a3coin.Domain;

public class My3CoinResponse {
    private String coins;
    private String expiryDate;

    public  My3CoinResponse(String coins, String expiryDate){
        this.coins = coins;
        this.expiryDate = expiryDate;
    }

    public String getCoins() {
        return coins;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "My3CoinResponse{" +
                "coins='" + coins + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}

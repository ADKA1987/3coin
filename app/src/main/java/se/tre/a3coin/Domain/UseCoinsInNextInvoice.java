package se.tre.a3coin.Domain;

public class UseCoinsInNextInvoice {
    private String coins;
    private String PersonalId;

    public UseCoinsInNextInvoice(String coins, String personalId) {
        this.coins = coins;
        PersonalId = personalId;
    }

    public String getCoins() {
        return coins;
    }

    public String getPersonalId() {
        return PersonalId;
    }

    @Override
    public String toString() {
        return "UseCoinsInNextInvoice{" +
                "coins='" + coins + '\'' +
                ", PersonalId='" + PersonalId + '\'' +
                '}';
    }
}

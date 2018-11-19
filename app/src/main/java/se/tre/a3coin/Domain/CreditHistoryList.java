package se.tre.a3coin.Domain;

import java.io.Serializable;

public class CreditHistoryList implements Serializable{
    private String creditsEventId;
    private String creditedEvent;
    private String creditedCoins;
    private String creditAddedOn;
    private String creditAddedBy;
    private String creditExpiryDate;

    public CreditHistoryList(String creditsEventId, String creditedEvent, String creditedCoins, String creditAddedOn, String creditAddedBy, String creditExpiryDate) {
        this.creditsEventId = creditsEventId;
        this.creditedEvent = creditedEvent;
        this.creditedCoins = creditedCoins;
        this.creditAddedOn = creditAddedOn;
        this.creditAddedBy = creditAddedBy;
        this.creditExpiryDate = creditExpiryDate;
    }

    public String getCreditsEventId() {
        return creditsEventId;
    }

    public String getCreditedEvent() {
        return creditedEvent;
    }

    public String getCreditedCoins() {
        return creditedCoins;
    }

    public String getCreditAddedOn() {
        return creditAddedOn;
    }

    public String getCreditAddedBy() {
        return creditAddedBy;
    }

    public String getCreditExpiryDate() {
        return creditExpiryDate;
    }

    @Override
    public String toString() {
        return "CreditHistoryList{" +
                "creditsEventId='" + creditsEventId + '\'' +
                ", creditedEvent='" + creditedEvent + '\'' +
                ", creditedCoins='" + creditedCoins + '\'' +
                ", creditAddedOn='" + creditAddedOn + '\'' +
                ", creditAddedBy='" + creditAddedBy + '\'' +
                ", creditExpiryDate='" + creditExpiryDate + '\'' +
                '}';
    }
}

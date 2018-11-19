package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.Collection;

public class CreditHistoryResponse  implements Serializable {
    private Collection<CreditHistoryList> creditHistoryLists;

    public CreditHistoryResponse(Collection<CreditHistoryList> creditHistoryLists) {
        this.creditHistoryLists = creditHistoryLists;
    }

    public Collection<CreditHistoryList> getCreditHistoryLists() {
        return creditHistoryLists;
    }
}

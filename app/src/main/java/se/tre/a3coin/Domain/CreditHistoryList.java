package se.tre.a3coin.Domain;

import java.io.Serializable;
import java.util.List;

public class CreditHistoryList  implements Serializable {
    private List<Credit> listCredit;

    public CreditHistoryList(List<Credit> listCredit) {
        this.listCredit = listCredit;
    }

    public List<Credit> getListCredit() {
        return listCredit;
    }

    @Override
    public String toString() {
        return "CreditHistoryList{" +
                "listCredit=" + listCredit +
                '}';
    }
}

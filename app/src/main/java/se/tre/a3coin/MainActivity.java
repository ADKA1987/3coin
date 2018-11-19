package se.tre.a3coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import se.tre.a3coin.Domain.CreditHistoryList;
import se.tre.a3coin.Domain.CreditHistoryResponse;
import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Domain.ProductListResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intnet = getIntent();
        My3CoinResponse my3CoinResponse = (My3CoinResponse) intnet.getSerializableExtra("my3CoinResponse");
        final String personalId =(String )intnet.getSerializableExtra("personalId");
        String coins = "0";
        String expiryDate ="YYYY-MM-DD";
        if (null!=my3CoinResponse.getCoins())
            coins =my3CoinResponse.getCoins();

        if (null!=my3CoinResponse.getExpiryDate())
            expiryDate =my3CoinResponse.getExpiryDate();
       final CreditHistoryResponse creditHistoryResponse =my3CoinResponse.getCreditHistoryList();

        TextView coinsView =  findViewById(R.id.available_Coins);
        TextView expiryDateView =  findViewById(R.id.expiry_Date);

        Button historyButton = findViewById(R.id.getHistory);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToHistoryActivity(creditHistoryResponse);
            }
        });

        final ProductListResponse productListResponse = my3CoinResponse.getProductList();
        Button offersBtn = findViewById(R.id.getOffers);
        offersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOffersView(productListResponse,personalId);
            }
        });
        coinsView.setText(coins);
        expiryDateView.setText(expiryDate);
    }

    public void sendToHistoryActivity(CreditHistoryResponse creditHistoryResponse){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("creditHistoryList",creditHistoryResponse);
        startActivity(intent);
    }

    private void openOffersView(ProductListResponse productListResponse, String personalId) {
        Intent intent = new Intent(this, OffersActivity.class);
        intent.putExtra("offersLists",productListResponse);
        intent.putExtra("personalId",personalId);
        startActivity(intent);
    }

}

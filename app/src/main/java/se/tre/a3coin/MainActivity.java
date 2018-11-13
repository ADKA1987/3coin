package se.tre.a3coin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Service.Get3CoinRequest;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getMyCoin = findViewById(R.id.getMyCoin);
        final EditText personalId= findViewById(R.id.personal_id);
        final TextView resutltext = findViewById(R.id.textC3oins);
        final TextView expiryDate = findViewById(R.id.expiryDate);
        getMyCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    My3CoinResponse my3CoinResponse = null;

                    my3CoinResponse =  getCoins(personalId.getText().toString());
                    if(null!=my3CoinResponse){
                        resutltext.setText(my3CoinResponse.getCoins());
                        expiryDate.setText(my3CoinResponse.getExpiryDate());
                    } else {
                        resutltext.setText("No Coins were found");
                        expiryDate.setText(",,,,");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private My3CoinResponse getCoins(String personalId) throws IOException {


        My3CoinResponse my3CoinResponse = null;
        if(null == personalId || personalId.isEmpty()) {
            Toast.makeText(this, "Personal number cannot be empty", Toast.LENGTH_LONG).show();
            return my3CoinResponse;
        }


        Get3CoinRequest get3CoinRequest = new Get3CoinRequest();
        try {
            my3CoinResponse =  get3CoinRequest.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return my3CoinResponse;
    }
}

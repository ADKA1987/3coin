package se.tre.a3coin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Service.Get3CoinRequest;

public class MainActivity extends AppCompatActivity {
    final String[] peronalNumber = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getMyCoin = findViewById(R.id.getMyCoin);
        final EditText personalId= findViewById(R.id.personal_id);
        final TextView resutltext = findViewById(R.id.text);
        getMyCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //peronalNumber[0] = personalId.getText().toString();
                try {
                    resutltext.setText( getCoins(personalId.getText().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private String getCoins(String personalId) throws IOException {


        String my3CoinResponse = null;
        if(null == personalId || personalId.isEmpty()) {
            Toast.makeText(this, "Personal number cannot be empty", Toast.LENGTH_LONG).show();
            return my3CoinResponse;
        }


        Get3CoinRequest get3CoinRequest = new Get3CoinRequest();
        get3CoinRequest.getCoins(personalId);
        //my3CoinResponse = get3CoinRequest.getMy3Coins();

        return my3CoinResponse;
    }
}

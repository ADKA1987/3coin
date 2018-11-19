package se.tre.a3coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Service.Get3CoinRequest;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getMyCoin = findViewById(R.id.getMyCoin);

        final EditText personalId= findViewById(R.id.personal_id);
        getMyCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getCoins(personalId.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private void getCoins(String personalId) throws IOException {


        My3CoinResponse my3CoinResponse = null;
        if(null == personalId || personalId.isEmpty()) {
            Toast.makeText(this, "Personal number cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

         Get3CoinRequest get3CoinRequest = new Get3CoinRequest();
        try {
            my3CoinResponse =  get3CoinRequest.execute(personalId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(null!=my3CoinResponse){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("my3CoinResponse",my3CoinResponse);
            intent.putExtra("personalId",personalId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please pay your invoice to get 3Coins.", Toast.LENGTH_LONG).show();
        }
        //return my3CoinResponse;
    }
}

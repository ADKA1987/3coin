package se.tre.a3coin;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;

import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Domain.UseCoinsInNextInvoice;
import se.tre.a3coin.Service.PagerAdapter;

public class tab_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Toolbar toolbar = findViewById(R.id.toolbar);

        /*Intent intnet = getIntent();
        My3CoinResponse my3CoinResponse = (My3CoinResponse) intnet.getSerializableExtra("my3CoinResponse");

        String coins = my3CoinResponse.getCoins();
        String expiryDate = my3CoinResponse.getExpiryDate();

        TextView textC3oinsView = findViewById(R.id.textC3oins);
        TextView expiryDateView = findViewById(R.id.expiryDate);

        textC3oinsView.setText(coins);
        expiryDateView.setText(expiryDate);*/
        final String personalId="";

        Button setCoinsInNextInvoice = findViewById(R.id.set);
        final EditText coinsNumber = findViewById(R.id.coins_number);

        

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("My Coins"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Offers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setCoins(String coinsNumber, String personalId) {
        UseCoinsInNextInvoice useCoinsInNextInvoice = new UseCoinsInNextInvoice(coinsNumber,personalId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}

package se.tre.a3coin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Domain.ProductList;
import se.tre.a3coin.Domain.ProductListResponse;
import se.tre.a3coin.Domain.UseCoinsInNextInvoice;
import se.tre.a3coin.Service.Get3CoinRequest;
import se.tre.a3coin.Service.SetCoinsNumber;

public class OffersActivity extends AppCompatActivity {
    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;
    String personalId;
    ProductListResponse productLists ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Intent intnet = getIntent();
        personalId= (String) intnet.getSerializableExtra("personalId");
        productLists= (ProductListResponse) intnet.getSerializableExtra("offersLists");
        final EditText coinsAmount = findViewById(R.id.coins_amount);

        final Button sendCoinsBtn = findViewById(R.id.sendCoins);
        sendCoinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendCoins(coinsAmount.getText().toString(),personalId);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mProgressBar = new ProgressDialog(this);

        mTableLayout = (TableLayout) findViewById(R.id.offers_table);
        mTableLayout.setStretchAllColumns(true);
        startLoadData();

    }

    private void sendCoins(String coinsAmount,String personalId) throws IOException, InterruptedException {
        UseCoinsInNextInvoice request = new UseCoinsInNextInvoice(coinsAmount,personalId);
        SetCoinsNumber setCoinsNumber = new SetCoinsNumber(request);

        setCoinsNumber.send();
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
            Thread.sleep(300);
            getCoins(personalId);

    }

    private void sendOrders(String coinsAmount,String productId,String personalId) throws IOException, InterruptedException {
        UseCoinsInNextInvoice request = new UseCoinsInNextInvoice(coinsAmount,personalId);
        SetCoinsNumber setCoinsNumber = new SetCoinsNumber(request);

        setCoinsNumber.sendProduct(productId);
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
        Thread.sleep(300);
        getCoins(personalId);
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
    public void startLoadData() {

        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Offers..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new OffersActivity.LoadDataTask().execute(0);

    }

    public void loadData() {

        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 50, smallTextSize =50, mediumTextSize = 40;

        /*textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        */

        int rows = productLists.getProductLists().size();
        getSupportActionBar().setTitle("Offers");
        TextView textSpacer = null;

        mTableLayout.removeAllViews();

        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            ProductList row = null;
            if (i > -1)
                row = productLists.getProductLists().get(i);
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");

            }
            // data columns
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.LEFT);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("Coins");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(String.valueOf(row.getRequiredCoins()));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }


            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv2.setGravity(Gravity.LEFT);

            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("Product");
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(row.getAvailabelProductId());
            }


            final LinearLayout layCustomer = new LinearLayout(this);
            layCustomer.setOrientation(LinearLayout.VERTICAL);
            layCustomer.setPadding(0, 10, 0, 10);
            layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));

            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 5, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv3.setGravity(Gravity.TOP);


            if (i == -1) {
                tv3.setText("Description");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv3.setText(row.getProductDescription());
            }
            layCustomer.addView(tv3);

            final LinearLayout layOrder = new LinearLayout(this);
            layOrder.setOrientation(LinearLayout.VERTICAL);
            layOrder.setPadding(0, 10, 0, 10);
            layOrder.setBackgroundColor(Color.parseColor("#f8f8f8"));

            final Button tv4 = new Button(this);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 5, 0, 5);
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 0, 0, 5);
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv4.setGravity(Gravity.TOP);


            if (i == -1) {
                tv4.setText("Order");
                tv4.setBackgroundColor(Color.parseColor("#f0f0f0"));
            } else {
                tv4.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv4.setText("Order");
            }
            layOrder.addView(tv4);

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);



            tr.addView(tv);
            tr.addView(layCustomer);
            tr.addView(layOrder);

            tv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        sendOrders(tv.getText().toString(),tv2.getText().toString(),personalId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (i > -1) {
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;


                        //do whatever action is needed

                    }
                });


            }
            mTableLayout.addView(tr, trParams);

            if (i > -1) {

                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);

                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }


        }
    }

    //////////////////////////////////////////////////////////////////////////////

    //
    // The params are dummy and not used
    //
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            loadData();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

}

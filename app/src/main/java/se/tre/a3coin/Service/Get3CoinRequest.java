package se.tre.a3coin.Service;

import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import se.tre.a3coin.Domain.CreditHistoryList;
import se.tre.a3coin.Domain.CreditHistoryResponse;
import se.tre.a3coin.Domain.My3CoinResponse;
import se.tre.a3coin.Domain.ProductList;
import se.tre.a3coin.Domain.ProductListResponse;

public class Get3CoinRequest  extends AsyncTask<String, String , My3CoinResponse > {
    private String url = "http://x13824azz.nextrel.tre.se:8123/3labs/3coins/getPsftInfo/se/consumer/";
    public Get3CoinRequest(){

    }

    @Override
    protected My3CoinResponse doInBackground(String... personalId) {
        URL regUrl;
        My3CoinResponse my3CoinResponse = null;
        HttpURLConnection urlConnection = null;
        try {
            regUrl = new URL(url+personalId[0]);
            urlConnection = (HttpURLConnection) regUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            //Log.v("UrlConnection",  urlConnection.getContent().toString());
            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            if(responseCode == HttpURLConnection.HTTP_OK){
                String responseString = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient-Response", responseString);
                my3CoinResponse = parsemy3CoinResponse(responseString,personalId);
            }else{
                Log.v("CatalogClient", "Response code:"+ responseCode);
                Log.v("CatalogClient", "Response message:"+ responseMessage);
            }
        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return my3CoinResponse;
    }

    private My3CoinResponse parsemy3CoinResponse(String responseString, String[] personalid) throws IOException {
        My3CoinResponse my3CoinResponse= null;
        try {

            JSONObject jObj = new JSONObject(responseString);
            String coins = jObj.getString("available3Coins");
            String expiryDate = jObj.getString("expiryDate");
            String creditHistoryList = jObj.getString("creditHistoryList");
            String productList = jObj.getString("productList");

            Gson gson = new Gson();
            TypeToken<List<CreditHistoryList>> token = new TypeToken<List<CreditHistoryList>>(){};
            Collection<CreditHistoryList> creditHistoryListArray = gson.fromJson(creditHistoryList, token.getType());

            Gson gsonProducts = new Gson();
            TypeToken<List<ProductList>> tokenProducts = new TypeToken<List<ProductList>>(){};
            Collection<ProductList> productListArray = gsonProducts.fromJson(productList, tokenProducts.getType());


            CreditHistoryResponse creditHistoryResponse = new CreditHistoryResponse ((List<CreditHistoryList>) creditHistoryListArray);
            ProductListResponse productListResponse = new ProductListResponse((List<ProductList>) productListArray);

            for(CreditHistoryList ch : creditHistoryListArray) {
                Log.v("CreditHistoryList", " ch "+  ch );
            }

            for(ProductList pl : productListArray) {
                Log.v("ProductList", " pl "+  pl );
            }

           my3CoinResponse = new My3CoinResponse(coins, creditHistoryResponse,productListResponse, expiryDate,personalid);
            Log.v("My3Coin", "available3Coins "+ coins + ", expiryDate "+ expiryDate);
        } catch (JSONException e) {
            Log.e("My3Coin", "unexpected JSON exception", e);
        }

        return my3CoinResponse;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}


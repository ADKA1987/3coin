package se.tre.a3coin.Service;

import android.util.Log;

import org.json.JSONObject;


import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import se.tre.a3coin.Domain.UseCoinsInNextInvoice;


public class SetCoinsNumber {
    private String urlAddress = "http://x13824azz.nextrel.tre.se:8123/3labs/3coins/redeem";
     private final UseCoinsInNextInvoice useCoinsInNextInvoice;

     public SetCoinsNumber(UseCoinsInNextInvoice useCoinsInNextInvoice){
        this.useCoinsInNextInvoice = useCoinsInNextInvoice;
    }

    public String[] send() {
        final String[] respone = new String[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAddress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("coins", useCoinsInNextInvoice.getCoins());
                    jsonParam.put("taxpayerId", useCoinsInNextInvoice.getPersonalId());
                    jsonParam.put("country", "se");
                    jsonParam.put("customerType","consumer");
                    jsonParam.put("type","Invoice");

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());
                    Log.i("respone[0]", ""+conn.getResponseCode());
                    respone[0] = ""+conn.getResponseCode();
                    Log.i("respone[0]", respone[0]);
                    os.flush();
                    os.close();

                   // Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    //Log.i("MSG" , conn.getResponseMessage());
                    //respone[0] = String.valueOf(conn.getResponseCode());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        return respone;
    }
    public String[] sendProduct(String productId) {
        final String[] respone = new String[1];
        final String finalProductId = productId;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAddress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("coins", useCoinsInNextInvoice.getCoins());
                    jsonParam.put("taxpayerId", useCoinsInNextInvoice.getPersonalId());
                    jsonParam.put("country", "se");
                    jsonParam.put("customerType","consumer");
                    jsonParam.put("type","Invoice");
                    jsonParam.put("productId",finalProductId);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());
                    Log.i("respone[0]", ""+conn.getResponseCode());
                    respone[0] = ""+conn.getResponseCode();
                    Log.i("respone[0]", respone[0]);
                    os.flush();
                    os.close();

                    // Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    //Log.i("MSG" , conn.getResponseMessage());
                    //respone[0] = String.valueOf(conn.getResponseCode());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        return respone;
    }
}


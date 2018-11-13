package se.tre.a3coin.Service;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;

import se.tre.a3coin.Domain.My3CoinResponse;

public class Get3CoinRequest  extends AsyncTask<String, String , My3CoinResponse > {

    private final String NAMESPACE = "https://fastvedio.herokuapp.com/search/";
    private final String URL = "https://fastvedio.herokuapp.com/";
    private final String SOAP_ACTION = "https://fastvedio.herokuapp.com/search/alaa";
    private final String METHOD_NAME = "search";
    private String webResponse = "";

    private Thread thread;

    private String Webresponse = "";
    private Handler handler = new Handler();

    public Get3CoinRequest(){

    }

    @Override
    protected My3CoinResponse doInBackground(String... personalId) {
        My3CoinResponse result = null;
        String xml="xml\t<?xml version=\"1.0\"?> <getAvailable3CoinsRequest>     <country>SE</country>     <taxpayerId>"+personalId+"</taxpayerId>     <customerType>consumer</customerType> </getAvailable3CoinsRequest>\tQUERY\tRESOURCE";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        ArrayList<HeaderProperty> headerProperties = new ArrayList<HeaderProperty>();
        headerProperties.add(new HeaderProperty("Proxy-Remote-User","supash"));
        headerProperties.add(new HeaderProperty("xml",xml));
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;

        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION, envelope,headerProperties);

            SoapObject  soapResult = (SoapObject )envelope.getResponse();
            if (soapResult != null) {
                result = new My3CoinResponse(soapResult.getProperty("coins").toString(),soapResult.getProperty("expiryDate").toString());
            } else {
                result = new My3CoinResponse(null,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}


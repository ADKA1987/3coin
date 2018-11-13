package se.tre.a3coin.Service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.Handler;

public class Get3CoinRequest{

    public Get3CoinRequest(){

    }

    private final String NAMESPACE = "http://x12195tzz.testaccess.net:8105/";
    private final String URL = "http://x12802tzz.testaccess.net:8237/Services/ICC2/v3?wsdl";
    private final String SOAP_ACTION = "http://x12195tzz.testaccess.net:8105/xml/genericServlet";
    private final String METHOD_NAME = "genericServlet";

    private String webResponse = "";

    private Thread thread;

    private String Webresponse = "";
    private Handler handler = new Handler();

    public void getCoins(String personalId){
        thread = new Thread(){
            public void run(){
                try{
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    //envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                    androidHttpTransport.call(SOAP_ACTION, envelope);
                    SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                    Webresponse = response.toString();
                }

                catch(Exception e){
                    e.printStackTrace();
                }

                handler.post(createUI);
            }
        };

        thread.start();
    }
        final Runnable createUI = new Runnable() {
    public void run(){
        System.out.println(Webresponse);
           }
           };

}


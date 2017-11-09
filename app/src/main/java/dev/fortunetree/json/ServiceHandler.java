package dev.fortunetree.json;

/**
 * Created by myssd on 10/27/17.
 */
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
//import org.springframework.core.ParameterizedTypeReference;

import org.natha.lpmp.kamar.Kamar;

public class ServiceHandler {
    protected static final String TAG = ServiceHandler.class.getSimpleName();

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    private static final String TAG_NO = "no";
    private static final String TAG_LANTAI = "lantai";
    private static final String TAG_JML_TT = "jml_tt";
    private static final String TAG_URL_PICTURE = "url_picture";

    public ServiceHandler(){

    }

    // to make service call and http request for the url

//    public String makeServiceCall(String url, int method){
    public ArrayList<HashMap<String, String>> makeServiceCall(String url, int method){
        return this.makeServiceCall(url, method, null);
    }

//    public String makeServiceCall(String url,int method, List<NameValuePair> params) {  //Return String
    public ArrayList<HashMap<String, String>> makeServiceCall(String url,int method, List<NameValuePair> params) {
//        try {

            ArrayList<HashMap<String, String>> kamarList = new ArrayList<HashMap<String, String>>();

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            //spring auth
            HttpAuthentication authHeader = new HttpBasicAuthentication("mukesh", "m123");
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);

            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(acceptableMediaTypes);

            // Populate the headers in an HttpEntity object to use for the request
            org.springframework.http.HttpEntity<?> requestEntity = new org.springframework.http.HttpEntity<Object>(requestHeaders);


            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //end spring

//            List<Kamar> lKamar = new ArrayList<Kamar>();

        try {
            // Perform the HTTP GET request
            ResponseEntity<Kamar[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity, Kamar[].class);

            //terima jadi.. pake kelas yg di passing ke server..
            Kamar[] k = responseEntity.getBody();
            int ik = k.length;


            for (int i = 0; i < responseEntity.getBody().length; i++){  //atau k.length()
                // tmp hashmap for single contact
                HashMap<String, String> kamar = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                String no = k[i].getNo();
                int lantai = k[i].getLantai();
                int jml_tt = k[i].getJmlTt();
                String url_picture = k[i].getUrlPicture();
                if(url_picture==null) url_picture="";

                kamar.put(TAG_NO, "No : "+no);
                kamar.put(TAG_LANTAI, "Lantai : "+Integer.toString(lantai));  //Integer.toString(i) or String.valueOf(i)
                kamar.put(TAG_JML_TT, "Jml T. Tidur : "+String.valueOf(jml_tt));
                kamar.put(TAG_URL_PICTURE, "URL Picture : "+url_picture);

                boolean add;
                if (kamarList.add(kamar)) add = true;
                else add = false;
            }

//            LinkedHashMap<String, Kamar> authResponse = (LinkedHashMap<String, Kamar>) responseEntity.getBody();  //belum bisa

//            JSONObject temp = new JSONObject(responseEntity.getBody());   //belum bisa

//            ParameterizedTypeReference ptr = new ParameterizedTypeReference<List<Kamar>>(){};  //blum jalan..

//            ResponseEntity<List<Kamar>> rateResponse;
//            rateResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ptr);
//            List<Kamar> rates = rateResponse.getBody();

//            Kamar[] forNow = restTemplate.getForObject(url, Kamar[].class);  //gak bisa klo pakai header..
//            List<Kamar> searchList= Arrays.asList(forNow);

            // convert the array to a list and return it
//            String s = responseEntity.getBody().toString();     //ini salah
//            response = EntityUtils.toString(responseEntity.getBody());

            //checking http method type
//            if(method==POST){
//                HttpPost httpPost = new HttpPost(url);
//                //adding post params
//                if(params != null){
//                    httpPost.setEntity(new UrlEncodedFormEntity(params));
//                }
//                httpResponse = httpClient.execute(httpPost);
//            }else if (method==GET){
//                //appending params to url
//                if(params!=null){
//                    String paramString = URLEncodedUtils.format(params, "utf-8");
//                    url += "?" + paramString;
//                }
//                HttpGet httpGet = new HttpGet(url);
//                httpResponse = httpClient.execute(httpGet);
//            }
//            httpEntity = httpResponse.getEntity();
//            response = EntityUtils.toString(httpEntity);    //ini juga dibuat ke string kok..?

//            response = String.valueOf(kamarList);

//        }catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }catch(ClientProtocolException e){
//            e.printStackTrace();
//        }catch(IOException e){
//            e.printStackTrace();
            }catch(Exception e){
            Log.e(TAG, e.getMessage(), e);
        }

//        return response;
        return kamarList;
    }
}

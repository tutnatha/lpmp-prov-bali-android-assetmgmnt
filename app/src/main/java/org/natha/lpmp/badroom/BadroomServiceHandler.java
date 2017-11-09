package org.natha.lpmp.badroom;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.natha.lpmp.kamar.Kamar;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dev.fortunetree.json.ServiceHandler;

/**
 * Created by myssd on 11/5/17.
 */

public class BadroomServiceHandler {
    protected static final String TAG = BadroomServiceHandler.class.getSimpleName();

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    private static final String TAG_NO = "no";
    private static final String TAG_NO_KAMAR = "no_kamar";
    private static final String TAG_REG_NO = "reg_no";
    private static final String TAG_IS_USED = "is_used";
    private static final String TAG_START_DATE = "start_date";
    private static final String TAG_END_DATE = "end_date";

    public BadroomServiceHandler() {
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url, int method){
        return this.makeServiceCall(url, method, null);
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url,int method, List<NameValuePair> params) {
//        try {

        ArrayList<HashMap<String, String>> badroomList = new ArrayList<HashMap<String, String>>();

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

//            List<Badroom> lBadroom = new ArrayList<Badroom>();

        try {
            // Perform the HTTP GET request
            ResponseEntity<Badroom[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity, Badroom[].class);

            //terima jadi.. pake kelas yg di passing ke server..
            Badroom[] k = responseEntity.getBody();
            int ik = k.length;


            for (int i = 0; i < responseEntity.getBody().length; i++){  //atau k.length()
                // tmp hashmap for single contact
                HashMap<String, String> badroom = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                String no = k[i].getNo();
                Date endDate = k[i].getEndDate();
                String isUsed = k[i].getIsUsed();
                String noKamar = k[i].getNoKamar();
                String regNo = k[i].getRegNo();
                Date startDate = k[i].getStartDate();

                badroom.put(TAG_NO, "No : "+no);
                badroom.put(TAG_NO_KAMAR, "No Kamar : "+noKamar);  //Integer.toString(i) or String.valueOf(i)
                badroom.put(TAG_REG_NO, "Reg No : "+String.valueOf(regNo));
                badroom.put(TAG_IS_USED, "Is Used : "+isUsed);
                badroom.put(TAG_START_DATE, "Start Date : "+String.valueOf(startDate));
                badroom.put(TAG_END_DATE, "End Date : "+String.valueOf(endDate));

                boolean add;
                if (badroomList.add(badroom)) add = true;
                else add = false;
            }
        }catch(Exception e){
            Log.e(TAG, e.getMessage(), e);
        }

//        return response;
        return badroomList;
    }
}

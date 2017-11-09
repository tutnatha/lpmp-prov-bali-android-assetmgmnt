package dev.fortunetree.json;

/**
 * Created by myssd on 11/2/17.
 */

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

public class ServiceHandler2 {
    protected static final String TAG = ServiceHandler.class.getSimpleName();

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler2(){

    }

    public String makeServiceCall(String url, int method){
        return this.makeServiceCall(url, method, null);
    }

    public String makeServiceCall(String url,int method, List<NameValuePair> params) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            //spring auth
//            HttpAuthentication authHeader = new HttpBasicAuthentication("mukesh", "m123");
//            HttpHeaders requestHeaders = new HttpHeaders();
//            requestHeaders.setAuthorization(authHeader);

//            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
//            requestHeaders.setAccept(acceptableMediaTypes);

            // Populate the headers in an HttpEntity object to use for the request
//            org.springframework.http.HttpEntity<?> requestEntity = new org.springframework.http.HttpEntity<Object>(requestHeaders);


            // Create a new RestTemplate instance
//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //end spring

//            List<Kamar> lKamar = new ArrayList<Kamar>();

            // Perform the HTTP GET request
//            ResponseEntity<Kamar[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
//                    Kamar[].class);

            // convert the array to a list and return it
//            String s = responseEntity.toString();
//
            //checking http method type
            if(method==POST){
                HttpPost httpPost = new HttpPost(url);
                //adding post params
                if(params != null){
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
                httpResponse = httpClient.execute(httpPost);
            }else if (method==GET){
                //appending params to url
                if(params!=null){
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);    //ini juga dibuat ke string kok..?

//            response = s;

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            Log.e(TAG, e.getMessage(), e);
        }

        return response;
    }
}

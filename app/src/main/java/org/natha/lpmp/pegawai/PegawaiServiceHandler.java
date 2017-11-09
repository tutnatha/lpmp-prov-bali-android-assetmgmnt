package org.natha.lpmp.pegawai;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class PegawaiServiceHandler {
    protected static final String TAG = PegawaiServiceHandler.class.getSimpleName();

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    private static final String TAG_NIP = "nip";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_TEMPAT_LAHIR = "tempat_lahir";
    private static final String TAG_TGL_LAHIR = "tgl_lahir";
    private static final String TAG_PANGKAT = "pangkat";
    private static final String TAG_GOLONGAN_RUANG = "golongan_ruang";

    public PegawaiServiceHandler() {
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url, int method){
        return this.makeServiceCall(url, method, null);
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url,int method, List<NameValuePair> params) {
//        try {

        ArrayList<HashMap<String, String>> pegawaiList = new ArrayList<HashMap<String, String>>();

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

//            List<Pegawai> lPegawai = new ArrayList<Pegawai>();

        try {
            // Perform the HTTP GET request
            ResponseEntity<Pegawai[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity, Pegawai[].class);

            //terima jadi.. pake kelas yg di passing ke server..
            Pegawai[] k = responseEntity.getBody();
            int ik = k.length;


            for (int i = 0; i < responseEntity.getBody().length; i++){  //atau k.length()
                // tmp hashmap for single contact
                HashMap<String, String> pegawai = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                String nip = k[i].getNip();     //nip
                String name = k[i].getName();   //name
                String tempatLahir = k[i].getTempatLahir();            //tempatLahir
                Date tglLahir = k[i].getTglLahir();
                String pangkat = k[i].getPangkat();
                String golonganRuang = k[i].getGolonganRuang();

                pegawai.put(TAG_NIP, "Nip : "+nip);
                pegawai.put(TAG_NAMA, "Nama : "+name);  //Integer.toString(i) or String.valueOf(i)
                pegawai.put(TAG_TEMPAT_LAHIR, "Tempat Lahir : "+String.valueOf(tempatLahir));
                pegawai.put(TAG_TGL_LAHIR, "Tanggal Lahir : "+tglLahir);
                pegawai.put(TAG_PANGKAT, "Pangkat : "+String.valueOf(pangkat));
                pegawai.put(TAG_GOLONGAN_RUANG, "End Date : "+String.valueOf(golonganRuang));

                boolean add;
                if (pegawaiList.add(pegawai)) add = true;
                else add = false;
            }
        }catch(Exception e){
            Log.e(TAG, e.getMessage(), e);
        }

//        return response;
        return pegawaiList;
    }
}

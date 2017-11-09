package org.natha.lpmp.kegiatan;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.natha.lpmp.kegiatan.Kegiatan;
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

/**
 * Created by myssd on 11/6/17.
 */

public class KegiatanServiceHandler {
    protected static final String TAG = KegiatanServiceHandler.class.getSimpleName();

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    private static final String TAG_KODE = "kode";
    private static final String TAG_NAMA_KEGIATAN = "nama_kegiatan";
    private static final String TAG_KETERANGAN = "keterangan";
    private static final String TAG_NAMA = "nama";
//    private static final String TAG_START_DATE = "start_date";
//    private static final String TAG_END_DATE = "end_date";

    public KegiatanServiceHandler() {
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url, int method){
        return this.makeServiceCall(url, method, null);
    }

    public ArrayList<HashMap<String, String>> makeServiceCall(String url,int method, List<NameValuePair> params) {
//        try {

        ArrayList<HashMap<String, String>> kegiatanList = new ArrayList<HashMap<String, String>>();

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

//            List<Kegiatan> lKegiatan = new ArrayList<Kegiatan>();

        try {
            // Perform the HTTP GET request
            ResponseEntity<Kegiatan[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity, Kegiatan[].class);

            //terima jadi.. pake kelas yg di passing ke server..
            Kegiatan[] k = responseEntity.getBody();
            int ik = k.length;


            for (int i = 0; i < responseEntity.getBody().length; i++){  //atau k.length()
                // tmp hashmap for single contact
                HashMap<String, String> kegiatan = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                int kode = k[i].getKode();
                String namaKegiatan = k[i].getNamaKegiatan();
                String keterangan = k[i].getKeterangan();
                String nama = k[i].getNama();

                kegiatan.put(TAG_KODE, "Kode : "+kode);
                kegiatan.put(TAG_NAMA_KEGIATAN, "No Kamar : "+namaKegiatan);  //Integer.toString(i) or String.valueOf(i)
                kegiatan.put(TAG_KETERANGAN, "Keterangan : "+String.valueOf(keterangan));
                kegiatan.put(TAG_NAMA, "Nama : "+nama);

                boolean add;
                if (kegiatanList.add(kegiatan)) add = true;
                else add = false;
            }
        }catch(Exception e){
            Log.e(TAG, e.getMessage(), e);
        }

//        return response;
        return kegiatanList;
    }
}

package org.natha.lpmp.kamar;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import dev.fortunetree.json.MainActivity;
import dev.fortunetree.json.R;
import dev.fortunetree.json.ServiceHandler;
import dev.fortunetree.json.SingleContactActivity;

//public class KamarActivity extends AppCompatActivity {    //aslinya...
public class KamarActivity extends ListActivity {

    private ProgressDialog pDialog;

    //URL to get contacts JSON
    private static String url = "http://192.168.43.170:8080/user/kamars/";  //ganti pakai variable

    //Json Node names
    private static final String TAG_KAMARS = "kamars";
    private static final String TAG_NO = "no";
    private static final String TAG_LANTAI = "lantai";
    private static final String TAG_JML_TT = "jml_tt";
    private static final String TAG_URL_PICTURE = "url_picture";

    //kamars JSONArray
    JSONArray kamars = null;

//    ArrayList<HashMap<String, String>> contactList;
    ArrayList<HashMap<String, String>> kamarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //mulai disini..
        kamarList = new ArrayList<HashMap<String,String>>();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String no = ((TextView) view.findViewById(R.id.no))
                        .getText().toString();
                String lantai = ((TextView) view.findViewById(R.id.lantai))
                        .getText().toString();
                String jml_tt = ((TextView) view.findViewById(R.id.jml_tt))
                        .getText().toString();
                String url_picture = ((TextView) view.findViewById(R.id.url_picture))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra(TAG_NO, "No : "+no);
                in.putExtra(TAG_LANTAI, "Lantai : "+lantai);
                in.putExtra(TAG_JML_TT, "Jml Tt : "+jml_tt);
                in.putExtra(TAG_URL_PICTURE, "Url : "+url_picture);

                startActivity(in);

            }
        });

        // Calling async task to get json
        new KamarActivity.GetKamars().execute();

    }

    private class GetKamars extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(KamarActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }   //debugernya exit disini..

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            //buat ServiceHandler yg ngereturn ArrayList<HashMap<String, String>>
//            ArrayList<HashMap<String, String>> kamarList = sh.makeServiceCall(url, ServiceHandler.GET);
            kamarList = sh.makeServiceCall(url, ServiceHandler.GET);

//            Log.d("Response: ", "> " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    kamars = jsonObj.getJSONArray(TAG_KAMARS);
//
//                    // looping through All Contacts
//                    for (int i = 0; i < kamars.length(); i++) {
//                        JSONObject c = kamars.getJSONObject(i);
//
//                        String no = c.getString(TAG_NO);
//                        String lantai = c.getString(TAG_LANTAI);
//                        String jml_tt = c.getString(TAG_JML_TT);
//                        String url_picture = c.getString(TAG_URL_PICTURE);
////                        String gender = c.getString(TAG_GENDER);
//
//                        // Phone node is JSON Object
////                        JSONObject phone = c.getJSONObject(TAG_PHONE);
////                        String mobile = phone.getString(TAG_PHONE_MOBILE);
////                        String home = phone.getString(TAG_PHONE_HOME);
////                        String office = phone.getString(TAG_PHONE_OFFICE);
//
//                        // tmp hashmap for single contact
//                        HashMap<String, String> kamar = new HashMap<String, String>();
//
//                        // adding each child node to HashMap key => value
//                        kamar.put(TAG_NO, no);
//                        kamar.put(TAG_LANTAI, lantai);
//                        kamar.put(TAG_JML_TT, jml_tt);
//                        kamar.put(TAG_URL_PICTURE, url_picture);
//
//                        // adding contact to contact list
//                        kamarList.add(kamar);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Log.e("ServiceHandler", "Couldn't get any data from the url");
//            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    KamarActivity.this, kamarList,
                    R.layout.kamar_list_layout, new String[] { TAG_NO, TAG_LANTAI,
                    TAG_JML_TT,  TAG_URL_PICTURE}, new int[] { R.id.no, R.id.lantai,
                    R.id.jml_tt, R.id.url_picture });

            setListAdapter(adapter);
        }
    }
}

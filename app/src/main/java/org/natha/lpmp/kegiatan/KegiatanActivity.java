package org.natha.lpmp.kegiatan;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//import org.natha.lpmp.kegiatan.KegiatanActivity;
//import org.natha.lpmp.kegiatan.KegiatanServiceHandler;

import java.util.ArrayList;
import java.util.HashMap;

import dev.fortunetree.json.R;
import dev.fortunetree.json.SingleContactActivity;

//public class KegiatanActivity extends AppCompatActivity {
public class KegiatanActivity extends ListActivity {

    private ProgressDialog pDialog;

    //URL to get contacts JSON
    private static String url = "http://192.168.43.170:8080/user/kegiatans/";  //ganti pakai variable

    //Json Node names
//    private static final String TAG_KEGIATANS = "kegiatans";
    private static final String TAG_KODE = "kode";
    private static final String TAG_NAMA_KEGIATAN = "nama_kegiatan";
    private static final String TAG_KETERANGAN = "keterangan";
    private static final String TAG_NAMA = "nama";

    ArrayList<HashMap<String, String>> kegiatanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//mulai disini..
        kegiatanList = new ArrayList<HashMap<String,String>>();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String kode = ((TextView) view.findViewById(R.id.kode))
                        .getText().toString();
                String namaKegiatan = ((TextView) view.findViewById(R.id.nama_kegiatan))
                        .getText().toString();
                String keterangan = ((TextView) view.findViewById(R.id.keterangan))
                        .getText().toString();
                String nama = ((TextView) view.findViewById(R.id.nama))    //harusnya Date
                        .getText().toString();

                // Starting single kegiatan activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra(TAG_KODE, kode);
                in.putExtra(TAG_NAMA_KEGIATAN, namaKegiatan);
                in.putExtra(TAG_KETERANGAN, keterangan);
                in.putExtra(TAG_NAMA, nama);

                startActivity(in);

            }
        });

        // Calling async task to get json
        new KegiatanActivity.GetKegiatan().execute();

    }

    private class GetKegiatan extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(KegiatanActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }   //debugernya exit disini..

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            KegiatanServiceHandler bsh = new KegiatanServiceHandler();

            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            //buat ServiceHandler yg ngereturn ArrayList<HashMap<String, String>>
//            ArrayList<HashMap<String, String>> kamarList = sh.makeServiceCall(url, ServiceHandler.GET);
            kegiatanList = bsh.makeServiceCall(url, KegiatanServiceHandler.GET);

//            Log.d("Response: ", "> " + jsonStr);
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
                    KegiatanActivity.this, kegiatanList,
                    R.layout.kegiatan_list_layout, new String[] { TAG_KODE, TAG_NAMA_KEGIATAN,
                    TAG_KETERANGAN,  TAG_NAMA},
                    new int[] { R.id.kode, R.id.nama_kegiatan, R.id.keterangan, R.id.nama});

            setListAdapter(adapter);
        }

    }

}

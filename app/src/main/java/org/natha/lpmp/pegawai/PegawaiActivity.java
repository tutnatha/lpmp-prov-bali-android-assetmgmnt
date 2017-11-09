package org.natha.lpmp.pegawai;

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

import org.natha.lpmp.badroom.BadroomActivity;
import org.natha.lpmp.badroom.BadroomServiceHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dev.fortunetree.json.R;
import dev.fortunetree.json.SingleContactActivity;

//public class PegawaiActivity extends AppCompatActivity {
public class PegawaiActivity extends ListActivity {

    private ProgressDialog pDialog;

    //URL to get contacts JSON
    private static String url = "http://192.168.43.170:8080/user/pegawais/";  //ganti pakai variable

    //Json Node names
//    private static final String TAG_PEGAWAIS = "pegawais";
    private static final String TAG_NIP = "nip";
    private static final String TAG_NAME = "name";
    private static final String TAG_TEMPAT_LAHIR = "tempat_lahir";
    private static final String TAG_TGL_LAHIR = "tgl_lahir";
    private static final String TAG_PANGKAT = "pangkat";
    private static final String TAG_GOLONGAN_RUANG = "golongan_ruang";

    ArrayList<HashMap<String, String>> pegawaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);
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
        pegawaiList = new ArrayList<HashMap<String,String>>();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String nip = ((TextView) view.findViewById(R.id.nip))
                        .getText().toString();
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String tempatLahir = ((TextView) view.findViewById(R.id.tempat_lahir))
                        .getText().toString();
                String tglLahir = ((TextView) view.findViewById(R.id.tgl_lahir))    //harusnya Date
                        .getText().toString();
                String pangkat = ((TextView) view.findViewById(R.id.pangkat))
                        .getText().toString();
                String golonganRuang = ((TextView) view.findViewById(R.id.golongan_ruang))
                        .getText().toString();

                // Starting single pegawai activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra(TAG_NIP, nip);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_TEMPAT_LAHIR, tempatLahir);
                in.putExtra(TAG_TGL_LAHIR, tglLahir);
                in.putExtra(TAG_PANGKAT, pangkat);
                in.putExtra(TAG_GOLONGAN_RUANG, golonganRuang);

                startActivity(in);

            }
        });

        // Calling async task to get json
        new PegawaiActivity.GetPegawai().execute();

    }

    private class GetPegawai extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(PegawaiActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }   //debugernya exit disini..

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            PegawaiServiceHandler bsh = new PegawaiServiceHandler();

            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            //buat ServiceHandler yg ngereturn ArrayList<HashMap<String, String>>
//            ArrayList<HashMap<String, String>> kamarList = sh.makeServiceCall(url, ServiceHandler.GET);
            pegawaiList = bsh.makeServiceCall(url, PegawaiServiceHandler.GET);

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
                    PegawaiActivity.this, pegawaiList,
                    R.layout.pegawai_list_layout, new String[] { TAG_NIP, TAG_NAME,
                    TAG_TEMPAT_LAHIR,  TAG_TGL_LAHIR, TAG_PANGKAT, TAG_GOLONGAN_RUANG},
                    new int[] { R.id.nip, R.id.name, R.id.tempat_lahir, R.id.tgl_lahir,
                            R.id.pangkat, R.id.golongan_ruang});

            setListAdapter(adapter);
        }

    }

}

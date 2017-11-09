package org.natha.lpmp.badroom;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import dev.fortunetree.json.R;
import dev.fortunetree.json.ServiceHandler;
import dev.fortunetree.json.SingleContactActivity;

/**
 * Created by myssd on 11/4/17.
 */

public class BadroomActivity extends ListActivity {
    private ProgressDialog pDialog;

    //URL to get contacts JSON
    private static String url = "http://192.168.43.170:8080/user/badrooms/";  //ganti pakai variable

    //Json Node names
//    private static final String TAG_BADROOMS = "badrooms";
    private static final String TAG_NO = "no";
    private static final String TAG_NO_KAMAR = "no_kamar";
    private static final String TAG_REG_NO = "reg_no";
    private static final String TAG_IS_USED = "is_used";
    private static final String TAG_START_DATE = "start_date";
    private static final String TAG_END_DATE = "end_date";

    ArrayList<HashMap<String, String>> badroomList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_badroom);

        //mulai disini..
        badroomList = new ArrayList<HashMap<String,String>>();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String no = ((TextView) view.findViewById(R.id.no))
                        .getText().toString();
                String no_kamar = ((TextView) view.findViewById(R.id.no_kamar))
                        .getText().toString();
                String reg_no = ((TextView) view.findViewById(R.id.reg_no))
                        .getText().toString();
                String is_used = ((TextView) view.findViewById(R.id.is_used))
                        .getText().toString();
                String start_date = ((TextView) view.findViewById(R.id.start_date))
                        .getText().toString();
                String end_date = ((TextView) view.findViewById(R.id.end_date))
                        .getText().toString();

                // Starting single badroom activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra(TAG_NO, no);
                in.putExtra(TAG_NO_KAMAR, no_kamar);
                in.putExtra(TAG_REG_NO, reg_no);
                in.putExtra(TAG_IS_USED, is_used);
                in.putExtra(TAG_START_DATE, start_date);
                in.putExtra(TAG_END_DATE, end_date);

                startActivity(in);

            }
        });

        // Calling async task to get json
        new BadroomActivity.GetBadrooms().execute();

    }

    private class GetBadrooms extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BadroomActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }   //debugernya exit disini..

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            BadroomServiceHandler bsh = new BadroomServiceHandler();

            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            //buat ServiceHandler yg ngereturn ArrayList<HashMap<String, String>>
//            ArrayList<HashMap<String, String>> kamarList = sh.makeServiceCall(url, ServiceHandler.GET);
            badroomList = bsh.makeServiceCall(url, BadroomServiceHandler.GET);

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
                    BadroomActivity.this, badroomList,
                    R.layout.badroom_list_layout, new String[] { TAG_NO, TAG_NO_KAMAR,
                    TAG_REG_NO,  TAG_IS_USED, TAG_START_DATE, TAG_END_DATE},
                    new int[] { R.id.no, R.id.no_kamar, R.id.reg_no, R.id.is_used,
                            R.id.start_date, R.id.end_date});

            setListAdapter(adapter);
        }
    }
}

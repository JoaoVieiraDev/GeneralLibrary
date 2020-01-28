package com.example.butler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.butler.REST_requests.requestREST;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CreateModes extends AppCompatActivity {
SharedPreferences sp;
RecyclerView mRecyclerView;
    private FragmentTransaction fragmentTransaction;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         i=0;
         final Context mContext=this;
        setContentView(R.layout.activity_createmode);
        Button bttt=findViewById(R.id.register_button);
        ImageButton im=findViewById(R.id.menu);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav_fragment fm=new nav_fragment();
                setFragment(fm);

            }
        });

        bttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=findViewById(R.id.username);
               String name= et.getText().toString();
                Intent it=new Intent(mContext,ModesActivity.class);
                it.putExtra("name",name);
                startActivity(it);
            }
        });
        sp=getSharedPreferences("tokenId",MODE_PRIVATE);
        final AppCompatTextView tx=findViewById(R.id.text);
        final LinearLayout l=findViewById(R.id.recycler_view2);
      SharedPreferences  sd=getSharedPreferences(sp.getString("token",null),MODE_PRIVATE);
        boolean t=sd.getBoolean("garagem",false);
        final Spinner spinner=findViewById(R.id.spinner);
        if(t==true){
            String[]  st={"<select device>","Sound System","Air conditioner & Heating","Lights","Pet Feeder","Garage Door"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, st);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }else{
            String[] st={"<select device>","Sound System","Air conditioner & Heating","Lights","Pet Feeder"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, st);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String device= spinner.getSelectedItem().toString();
               if(device.equals("Sound System")){
                 tx.setText("PlayList");
                 l.setVisibility(View.VISIBLE);
                   ImageButton btt=findViewById(R.id.appCompatImageView);
                   btt.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if(i==0){
                               AppCompatTextView tx2=findViewById(R.id.text2);
                               LinearLayout l1=findViewById(R.id.l1);
                               tx2.setText("Manage Sound System");
                               l1.setVisibility(View.VISIBLE);

                           }
                           if(i==1){
                               AppCompatTextView tx2=findViewById(R.id.text3);
                               LinearLayout l1=findViewById(R.id.l2);
                               tx2.setText("Manage Sound System");
                               l1.setVisibility(View.VISIBLE);
                           }
                           if(i==2){
                               AppCompatTextView tx2=findViewById(R.id.text4);
                               LinearLayout l1=findViewById(R.id.l3);
                               tx2.setText("Manage Sound System");
                               l1.setVisibility(View.VISIBLE);
                           }
                           if(i==3){
                               AppCompatTextView tx2=findViewById(R.id.text5);
                               LinearLayout l1=findViewById(R.id.l4);
                               tx2.setText("Manage Sound System");
                               l1.setVisibility(View.VISIBLE);
                           }
                           if(i==4){
                               AppCompatTextView tx2=findViewById(R.id.text6);
                               LinearLayout l1=findViewById(R.id.l5);
                               tx2.setText("Manage Sound System");
                               l1.setVisibility(View.VISIBLE);
                           }
                           l.setVisibility(View.INVISIBLE);
                           i++;

                       }
                   });
               }
                if(device.equals("Air conditioner & Heating")){
                    tx.setText("Manage Temperature");
                    l.setVisibility(View.VISIBLE);
                    ImageButton btt=findViewById(R.id.appCompatImageView);
                    btt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(i==0){
                                AppCompatTextView tx2=findViewById(R.id.text2);
                                LinearLayout l1=findViewById(R.id.l1);
                                tx2.setText("Manage Temperature");
                                l1.setVisibility(View.VISIBLE);

                            }
                            if(i==1){
                                AppCompatTextView tx2=findViewById(R.id.text3);
                                LinearLayout l1=findViewById(R.id.l2);
                                tx2.setText("Manage Temperature");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==2){
                                AppCompatTextView tx2=findViewById(R.id.text4);
                                LinearLayout l1=findViewById(R.id.l3);
                                tx2.setText("Manage Temperature");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==3){
                                AppCompatTextView tx2=findViewById(R.id.text5);
                                LinearLayout l1=findViewById(R.id.l4);
                                tx2.setText("Manage Temperature");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==4){
                                AppCompatTextView tx2=findViewById(R.id.text6);
                                LinearLayout l1=findViewById(R.id.l5);
                                tx2.setText("Manage Temperature");
                                l1.setVisibility(View.VISIBLE);
                            }
                            l.setVisibility(View.INVISIBLE);
                            i++;

                        }
                    });
                }
                if(device.equals("Lights")){
                    tx.setText("Lights");
                    l.setVisibility(View.VISIBLE);
                    ImageButton btt=findViewById(R.id.appCompatImageView);
                    btt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(i==0){
                                AppCompatTextView tx2=findViewById(R.id.text2);
                                LinearLayout l1=findViewById(R.id.l1);
                                tx2.setText("Lights");
                                l1.setVisibility(View.VISIBLE);

                            }
                            if(i==1){
                                AppCompatTextView tx2=findViewById(R.id.text3);
                                LinearLayout l1=findViewById(R.id.l2);
                                tx2.setText("Lights");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==2){
                                AppCompatTextView tx2=findViewById(R.id.text4);
                                LinearLayout l1=findViewById(R.id.l3);
                                tx2.setText("Lights");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==3){
                                AppCompatTextView tx2=findViewById(R.id.text5);
                                LinearLayout l1=findViewById(R.id.l4);
                                tx2.setText("Lights");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==4){
                                AppCompatTextView tx2=findViewById(R.id.text6);
                                LinearLayout l1=findViewById(R.id.l5);
                                tx2.setText("Lights");
                                l1.setVisibility(View.VISIBLE);
                            }
                            l.setVisibility(View.INVISIBLE);
                            i++;

                        }
                    });
                }
                if(device.equals("Pet Feeder")){
                    tx.setText("Fill Feed Dog");
                    l.setVisibility(View.VISIBLE);
                    ImageButton btt=findViewById(R.id.appCompatImageView);
                    btt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(i==0){
                                AppCompatTextView tx2=findViewById(R.id.text2);
                                LinearLayout l1=findViewById(R.id.l1);
                                tx2.setText("Fill Feed Dog");
                                l1.setVisibility(View.VISIBLE);

                            }
                            if(i==1){
                                AppCompatTextView tx2=findViewById(R.id.text3);
                                LinearLayout l1=findViewById(R.id.l2);
                                tx2.setText("Fill Feed Dog");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==2){
                                AppCompatTextView tx2=findViewById(R.id.text4);
                                LinearLayout l1=findViewById(R.id.l3);
                                tx2.setText("Fill Feed Dog");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==3){
                                AppCompatTextView tx2=findViewById(R.id.text5);
                                LinearLayout l1=findViewById(R.id.l4);
                                tx2.setText("Fill Feed Dog");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==4){
                                AppCompatTextView tx2=findViewById(R.id.text6);
                                LinearLayout l1=findViewById(R.id.l5);
                                tx2.setText("Fill Feed Dog");
                                l1.setVisibility(View.VISIBLE);
                            }
                            l.setVisibility(View.INVISIBLE);
                            i++;

                        }
                    });
                }
                if(device.equals("Garage Door")){
                    tx.setText("Manage Garaje Door");
                    l.setVisibility(View.VISIBLE);
                    ImageButton btt=findViewById(R.id.appCompatImageView);
                    btt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(i==0){
                                AppCompatTextView tx2=findViewById(R.id.text2);
                                LinearLayout l1=findViewById(R.id.l1);
                                tx2.setText("Manage Garaje Door");
                                l1.setVisibility(View.VISIBLE);

                            }
                            if(i==1){
                                AppCompatTextView tx2=findViewById(R.id.text3);
                                LinearLayout l1=findViewById(R.id.l2);
                                tx2.setText("Manage Garaje Door");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==2){
                                AppCompatTextView tx2=findViewById(R.id.text4);
                                LinearLayout l1=findViewById(R.id.l3);
                                tx2.setText("Manage Garaje Door");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==3){
                                AppCompatTextView tx2=findViewById(R.id.text5);
                                LinearLayout l1=findViewById(R.id.l4);
                                tx2.setText("Manage Garaje Door");
                                l1.setVisibility(View.VISIBLE);
                            }
                            if(i==4){
                                AppCompatTextView tx2=findViewById(R.id.text6);
                                LinearLayout l1=findViewById(R.id.l5);
                                tx2.setText("Manage Garaje Door");
                                l1.setVisibility(View.VISIBLE);
                            }
                            l.setVisibility(View.INVISIBLE);
                            i++;

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setFragment(nav_fragment fm) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fm);
        fragmentTransaction.addToBackStack(fm.toString());
        fragmentTransaction.commit();
    }
    public class UserFindTaskdevices extends AsyncTask<Void, Void, String> {

        private final String mDevice;
        String mToken;


        UserFindTaskdevices(String device,String token) {
       mDevice=device;
       mToken=token;
        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */
        @Override
        protected void onPreExecute() {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected() ||
                    (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                            && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                // If no connectivity, cancel task and update Callback with null data.
                cancel(true);
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                //TODO: create JSON object with credentials and call doPost
                String urlDest = new String("https://ipmworks.appspot.com//rest/task?homeId=");
                urlDest=urlDest.concat(sp.getString("homeId",null));
                urlDest=urlDest.concat("&device=");
                urlDest=urlDest.concat(mDevice);



                return requestREST.doGET(new URL(urlDest),mToken );

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {

            //showProgress(false);

            if (result != null) {
                JSONArray response = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                        response = new JSONArray(result);
                  UserTask ut=new UserTask(mToken,response);
                  ut.execute((Void) null);

                        Log.i("LoginActivity", response.toString());




                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
    public class UserTask extends AsyncTask<Void, TasksClass, ArrayList<TasksClass>> {
        String mToken;
        JSONArray arr;
        int i;


        UserTask(String token,JSONArray arr) {
            mToken=token;
            this.arr=arr;
            this.i=i;

        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */
        @Override
        protected void onPreExecute() {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected() ||
                    (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                            && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                // If no connectivity, cancel task and update Callback with null data.
                cancel(true);
            }
        }

        @Override
        protected ArrayList<TasksClass> doInBackground(Void... params) {
            ArrayList<TasksClass> l = new ArrayList();
            try {

                //TODO: create JSON object with credentials and call doPost
                for (int i = 0; i < arr.length(); i++) {
                    String arrr = arr.get(i).toString();
                    String urlDest = new String("https://ipmworks.appspot.com/rest/task/");
                    urlDest = urlDest.concat(arrr);
                    Log.e("arr", arrr);
                    Log.e("url: ", urlDest);
                    String res = requestREST.doGET(new URL(urlDest), mToken);
                    JSONObject responsee = null;
                    responsee = new JSONObject(res);
                    Log.e("TT ", responsee.toString());
                    TasksClass tk = new TasksClass(responsee.getString("name"), responsee.getString("device"), responsee.getString("desc"), responsee.getString("state"));
                    l.add(tk);
                }

            } catch (Exception e) {
                e.toString();

            }
            return l;
        }


        @Override
        protected void onPostExecute(final ArrayList<TasksClass> result) {
       ModeAdapter ma=new ModeAdapter(result);
mRecyclerView.setAdapter(ma);
            }
        }



}

package com.example.butler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.butler.REST_requests.requestREST;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DeviceActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    Context mContext;
    SharedPreferences sp,st;
    List<TasksClass> list;

    boolean isOn1 = false;
    boolean isOn2 = false;
    boolean isOn3 = false;
    boolean isOn4 = false;
    boolean isOn5 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        st=getSharedPreferences("tokenId",MODE_PRIVATE);
        sp=getSharedPreferences(st.getString("token",null),MODE_PRIVATE);
        boolean t=sp.getBoolean("garagem",false);
        if(t==true){
            CardView cv=findViewById(R.id.cardView5);
            cv.setVisibility(View.VISIBLE);
        }
        ImageButton imb=findViewById(R.id.st2);
        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mContext,SoundSystemSettingsActivity.class);
                startActivity(it);
            }
        });
        Set<String> sett=new HashSet<>();
        sett= sp.getStringSet("set", new HashSet<String>());
        Iterator<String> iterator=sett.iterator();
        Log.e("number: ",String.valueOf(sett.size()));
        int i=0;
        while (iterator.hasNext()) {
            String name=iterator.next();
            Log.e("t",name);
            if (i == 0) {
                i++;
                TextView t1 = findViewById(R.id.text);
                t1.setText(name);
            }
            if (i == 2) {
                TextView t3 = findViewById(R.id.text3);
                t3.setText(name);
            }
            if (i == 3) {
                TextView t4 = findViewById(R.id.text4);
                t4.setText(name);
            }
            if (i == 4) {
                TextView t5 = findViewById(R.id.text5);
                t5.setText(name);
            }
            i++;
        }
       mContext=this;
       SharedPreferences st=getSharedPreferences("tokenId",MODE_PRIVATE);
       String tokenId=st.getString("token",null);
       UserTaskNumber utn=new UserTaskNumber(tokenId);
       utn.execute((Void) null);
         ImageButton bt=findViewById(R.id.imageButton);
         ImageButton btm=findViewById(R.id.my_modess);
         btm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(mContext,ModesActivity.class);
                 startActivity(i);
             }
         });
         bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             Intent it=new Intent(mContext,AddDeviceActivity.class);
             startActivity(it);
             }
         });
        Intent it=getIntent();
        String user=it.getStringExtra("username");
        ImageButton im=findViewById(R.id.menu);
           im.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   nav_fragment fm=new nav_fragment();
                   setFragment(fm);

               }
           });


        CardView task1 = findViewById(R.id.cardView);
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = v.findViewById(R.id.linear1);
                if(!isOn1){
                    l.setBackgroundResource(R.drawable.taskbutton_background_on);
                    changeOnState1();
                }
                else{
                    l.setBackgroundResource(R.drawable.taskbutton_background);
                    changeOnState1();
                }
            }
        });

        CardView task2 = findViewById(R.id.cardView2);
        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = v.findViewById(R.id.linear2);
                if(!isOn2){
                    l.setBackgroundResource(R.drawable.taskbutton_background_on);
                    changeOnState2();
                }
                else{
                    l.setBackgroundResource(R.drawable.taskbutton_background);
                    changeOnState2();
                }
            }
        });

        CardView task3 = findViewById(R.id.cardView3);
        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = v.findViewById(R.id.linear3);
                if(!isOn3){
                    l.setBackgroundResource(R.drawable.taskbutton_background_on);
                    changeOnState3();
                }
                else{
                    l.setBackgroundResource(R.drawable.taskbutton_background);
                    changeOnState3();
                }
            }
        });

        CardView task4 = findViewById(R.id.cardView4);
        task4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = v.findViewById(R.id.linear4);
                if(!isOn4){
                    l.setBackgroundResource(R.drawable.taskbutton_background_on);
                    changeOnState4();
                }
                else{
                    l.setBackgroundResource(R.drawable.taskbutton_background);
                    changeOnState4();
                }
            }
        });

        CardView task5 = findViewById(R.id.cardView5);
        task5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = v.findViewById(R.id.linear5);
                if(!isOn5){
                    l.setBackgroundResource(R.drawable.taskbutton_background_on);
                    changeOnState5();
                }
                else{
                    l.setBackgroundResource(R.drawable.taskbutton_background);
                    changeOnState5();
                }
            }
        });
    }

    private void changeOnState1(){
        isOn1 = !isOn1;
    }
    private void changeOnState2(){
        isOn2 = !isOn2;
    }
    private void changeOnState3(){
        isOn3 = !isOn3;
    }
    private void changeOnState4(){
        isOn4 = !isOn4;
    }
    private void changeOnState5(){
        isOn5 = !isOn5;
    }

    private void setFragment(nav_fragment fm) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fm);
        fragmentTransaction.addToBackStack(fm.toString());
        fragmentTransaction.commit();
    }

    public class UserTaskNumber extends AsyncTask<Void, Void, String> {
        String mToken;


        UserTaskNumber(String token) {
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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/task");
                JSONObject jsonObj = new JSONObject();
                //MUDAR SE TIVER DE MUDAR O LOGIN DATA
                return requestREST.doGET(urlDest, mToken);
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

                    if (result.equalsIgnoreCase("login failed")) {
                        Toast.makeText(mContext, "Login failed",
                                Toast.LENGTH_LONG).show();
                    } else {
                        response = new JSONArray(result);
                        Log.e("TT ", response.toString());
                        Log.e("num", String.valueOf(response.length()));
                        for (int i = 0; i < response.length(); i++) {
                            Log.e("ggg ", response.get(i).toString());
                            UserTask ut = new UserTask(mToken, response.get(i).toString(), i);
                            ut.execute((Void) null);


                        }
                    }

                        Log.i("LoginActivity", response.toString());


                    } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    public class UserTask extends AsyncTask<Void, Void, String> {
        String mToken;
        String arr;
        int i;


        UserTask(String token,String arr,int i) {
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
        protected String doInBackground(Void... params) {
            try {
                //TODO: create JSON object with credentials and call doPost
                String urlDest = new String("https://ipmworks.appspot.com/rest/task/");
              urlDest=  urlDest.concat(arr);
                Log.e("arr",arr);
                Log.e("url: ",urlDest);
                    return requestREST.doGET(new URL(urlDest), mToken);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            //showProgress(false);

            if (result != null) {
                JSONObject responsee = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                    if (result.equalsIgnoreCase("login failed")) {
                        Toast.makeText(mContext, "Login failed",
                                Toast.LENGTH_LONG).show();
                    } else {
                        responsee = new JSONObject(result);
                        Log.e("TT ",responsee.toString());
                        TasksClass tk=new TasksClass(responsee.getString("name"),responsee.getString("device"),responsee.getString("desc"),responsee.getString("state"));


                    }


                    Log.i("LoginActivity", responsee.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }


    }
}

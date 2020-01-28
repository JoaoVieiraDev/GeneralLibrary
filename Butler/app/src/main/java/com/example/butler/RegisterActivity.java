package com.example.butler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.butler.REST_requests.requestREST;
import com.example.butler.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private UserRegisterTask mAuthTask=null;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_register);
        final EditText user=findViewById(R.id.username);
       final  EditText pas=findViewById(R.id.password);
      final  EditText realName=findViewById(R.id.realName);
      final  EditText house=findViewById(R.id.housecode);
      final  EditText email=findViewById(R.id.email);
        Button register=findViewById(R.id.register_button);
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mAuthTask=new UserRegisterTask(user.getText().toString(),pas.getText().toString(),realName.getText().toString(),house.getText().toString(),email.getText().toString(),mContext);
        mAuthTask.execute((Void) null);

    }
});

    }
    public class UserRegisterTask extends AsyncTask<Void, Void, String> {

        private String mUser;
        private String mPas;
        private String mRealName;
        private String mHouse;
        private Context mContext;
        String mEmail;

        UserRegisterTask(String user, String pas, String realName, String house, String email, Context mContext) {
            mUser = user;
            mPas = pas;
            mRealName = realName;
            mHouse = house;
            mEmail = email;
            this.mContext = mContext;
        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */
        @Override
        protected void onPreExecute() {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/user/");
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("userId", mUser);
                jsonObj.put("email", mEmail);
                jsonObj.put("pass", mPas);
                jsonObj.put("realName", mRealName);
                Log.e("user: ", mUser);
                Log.e("email: ", mEmail);
                Log.e("pass: ", mPas);
                Log.e("name: ", mRealName);

                return requestREST.doPOST(urlDest, jsonObj);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            //showProgress(false);
            if (result.equalsIgnoreCase("login failed")) {
                Toast.makeText(mContext, "Register Failed",
                        Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(mContext, "Register Done Sucessfully",
                        Toast.LENGTH_LONG).show();
                Intent it = new Intent(mContext, LoginActivity.class);
                startActivity(it);
            }

        }
    }

}

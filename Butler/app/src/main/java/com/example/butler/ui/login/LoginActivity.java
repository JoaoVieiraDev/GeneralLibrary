package com.example.butler.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.butler.DeviceActivity;
import com.example.butler.R;
import com.example.butler.REST_requests.requestREST;
import com.example.butler.RegisterActivity;
import com.example.butler.TasksClass;
import com.example.butler.nav_fragment;
import com.example.butler.ui.login.LoginViewModel;
import com.example.butler.ui.login.LoginViewModelFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    private LoginViewModel loginViewModel;
    private UserLoginTask mAuthTask = null;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView tc;
    private SharedPreferences sp;
    SharedPreferences.Editor et;
    Set<String>st;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       st=new HashSet<String>();


        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        mContext = this;
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login_button);
        final  Button registerButton=findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, RegisterActivity.class);
                startActivity(t);
            }
        });



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthTask=new UserLoginTask(usernameEditText.getText().toString(),passwordEditText.getText().toString(),mContext);
                mAuthTask.execute((Void) null);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void showProgress ( final boolean show){
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        usernameEditText.setVisibility(show ? View.GONE : View.VISIBLE);
        usernameEditText.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                usernameEditText.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        passwordEditText.setVisibility(show ? View.VISIBLE : View.GONE);
        passwordEditText.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                passwordEditText.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });


    }
    public class UserLoginTask extends AsyncTask<Void, Void, String> {

        private final String mEmail;
        private final String mPassword;
        private final Context mContext;

        UserLoginTask(String email, String password,Context mContext) {
            mEmail = email;
            mPassword = password;
            this.mContext=mContext;
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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/user/login");

                JSONObject jsonObj = new JSONObject();
                //MUDAR SE TIVER DE MUDAR O LOGIN DATA
                jsonObj.put("u", mEmail);
                jsonObj.put("p", mPassword);
                jsonObj.put("k", true);

                return requestREST.doPOST(urlDest, jsonObj);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
            //showProgress(false);

            if (result != null) {
                JSONObject response = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                    if (result.equalsIgnoreCase("login failed")) {
                        Toast.makeText(mContext, "Login failed",
                                Toast.LENGTH_LONG).show();
                    } else {

                        response = new JSONObject(result);
                       String tokenId= response.getString("tokenID");
                        sp=getSharedPreferences(String.valueOf(tokenId),MODE_PRIVATE);
                        st=sp.getStringSet("set",new HashSet<String>());
                        Log.e("st",String.valueOf(st.size()));
                        if(st.isEmpty()) {
                            Log.e("ttt", "ttt");
                            st.add("Manage\nTemperature");
                            st.add("Lights");
                            st.add("Fill Pet\nFeeder");
                        }
                            sp.edit().putStringSet("set", st).commit();
SharedPreferences spp=getSharedPreferences("tokenId",MODE_PRIVATE);
                        spp.edit().putString("token",tokenId).commit();

                    /*    UserHouseNumnber uhn=new UserHouseNumnber(tokenId);
                       uhn.execute((Void) null);
                       UserTaskNumber utn=new UserTaskNumber(tokenId);
                        utn.execute((Void) null);
                        UserModeNumber umn=new UserModeNumber(tokenId);
                        umn.execute((Void) null);*/
                       Intent it=new Intent(mContext,DeviceActivity.class);
                       it.putExtra("tokenID",tokenId);
                       it.putExtra("username",mEmail);
                       startActivity(it);
                       finish();

                        Log.i("LoginActivity", response.toString());


                    }

                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
   /* public class UserTaskNumber extends AsyncTask<Void, Void, String> {
String mToken;


        UserTaskNumber(String token) {
            mToken=token;

        }/*


         * Cancel background network operation if we do not have network connectivity.

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
            mAuthTask = null;
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
                        Log.e("TT ",response.toString());
                        Log.e("num",String.valueOf(response.length()));
                        if(response.length()==0){
                         UserAddTask uat=new   UserAddTask(mToken);
                         uat.execute((Void) null);

                        }


                        Log.i("LoginActivity", response.toString());


                    }

                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
    public class UserAddTask extends AsyncTask<Void, Void, String> {
        String mToken;



        UserAddTask(String token) {
            mToken=token;

        }


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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/task");
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name","Luz Sala");
                jsonObj.put("desc","Luz");
                jsonObj.put("device","Lights");
                jsonObj.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj.put("homeId",sp.getString("homeId",null));
                requestREST.doPOST(urlDest,jsonObj,mToken);
                JSONObject jsonObj1 = new JSONObject();
                jsonObj1.put("name","PlayList");
                jsonObj1.put("desc","Playlist");
                jsonObj1.put("device","Sound System");
                jsonObj1.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj1.put("homeId",sp.getString("homeId",null));
               requestREST.doPOST(urlDest,jsonObj1,mToken);
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("name","Comida Cão");
                jsonObj2.put("desc","Projetor");
                jsonObj2.put("device","Pet Feeder");
                jsonObj2.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj2.put("homeId",sp.getString("homeId",null));
                 requestREST.doPOST(urlDest,jsonObj2,mToken);
                JSONObject jsonObj4 = new JSONObject();
                jsonObj4.put("name","Temperatura");
                jsonObj4.put("desc","temperatura");
                jsonObj4.put("device","Air conditioner & Heating");
                jsonObj4.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj4.put("homeId",sp.getString("homeId",null));
                return requestREST.doPOST(urlDest,jsonObj4,mToken);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
            //showProgress(false);

            if (result != null) {
                JSONObject response = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                    if (result.equalsIgnoreCase("login failed")) {
                        Toast.makeText(mContext, "Login failed",
                                Toast.LENGTH_LONG).show();
                    } else {

                        response = new JSONObject(result);
                        Log.i("LoginActivity", response.toString());


                    }

                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
    public class UserHouseNumnber extends AsyncTask<Void, Void, String> {
        String mToken;


        UserHouseNumnber(String token) {
            mToken=token;

        }


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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/home/own");
                return requestREST.doGET(urlDest, mToken);
            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
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
                        sp.edit().putString("homeId", response.get(0).toString()).commit();
                        Log.e("home",sp.getString("homeId",null));

                        }


                        Log.i("LoginActivity", response.toString());


                    } catch (JSONException e) {
                    Log.e("Authentication AQUI", e.toString());
                }

            }
        }
    }
    public class UserModeNumber extends AsyncTask<Void, Void, String> {
        String mToken;


        UserModeNumber(String token) {
            mToken=token;

        }


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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/mode?homeId="+sp.getString("homeId",null));
                JSONObject jsonObj = new JSONObject();
                //MUDAR SE TIVER DE MUDAR O LOGIN DATA
                return requestREST.doGET(urlDest, mToken);
            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
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
                        Log.e("TT ",response.toString());
                        Log.e("num",String.valueOf(response.length()));
                        if(response.length()==0){
                            UserAddMode uam=new UserAddMode(mToken);
                            uam.execute((Void) null);

                        }


                        Log.i("LoginActivity", response.toString());


                    }

                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
    public class UserAddMode extends AsyncTask<Void, Void, String> {
        String mToken;



        UserAddMode(String token) {
            mToken=token;

        }


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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/mode/");
                JSONObject jsonObj3 = new JSONObject();
                jsonObj3.put("name","Pet Care");
                jsonObj3.put("desc","Take care of your dog");
                jsonObj3.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj3.put("homeId",sp.getString("homeId",null));
                requestREST.doPOST(urlDest,jsonObj3,mToken);
                JSONObject jsonObj4 = new JSONObject();
                jsonObj4.put("name","Party Mode");
                jsonObj4.put("desc","Throw a party");
                jsonObj4.put("state","off");
                Log.e("ttt ",sp.getString("homeId",null));
                jsonObj4.put("homeId",sp.getString("homeId",null));
                return requestREST.doPOST(urlDest,jsonObj4,mToken);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
            //showProgress(false);

            if (result != null) {
                JSONObject response = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                    if (result.equalsIgnoreCase("login failed")) {
                        Toast.makeText(mContext, "Login failed",
                                Toast.LENGTH_LONG).show();
                    } else {

                        response = new JSONObject(result);
                        Log.i("LoginActivity", response.toString());


                    }

                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }*/

}

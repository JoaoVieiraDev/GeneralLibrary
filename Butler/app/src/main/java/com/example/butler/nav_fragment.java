package com.example.butler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.butler.REST_requests.requestREST;
import com.example.butler.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link nav_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nav_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nav_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String tokenID;
    String username;
    TextView txt;
    UserDefTask mAuthTask;
    Context mContext;


    private OnFragmentInteractionListener mListener;
    public nav_fragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static nav_fragment newInstance(String param1, String param2) {
        nav_fragment fragment = new nav_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext=getContext();
        txt=getView().findViewById(R.id.username);
        CardView img=getView().findViewById(R.id.new_house_card);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHomeAdd uha=new UserHomeAdd();
                uha.execute((Void) null);
            }
        });

        CardView newModeButton = getView().findViewById(R.id.new_mode_card);
        newModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, CreateModes.class);
                startActivity(t);
            }
        });

        CardView myModesButton = getView().findViewById(R.id.my_modes_card);
        myModesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, ModesActivity.class);
                startActivity(t);
            }
        });
/*
        CardView myTasksButton = getView().findViewById(R.id.my_tasks_card);
        myTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, TasksClass.class);
                startActivity(t);
            }
        });
-*/
        CardView addMemberButton = getView().findViewById(R.id.add_member_card);
        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, AddUserActivity.class);
                startActivity(t);
            }
        });

        CardView logoutButton = getView().findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(mContext, LoginActivity.class);
                startActivity(t);
            }
        });

        SharedPreferences sp=getContext().getSharedPreferences("tokenId",Context.MODE_PRIVATE);
        tokenID=sp.getString("token",null);
        Log.e("token: ",tokenID);
        mAuthTask=new UserDefTask();
        mAuthTask.execute((Void) null);


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class UserDefTask extends AsyncTask<Void, Void, String> {


        UserDefTask() {

        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */


        @Override
        protected String doInBackground(Void... params) {
            try {
                //TODO: create JSON object with credentials and call doPost
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/user/");
                Log.e("pe: ",tokenID);
                   return requestREST.doGET(urlDest,tokenID);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {

            //showProgress(false);

            if (result != null) {
                JSONObject response = null;
                Log.e("pp",result.toString());
                try {
                    // We parse the result

                        response = new JSONObject(result);
                         username= response.getString("userId");
                         txt.setText(username);






                } catch (JSONException e) {
                    // WRONG DATA SENT BY THE SERVER
                    Log.e("Authentication AQUI", e.toString());
                }
            }
        }
    }
    public class UserHomeAdd extends AsyncTask<Void, Void, String> {


        UserHomeAdd() {

        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */
        @Override
        protected void onPreExecute() {
            ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
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
                URL urlDest = new URL("https://ipmworks.appspot.com/rest/home");
                return requestREST.doPOST(urlDest, tokenID);

            } catch (Exception e) {
                return e.toString();
            }
        }


        @Override
        protected void onPostExecute(final String result) {
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
}

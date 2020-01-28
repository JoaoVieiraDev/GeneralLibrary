package com.example.butler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.butler.R;

public class InitialActivity extends AppCompatActivity {
private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        /*
        mContext=this;
        Button loginBt=(Button) findViewById(R.id.login);
        Button register=findViewById(R.id.register);
        Log.e("t","1");
        Log.e("tt", String.valueOf(loginBt.getId()));
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("t","1");
                Intent it=new Intent(mContext,LoginActivity.class);
                Log.e("t","1");
           startActivity(it);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("aqui","aqui");
            }
        });

*/
    }
}

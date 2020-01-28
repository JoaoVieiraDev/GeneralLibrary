package com.example.butler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class AddDeviceActivity extends AppCompatActivity {
SharedPreferences sp,sd;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context mContext=this;
        setContentView(R.layout.activity_adddevice);
        sp=getSharedPreferences("tokenId",MODE_PRIVATE);
        sd=getSharedPreferences(sp.getString("token",null),MODE_PRIVATE);
        if( sd.getBoolean("garagem",false)==true){
            LinearLayout li=findViewById(R.id.lin);
            li.setVisibility(View.INVISIBLE);
        }
        ImageButton im=findViewById(R.id.menu);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav_fragment fm=new nav_fragment();
                setFragment(fm);

            }
        });


        Button register=findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sd.edit().putBoolean("garagem",true).commit();
                LinearLayout li=findViewById(R.id.lin);
                li.setVisibility(View.INVISIBLE);
                Intent it=new Intent(mContext,DeviceActivity.class);
                startActivity(it);
            }
        });
    }
    private void setFragment(nav_fragment fm) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fm);
        fragmentTransaction.addToBackStack(fm.toString());
        fragmentTransaction.commit();
    }
}

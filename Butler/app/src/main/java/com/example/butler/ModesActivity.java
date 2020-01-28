package com.example.butler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ModesActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context mContext=this;
        setContentView(R.layout.activity_modes);
        ImageButton im=findViewById(R.id.menu);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav_fragment fm=new nav_fragment();
                setFragment(fm);

            }
        });

        SharedPreferences sp=getSharedPreferences("tokenId",MODE_PRIVATE);
        int n=sp.getInt("num",2);
        ImageButton bt=findViewById(R.id.imageButton);
        TextView m1=findViewById(R.id.text);
        m1.setText("Pet Care");
        TextView m2=findViewById(R.id.text2);
        m2.setText("Party");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mContext,CreateModes.class);
                startActivity(it);
            }
        });
        Intent it=getIntent();
        String name=it.getStringExtra("name");
        if(name!=null){
            if(n==2){
                TextView m3=findViewById(R.id.text3);
                m3.setText(name);
                n=3;
            }
            if(n==3){
                TextView m3=findViewById(R.id.text4);
                m3.setText(name);
                n=4;
            }
            if(n==4){
                TextView m3=findViewById(R.id.text6);
                m3.setText(name);
                n=5;
            }
            if(n==5){
                TextView m3=findViewById(R.id.text6);
                m3.setText(name);
                n=6;
            }
            sp.edit().putInt("num",n).commit();


        }
    }
    private void setFragment(nav_fragment fm) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fm);
        fragmentTransaction.addToBackStack(fm.toString());
        fragmentTransaction.commit();
    }
}

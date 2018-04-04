package com.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Tollfree extends AppCompatActivity implements View.OnClickListener {
  ImageView sitaram,chiranjeevi,kgh,raja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tollfree);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Toll Free GateWay");
        raja = (ImageView) findViewById(R.id.raja);
        kgh = (ImageView) findViewById(R.id.kgh);
        chiranjeevi = (ImageView) findViewById(R.id.chiranjeevi);
        sitaram = (ImageView) findViewById(R.id.sitaram);
        raja.setOnClickListener(Tollfree.this);
        kgh.setOnClickListener(Tollfree.this);
        chiranjeevi.setOnClickListener(Tollfree.this);
        sitaram.setOnClickListener(Tollfree.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kgh:
                String dial = "tel:" + "08912543436";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                break;
            case R.id.raja:
                String dial2 = "tel:" + "08912543342";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial2)));
                break;
            case R.id.chiranjeevi:
                String dial3 = "tel:" + "08912754787";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial3)));
                break;
            case R.id.sitaram:
                String dial4 = "tel:" + "08912706025";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial4)));
                break;
        }
    }
}






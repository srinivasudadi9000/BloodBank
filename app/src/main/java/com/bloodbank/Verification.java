package com.bloodbank;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verification extends AppCompatActivity {
    Button clickme;
    EditText enter_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);
        enter_txt = (EditText) findViewById(R.id.enter_txt);
        clickme = (Button) findViewById(R.id.clickme);

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences vendorname = Verification.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (vendorname.getString("verified", "").equals("not")) {
                    Toast.makeText(getBaseContext(),"verified",Toast.LENGTH_SHORT).show();
                    if (vendorname.getString("otp", "").equals(enter_txt.getText().toString())) {
                        Toast.makeText(getBaseContext(),"otp",Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor otp = Verification.this.getSharedPreferences("OTP", Verification.this.MODE_PRIVATE).edit();
                        otp.putString("otp", "1");
                        otp.putString("verified", "Verified");
                        otp.commit();
                        finish();
                    }
                }else {
                    finish();
                }
            }
        });

    }
}

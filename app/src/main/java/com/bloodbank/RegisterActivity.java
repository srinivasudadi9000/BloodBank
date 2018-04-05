package com.bloodbank;

import android.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodbank.DonarDetails.Details;
import com.bloodbank.EventActivities.EventsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {
    EditText etFullName, etMobile, etEmail, etPassword, etAddress,aadhar_et;
    String name, email, number, password, bloodgroup, pincode,aadhar;
    Button btnSave;
    Spinner spinner;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView textView;
    FrameLayout register_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Register");
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("details");

        register_frame = (FrameLayout) findViewById(R.id.register_frame);
        register_frame.startAnimation(AnimationUtils.loadAnimation(RegisterActivity.this,
                android.R.anim.slide_in_left | android.R.anim.fade_in));
        spinner = findViewById(R.id.spinnerBlood);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        etFullName = (EditText) findViewById(R.id.etFullName);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAddress = (EditText) findViewById(R.id.etAddress);
        aadhar_et = (EditText) findViewById(R.id.aadhar_et);
        textView = findViewById(R.id.txtLogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(register);
            }
        });


        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etFullName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    etFullName.setError("Invalid Name");
                }
                email = etEmail.getText().toString().trim();

                if (!isValidEmail(email)) {
                    etEmail.setError("Invalid Email");
                }
                number = etMobile.getText().toString().trim();
                if (!isValidNumber(number)) {
                    etMobile.setError("Invalid Number");
                }
                pincode = etAddress.getText().toString().trim();
                if (!isValidPincode(pincode)) {
                    etAddress.setError("Invalid Pincode");
                }
                aadhar = aadhar_et.getText().toString();
                bloodgroup = spinner.getSelectedItem().toString();
                password = etPassword.getText().toString().trim();
              /*  if (!isValidPassword(password)) {
                    Toast.makeText(RegisterActivity.this, "Password Should be Minimum 6 Letters", Toast.LENGTH_SHORT).show();
                }*/


                saveDate();

            }
        });
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }

    }

    private boolean isValidEmail(String Emailid) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Emailid);
        return matcher.matches();
    }

    public boolean isValidNumber(String num) {
        if (num.length() == 10) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String pass) {
        if (pass.length() >= 6) {
            return true;
        }
        return false;
    }

    public boolean isValidPincode(String pin) {
        if (pin.length() == 6) {
            return true;
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
    private void saveDate() {


        if (name.length() == 0) {
            showalert("Please enter username should not be empty", RegisterActivity.this);
        } else if (aadhar_et.getText().toString().length() ==0 || aadhar_et.getText().toString().length() <12){
            showalert("Please enter valid Aadhar Number", RegisterActivity.this);

        }else if (email.length() == 0) {
            showalert("Please enter email should not be empty", RegisterActivity.this);


        } else if (!isValidEmail(email)) {
            showalert("Please enter valid Email - Id ", RegisterActivity.this);

        } else if (number.length() == 0) {
            showalert("Please enter phone number should no be empty", RegisterActivity.this);

        } else if (number.length() < 9 || number.length() > 10) {
            showalert("Please enter valid phone number ", RegisterActivity.this);

        } else if (password.length() == 0) {
            showalert("Please enter password number should no be empty", RegisterActivity.this);
        } else if (password.length() < 5) {
            showalert("Please enter password should no be < 6 characters", RegisterActivity.this);

        } else if (bloodgroup.equals("Select Your Blood Group")) {
            showalert("Please select blood group", RegisterActivity.this);

        } else if (pincode.length() == 0) {
            showalert("Please enter pincode should no be empty", RegisterActivity.this);

        } else if (pincode.length() < 6 || pincode.length() > 6) {
            showalert("Please enter valid pincode ", RegisterActivity.this);

        } else {
           /* if (!name.isEmpty() && !pincode.isEmpty() && isValidPincode(pincode) && !email.isEmpty() && !isValidEmail(email) && !number.isEmpty() && isValidNumber(number)
                    && !password.isEmpty() && isValidPassword(password) && !bloodgroup.equals("Select Your Blood Group")) {
*/
            GPSTracker gpsTracker = new GPSTracker(RegisterActivity.this);
            Location locatio = gpsTracker.getLocation();
            Double lat = 0.0, lat_long = 0.0;
            if (locatio != null) {
                lat = locatio.getLatitude();
                lat_long = locatio.getLongitude();
            }
            String id = myRef.push().getKey();
            Details details = new Details(id, name, email, number, password, bloodgroup, pincode,aadhar,lat,lat_long);
            myRef.child(id).setValue(details);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                //finish();
                                showalert_success("Successfully Registered in Blood Bank ", RegisterActivity.this);
                                SharedPreferences.Editor outlet = RegisterActivity.this.getSharedPreferences("OUTLET", RegisterActivity.this.MODE_PRIVATE).edit();
                                outlet.putString("username", "Existing");
                                outlet.commit();

                                Random ran = new Random();
                                int x = ran.nextInt(98356) + 5;
                                String reg = String.valueOf(x);
                                List<String> toEmailList = Arrays.asList(email.toString().split("\\s*,\\s*"));
                                new SendMailTask(RegisterActivity.this).execute("texvndinesh@gmail.com",
                                        "bunty4444", toEmailList,
                                        "BloodDonation ", "<!DOCTYPE html>\n" +
                                                "<html>\n" +
                                                "<body style=\"color:#000000\">\n" +
                                                " \n" +
                                                "<p ><b>Dear  Applicant  " + name.toString() + " Garu,</b></p>" +
                                                " \n" +
                                                "<p>Your Blood Donation Request was successfully registered vide Verification No  : </p>" + reg +
                                                "<p> Thanks For Choosing Us. </p>\n" +
                                                "<p> Regards </p><p>\nBloodDonation Team\n</p><p>Visakhapatnam District.</p>\n" +
                                                " \n" +
                                                "</body>\n" +
                                                "</html>\n"
                                );
                                SharedPreferences.Editor otp = RegisterActivity.this.getSharedPreferences("OTP", RegisterActivity.this.MODE_PRIVATE).edit();
                                otp.putString("otp",reg );
                                otp.putString("verified","not" );
                                otp.commit();

                            } else {
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                showalert_success("Sorry Email Already Exists !!", RegisterActivity.this);
                                // Toast.makeText(RegisterActivity.this, "Email Already Exists.",
                                //        Toast.LENGTH_SHORT).show();

                            }


                        }
                    });


            //  }
        }
       /* else {
            Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
        }*/
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

    public static void showalert(String alert_msg, Context context) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Registration Form Validation ");
        alertDialogBuilder.setIcon(R.drawable.splash_icon);

        // set dialog message
        alertDialogBuilder.setMessage(alert_msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // create alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static void showalert_success(String alert_msg, Context context) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Registration Form Validation ");
        alertDialogBuilder.setIcon(R.drawable.splash_icon);

        // set dialog message
        alertDialogBuilder.setMessage(alert_msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // create alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }


}

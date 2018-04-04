package com.bloodbank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bloodbank.ChatMessage.ChatActivity;
import com.bloodbank.DonarDetails.DonarDetailsActivity;
import com.bloodbank.EventActivities.EventsActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout donarLayout, chatLayout, eventsLayout, linksLayout, tollfree_ll;
    ImageView link_img, event_img, chat_img, donor_img, tollfree_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        donarLayout = (LinearLayout) findViewById(R.id.donarLayout);
        chatLayout = (LinearLayout) findViewById(R.id.chatLayout);
        eventsLayout = (LinearLayout) findViewById(R.id.eventsLayout);
        linksLayout = (LinearLayout) findViewById(R.id.donarLinks);
        tollfree_ll = (LinearLayout) findViewById(R.id.tollfree_ll);

        donor_img = (ImageView) findViewById(R.id.donor_img);
        chat_img = (ImageView) findViewById(R.id.chat_img);
        event_img = (ImageView) findViewById(R.id.event_img);
        link_img = (ImageView) findViewById(R.id.link_img);
        tollfree_img = (ImageView) findViewById(R.id.tollfree_img);

        donarLayout.setOnClickListener(this);
        chatLayout.setOnClickListener(this);
        eventsLayout.setOnClickListener(this);
        linksLayout.setOnClickListener(this);
        tollfree_ll.setOnClickListener(this);

        event_img.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoomout));
        chat_img.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoomout));
        link_img.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoomout));
        donor_img.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoomout));


    }

    public void verifyme() {
        SharedPreferences vendorname = HomeActivity.this.
                getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
        if (vendorname.getString("verified", "").equals("verified")) {

        } else {
            verification("Must and should need to verify your mail id", HomeActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.donarLayout:
                SharedPreferences vendorname = HomeActivity.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (vendorname.getString("verified", "").equals("Verified")) {
                    startActivity(new Intent(this, DonarDetailsActivity.class));
                } else {
                    verification("Must and should need to verify your mail id", HomeActivity.this);
                }

                break;

            case R.id.chatLayout:
                SharedPreferences chat = HomeActivity.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (chat.getString("verified", "").equals("Verified")) {
                    startActivity(new Intent(this, ChatActivity.class));
                } else {
                    verification("Must and should need to verify your mail id", HomeActivity.this);
                }

                break;

            case R.id.eventsLayout:
                SharedPreferences event = HomeActivity.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (event.getString("verified", "").equals("Verified")) {
                    startActivity(new Intent(this, EventsActivity.class));
                } else {
                    verification("Must and should need to verify your mail id", HomeActivity.this);
                }

                break;
            case R.id.donarLinks:
                SharedPreferences donar = HomeActivity.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (donar.getString("verified", "").equals("Verified")) {
                    startActivity(new Intent(this, LinksActivity.class));
                } else {
                    verification("Must and should need to verify your mail id", HomeActivity.this);
                }

                break;
            case R.id.tollfree_ll:
                SharedPreferences tol = HomeActivity.this.
                        getSharedPreferences("OTP", SplashScreenActivity.MODE_PRIVATE);
                if (tol.getString("verified", "").equals("Verified")) {
                    startActivity(new Intent(this, Tollfree.class));
                } else {
                    verification("Must and should need to verify your mail id", HomeActivity.this);
                }

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor outlet = HomeActivity.this.getSharedPreferences("OUTLET", HomeActivity.this.MODE_PRIVATE).edit();
                outlet.putString("username", "signout");
                outlet.commit();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(HomeActivity.this);
        } else {
            builder = new AlertDialog.Builder(HomeActivity.this);
        }
        builder.setTitle("Confirm Exit ")
                .setMessage("Do you want to exit app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void verification(String alert_msg, Context context) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Verification");
        // alertDialogBuilder.setMessage("Must and should need to verify your mail id ");
        alertDialogBuilder.setIcon(R.drawable.splash_icon);

        // set dialog message
        alertDialogBuilder.setMessage(alert_msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent verification = new Intent(HomeActivity.this, Verification.class);
                        startActivity(verification);
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

package com.bloodbank.EventActivities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodbank.MapsActivity;
import com.bloodbank.R;
import com.bloodbank.SplashScreenActivity;
import com.bloodbank.Tollfree;

import java.util.Calendar;
import java.util.List;

/**
 * Created by USER on 2/20/2018.
 */

public class EventsAdapter extends ArrayAdapter<EventItem> {
    private Activity context;
    private List<EventItem> eventList;
    private ImageView imageView;
    EventItem  event;

    public EventsAdapter(@NonNull Activity context, List<EventItem> eventList) {
        super(context, R.layout.event_item, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listviewitem = layoutInflater.inflate(R.layout.event_item, null, true);
        TextView textView = listviewitem.findViewById(R.id.txtDate);
        TextView textView1 = listviewitem.findViewById(R.id.txtLocation);
        TextView textView2 = listviewitem.findViewById(R.id.txtEvent);
        imageView = listviewitem.findViewById(R.id.imageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, eventList.get(position).getDate1())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, eventList.get(position).getDate1())
                .putExtra(CalendarContract.Events.TITLE, eventList.get(position).getName())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, eventList.get(position).getLocation())
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "");
                context.startActivity(intent);
            }
        });
        ImageView map_img = listviewitem.findViewById(R.id.map_img);
        map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(context, MapsActivity.class);
                map.putExtra("latitude",eventList.get(position).getLat().toString());
                map.putExtra("longitude",eventList.get(position).getLongitude().toString());
                context.startActivity(map);
            }
        });
        map_img.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoomout));

        event =eventList.get(position);
        textView.setText(event.getDate1());
        textView1.setText( event.getLocation());
        textView2.setText(event.getName());
        return  listviewitem;
    }

}

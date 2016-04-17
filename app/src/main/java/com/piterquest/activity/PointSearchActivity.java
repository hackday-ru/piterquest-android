package com.piterquest.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.piterquest.R;
import com.piterquest.data.DataTransition;
import com.piterquest.data.QuestPoint;
import com.squareup.picasso.Picasso;

// Google location-related imports; are used to determine location
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class PointSearchActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener {
    private static final float DISTANCE_THRESHOLD = 20.0f;

    // these should be saved and restored
    private QuestPoint questPoint;
    private Location pointLocation;

    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_search);

        // assigning controls
        final TextView tvHint = (TextView) findViewById(R.id.textview_point_hint);
        final ImageView imgHint = (ImageView) findViewById(R.id.image_point_hint);

        if (savedInstanceState == null) {
            // extracting data from intent sent by Task and setting up header
            Intent intent = getIntent();
            questPoint = intent.getParcelableExtra(DataTransition.QUEST_POINT);
            pointLocation = new Location(LocationManager.GPS_PROVIDER);
            pointLocation.setLatitude(questPoint.getDest_lat());
            pointLocation.setLongitude(questPoint.getDest_long());
        } else {
            // extracting data from saved instance state
            questPoint = savedInstanceState.getParcelable(DataTransition.QUEST_POINT);
            pointLocation = new Location(LocationManager.GPS_PROVIDER);
            pointLocation.setLatitude(questPoint.getDest_lat());
            pointLocation.setLongitude(questPoint.getDest_long());
        }

        // setting up controls
        if (tvHint != null && questPoint != null) {
            tvHint.setText(questPoint.getHintText());
        }
        Picasso.with(PointSearchActivity.this).load(questPoint.getHintImage()).into(imgHint);

        final Button button = (Button) findViewById(R.id.button_see_map);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PointSearchActivity.this, MapActivity.class);
                intent.putExtra(DataTransition.QUEST_POINT, questPoint);
                startActivity(intent);
            }
        });

        buildGoogleApiClient();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DataTransition.QUEST_POINT, questPoint);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        questPoint = inState.getParcelable(DataTransition.QUEST_POINT);
        pointLocation = new Location(LocationManager.GPS_PROVIDER);
        pointLocation.setLatitude(questPoint.getDest_lat());
        pointLocation.setLongitude(questPoint.getDest_long());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            tryAbort();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        tryAbort();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    public void buttonClickCheckLocation(View view) {
        checkLocation();
    }

    private void tryAbort() {
        QuestAbortPopup.createAndShow(PointSearchActivity.this);
    }

    // --------------------------------------------------------------------------------------------
    // location-related methods

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //checkLocation();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    // copypasted code; shouldn't really be here
    public void requestPermissionsIfNeeded() {
        // if has permissions, return
        if ((ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)) return;
        // if on Android >= 6.0, ask for permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            }, 0);
        }
    }

    /**
     * Checks whether user has reached the point, and moves one to the task, if yes.
     */
    private void checkLocation() {
        try {
            // checking for permissions
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(PointSearchActivity.this, R.string.cant_work_without_location,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            // updating last known location
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {
                if (lastLocation.distanceTo(pointLocation) > DISTANCE_THRESHOLD) {
                    // user is at the place
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(PointSearchActivity.this, R.string.not_there_yet, Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

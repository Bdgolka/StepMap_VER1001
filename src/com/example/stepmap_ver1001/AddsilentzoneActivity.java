package com.example.stepmap_ver1001;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AddsilentzoneActivity extends Activity implements
		OnSeekBarChangeListener, LocationListener {

	public static final String TAG = "myLogs";

	private SeekBar sbRadius;
	private EditText etNameofZone;
	private TextView tvRadiusInfo;

	// Location Manager
	private LocationManager locationManager;
	private EditText etLatitude;
	private EditText etLongtitude;

	// Class extends AsyncTask to get coordinates in BAckground
	GetGPS gGPS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addsilentzone);

		sbRadius = (SeekBar) findViewById(R.id.sbRadius);
		sbRadius.setOnSeekBarChangeListener(this);
		tvRadiusInfo = (TextView) findViewById(R.id.tvRadiusInfo);

		etNameofZone = (EditText) findViewById(R.id.etNameofZone);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, this);

		etLatitude = (EditText) findViewById(R.id.etLatitude);
		etLongtitude = (EditText) findViewById(R.id.etLongtitude);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addsilentzone, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		tvRadiusInfo.setText(getString(R.string.radius) + "(" + progress
				+ "/100 m) :");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			gGPS = new GetGPS();
			gGPS.execute(location);

			// etLatitude.setText((int) location.getLatitude());
			// etLongtitude.setText((int) location.getLongitude());

			// Log.d(TAG, "Широта=" + location.getLatitude());
			// Log.d(TAG, "Долгота=" + location.getLongitude());
		}

	}

	// Class for getting coordinates in the Background
	class GetGPS extends AsyncTask<Location, Void, Void> {

		@Override
		protected Void doInBackground(Location... params) {
			for (Location location : params) {
				
				String lat = Double.toString(location.getLatitude());
				etLatitude.setText(lat);
				// etLatitude.setText((int)location.getLatitude());
				// etLongtitude.setText((int) location.getLongitude());

				Log.d(TAG, "Широта=" + location.getLatitude());
				Log.d(TAG, "Долгота=" + location.getLongitude());
			}
			return null;
		}

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
}

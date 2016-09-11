package com.facci.gpsdmgc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivityDMGC extends AppCompatActivity {
    LocationManager locManager;

    private Double latitudDMGC;
    private Double longitudDMGC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_dmgc);
        //Inicializar LocationManager
        locManager=(LocationManager) getSystemService(LOCATION_SERVICE);

        //Obtener lista de proveedores
        List<String> listaProviders=locManager.getAllProviders();

        //Obtener el primer proveedor de la lista
        LocationProvider provider=locManager.getProvider(listaProviders.get(0));


    }
    public void ActualizarLatLongDMGCClick(View v){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

        }

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
    }

    private final LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitudDMGC=location.getLongitude();
            latitudDMGC=location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitudDMGC=(EditText)findViewById(R.id.txtLongitudDMGC);
                    EditText txtLatitudDMGC=(EditText)findViewById(R.id.txtLatitudDMGC);

                    txtLatitudDMGC.setText(longitudDMGC+"");
                    txtLongitudDMGC.setText(String.valueOf(latitudDMGC));

                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}

package com.test.blebeamer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.util.Arrays;
import java.util.List;

import static com.test.blebeamer.MainActivity.CHANNEL_ID;
//import static com.wildanka.learnforegroundservice.App.CHANNEL_ID;

//import android.support.annotation.Nullable;
//import android.support.v4.app.NotificationCompat;

public class ExampleService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        PowerButtonBroadcastReceiver broadcastReceiver = new PowerButtonBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(); intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        if (broadcastReceiver != null) {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String inString = intent.getStringExtra("inputExtra");
        String sysId = intent.getStringExtra(MainActivity.sysIdKey);
        Log.i("myid","system id recived "+ sysId);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("BleBeamers")
                .setContentText(inString)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

//        if we don't start our service in foreground, our service will be killed in about one minutes.
//        for example, just try to comment startForeground line below and see on the running services via developer options menu in your android emulator/smartphone
        startForeground(Notification.FLAG_ONGOING_EVENT, notification);
        startBroadcast(sysId);
        return START_STICKY; //not sticky so this service will only create one service if there is a change or modification
    }

    public  void startBroadcast(String sysid){
        Log.d("test_ble","starting broadcast function");
        Beacon beacon = new Beacon.Builder()
                .setId1(sysid)
                .setId2("1")
//                .setId3("2")
                .setManufacturer(0x0118) // Radius Networks.  Change this for other beacon layouts
                .setTxPower(-59)
                .setDataFields(Arrays.asList(new Long[] {0l})) // Remove this for beacon layouts without d: fields
                .build();
// Change the layout below for other beacon types
        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout(  BeaconParser.EDDYSTONE_UID_LAYOUT);
        BeaconTransmitter beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {

            @Override
            public void onStartFailure(int errorCode) {
                Log.e("test_ble", "Advertisement start failed with code: "+errorCode);
            }

            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                Log.i("test_ble", "Advertisement start succeeded.");
                Log.d("test_ble", settingsInEffect.toString());
            }
        });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //this is mandatory, but this is only for binding purpose so other components can communicate by binding, but the method that matters
    // is onStartCommand()
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
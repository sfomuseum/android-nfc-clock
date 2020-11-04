package org.sfomuseum.nfcclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = this.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
            Log.i(TAG, "Missing HCE functionality.");
        }

        Context context = this;
        Timer t = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Date dt = Calendar.getInstance().getTime();
                Log.d(TAG, "Set time as " + dt.toString());

                TextView t = findViewById(R.id.current_time);

                if (t != null) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            t.setText(dt.toString());
                        }});
                }

                if (pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
                    Intent intent = new Intent(context, CardService.class);
                    intent.putExtra("ndefMessage", dt.toString());
                    // Log.d("BROADCAST", intent.toString());
                    startService(intent);
                }
            }

        };

        t.scheduleAtFixedRate(task, 0, 5000);    }
}
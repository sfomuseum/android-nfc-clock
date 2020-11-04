package org.sfomuseum.nfcclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        PackageManager pm = this.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
            Log.i("MAIN", "NO HCE");
            return;
        }
        */

        Context context = this;

        Timer t = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Date dt = Calendar.getInstance().getTime();
                Log.d("CLOCK", dt.toString());

                TextView t = findViewById(R.id.current_time);

                if (t != null) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            t.setText(dt.toString());
                        }});
                }

                /*
                Intent intent = new Intent(context, KardService.class);
                intent.putExtra("ndefMessage", dt.toString());

                Log.i("BROADCAST", intent.toString());
                startService(intent);
                 */
            }

        };

        t.scheduleAtFixedRate(task, 0, 1000);    }
}
package com.huawei.hmspush;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PushDemoLog";

    Button pushNotiy;
    String pushtoken;
    TextView logMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushNotiy = findViewById(R.id.PsuhNotiy);
        logMessage = findViewById(R.id.PsuhNotiy);

        pushNotiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getToken();
            }
        });


    }

    private void getToken() {
        Log.i(TAG, "get token: begin");

        // get token
        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(MainActivity.this).getString("client/app_id");
                    pushtoken = HmsInstanceId.getInstance(MainActivity.this).getToken(appId, "HCM");
                    if(!TextUtils.isEmpty(pushtoken)) {
                        Log.i(TAG, "get token:" + pushtoken);
                        showLog(pushtoken);
                    }
                } catch (Exception e) {
                    Log.i(TAG,"getToken failed, " + e);

                }
            }
        }.start();
    }

    private void showLog(final String log) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View tvView = findViewById(R.id.LogMessage);
                if (tvView instanceof TextView) {
                    ((TextView) tvView).setText(log);
                    Toast.makeText(MainActivity.this, pushtoken, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
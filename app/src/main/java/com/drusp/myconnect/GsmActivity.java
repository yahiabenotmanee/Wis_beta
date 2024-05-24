package com.drusp.myconnect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GsmActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private TextView messageTextView;
    private SMSReceiver smsReceiver;

    LinearLayout layoutPower,layoutRelaod;
    String textmessageON="your system is on",textmessageOFF="your system is off";

    public String verify="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsm);

//        LinearLayout lyercontrol=findViewById(R.id.layer_control);
//        run_anim(lyercontrol);
//
//       // messageTextView = findViewById(R.id.text_status);
//
//       // Button btn_power=findViewById(R.id.btn_power);
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                run_anim2(lyercontrol);
////                if (checkSmsPermission()) {
////                    setupSmsReceiver();
////                } else {
////                    requestSmsPermission();
////                }
//            }
//        },5000);
////
////
////
////        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn_switching =findViewById(R.id.button_switching);
////        btn_switching.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(getBaseContext(), MainActivity.class));
////            }
////        });
    }

    private boolean checkSmsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestSmsPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_REQUEST_CODE);
    }

    private void setupSmsReceiver() {
        smsReceiver = new SMSReceiver();
        IntentFilter intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(smsReceiver, intentFilter);

        // Register a content observer to listen for changes to the SMS inbox
      //  getContentResolver().registerContentObserver(Telephony.Sms.CONTENT_URI, true, new SMSContentObserver(new Handler()));
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupSmsReceiver();
            } else {
                Toast.makeText(this, "Permission SMS refusée. Impossible de lire les messages.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }

    private class SMSReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
                displayLastReceivedMessage();
            }
        }
    }

    private class SMSContentObserver extends ContentObserver {
        public SMSContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            displayLastReceivedMessage();
        }
    }

    private void displayLastReceivedMessage() {
       // layoutPower=findViewById(R.id.linearLayoutPower);
       // layoutRelaod=findViewById(R.id.linearLayoutRELOAD);

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, "date DESC");

        if (cursor != null && cursor.moveToFirst()) {
            String message = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            messageTextView.setText("" + message);


            if (message.equals("1")){
                layoutPower.setBackgroundResource(R.drawable.bachground_button_green);

            }
            if (message.equals("0")){
                layoutPower.setBackgroundResource(R.drawable.bachground_button_red);
            }

            cursor.close();

        } else {
            messageTextView.setText("Aucun message trouvé dans la boîte de réception.");
        }
    }


    void run_anim(View view){
        view.animate().alpha(1).setDuration(1000).translationY(430);
    }
    void run_anim2(View view){
        view.animate().alpha(1).setDuration(1600).translationY(0);
    }
}
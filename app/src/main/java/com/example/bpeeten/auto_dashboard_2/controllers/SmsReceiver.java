package com.example.bpeeten.auto_dashboard_2.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.activities.SmsActivity;

/**
 * Created by bpeeten on 12/02/18.
 */

public class SmsReceiver extends BroadcastReceiver {
    private String TAG = SmsReceiver.class.getSimpleName();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";


    public SmsReceiver() {
    }

    /*@Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String smsMessage = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // For every SMS message received
            for (int i=0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                smsMessage += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                // Fetch the text message
                smsMessage += msgs[i].getMessageBody().toString();
                // Newline 🙂
                smsMessage += "\n";
            }

            // Display the entire SMS Message
            Log.d(TAG, smsMessage);
            SmsActivity smsActivity = SmsActivity.instance();
            smsActivity.updateInbox(smsMessage);
        }
    } */

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                // prevent any other broadcast receivers from receiving broadcast
                abortBroadcast();
                SmsActivity smsActivity = SmsActivity.instance();
                smsActivity.updateInbox(message);
            }
        }
    }


}

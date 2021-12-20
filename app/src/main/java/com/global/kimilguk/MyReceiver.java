package com.global.kimilguk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        //리시버에서 인텐트 액션 이름으로 구분가능
        if(null != intent && intent.getAction().equals("shake.detector")) {
            Log.d("My리시버", "onReceive() 호출됨");
            Intent DialogIntent = new Intent(context, DialogActivity.class);
            //DialogIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(DialogIntent);
        }
    }
}
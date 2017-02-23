package com.colorful.game;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.view.View;

import com.colorful.alipayservice.IService;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private IService iService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setAction("com.colorful.alipay");//
    }

    public void bind(View view){
        bindService(intent,new conn(),BIND_AUTO_CREATE);
    }

    private class conn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService = IService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void call(View view){
        try {
            iService.callAlipay();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

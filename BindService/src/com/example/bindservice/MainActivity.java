package com.example.bindservice;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button bind,unBind,getCount;
	TextView countText;
	BindService.MyBinder binder;
	private ServiceConnection conn=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO 自动生成的方法存根
			System.out.println("service connected");
			binder=(BindService.MyBinder)service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO 自动生成的方法存根
			System.out.println("service disconnected");
		}
		
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bind =(Button)findViewById(R.id.bind);
        Button unBind=(Button)findViewById(R.id.unbind);
        Button getCount=(Button)findViewById(R.id.getcount);
        final TextView countText=(TextView)findViewById(R.id.textView1);
        
        final Intent intent=new Intent();
        intent.setAction("com.example.BIND_SERVICE");
        bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				bindService(intent,conn,Service.BIND_AUTO_CREATE);
				
			}
		});
        unBind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				unbindService(conn);
			}
		});
        getCount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			countText.setText(String.valueOf(binder.getCount()));
			System.out.println("getcount = "+ binder.getCount());
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

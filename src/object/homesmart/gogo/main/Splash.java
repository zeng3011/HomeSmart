package object.homesmart.gogo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.homesmart.R;

public class Splash extends Activity{
	
	 private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent(Splash.this,MainActivity.class);
			startActivity(intent);
			Splash.this.finish();	//关闭界面，避免退出的时候还会再次显示
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_up);
		mHandler.sendEmptyMessageDelayed(0, 2000);
	}
}

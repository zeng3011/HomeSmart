package object.homesmart.gogo.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import object.homesmart.gogo.db.SQLiteDemo;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.homesmart.R;

/**
 * @author zeng
 * 中上屏幕设置成fragment，下方三个按钮：主界面，最爱，设置，为固定按钮
 */
public class MainActivity extends Activity implements OnClickListener {

	private FragmentManager manager;
	private FragmentTransaction transaction;
	private MainFragment mainFragment;
	private SetUp setUp;
	private Button back_home;
	private Button favorite;
	private Button set_up;
	private static boolean isExit = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        changeFragment();
    }
    
	private void changeFragment() {
		//实例化需要跳转的页面：主界面，设置
		mainFragment = new MainFragment();
		setUp = new SetUp();
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		//获取从control和Conditional界面传过来的值，用于跳转界面
		Intent intent = getIntent();
	    int control_intent = intent.getIntExtra("control_intent",1);
	    int air_condition_intent = intent.getIntExtra("air_condition_intent", 1);
	    int favorite_intent = intent.getIntExtra("favorite", 1);
		//如果这两个传过来的值都是1，则显示在主界面，如果为传过来的值，则跳转到设置界面（activity跳转到fragment的方式）
		if(control_intent == 1 
				|| air_condition_intent == 1
				|| favorite_intent == 1){
			transaction.add(R.id.fragmen_content, mainFragment);
		}
		if(control_intent == 0x7f02000e
				|| air_condition_intent == 0x7f030003
				|| favorite_intent == 0x7f090005){
			transaction.replace(R.id.fragmen_content, setUp);
		}
		transaction.commit();
	}
	
	private void initView() {
		back_home = (Button) findViewById(R.id.back_home);
		favorite = (Button) findViewById(R.id.favorite);
		set_up = (Button) findViewById(R.id.set_up);
		back_home.setOnClickListener(this);
		favorite.setOnClickListener(this);
		set_up.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.back_home:
			transaction.replace(R.id.fragmen_content, mainFragment);
			break;

		case R.id.favorite:
//			String homeName = SmartHome.showTV.getText().toString();
//			SharedPreferences preferences = getSharedPreferences("value", MODE_PRIVATE);
//			Editor editor = preferences.edit();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			BitmapFactory.decodeResource(getResources(), R.drawable.one).compress(
//					CompressFormat.PNG, 50, baos);
//			String base64 = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
//			editor.putString("image", base64);
//			editor.putString("name", homeName);
//			editor.commit();
			break;
			
		case R.id.set_up:
			transaction.replace(R.id.fragmen_content, setUp);
			break;
			
		default:
			break;
		}
		transaction.commit();
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			isExit = false;
		};
	};
	
	//点击后台物理键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			exitAPP();
			return true;
		}else{
			return super.onKeyDown(keyCode, event);
		}
	}

	//点击两次后台物理键退出程序
	private void exitAPP() {
		if(!isExit){
			isExit = true;
			Toast.makeText(this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
			mHandler.sendEmptyMessageDelayed(0, 2000);
		}else{
			this.finish();
		}
	}
}

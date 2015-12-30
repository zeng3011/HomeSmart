package object.homesmart.gogo.main;

import com.example.homesmart.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author zeng
 *	房间设备/the room device.
 */
public class Control extends Activity implements OnClickListener{

	private LinearLayout air_condition;
	private Button up_back;
	private Button back_home;
	private Button set_up;
	private TextView room_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.control);
		initView();
	}

	private void initView() {
		air_condition = (LinearLayout) findViewById(R.id.air_condition);
		up_back = (Button) findViewById(R.id.up_back);
		back_home = (Button) findViewById(R.id.back_home);
		set_up = (Button) findViewById(R.id.set_up);
		room_name = (TextView) findViewById(R.id.room_name);
		air_condition.setOnClickListener(this);	
		up_back.setOnClickListener(this);	
		back_home.setOnClickListener(this);	
		set_up.setOnClickListener(this);
		room_name.setText(SmartHome.showTV.getText().toString());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//air_condition
		case R.id.air_condition:
			Intent air_condition_intent = new Intent(Control.this,Conditional.class);
			startActivity(air_condition_intent);
			break;
			
		//返回上一级/cdup
		case R.id.up_back:
			finish();
			break;

		//返回主界面/Back to the main interface
		case R.id.back_home:
			Intent intent = new Intent(Control.this,MainActivity.class);
			startActivity(intent);
			break;

		//设置一个tag，然后跳转到主界面，再由主界面判断跳转到设置界面（由activity跳转到fragment的方式）
		case R.id.set_up:
			Intent set_up_intent = new Intent();
			set_up_intent.setClass(Control.this, MainActivity.class);
			set_up_intent.putExtra("control_intent", 0x7f02000e);
			startActivity(set_up_intent);
			break;

		default:
			break;
		}
	}
}

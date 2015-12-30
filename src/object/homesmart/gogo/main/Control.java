package object.homesmart.gogo.main;

import com.example.homesmart.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author zeng
 *	房间设备/the room device.
 */
public class Control extends Activity implements OnClickListener{

	private LinearLayout air_condition;
	private Button up_back;
	private Button back_home;
	private Button set_up;
	private TextView room_name;
	private CheckBox lamp_check,lamplight_check,heating_check;
	private int lampChecked = 0;
	private int lamplightChecked = 0;
	private int heatingChecked = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.control);
		initView();
		checkListener(); 		//三个check的点击事件集
		lampCheckChange();		//改变吸顶灯的check背景样式
		lamplightCheckChange();	//改变可调灯光的check背景样式
		heatingCheckChange();	//改变地暖的check背景样式
	}

	private void initView() {
		air_condition = (LinearLayout) findViewById(R.id.air_condition);
		up_back = (Button) findViewById(R.id.up_back);
		back_home = (Button) findViewById(R.id.back_home);
		set_up = (Button) findViewById(R.id.set_up);
		room_name = (TextView) findViewById(R.id.room_name);
		lamp_check = (CheckBox) findViewById(R.id.lamp_check);
		lamplight_check = (CheckBox) findViewById(R.id.lamplight_check);
		heating_check = (CheckBox) findViewById(R.id.heating_check);
		air_condition.setOnClickListener(this);	
		up_back.setOnClickListener(this);	
		back_home.setOnClickListener(this);	
		set_up.setOnClickListener(this);
		room_name.setText(SmartHome.showTV.getText().toString());	//界面的title
	}
	
	//三个check的点击事件集
	private void checkListener() {
		//吸顶灯的check点击事件
		lamp_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				SharedPreferences preferences = getSharedPreferences("lamp_check", MODE_PRIVATE);
				Editor editor = preferences.edit();
				if(lampChecked == 1){
					lampChecked = 0;
					editor.putInt("lamp_check", 0);
					editor.commit();
					return ;
				}
				if(lampChecked == 0){
					lampChecked = 1;
					editor.putInt("lamp_check", 1);
					editor.commit();
				}
			}
		});
		
		//可调灯光的check点击事件
		lamplight_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				SharedPreferences preferences = getSharedPreferences("lamplight_check", MODE_PRIVATE);
				Editor editor = preferences.edit();
				if(lamplightChecked == 1){
					lamplightChecked = 0;
					editor.putInt("lamplight_check", 0);
					editor.commit();
					Log.i("check", "lamplightChecked = 0");
					return ;
				}
				if(lamplightChecked == 0){
					lamplightChecked = 1;
					editor.putInt("lamplight_check", 1);
					editor.commit();
					Log.i("check", "lamplightChecked = 1");
				}
			}
		});

		//地暖的check点击事件
		heating_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				SharedPreferences preferences = getSharedPreferences("heating_check", MODE_PRIVATE);
				Editor editor = preferences.edit();
				if(heatingChecked == 1){
					heatingChecked = 0;
					editor.putInt("heating_check", 0);
					editor.commit();
					Log.i("check", "heatingChecked = 0");
					return ;
				}
				if(heatingChecked == 0){
					heatingChecked = 1;
					editor.putInt("heating_check", 1);
					editor.commit();
					Log.i("check", "heatingChecked = 1");
				}
			}
		});
	}

	//改变吸顶灯的check背景样式
	private void lampCheckChange() {
		SharedPreferences preferences = getSharedPreferences("lamp_check", MODE_PRIVATE);
		if(preferences.getInt("lamp_check", 0) == 1){
			lamp_check.setChecked(true);
			return ;
		}
		if(preferences.getInt("lamp_check", 0) == 0){
			lamp_check.setChecked(false);
		}
	}
	
	//改变可调灯光的check背景样式
	private void lamplightCheckChange() {
		SharedPreferences preferences = getSharedPreferences("lamplight_check", MODE_PRIVATE);
		if(preferences.getInt("lamplight_check", 0) == 1){
			lamplight_check.setChecked(true);
			Log.i("check", "lamplight_check.setChecked(true)");
			return ;
		}
		if(preferences.getInt("lamplight_check", 0) == 0){
			lamplight_check.setChecked(false);
			Log.i("check", "lamplight_check.setChecked(false)");
		}
	}

	//改变地暖的check背景样式
	private void heatingCheckChange() {
		SharedPreferences preference = getSharedPreferences("heating_check", MODE_PRIVATE);
		if(preference.getInt("heating_check", 0) == 1){
			heating_check.setChecked(true);
			Log.i("check", "heating_check.setChecked(true)");
			return ;
		}
		if(preference.getInt("heating_check", 0) == 0){
			heating_check.setChecked(false);
			Log.i("check", "heating_check.setChecked(false)");
		}
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

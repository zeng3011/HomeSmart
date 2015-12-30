package object.homesmart.gogo.main;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.homesmart.R;

/**
 * @author zeng
 * 主界面显示的中上部分
 */
public class MainFragment extends Fragment implements OnClickListener{

	private ImageView smart_home;
	private ImageView main_alarm;
	private ImageView main_notification;
	private Button collect1;
	private Button collect2;
	private Button collect3;
	private SmartHome smartHome;
	private Alarm alarm;
	private Notification notification;
	private View main;
	private String getID;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		main = inflater.inflate(R.layout.main_fragment, container, false);
		initView();
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"gatewayID", getActivity().MODE_PRIVATE);
		getID = preferences.getString("id", "");
		return main;
	}
	
	private void initView() {
		smart_home = (ImageView) main.findViewById(R.id.smart_home);
		main_alarm = (ImageView) main.findViewById(R.id.main_alarm);
		main_notification = (ImageView) main.findViewById(R.id.main_notification);
		collect1 = (Button) main.findViewById(R.id.collect1);
		collect2 = (Button) main.findViewById(R.id.collect2);
		collect3 = (Button) main.findViewById(R.id.collect3);
		smart_home.setOnClickListener(this);
		main_alarm.setOnClickListener(this);
		main_notification.setOnClickListener(this);
		collect1.setOnClickListener(this);
		collect2.setOnClickListener(this);
		collect3.setOnClickListener(this);
		smartHome = new SmartHome();
		alarm = new Alarm();
		notification = new Notification();
	}

	@Override
	public void onClick(View v) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.smart_home:
			if(getID.equals("")){
				judgeGateway();
			}else{
				transaction.replace(R.id.fragmen_content, smartHome);
			}
			break;
			
		case R.id.main_alarm:
			if(getID.equals("")){
				judgeGateway();
			}else{
				transaction.replace(R.id.fragmen_content, alarm);
			}
			break;
			
		case R.id.main_notification:
			if(getID.equals("")){
				judgeGateway();
			}else{
				transaction.replace(R.id.fragmen_content, notification);
			}
			break;
			
		case R.id.collect1:
			Intent intent = new Intent(getActivity(),Favorite.class);
			startActivity(intent);
			break;
			
		case R.id.collect2:
			reminder();
			
			break;
			
		case R.id.collect3:
			reminder();
			
			break;
		default:
			break;
		}
		transaction.commit();
	}
	
	//提示框
	private void reminder() {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setTitle(R.string.warm_prompt);
		builder.setMessage(R.string.no_post);
		builder.setPositiveButton(R.string.confirm, null);
		builder.setNegativeButton(R.string.cancel, null);
		builder.show();
	}
	
	//判断网关
	public void judgeGateway(){
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setTitle(R.string.warm_prompt);
		builder.setMessage(R.string.gateway);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setPositiveButton(R.string.confirm, null);
		builder.setCancelable(false);	//点击屏幕其他不属于提示框的部分，提示框不消失
		builder.show();
	}
}

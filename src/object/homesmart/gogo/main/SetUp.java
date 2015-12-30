package object.homesmart.gogo.main;

import object.homesmart.gogo.utils.CustomProgress;

import com.example.homesmart.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zeng
 *	设置界面
 */
public class SetUp extends Fragment implements OnClickListener{
	
	private Button up_back;
	private TextView gateway_id;
	public static TextView versions;
	private LinearLayout about;
	private LinearLayout gateway_register;
	private LinearLayout gateway_edit;
	private LinearLayout DS;
	private MainFragment mainFragment;
	private About aboutFragment;
	private GatewayRegister gatewayRegister;
	private GatewayEdit gatewayEdit;
	private View setUp;
	private Boolean DataUpdata = true;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setUp = inflater.inflate(R.layout.set_up, container, false);
		initView();
		SharedPreferences preferences = getActivity()
				.getSharedPreferences("gatewayID", getActivity().MODE_PRIVATE);
		gateway_id.setText(preferences.getString("id", ""));
		return setUp;
	}
	
	private void initView() {
		up_back = (Button) setUp.findViewById(R.id.up_back);
		gateway_id = (TextView) setUp.findViewById(R.id.gateway_id);
		versions = (TextView) setUp.findViewById(R.id.versions);
		about = (LinearLayout) setUp.findViewById(R.id.about);
		gateway_register = (LinearLayout) setUp.findViewById(R.id.gateway_register);
		gateway_edit = (LinearLayout) setUp.findViewById(R.id.gateway_edit);
		DS = (LinearLayout) setUp.findViewById(R.id.DS);
		up_back.setOnClickListener(this);
		about.setOnClickListener(this);
		gateway_register.setOnClickListener(this);
		gateway_edit.setOnClickListener(this);
		DS.setOnClickListener(this);
		//实例化需要切换的界面
		mainFragment = new MainFragment();
		aboutFragment = new About();
		gatewayRegister = new GatewayRegister();
		gatewayEdit = new GatewayEdit();
	}
	
	@Override
	public void onClick(View v) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.up_back:
			transaction.replace(R.id.fragmen_content, mainFragment);
			break;

		case R.id.about:
			transaction.replace(R.id.fragmen_content, aboutFragment);
			break;
			
		case R.id.gateway_register:
			transaction.replace(R.id.fragmen_content, gatewayRegister);
			break;
			
		case R.id.gateway_edit:
			transaction.replace(R.id.fragmen_content, gatewayEdit);
			break;
			
		case R.id.DS:
			CustomProgress.show(getActivity(), 
					getResources().getString(R.string.update_data), true, null);
			cancelThread();
			break;
		default:
			break;
		}
		transaction.commit();
	}
	
	//睡三秒，然后关闭提示框（同步数据完成，关闭提示框）
	public void cancelThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					CustomProgress.progress.cancel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

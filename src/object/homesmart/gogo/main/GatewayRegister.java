package object.homesmart.gogo.main;

import java.util.Map;

import object.homesmart.gogo.ZXing.CaptureActivity;
import object.homesmart.gogo.adapter.SearchListAdapter;
import object.homesmart.gogo.bean.GatewayBean;
import object.homesmart.gogo.content.ContentCommon;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesmart.R;

/**
 * @author zeng
 *	网关注册/GatewayRegister
 */
public class GatewayRegister extends Fragment implements OnClickListener{

	private MainFragment mainFragment;
	private Button up_back;
	private SetUp setUp;
	public static EditText register_id;
	private EditText register_user;
	private EditText register_pass;
	private Button register;
	private TextView reminder;
	private ImageView visible_number;
	private ImageView search;
	private ImageView zxing;
	private View gatewayRegidter;
	private String user_default = "admin";	//默认用户名/default user
	private String psw_default = "888888";	//默认密码/default password
	int number = 0;
	private ProgressDialog progressDialog;
	private boolean isSearched;
	private SearchListAdapter listAdapter = null;
	private ProgressDialog progressdlg = null;
	private static final int SEARCH_TIME = 3000;
	private boolean isStart = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		gatewayRegidter = inflater.inflate(R.layout.gateway_register, container, false);
		initView();
		progressDialog = new ProgressDialog(getActivity());
		listAdapter = new SearchListAdapter(getActivity());
		progressdlg = new ProgressDialog(getActivity());
		return gatewayRegidter;
	}
	
	private void initView() {
		register = (Button) gatewayRegidter.findViewById(R.id.register);
		up_back = (Button) gatewayRegidter.findViewById(R.id.up_back);
		reminder = (TextView) gatewayRegidter.findViewById(R.id.reminder);
		visible_number = (ImageView) gatewayRegidter.findViewById(R.id.visible_number);
		search = (ImageView) gatewayRegidter.findViewById(R.id.search);
		zxing = (ImageView) gatewayRegidter.findViewById(R.id.zxing);
		register_id = (EditText) gatewayRegidter.findViewById(R.id.edit_id);
		register_user = (EditText) gatewayRegidter.findViewById(R.id.edit_user);
		register_pass = (EditText) gatewayRegidter.findViewById(R.id.edit_pass);
		up_back.setOnClickListener(this);
		register.setOnClickListener(this);
		visible_number.setOnClickListener(this);
		search.setOnClickListener(this);
		zxing.setOnClickListener(this);
		setUp = new SetUp();
		mainFragment = new MainFragment();
		//获取输入的网关id/用户名/密码，初始账号/密码为默认账号/密码
		SharedPreferences preferences = getActivity()
				.getSharedPreferences("gatewayID", getActivity().MODE_PRIVATE);
		register_id.setText(preferences.getString("id", ""));
		register_user.setText(preferences.getString("user", user_default));
		register_pass.setText(preferences.getString("pass", psw_default));
	}

	@Override
	public void onClick(View v) {
		FragmentManager manager = getFragmentManager();
		//开启事务/open transaction
		FragmentTransaction transaction = manager.beginTransaction();
		
		switch (v.getId()) {
		//返回上一级/cdup
		case R.id.up_back:
			transaction.replace(R.id.fragmen_content, setUp);
			break;
		
		//如果用户名/密码/网关id为空，则打印"用户名/密码/网关id不能为空！"
		//否则清除
		case R.id.register:
			SharedPreferences preferences = getActivity().getSharedPreferences(
					"gatewayID", getActivity().MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			String newUser = register_user.getText().toString();
			String newPass = register_pass.getText().toString();
			if(register_user.length() > 0 
					&& register_pass.length() > 0 
					&& register_id.length() > 0){
				if(newUser.equals(user_default) && newPass.equals(psw_default)){
					Toast.makeText(getActivity(), getResources()
							.getString(R.string.register_success), Toast.LENGTH_SHORT).show();
					editor.putString("id", register_id.getText().toString());
					editor.putString("user", register_user.getText().toString());
					editor.putString("pass", register_pass.getText().toString());
					//登录成功，跳转到主界面
					transaction.replace(R.id.fragmen_content, mainFragment);
				}else{
					register_user.setText("");
					register_pass.setText("");
					editor.putString("user", "");
					editor.putString("pass", "");
					Toast.makeText(getActivity(), getResources()
							.getString(R.string.register_lose), Toast.LENGTH_SHORT).show();
				}
				reminder.setText("");
			}else{
				reminder.setText(R.string.userAndPsw);
			}
			editor.commit();
			break;
			
		case R.id.visible_number:
			if(number == 0){
				register_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				visible_number.setBackgroundResource(R.drawable.yes_check);
				number = 1;
			}else{
				register_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
				visible_number.setBackgroundResource(R.drawable.uncheck);
				number = 0;
			}
			break;
			
		case R.id.search:
			searchCamera();
			break;
			
		case R.id.zxing:
			progressDialog.setCancelable(false);
			Intent zxing_intent = new Intent(getActivity(),CaptureActivity.class);
			startActivityForResult(zxing_intent, 0);
			break;
		default:
			break;
		}
		transaction.commit();
	}

	//提示框/prompt dialog box
	private void reminder() {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setTitle(R.string.dialog);
		builder.setMessage(R.string.wrong);
		builder.setPositiveButton(R.string.confirm, null);
		builder.show();
	}
	
	private void searchCamera() {
		if (!isSearched) {
			isSearched = true;
			startSearch();
		} else {
			AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
			dialog.setTitle(getResources()
					.getString(R.string.add_search_result));
			dialog.setCancelable(false);
			dialog.setPositiveButton(
					getResources().getString(R.string.refresh),
					new DialogInterface.OnClickListener() {  //刷新搜索结果

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startSearch();

						}
					});
			dialog.setNegativeButton(
					getResources().getString(R.string.str_cancel), null);
			dialog.setAdapter(listAdapter,
					new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int arg2) {
					Map<String, Object> mapItem = (Map<String, Object>) listAdapter
							.getItemContent(arg2);
					if (mapItem == null) {
						return;
					}
					String strName = (String) mapItem
							.get(ContentCommon.STR_CAMERA_NAME);
					String strDID = (String) mapItem
							.get(ContentCommon.STR_CAMERA_ID);
					String strUser = ContentCommon.DEFAULT_USER_NAME;
					String strPwd = ContentCommon.DEFAULT_USER_PWD;
					register_user.setText(strUser);
					register_pass.setText(strPwd);
					register_id.setText(strDID);
				}
			});
			dialog.show();
		}
	}
	
	Runnable updateThread = new Runnable() {
		public void run() {
//			NativeCaller.StopSearch();
			progressdlg.dismiss();
			Message msg = updateListHandler.obtainMessage();
			msg.what = 1;
			updateListHandler.sendMessage(msg);
		}
	};
	
	Handler updateListHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				listAdapter.notifyDataSetChanged();
				if (listAdapter.getCount() > 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
					dialog.setTitle(getResources().getString(
							R.string.add_search_result));
					dialog.setCancelable(false);
					dialog.setPositiveButton(
							getResources().getString(R.string.refresh),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startSearch();
								}
							});
					dialog.setNegativeButton(
							getResources().getString(R.string.str_cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									isStart = false;
								}
							});
					dialog.setAdapter(listAdapter,
							new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int arg2) {
							Map<String, Object> mapItem = (Map<String, Object>) listAdapter
									.getItemContent(arg2);
							if (mapItem == null) {
								return;
							}
							String strName = (String) mapItem
									.get(ContentCommon.STR_CAMERA_NAME);
							String strDID = (String) mapItem
									.get(ContentCommon.STR_CAMERA_ID);
							String strUser = ContentCommon.DEFAULT_USER_NAME;
							String strPwd = ContentCommon.DEFAULT_USER_PWD;
							// devNameEdit.setText(strName);
							register_id.setText(strUser);
							register_pass.setText(strPwd);
							register_user.setText(strDID);
							isStart = false;
						}
					});
					dialog.show();
				} else {
					Toast.makeText(getActivity(),getResources().getString(R.string.add_search_no),
							Toast.LENGTH_LONG).show();
					isSearched = false;
					isStart = false;
				}
			}
		}
	};

	private void startSearch() {
		listAdapter.ClearAll();
		progressdlg.setMessage(getString(R.string.searching_tip));
		progressdlg.setCancelable(false);
		progressdlg.show();
		new Thread(new SearchThread()).start();  
		Log.d("start", "------startSearch");
		updateListHandler.postDelayed(updateThread, SEARCH_TIME);
	}
	
	private class SearchThread implements Runnable {
		@Override
		public void run() {
			Log.d("tag", "startSearch");
//			NativeCaller.StartSearch();
		}
	}
}

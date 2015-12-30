package object.homesmart.gogo.main;

import com.example.homesmart.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zeng
 *	网关编辑/GatewayEdit
 */
public class GatewayEdit extends Fragment implements OnClickListener{

	private Button up_back;
	private Button edit_commit;
	private EditText edit_user;
	private EditText edit_pass;
	private TextView reminder;
	private TextView edit_id;
	private SetUp setUp;
	private View gatewayEdit;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		gatewayEdit = inflater.inflate(R.layout.gateway_edit, container, false);
		up_back = (Button) gatewayEdit.findViewById(R.id.up_back);
		edit_commit = (Button) gatewayEdit.findViewById(R.id.edit_commit);
		edit_id = (TextView) gatewayEdit.findViewById(R.id.edit_id);
		edit_user = (EditText) gatewayEdit.findViewById(R.id.edit_user);
		edit_pass = (EditText) gatewayEdit.findViewById(R.id.edit_pass);
		reminder = (TextView) gatewayEdit.findViewById(R.id.reminder);
		up_back.setOnClickListener(this);
		edit_commit.setOnClickListener(this);
		setUp = new SetUp();
		//取出注册网关的id，是网关编辑和网关注册的id一致性
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"gatewayID", getActivity().MODE_PRIVATE);
		edit_id.setText(preferences.getString("id", ""));
		return gatewayEdit;
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
			
		//如果用户名或者密码为空，则打印"用户名/密码不能为空！"，否则打印空
		case R.id.edit_commit:
			if(edit_user.equals("") || edit_pass.equals("")){
				reminder.setText(getResources().getString(R.string.userAndPass));
			}else{
				reminder.setText("");
				transaction.replace(R.id.fragmen_content, setUp);
				Toast.makeText(getActivity(), getResources()
						.getString(R.string.register_success), Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
		transaction.commit();
	}
}

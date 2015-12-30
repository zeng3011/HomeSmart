package object.homesmart.gogo.main;

import com.example.homesmart.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zeng
 *	about activity message
 */
public class About extends Fragment implements OnClickListener{

	private Button up_back;
	private SetUp setUp;
	private TextView version;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View about = inflater.inflate(R.layout.about, container, false);
		up_back = (Button) about.findViewById(R.id.up_back);
		version = (TextView) about.findViewById(R.id.version);
		SetUp.versions.setText(version.getText().toString());
		up_back.setOnClickListener(this);
		return about;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.up_back:
			upBack();
			break;
		}
	}
	
	public void upBack(){
		setUp = new SetUp();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fragmen_content, setUp);
		transaction.commit();
	}
}

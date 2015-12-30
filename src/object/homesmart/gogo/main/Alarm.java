package object.homesmart.gogo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import object.homesmart.gogo.adapter.AlarmAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homesmart.R;

/**
 * @author zeng
 *	alarm list message ，include have image,time,title , and one back button.
 */

public class Alarm extends Fragment implements OnClickListener{

	private MainFragment mainFragment;
	private Button up_back;
	private ListView listView;
	private AlarmAdapter adapter;
	private View alarm;
	private List<HashMap<String, String>> list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		alarm = inflater.inflate(R.layout.alarm, container, false);
		initView();
		return alarm;
	}
	
	private void initView() {
		up_back = (Button) alarm.findViewById(R.id.up_back);
		up_back.setOnClickListener(this);
		mainFragment = new MainFragment();
		listView = (ListView) alarm.findViewById(R.id.listView);
		adapter = new AlarmAdapter(getData(), getActivity());
		listView.setAdapter(adapter);
	}

	private List<HashMap<String, String>> getData() {
		list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("time", getResources().getString(R.string.alarm_time));
		hashMap.put("title", getResources().getString(R.string.alarm_content1));
		list.add(hashMap);

		hashMap = new HashMap<String, String>();
		hashMap.put("time", getResources().getString(R.string.alarm_time));
		hashMap.put("title", getResources().getString(R.string.alarm_content2));
		list.add(hashMap);
		
		hashMap = new HashMap<String, String>();
		hashMap.put("time", getResources().getString(R.string.alarm_time));
		hashMap.put("title", getResources().getString(R.string.alarm_content3));
		list.add(hashMap);
		return list;
	}

	@Override
	public void onClick(View v) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.up_back:
			transaction.replace(R.id.fragmen_content, mainFragment);
			break;

		default:
			break;
		}
		transaction.commit();
	}
}

package object.homesmart.gogo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import object.homesmart.gogo.adapter.NotificationAdapter;
import object.homesmart.gogo.utils.SwipeDismissListViewTouchListener;
import object.homesmart.gogo.utils.SwipeDismissListViewTouchListener.DismissCallbacks;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.homesmart.R;

/**
 * @author zeng
 * 物业公告/notification
 */
public class Notification extends Fragment implements OnClickListener{

	private MainFragment mainFragment;
	private Content content;
	private Button up_back;
	public static ListView show_noti_list;
	private View notification;
	private List<HashMap<String, String>> list;
	private NotificationAdapter adapter;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		notification = inflater.inflate(R.layout.notification, container, false);
		initView();
		return notification;
	}
	
	Handler handler = new Handler();
	private void handlerMessage(Message msg) {
		switch (msg.what) {
		case 11:
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
	
	private void initView() {
		mainFragment = new MainFragment();
		content = new Content();
		up_back = (Button) notification.findViewById(R.id.up_back);
		up_back.setOnClickListener(this);
		show_noti_list = (ListView)notification.findViewById(R.id.show_list);
		//初始化构造参数，一般用来传递数据或初始化一些东西
		adapter = new NotificationAdapter(getData(),getActivity());
		show_noti_list.setAdapter(adapter);
		addSwipe(show_noti_list);
		//item的点击事件
		show_noti_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//将列表的时间和标题存起来
				SharedPreferences preferences = getActivity()
						.getSharedPreferences("notification", getActivity().MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("time", list.get(position).get("time"));
				editor.putString("title", list.get(position).get("title"));
				editor.commit();
				//切换到content界面
				manager = getFragmentManager();
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragmen_content, content);
				transaction.commit();
			}
		});
	}

	//滑动删除item
	private void addSwipe(ListView listView) {
		SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(listView,
                new DismissCallbacks() {
                    @Override  
                    public boolean canDismiss(int position) {
                        return true;
                    }
  
                    @Override
                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                        for (int index : reverseSortedPositions) {
                            list.remove(index);		//移除滑动的item
                            Message message = new Message();
                            message.obj = 11;
                            handler.sendMessage(message);
                        }
                        adapter.notifyDataSetChanged();	//更新列表
                    }
                });
        listView.setOnTouchListener(touchListener);
	}

	@Override
	public void onClick(View v) {
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.up_back:
			transaction.replace(R.id.fragmen_content, mainFragment);
			break;

		default:
			break;
		}
		transaction.commit();
	}
	
	/**拿到数据,根据数据格式自己设置自己需要的数据类型**/
	private List<HashMap<String, String>> getData(){
		/**假设数据类型是这样的。**/
		list = new ArrayList<HashMap<String,String>>();
		for(int i=1; i<=10; i++){
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("time", getResources().getString(R.string.notice_time)+i);
			map.put("title", getResources().getString(R.string.notice)+i);
			list.add(map);	//每次把新的集合添加进去
		}
		return list;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		show_noti_list.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}

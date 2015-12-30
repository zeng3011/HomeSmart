package object.homesmart.gogo.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.homesmart.R;

/**
 * @author zeng
 *	notification the details of message.
 */
public class Content extends Fragment{

	private View content;
	private Button up_back;
	private TextView show_time;
	private TextView show_title;
	private Notification notification;
	private TextView contentTV;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.content, container, false);
		up_back = (Button)  content.findViewById(R.id.up_back);
		show_time = (TextView) content.findViewById(R.id.show_time);
		show_title = (TextView) content.findViewById(R.id.show_title);
		contentTV = (TextView) content.findViewById(R.id.contentTV);
		SharedPreferences preferences = getActivity()
				.getSharedPreferences("notification", getActivity().MODE_PRIVATE);
		String getTime = preferences.getString("time", "");
		String getTitle = preferences.getString("title", "");
		show_time.setText(getTime);
		show_title.setText(getTitle);
		contentTV.setTextSize(20);
		//\u3000\u3000:首行缩进两个字
		contentTV.setText("\u3000\u3000"+"今天是"+getTime+"然后我们要做什么呢，额，去做梦吧.....");
		notification = new Notification();
		up_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(R.id.fragmen_content, notification);
				transaction.commit();
			}
		});
		return content;
	}
}

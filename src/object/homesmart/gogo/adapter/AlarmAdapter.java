package object.homesmart.gogo.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.homesmart.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author zeng
 *  alarm message list.
 */
public class AlarmAdapter extends BaseAdapter{
	private List<HashMap<String, String>> list;
	private Context context;
	
	public AlarmAdapter(List<HashMap<String, String>> list, Context context){
		this.list = list;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup view) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_alarm_item, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.time = (TextView) convertView.findViewById(R.id.alarm_time);
		holder.title = (TextView) convertView.findViewById(R.id.alarm_title);
		holder.time.setText(list.get(position).get("time"));
		holder.title.setText(list.get(position).get("title"));
		return convertView;
	}
	
	class ViewHolder{
		TextView time;
		TextView title;
	}
}
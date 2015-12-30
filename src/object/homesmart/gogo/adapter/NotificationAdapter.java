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
 *  Property announcement message list.
 *	1.首先继承BaseAdapter,实现它的方法。
 *  2.创建一个构造方法是公有的。一般是用来传递数据，根据拿到的数据绑定到适配器上显示出来
 */
public class NotificationAdapter extends BaseAdapter{
	private List<HashMap<String, String>> list;
	private Context context;
	
	public NotificationAdapter(List<HashMap<String, String>> list,Context context){
		this.list = list;	//我要的是当前的list，赋值给当前的list。this代表是本类的
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list.size();		//长度是数据的长度
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * 若没有布局，则将布局加到convertView,将缓存器holder设置成标签，便于使用布局中的内容和优化
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){	//为空加载布局
			convertView = LayoutInflater.from(context).inflate(R.layout.list_noti_item, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.time = (TextView) convertView.findViewById(R.id.noti_item_time);
		holder.title = (TextView) convertView.findViewById(R.id.noti_item_title);
		
		holder.time.setText(list.get(position).get("time"));
		holder.title.setText(list.get(position).get("title"));
		return convertView;
	}

	/**缓存器**/
	class ViewHolder{
		TextView time;
		TextView title;
	}
}

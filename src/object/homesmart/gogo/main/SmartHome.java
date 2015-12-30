package object.homesmart.gogo.main;

import object.homesmart.gogo.model.ModelFive;
import object.homesmart.gogo.model.ModelFour;
import object.homesmart.gogo.model.ModelOne;
import object.homesmart.gogo.model.ModelThree;
import object.homesmart.gogo.model.ModelTwo;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesmart.R;

/**
 * @author zeng
 *	家居生活/smartHome
 */
public class SmartHome extends Fragment implements OnClickListener{

	private View smartHome;
	private MainFragment mainFragment;
	private Button up_back;
	public static TextView showTV;
	private ViewPager viewPager;
	private int[] imgArray;
	private ImageView[] mImageViews;
	private ModelOne model_one;
	private ModelTwo model_two;
	private ModelThree model_three;
	private ModelFour model_four;
	private ModelFive model_five;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		smartHome = inflater.inflate(R.layout.smart_home, container, false);
		initView();
		showViewPager();
		return smartHome;
	}

	private void initView() {
		//实例化情景模式的几个界面
		model_one = new ModelOne();
		model_two = new ModelTwo();
		model_three = new ModelThree();
		model_four = new ModelFour();
		model_five = new ModelFive(); 
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		//情景模式设置默认界面
		transaction.add(R.id.demol_fragment, model_five);
		transaction.commit();
		up_back = (Button) smartHome.findViewById(R.id.up_back);
		showTV = (TextView) smartHome.findViewById(R.id.showTV);
		viewPager = (ViewPager) smartHome.findViewById(R.id.viewPager);
		up_back.setOnClickListener(this);
		mainFragment = new MainFragment();
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
	
	// 上方图片的左右滑动
	private void showViewPager() {
		imgArray = new int[]{R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.smart_home};
		mImageViews = new ImageView[imgArray.length];
		for(int i = 0; i < mImageViews.length; i++){
			ImageView imageView = new ImageView(getActivity());
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgArray[i]);
		}
		viewPager.setAdapter(new MyAdapter());
		viewPager.setCurrentItem(2);
		showTV.setText("3L"); 	//设置默认的房间名字为“3L”
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			//图片左右滑动触发的事件
			@Override
			public void onPageScrollStateChanged(int arg0) {
				//对图片取余，循环滑动的时候就知道是哪张图片
				int showView = viewPager.getCurrentItem() % imgArray.length;
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				if(showView == 0){
					showTV.setText("1L");
					transaction.replace(R.id.demol_fragment, model_five);
				}
				if(showView == 1){
					showTV.setText("2L");
					transaction.replace(R.id.demol_fragment, model_four);
				}
				if(showView == 2){
					showTV.setText("3L");
					transaction.replace(R.id.demol_fragment, model_one);
				}
				if(showView == 3){
					showTV.setText("4L");
					transaction.replace(R.id.demol_fragment, model_three);
				}
				transaction.commit();
			}
		});
	}
	
	//图片滑动需要的适配器设置
	public class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(mImageViews[position % mImageViews.length]);
			return mImageViews[position % mImageViews.length];
		}
	}
}

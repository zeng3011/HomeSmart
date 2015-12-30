package object.homesmart.gogo.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homesmart.R;

/**
 * 数据同步的自定义提示框
 * @author Administrator
 */
public class CustomProgress extends Dialog{
	
	public static CustomProgress progress;
	
	public CustomProgress(Context context) {
		super(context);
	}

	public CustomProgress(Context context, int theme) {
		super(context, theme);
	}

	//当窗口焦点改变时调用
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
		AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
		drawable.start();
	}
	
	//给Dialog设置提示信息
	public void setMessage(CharSequence message) {
		if(message != null && message.length() > 0){
			findViewById(R.id.message).setVisibility(View.VISIBLE);
			TextView tv = (TextView) findViewById(R.id.message);
			tv.setText(message);
			tv.invalidate();
		}
	}
	
	/**
	   * 弹出自定义ProgressDialog
	   * 
	   * @param context 		上下文
	   * @param message 		提示
	   * @param cancelable 		是否按返回键取消
	   * @param cancelListener	按下返回键监听
	   * @return
	   */
	public static CustomProgress show(Context context, CharSequence message,
			boolean cancelable, OnCancelListener cancelListener){
		progress = new CustomProgress(context,R.style.Custom_Progress);
		progress.setTitle("");
		progress.setContentView(R.layout.dialog);
		if(message == null || message.length() == 0){
			progress.findViewById(R.id.message).setVisibility(View.GONE);
		}else{
			TextView tv = (TextView) progress.findViewById(R.id.message);
			tv.setText(message);
		}
		 // 按返回键是否取消
		progress.setCancelable(cancelable);
	    // 监听返回键处理
		progress.setOnCancelListener(cancelListener);
	    // 设置居中
		progress.getWindow().getAttributes().gravity = Gravity.CENTER;
	    WindowManager.LayoutParams lp = progress.getWindow().getAttributes();
	    // 设置背景层透明度
	    lp.dimAmount = 0.3f;
	    progress.getWindow().setAttributes(lp);
	    progress.show();
		return progress;
	}
}

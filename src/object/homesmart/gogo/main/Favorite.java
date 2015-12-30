package object.homesmart.gogo.main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homesmart.R;

public class Favorite extends Activity implements OnClickListener{

	private Button up_back;
	private Button back_home;
	private Button set_up;
	private TextView room_name;
	private ImageView get_image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.favorite);
		initView();
		SharedPreferences preferences = getSharedPreferences("value", MODE_PRIVATE);
		String image = preferences.getString("image", "");
		byte[] base64 = Base64.decode(image.getBytes(), Base64.DEFAULT);
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		get_image.setImageDrawable(Drawable.createFromStream(bais, image));
		room_name.setText(preferences.getString("name", ""));
	}

	private void initView() {
		up_back = (Button) findViewById(R.id.up_back);
		back_home = (Button) findViewById(R.id.back_home);
		set_up = (Button) findViewById(R.id.set_up);
		room_name = (TextView) findViewById(R.id.room_name);
		get_image = (ImageView) findViewById(R.id.get_image);
		up_back.setOnClickListener(this);
		back_home.setOnClickListener(this);
		set_up.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.up_back:
			Intent up_back_intent = new Intent(Favorite.this, MainActivity.class);
			startActivity(up_back_intent);
			break;

		case R.id.back_home:
			Intent back_home_intent = new Intent(Favorite.this, MainActivity.class);
			startActivity(back_home_intent);
			break;

		case R.id.set_up:
			Intent set_up_intent = new Intent(Favorite.this, MainActivity.class);
			set_up_intent.putExtra("favorite", 0x7f090005);
			startActivity(set_up_intent);
			
			break;

		default:
			break;
		}
	}
}

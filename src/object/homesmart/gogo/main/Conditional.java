package object.homesmart.gogo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesmart.R;

/**
 * @author zeng
 *	air-condition,control the user of air-condition 
 */
public class Conditional extends Activity implements OnClickListener{

	private TextView now_T;
	private TextView setting_T;
	private Button up_back;
	private Button back_home;
	private Button set_up;
	private Button favorite;
	private Button breeze;
	private Button automatic;
	private Button apoplexy;
	private Button refrigeration;
	private Button gale;
	private Button makehot;
	private Button ventilate;
	private Button add_T;
	private Button cut_T;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.conditional);
		initView();
	}
	
	private void initView() {
		now_T = (TextView) findViewById(R.id.now_T);
		setting_T = (TextView) findViewById(R.id.setting_T);
		up_back = (Button) findViewById(R.id.up_back);
		back_home = (Button) findViewById(R.id.back_home);
		set_up = (Button) findViewById(R.id.set_up);
		favorite = (Button) findViewById(R.id.favorite);
		breeze = (Button) findViewById(R.id.breeze);
		automatic = (Button) findViewById(R.id.automatic);
		apoplexy = (Button) findViewById(R.id.apoplexy);
		refrigeration = (Button) findViewById(R.id.refrigeration);
		gale = (Button) findViewById(R.id.gale);
		makehot = (Button) findViewById(R.id.makehot);
		ventilate = (Button) findViewById(R.id.ventilate);
		add_T = (Button) findViewById(R.id.add_T);
		cut_T = (Button) findViewById(R.id.cut_T);
		
		now_T.setOnClickListener(this);
		setting_T.setOnClickListener(this);
		up_back.setOnClickListener(this);
		back_home.setOnClickListener(this);
		set_up.setOnClickListener(this);
		favorite.setOnClickListener(this);
		breeze.setOnClickListener(this);
		automatic.setOnClickListener(this);
		apoplexy.setOnClickListener(this);
		refrigeration.setOnClickListener(this);
		gale.setOnClickListener(this);
		makehot.setOnClickListener(this);
		ventilate.setOnClickListener(this);
		add_T.setOnClickListener(this);
		cut_T.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.up_back:
			finish();
			break;
			
		case R.id.back_home:
			Intent back_home_intent = new Intent(Conditional.this,MainActivity.class);
			startActivity(back_home_intent);
			break;

		case R.id.set_up:
			Intent conditional_intent = new Intent(Conditional.this,MainActivity.class);
			conditional_intent.putExtra("air_condition_intent", 0x7f030003);
			startActivity(conditional_intent);
			break;

		case R.id.favorite:
			
			break;

		case R.id.breeze:
			
			break;

		case R.id.automatic:
			
			break;

		case R.id.apoplexy:
			
			break;

		case R.id.refrigeration:
			
			break;

		case R.id.gale:
			
			break;

		case R.id.makehot:
			
			break;

		case R.id.ventilate:
			
			break;

		case R.id.add_T:
			int showAddT = Integer.parseInt(setting_T.getText().toString());
			if(showAddT > 15 && showAddT < 30){
				showAddT += 1;
				setting_T.setText(showAddT+"");
			}else{
				return ;
			}
			break;

		case R.id.cut_T:
			int showCutT = Integer.parseInt(setting_T.getText().toString());
			if(showCutT > 16 && showCutT < 31){
				showCutT -= 1;
				setting_T.setText(showCutT+"");
			}else{
				return ;
			}
			break;

		default:
			break;
		}
	}
}

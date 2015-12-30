package object.homesmart.gogo.model;

import object.homesmart.gogo.main.Control;
import object.homesmart.gogo.main.SmartHome;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homesmart.R;

public class ModelFive extends Fragment implements OnClickListener{

	private View demol_five;
	private ImageView five_ivOne,five_ivTwo,five_ivThree,five_ivFour,five_ivFive,more;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		demol_five = inflater.inflate(R.layout.model_five,container, false);
		initView();
		return demol_five;
	}

	private void initView() {
		more = (ImageView) demol_five.findViewById(R.id.more);
		five_ivOne = (ImageView) demol_five.findViewById(R.id.five_ivOne);
		five_ivTwo = (ImageView) demol_five.findViewById(R.id.five_ivTwo);
		five_ivThree = (ImageView) demol_five.findViewById(R.id.five_ivThree);
		five_ivFour = (ImageView) demol_five.findViewById(R.id.five_ivFour);
		five_ivFive = (ImageView) demol_five.findViewById(R.id.five_ivFive);
		more.setOnClickListener(this);
		five_ivOne.setOnClickListener(this);
		five_ivTwo.setOnClickListener(this);
		five_ivThree.setOnClickListener(this);
		five_ivFour.setOnClickListener(this);
		five_ivFive.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int i ;
		switch (v.getId()) {
		case R.id.more:
			Intent more_intent = new Intent();
			more_intent.setClass(getActivity(), Control.class);
			getActivity().startActivity(more_intent);
			break;

		case R.id.five_ivOne:
			Toast.makeText(getActivity(), R.string.five_ivOne, Toast.LENGTH_SHORT).show();
			break;

		case R.id.five_ivTwo:
			Toast.makeText(getActivity(), R.string.five_ivTwo, Toast.LENGTH_SHORT).show();
			break;

		case R.id.five_ivThree:
			Toast.makeText(getActivity(), R.string.five_ivThree, Toast.LENGTH_SHORT).show();
			break;

		case R.id.five_ivFour:
			Toast.makeText(getActivity(), R.string.five_ivFour, Toast.LENGTH_SHORT).show();
			break;

		case R.id.five_ivFive:
			Toast.makeText(getActivity(), R.string.five_ivFive, Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}

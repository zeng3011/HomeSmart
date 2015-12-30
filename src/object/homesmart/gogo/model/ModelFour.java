package object.homesmart.gogo.model;

import object.homesmart.gogo.main.Control;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homesmart.R;

public class ModelFour extends Fragment implements OnClickListener{
	
	private View demol_four;
	private ImageView more,four_ivOne,four_ivTwo,four_ivThree,four_ivFour;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		demol_four = inflater.inflate(R.layout.model_four,container, false);
		initView();
		return demol_four;
	}

	private void initView() {
		more = (ImageView) demol_four.findViewById(R.id.more);
		four_ivOne = (ImageView) demol_four.findViewById(R.id.four_ivOne);
		four_ivTwo = (ImageView) demol_four.findViewById(R.id.four_ivTwo);
		four_ivThree = (ImageView) demol_four.findViewById(R.id.four_ivThree);
		four_ivFour = (ImageView) demol_four.findViewById(R.id.four_ivFour);
		more.setOnClickListener(this);
		four_ivOne.setOnClickListener(this);
		four_ivTwo.setOnClickListener(this);
		four_ivThree.setOnClickListener(this);
		four_ivFour.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more:
			Intent more_intent = new Intent();
			more_intent.setClass(getActivity(), Control.class);
			getActivity().startActivity(more_intent);
			break;

		case R.id.four_ivOne:
			Toast.makeText(getActivity(), R.string.four_ivOne, Toast.LENGTH_SHORT).show();
			break;

		case R.id.four_ivTwo:
			Toast.makeText(getActivity(), R.string.four_ivTwo, Toast.LENGTH_SHORT).show();
			break;

		case R.id.four_ivThree:
			Toast.makeText(getActivity(), R.string.four_ivThree, Toast.LENGTH_SHORT).show();
			break;

		case R.id.four_ivFour:
			Toast.makeText(getActivity(), R.string.four_ivFour, Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
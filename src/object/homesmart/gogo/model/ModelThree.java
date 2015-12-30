package object.homesmart.gogo.model;

import object.homesmart.gogo.main.Control;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homesmart.R;

public class ModelThree extends Fragment implements OnClickListener{
	
	private View demol_three;
	private ImageView more,three_ivOne,three_ivTwo,three_ivThree;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		demol_three = inflater.inflate(R.layout.model_three,container, false);
		initView();
		return demol_three;
	}
	
	private void initView() {
		more = (ImageView) demol_three.findViewById(R.id.more);
		three_ivOne = (ImageView) demol_three.findViewById(R.id.three_ivOne);
		three_ivTwo = (ImageView) demol_three.findViewById(R.id.three_ivTwo);
		three_ivThree = (ImageView) demol_three.findViewById(R.id.three_ivThree);
		more.setOnClickListener(this);
		three_ivOne.setOnClickListener(this);
		three_ivTwo.setOnClickListener(this);
		three_ivThree.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more:
			Intent more_intent = new Intent();
			more_intent.setClass(getActivity(), Control.class);
			getActivity().startActivity(more_intent);
			break;

		case R.id.three_ivOne:
			Toast.makeText(getActivity(), R.string.three_ivOne, Toast.LENGTH_SHORT).show();
			break;

		case R.id.three_ivTwo:
			Toast.makeText(getActivity(), R.string.three_ivTwo, Toast.LENGTH_SHORT).show();
			break;

		case R.id.three_ivThree:
			Toast.makeText(getActivity(), R.string.three_ivThree, Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
package object.homesmart.gogo.model;

import object.homesmart.gogo.main.Control;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homesmart.R;

public class ModelTwo extends Fragment implements OnClickListener{
	
	private View demol_two;
	private ImageView more,two_ivOne,two_ivTwo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		demol_two = inflater.inflate(R.layout.model_two,container, false);
		initView();
		return demol_two;
	}
	
	private void initView() {
		more = (ImageView) demol_two.findViewById(R.id.more);
		two_ivOne = (ImageView) demol_two.findViewById(R.id.two_ivOne);
		two_ivTwo = (ImageView) demol_two.findViewById(R.id.two_ivTwo);
		more.setOnClickListener(this);
		two_ivOne.setOnClickListener(this);
		two_ivTwo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more:
			Intent more_intent = new Intent();
			more_intent.setClass(getActivity(), Control.class);
			getActivity().startActivity(more_intent);
			break;

		case R.id.two_ivOne:
			Toast.makeText(getActivity(), R.string.two_ivOne, Toast.LENGTH_SHORT).show();
			break;

		case R.id.two_ivTwo:
			Toast.makeText(getActivity(), R.string.two_ivTwo, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}

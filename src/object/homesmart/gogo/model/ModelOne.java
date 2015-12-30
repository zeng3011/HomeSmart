package object.homesmart.gogo.model;

import object.homesmart.gogo.main.Control;

import com.example.homesmart.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ModelOne extends Fragment implements OnClickListener{

	private View demol_one;
	private ImageView more,one_ivOne;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		demol_one = inflater.inflate(R.layout.model_one,container, false);
		initView();
		return demol_one;
	}
	
	private void initView() {
		more = (ImageView) demol_one.findViewById(R.id.more);
		one_ivOne = (ImageView) demol_one.findViewById(R.id.one_ivOne);
		more.setOnClickListener(this);
		one_ivOne.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more:
			Intent more_intent = new Intent();
			more_intent.setClass(getActivity(), Control.class);
			getActivity().startActivity(more_intent);
			break;
		case R.id.one_ivOne:
			Toast.makeText(getActivity(), R.string.one_ivOne, Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
	}
}

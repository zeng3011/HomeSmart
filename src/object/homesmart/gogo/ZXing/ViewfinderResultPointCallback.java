package object.homesmart.gogo.ZXing;

import android.widget.ImageView;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public final class ViewfinderResultPointCallback implements ResultPointCallback {

	private final ImageView viewfinderView;

	public ViewfinderResultPointCallback(ImageView viewfinderView2) {
		this.viewfinderView = viewfinderView2;
	}

	public void foundPossibleResultPoint(ResultPoint point) {
//    viewfinderView.addPossibleResultPoint(point);
	}
}

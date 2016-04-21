package com.my.luckbingofly.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.my.luckbingofly.R;

/**
 * 一个显示中奖了的Dialog
 * 
 * @author zhao
 * 
 */
public class SupriseDialog extends Dialog implements OnClickListener {

	private View view;
	private TextView mDateTimeTextView;
	private TextView mCloseTextView;
	private TextView mLuckNumTextView;
	private TextView mHintTextView;

	public SupriseDialog(Context context, String datetime, String hint,
			String lucknum) {
		super(context);
		initView(datetime, hint, lucknum);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		
//	----------------方案：让整个布局动起来（缩放）。。。有白色---------------------------
		 FrameLayout rlLayout= (FrameLayout) findViewById(R.id.rl_dialog);
		 AnimationSet animationSet = new AnimationSet(true);
		 //参数1：x轴的初始值
		 //参数2：x轴收缩后的值
		 //参数3：y轴的初始值
		 //参数4：y轴收缩后的值
		 //参数5：确定x轴坐标的类型
		 //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
		 //参数7：确定y轴坐标的类型
		 //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
		 ScaleAnimation scaleAnimation = new ScaleAnimation(
		 0.1f, 1,0.1f,1,
		 Animation.RELATIVE_TO_SELF,0.5f,
		 Animation.RELATIVE_TO_SELF,0.5f);
		 scaleAnimation.setDuration(1000);
		 animationSet.addAnimation(scaleAnimation);
		 rlLayout.startAnimation(animationSet);
	}

	private void initView(String datetime, String hint, String lucknum) {
		view = View.inflate(getContext(), R.layout.dialog, null);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 取消dialog的标题栏
		setContentView(view);

		mDateTimeTextView = (TextView) findViewById(R.id.tv_date_time);
		mCloseTextView = (TextView) findViewById(R.id.tv_close);
		mLuckNumTextView = (TextView) findViewById(R.id.tv_luck_num);
		mHintTextView = (TextView) findViewById(R.id.tv_tishi);

		mCloseTextView.setOnClickListener(this);

		mDateTimeTextView.setText(datetime);
		mHintTextView.setText(hint);
		mLuckNumTextView.setText(lucknum);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_close:
			dismiss();
			// System.exit (0);
			/*
			 * 可以使用 setVisible(false) 但是不会释放资源 如果你这个对话框，以后还要再次显示，那就
			 * setVisible(false) 否则应当 dispose() 全部退出还可以使用 system.exit (0) 分享
			 */

			break;

		default:
			break;
		}
	}

}

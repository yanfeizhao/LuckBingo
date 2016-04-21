package com.my.luckbingofly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 这是主持人页面,
 * @author zhao
 * 
 */
public class CompereActivity extends Activity implements OnClickListener,
		AnimatorListener {

	private int mDataSize;// 传进来的随机数的上限。
	private Button mResetButton;
	private TextView mAnimationNumTextView;
	private TextView mShowLucyNumTextView;

	private int mControlNum = 0;// k控制reset按钮的点击后的效果。
	private Random random;
	private List<Integer> mLuckList;// 存放已经产生的随机数

	private boolean mAnimatonFlag = false;// false表示没在动，true表示动画在转。
	private ObjectAnimator animator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_conpere);

		Intent intent = getIntent();
		mDataSize = Integer.valueOf(intent.getStringExtra("mDataSize"));
		// 产生随机数。。。显示在textView上面。
		initData();
		initView();
		initAnimator();

	}

	private void initData() {
		// TODO Auto-generated method stub
		mLuckList = new ArrayList<Integer>();
		random = new Random();
	}

	/**
	 * 获得指定上限的随机数
	 * @param max
	 * @return
	 */
	private int getRandomNum(int max) {
		int num;
		num = random.nextInt(max) + 1;// 的都0--》max的数，加上一之后，得到的是1---max+1
		if (mLuckList.contains(num)) {
			num = getRandomNum(max);
		}
		return num;
	}

	int startTime = 1000;//动画开始时的运行时间
	private void initAnimator() {
		// TODO Auto-generated method stub
		animator = ObjectAnimator.ofFloat(mAnimationNumTextView, "rotationY",
				0, 360);
		animator.setDuration(1000);
		animator.setInterpolator(new LinearInterpolator());// 动画插值
		animator.setRepeatCount(-1);// 设置动画重复次数
		animator.setRepeatMode(ValueAnimator.RESTART);// 动画重复模式
		animator.addListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mResetButton = (Button) findViewById(R.id.btn_reset);
		mAnimationNumTextView = (TextView) findViewById(R.id.tv_animation_num);
		mShowLucyNumTextView = (TextView) findViewById(R.id.tv_showLucyNum);
		mAnimationNumTextView.setOnClickListener(this);
		mResetButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_reset:
			reset();
			break;

		case R.id.tv_animation_num:
			animationNum();
			break;
		default:
			break;
		}

	}

	
	private void animationNum() {
		if (!mAnimatonFlag) {
			mAnimatonFlag = !mAnimatonFlag;
			startTime = 1000;
			animator.setDuration(startTime);
			animator.start();
		} else {
			if (startTime > 200) {
				Toast.makeText(getApplicationContext(), "想耍赖呀。。等会吧！！！",
						Toast.LENGTH_SHORT).show();
			} else {
				animator.end();
				mAnimatonFlag = !mAnimatonFlag;
			}
		}
	}

	

	/**
	 * 点击重置按钮之后的处理
	 */
	private void reset() {
		mControlNum = (mControlNum + 1) % 2;
		if (mControlNum == 1) {
			Toast.makeText(this, "再按一航次进入设置画面！", Toast.LENGTH_SHORT).show();
		} else if (mControlNum == 0) {
			// Intent intent = new Intent(CompereActivity.this,
			// MainActivity.class);
			// startActivity(intent);
			finish();
		} else {
			// 备用
		}
	}

	@Override
	public void onAnimationStart(Animator animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationCancel(Animator animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		if (startTime > 200) {
			startTime -= 200;
		}
		animation.setDuration(startTime);
		int randomNum = getRandomNum(mDataSize);
		mAnimationNumTextView.setText(randomNum + "");

	}

	@Override
	public void onAnimationEnd(Animator animation) {
		String stopNum=mAnimationNumTextView.getText().toString();
		mLuckList.add(Integer.valueOf(stopNum));
		mShowLucyNumTextView.append(stopNum+ ",");

	}

}

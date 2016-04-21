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
 * ����������ҳ��,
 * @author zhao
 * 
 */
public class CompereActivity extends Activity implements OnClickListener,
		AnimatorListener {

	private int mDataSize;// ������������������ޡ�
	private Button mResetButton;
	private TextView mAnimationNumTextView;
	private TextView mShowLucyNumTextView;

	private int mControlNum = 0;// k����reset��ť�ĵ�����Ч����
	private Random random;
	private List<Integer> mLuckList;// ����Ѿ������������

	private boolean mAnimatonFlag = false;// false��ʾû�ڶ���true��ʾ������ת��
	private ObjectAnimator animator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_conpere);

		Intent intent = getIntent();
		mDataSize = Integer.valueOf(intent.getStringExtra("mDataSize"));
		// �����������������ʾ��textView���档
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
	 * ���ָ�����޵������
	 * @param max
	 * @return
	 */
	private int getRandomNum(int max) {
		int num;
		num = random.nextInt(max) + 1;// �Ķ�0--��max����������һ֮�󣬵õ�����1---max+1
		if (mLuckList.contains(num)) {
			num = getRandomNum(max);
		}
		return num;
	}

	int startTime = 1000;//������ʼʱ������ʱ��
	private void initAnimator() {
		// TODO Auto-generated method stub
		animator = ObjectAnimator.ofFloat(mAnimationNumTextView, "rotationY",
				0, 360);
		animator.setDuration(1000);
		animator.setInterpolator(new LinearInterpolator());// ������ֵ
		animator.setRepeatCount(-1);// ���ö����ظ�����
		animator.setRepeatMode(ValueAnimator.RESTART);// �����ظ�ģʽ
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
				Toast.makeText(getApplicationContext(), "��ˣ��ѽ�����Ȼ�ɣ�����",
						Toast.LENGTH_SHORT).show();
			} else {
				animator.end();
				mAnimatonFlag = !mAnimatonFlag;
			}
		}
	}

	

	/**
	 * ������ð�ť֮��Ĵ���
	 */
	private void reset() {
		mControlNum = (mControlNum + 1) % 2;
		if (mControlNum == 1) {
			Toast.makeText(this, "�ٰ�һ���ν������û��棡", Toast.LENGTH_SHORT).show();
		} else if (mControlNum == 0) {
			// Intent intent = new Intent(CompereActivity.this,
			// MainActivity.class);
			// startActivity(intent);
			finish();
		} else {
			// ����
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

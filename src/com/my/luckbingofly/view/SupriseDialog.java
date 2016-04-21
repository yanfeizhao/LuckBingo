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
 * һ����ʾ�н��˵�Dialog
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
		
//	----------------���������������ֶ����������ţ��������а�ɫ---------------------------
		 FrameLayout rlLayout= (FrameLayout) findViewById(R.id.rl_dialog);
		 AnimationSet animationSet = new AnimationSet(true);
		 //����1��x��ĳ�ʼֵ
		 //����2��x���������ֵ
		 //����3��y��ĳ�ʼֵ
		 //����4��y���������ֵ
		 //����5��ȷ��x�����������
		 //����6��x���ֵ��0.5f����������������ؼ���һ�볤��Ϊx��
		 //����7��ȷ��y�����������
		 //����8��y���ֵ��0.5f����������������ؼ���һ�볤��Ϊx��
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȡ��dialog�ı�����
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
			 * ����ʹ�� setVisible(false) ���ǲ����ͷ���Դ ���������Ի����Ժ�Ҫ�ٴ���ʾ���Ǿ�
			 * setVisible(false) ����Ӧ�� dispose() ȫ���˳�������ʹ�� system.exit (0) ����
			 */

			break;

		default:
			break;
		}
	}

}

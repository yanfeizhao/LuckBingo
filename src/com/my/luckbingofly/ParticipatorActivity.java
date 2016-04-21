package com.my.luckbingofly;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.my.luckbingofly.entity.Checkbox;
import com.my.luckbingofly.fragment.CardContentFragmentOne;
import com.my.luckbingofly.fragment.CardContentFragmentThree;
import com.my.luckbingofly.fragment.CardContentFragmentTwo;
import com.my.luckbingofly.utils.GetSystemDateTimeUtils;
import com.my.luckbingofly.utils.WinPrizeStateUtils;
import com.my.luckbingofly.view.SupriseDialog;

public class ParticipatorActivity extends FragmentActivity implements
		OnClickListener,OnPageChangeListener {

	private int mDataSize;// ��̬�����ɲ��ظ����������
	private int mCountOfCard;// ��̬����viewpager��ҳ�����õ�
	private Button mGetAwardButton;
	private Button mResetbButton;
	private int mControl = 0;
	private RadioGroup mRadioGroup;
	private ViewPager viewPager;
	private CardContentFragmentOne mCardContentFragmentOne;
	private CardContentFragmentTwo mCardContentFragmentTwo;
	private CardContentFragmentThree mCardContentFragmentThree;

	private RadioButton mCardOneRadioButton;
	private RadioButton mCardTwoRadioButton;
	private RadioButton mCardThreeRadioButton;

	private TextView mCardLineOneTextView;
	private TextView mCardLineTwoTextView;
	private TextView mCardLineThreeTextView;

	private SupriseDialog mSupriseDialog;

	private boolean gainPrizeCheck1 = false;// ��һ��ҳ���Ƿ��콱��true�Ѿ������
	private boolean gainPrizeCheck2 = false;
	private boolean gainPrizeCheck3 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_participator);

		getIntentParams();
		setCardCount(mCountOfCard);
		initFtagment();
		initView();
	}

	private void getIntentParams() {
		Intent intent = getIntent();
		mDataSize = Integer.valueOf(intent.getStringExtra("mDataSize"));
		mCountOfCard = Integer.valueOf(intent.getStringExtra("mCountOfCard"));
	}

	private void initFtagment() {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putInt("mDataSize", mDataSize);

		mCardContentFragmentOne = new CardContentFragmentOne();
		mCardContentFragmentOne.setArguments(bundle);

		mCardContentFragmentTwo = new CardContentFragmentTwo();
		mCardContentFragmentTwo.setArguments(bundle);

		mCardContentFragmentThree = new CardContentFragmentThree();
		mCardContentFragmentThree.setArguments(bundle);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mGetAwardButton = (Button) findViewById(R.id.btn_getaward);
		mResetbButton = (Button) findViewById(R.id.btn_reset);

		mCardOneRadioButton = (RadioButton) findViewById(R.id.rb_cardone);
		mCardTwoRadioButton = (RadioButton) findViewById(R.id.rb_cardtwo);
		mCardThreeRadioButton = (RadioButton) findViewById(R.id.rb_cardthree);
		mCardLineOneTextView = (TextView) findViewById(R.id.tv_card_line_one);
		mCardLineTwoTextView = (TextView) findViewById(R.id.tv_card_line_two);
		mCardLineThreeTextView = (TextView) findViewById(R.id.tv_card_line_three);

		mGetAwardButton.setOnClickListener(this);
		mResetbButton.setOnClickListener(this);

		mRadioGroup = (RadioGroup) findViewById(R.id.rg_cardnum);

		viewPager = (ViewPager) findViewById(R.id.vp_gridview);
		viewPager.setOffscreenPageLimit(2);// �ܹ�������ҳ�Ķ���
		viewPager.setAdapter(new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mCountOfCard;// mCountOfCard����Viewpager��ҳ����ʵ����ʾ������
			}

			@Override
			public Fragment getItem(int position) {
				if (position == 0) {
					return mCardContentFragmentOne;
				} else if (position == 1) {
					return mCardContentFragmentTwo;
				} else if (position == 2) {
					return mCardContentFragmentThree;
				}
				return null;
			}
		});

		// ����Viewpager��ҳ
		viewPager.setOnPageChangeListener(this);

		// ����radiogroup�л�
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_cardone:
					viewPager.setCurrentItem(0);
					break;
				case R.id.rb_cardtwo:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rb_cardthree:
					viewPager.setCurrentItem(2);
					break;
				}
			}
		});

		viewPager.setCurrentItem(0);
		titleSelected(mCardOneRadioButton, mCardLineOneTextView);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_getaward:
			getAward();
			break;
		case R.id.btn_reset:
			reset();
			break;
		default:
			break;
		}
	}

	private void getAward() {
		int page = viewPager.getCurrentItem();
		// �л񽱵Ļ��ᣬû�������Ʒ�ġ�----�Żᵯ���н��Ի���
		if (page == 0 && gainPrizeCheck() && (!gainPrizeCheck1)) {
			showPrizeDialog();
			gainPrizeCheck1 = true;
			CardContentFragmentOne.clearCheckBoxFocus();//��Ҫ���ľ�����checkboxʧȥ���������
		} else if (page == 1 && gainPrizeCheck() && (!gainPrizeCheck2)) {
			showPrizeDialog();
			gainPrizeCheck2 = true;
			CardContentFragmentTwo.clearCheckBoxFocus();
		} else if (page == 2 && gainPrizeCheck() && (!gainPrizeCheck3)) {
			showPrizeDialog();
			gainPrizeCheck3 = true;
			CardContentFragmentThree.clearCheckBoxFocus();
		}
	}

	private void reset() {
		mControl = (mControl + 1) % 2;
		if (mControl == 1) {
			Toast.makeText(this, "�ٰ�һ���ν������û��棡", Toast.LENGTH_SHORT).show();
		} else if (mControl == 0) {
//			Intent intent = new Intent(ParticipatorActivity.this,
//					MainActivity.class);
//			startActivity(intent);
			finish();
		} else {
			// ����
		}
	}

	private void showPrizeDialog() {
		String dateAndTimeString = GetSystemDateTimeUtils.GetDateAndTime();// ���ϵͳʱ��
		String lucknum = getGoodgLuckyNum();// ����н�����
		mSupriseDialog = new SupriseDialog(this, dateAndTimeString, "��ϲ���н���",lucknum);
		mSupriseDialog.show();
	}

	// ����ViewPager�ĵ�ǰҳ�룬��ö�Ӧҳ�ϵ��н�����
	private String getGoodgLuckyNum() {
		String lucknum = "�н�����Ϊ:";
		int curentItem = viewPager.getCurrentItem();
		switch (curentItem) {
		case 0:
			lucknum = getSpecialLuckString(lucknum, 0);
			break;
		case 1:
			lucknum = getSpecialLuckString(lucknum, 1);
			break;
		case 2:
			lucknum = getSpecialLuckString(lucknum, 2);
			break;

		default:
			break;
		}

		lucknum = lucknum + "�뼰ʱȥ�콱��^_^";
		return lucknum;
	}

	/**
	 * ��ȡfragment����Ĵ���н�����ġ�������
	 * 
	 * @param lucknum
	 * @param j
	 *            ���жϵ�ǰViewpager��ҳ��
	 * @return
	 */
	private String getSpecialLuckString(String lucknum, int j) {
		List<Checkbox> checkboxEtityList;
		ArrayList<Integer> subscript;
		if (j == 0) {
			checkboxEtityList = CardContentFragmentOne.mCheckboxEtityList1;// ����
			subscript = CardContentFragmentOne.subscript1;// ����
			lucknum = getLuckNum(lucknum, checkboxEtityList, subscript);

		} else if (j == 1) {

			checkboxEtityList = CardContentFragmentTwo.mCheckboxEtityList2;
			subscript = CardContentFragmentTwo.subscript2;
			lucknum = getLuckNum(lucknum, checkboxEtityList, subscript);

		} else if (j == 2) {

			checkboxEtityList = CardContentFragmentThree.mCheckboxEtityList3;
			subscript = CardContentFragmentThree.subscript3;
			lucknum = getLuckNum(lucknum, checkboxEtityList, subscript);

		} else {
		}

		return lucknum;
	}

	// ��������ȡ�����ݣ�����ƴ��
	private String getLuckNum(String lucknum, List<Checkbox> checkboxEtityList,
			ArrayList<Integer> subscript) {
		for (int i = 0; i < subscript.size(); i++) {
			int num = checkboxEtityList.get(subscript.get(i)).getValue();
			// �����if����Ƿ��",",�����if�����ǲ��ǡ����ˡ�����
			if (i == 4) {
				if (subscript.get(i) == 12) {
					lucknum = lucknum + "����";
				} else {
					lucknum = lucknum + num + "";
				}

			} else {
				if (subscript.get(i) == 12) {
					lucknum = lucknum + "����,";
				} else {
					lucknum = lucknum + num + ",";
				}
			}

		}
		return lucknum;
	}

	/**
	 * ��������ҳ����Ҫ��ʾ�Ŀ�Ƭ���������
	 * 
	 * @param count
	 */
	public void setCardCount(int count) {
		switch (count) {
		case 1:
			findViewById(R.id.rb_cardtwo).setVisibility(View.GONE);
			findViewById(R.id.rb_cardthree).setVisibility(View.GONE);
			findViewById(R.id.tv_card_line_two).setVisibility(View.GONE);
			findViewById(R.id.tv_card_line_three).setVisibility(View.GONE);
			break;
		case 2:
			findViewById(R.id.rb_cardthree).setVisibility(View.GONE);
			findViewById(R.id.tv_card_line_three).setVisibility(View.GONE);
			break;
		case 3:
			break;

		default:
			break;

		}

	}

	/**
	 * ����radiobutton����Ϊѡ��״̬��
	 * 
	 * @param btnҪ����Ϊ��ɫѡ��״̬�İ�ť
	 *            ��
	 * @param tvҪ���ñ�����Ϊѡ��״̬����ɫ
	 */
	public void titleSelected(Button btn, TextView tv) {

		btn.setTextColor(Color.argb(153, 0, 255, 0));
		tv.setBackgroundColor(Color.argb(153, 0, 255, 0));
	}

	/**
	 * ����radioButtonΪδѡ��״̬��
	 * 
	 * @param btn
	 * @param tv
	 */
	public void unSelected(Button btn, TextView tv) {

		btn.setTextColor(Color.argb(255, 0, 0, 0));
		tv.setBackgroundColor(Color.argb(255, 255, 255, 255));
	}

	/**
	 * ����pager��ǰ��ʾҳ���position������Ӧ��RadioButton����ѡ�У��Լ�����text_line����ѡ����ɫ��
	 * �����ľ�����Ϊδѡ��״̬����ɫ������
	 * 
	 * @param position
	 */
	public void setSelected(int position) {
		if (position == 0) {
			titleSelected(mCardOneRadioButton, mCardLineOneTextView);
			unSelected(mCardTwoRadioButton, mCardLineTwoTextView);
			unSelected(mCardThreeRadioButton, mCardLineThreeTextView);
		} else if (position == 1) {
			unSelected(mCardOneRadioButton, mCardLineOneTextView);
			titleSelected(mCardTwoRadioButton, mCardLineTwoTextView);
			unSelected(mCardThreeRadioButton, mCardLineThreeTextView);
		} else if (position == 2) {
			unSelected(mCardOneRadioButton, mCardLineOneTextView);
			unSelected(mCardTwoRadioButton, mCardLineTwoTextView);
			titleSelected(mCardThreeRadioButton, mCardLineThreeTextView);
		} else {

		}
	}

	public boolean gainPrizeCheck() {
		boolean flag = false;
		int curentItem = viewPager.getCurrentItem();
		switch (curentItem) {
		case 0:
			flag = WinPrizeStateUtils
					.winPrize(CardContentFragmentOne.mCheckboxEtityList1);
			break;
		case 1:
			flag = WinPrizeStateUtils
					.winPrize(CardContentFragmentTwo.mCheckboxEtityList2);
			break;
		case 2:
			flag = WinPrizeStateUtils
					.winPrize(CardContentFragmentThree.mCheckboxEtityList3);
			break;

		default:
			break;
		}

		return flag;
	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			setSelected(0);
			break;
		case 1:
			setSelected(1);
			break;
		case 2:
			setSelected(2);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
}

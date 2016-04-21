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

	private int mDataSize;// 动态的生成不重复的随机数。
	private int mCountOfCard;// 动态产生viewpager的页数会用到
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

	private boolean gainPrizeCheck1 = false;// 第一个页面是否领奖？true已经领过。
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
		viewPager.setOffscreenPageLimit(2);// 能够保存两页的东西
		viewPager.setAdapter(new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mCountOfCard;// mCountOfCard控制Viewpager的页卡的实际显示的数量
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

		// 监听Viewpager换页
		viewPager.setOnPageChangeListener(this);

		// 监听radiogroup切换
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
		// 有获奖的机会，没有领过奖品的。----才会弹出中奖对话框
		if (page == 0 && gainPrizeCheck() && (!gainPrizeCheck1)) {
			showPrizeDialog();
			gainPrizeCheck1 = true;
			CardContentFragmentOne.clearCheckBoxFocus();//还要做的就是让checkbox失去点击能力。
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
			Toast.makeText(this, "再按一航次进入设置画面！", Toast.LENGTH_SHORT).show();
		} else if (mControl == 0) {
//			Intent intent = new Intent(ParticipatorActivity.this,
//					MainActivity.class);
//			startActivity(intent);
			finish();
		} else {
			// 备用
		}
	}

	private void showPrizeDialog() {
		String dateAndTimeString = GetSystemDateTimeUtils.GetDateAndTime();// 获得系统时间
		String lucknum = getGoodgLuckyNum();// 获得中奖号码
		mSupriseDialog = new SupriseDialog(this, dateAndTimeString, "恭喜你中奖了",lucknum);
		mSupriseDialog.show();
	}

	// 根据ViewPager的当前页码，获得对应页上的中奖号码
	private String getGoodgLuckyNum() {
		String lucknum = "中奖号码为:";
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

		lucknum = lucknum + "请及时去领奖！^_^";
		return lucknum;
	}

	/**
	 * 获取fragment里面的存放中奖号码的“容器”
	 * 
	 * @param lucknum
	 * @param j
	 *            ，判断当前Viewpager的页码
	 * @return
	 */
	private String getSpecialLuckString(String lucknum, int j) {
		List<Checkbox> checkboxEtityList;
		ArrayList<Integer> subscript;
		if (j == 0) {
			checkboxEtityList = CardContentFragmentOne.mCheckboxEtityList1;// 容器
			subscript = CardContentFragmentOne.subscript1;// 容器
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

	// 从容器中取出数据，进行拼接
	private String getLuckNum(String lucknum, List<Checkbox> checkboxEtityList,
			ArrayList<Integer> subscript) {
		for (int i = 0; i < subscript.size(); i++) {
			int num = checkboxEtityList.get(subscript.get(i)).getValue();
			// 外面的if检查是否加",",里免得if控制是不是“幸运”二字
			if (i == 4) {
				if (subscript.get(i) == 12) {
					lucknum = lucknum + "幸运";
				} else {
					lucknum = lucknum + num + "";
				}

			} else {
				if (subscript.get(i) == 12) {
					lucknum = lucknum + "幸运,";
				} else {
					lucknum = lucknum + num + ",";
				}
			}

		}
		return lucknum;
	}

	/**
	 * 用来控制页面上要显示的卡片标题的数量
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
	 * 设置radiobutton设置为选中状态。
	 * 
	 * @param btn要设置为绿色选中状态的按钮
	 *            ，
	 * @param tv要设置背景设为选中状态的绿色
	 */
	public void titleSelected(Button btn, TextView tv) {

		btn.setTextColor(Color.argb(153, 0, 255, 0));
		tv.setBackgroundColor(Color.argb(153, 0, 255, 0));
	}

	/**
	 * 设置radioButton为未选中状态。
	 * 
	 * @param btn
	 * @param tv
	 */
	public void unSelected(Button btn, TextView tv) {

		btn.setTextColor(Color.argb(255, 0, 0, 0));
		tv.setBackgroundColor(Color.argb(255, 255, 255, 255));
	}

	/**
	 * 根据pager当前显示页面的position，给相应的RadioButton设置选中，以及设置text_line设置选中颜色，
	 * 其他的就设置为未选中状态的颜色等属性
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

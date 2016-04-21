package com.my.luckbingofly.fragment;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.my.luckbingofly.R;
import com.my.luckbingofly.entity.Checkbox;
import com.my.luckbingofly.utils.CheckBoxStateUtils;
import com.my.luckbingofly.utils.RandomUtils;
import com.my.luckbingofly.utils.WinPrizeStateUtils;

public class CardContentFragmentThree extends Fragment{
	

	
	private View contentView;
	private int mDataSize;// 动态的生成不重复的随机数。
	private CheckBox mCheckBox;
	private static List<CheckBox> mCheckBoxsList;// 存放CheckBox控件的list---25个
	private int[] mCheckBoxId;

	public static  List<Checkbox> mCheckboxEtityList3;// 存放实体类的list----25个，
	private Checkbox checkbox;

	private int[] mCheckNum;//产生的随机数
	private int countOfChecked = 1;//用来记录已经被选中的checkbox的个数
	
	public static ArrayList<Integer> subscript3=new ArrayList<Integer>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		contentView = inflater.inflate(R.layout.fragment_carcontent,null);
		
		mDataSize=getArguments().getInt("mDataSize");
		mCheckNum = RandomUtils.randomCommon(0, mDataSize, 25);
		
		initData();
		initView();
		
		return contentView;
	}
	
	
	private void initData() {
		// TODO Auto-generated method stub
		mCheckBoxsList = new ArrayList<CheckBox>();
		mCheckBoxId = new int[] { R.id.cb_1_1, R.id.cb_1_2, R.id.cb_1_3,
				R.id.cb_1_4, R.id.cb_1_5, R.id.cb_2_1, R.id.cb_2_2,
				R.id.cb_2_3, R.id.cb_2_4, R.id.cb_2_5, R.id.cb_3_1,
				R.id.cb_3_2, R.id.cb_3_3, R.id.cb_3_4, R.id.cb_3_5,
				R.id.cb_4_1, R.id.cb_4_2, R.id.cb_4_3, R.id.cb_4_4,
				R.id.cb_4_5, R.id.cb_5_1, R.id.cb_5_2, R.id.cb_5_3,
				R.id.cb_5_4, R.id.cb_5_5 };

		mCheckboxEtityList3 = new ArrayList<Checkbox>();

		for (int i = 0; i < mCheckNum.length; i++) {
			checkbox = new Checkbox();
			checkbox.setValue(mCheckNum[i]);
			mCheckboxEtityList3.add(checkbox);// 最后共存了25个对象
		}

	}

	/**
	 * 初始化控件，并将他们添加到一个List里
	 */
	private void initView() {
		// TODO Auto-generated method stub

		// 绑定控件
		for (int i = 0; i < 25; i++) {
			mCheckBox = (CheckBox) contentView.findViewById(mCheckBoxId[i]);
			mCheckBoxsList.add(mCheckBox);
		}

		// 给每个checkbox设置监听。---同时设置要显示的值。
		for (int i = 0; i < mCheckBoxsList.size(); i++) {
			if (i == 12) {
				mCheckboxEtityList3.get(i).setCheckState(true);
				continue;// 处12位置上的checkbox有固定的显示，且已经是选中状态，不用判断。
			}

			final CheckBox cbBox = (CheckBox) mCheckBoxsList.get(i);
			final int position = i;
			cbBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked) {
						Checked(position, cbBox,mCheckboxEtityList3);
					} else {
						notChecked(position, cbBox,mCheckboxEtityList3);
					}
				}
			});
			cbBox.setText("" + mCheckboxEtityList3.get(i).getValue());
		}
	}

	/**
	 * 设置checkBox的--选中--时候的状态以及属性,并判断是否中奖
	 * @param i
	 * @param cbBox
	 */
	public void Checked(int i, final CheckBox cbBox,List<Checkbox> checkboxEtityList) {
		CheckBoxStateUtils.Checked(i, cbBox, checkboxEtityList);
		countOfChecked += 1;
		if (countOfChecked >= 5) {
			if (WinPrizeStateUtils.winPrize(checkboxEtityList)) {
				Toast.makeText(getActivity(), "baby,中奖了", Toast.LENGTH_SHORT).show();
				subscript3=WinPrizeStateUtils.winPrizeTwo(checkboxEtityList);
				
			}
		}
	}

	/**
	 * 设置checkBox的--未选中--时候的状态以及属性，并判断是否中奖
	 * @param i
	 * @param cbBox
	 */
	public void notChecked(int i, final CheckBox cbBox,List<Checkbox> checkboxEtityList) {
		CheckBoxStateUtils.unChecked(i, cbBox, checkboxEtityList);
		countOfChecked -= 1;
		if (countOfChecked >= 5) {
			if (WinPrizeStateUtils.winPrize(checkboxEtityList)) {
				Toast.makeText(getActivity(), "baby,中奖了", Toast.LENGTH_SHORT).show();
				subscript3=WinPrizeStateUtils.winPrizeTwo(checkboxEtityList);
				
			}
		}
	}

	/**
	 * 让这页的checkBox失去可以点击的能力。
	 */
	public static void clearCheckBoxFocus() {
		for (int i = 0; i < mCheckBoxsList.size(); i++) {
			CheckBox cbBox = (CheckBox) mCheckBoxsList.get(i);
			cbBox.setClickable(false);
		}
	}


}

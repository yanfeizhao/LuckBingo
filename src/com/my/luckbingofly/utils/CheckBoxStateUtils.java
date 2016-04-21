package com.my.luckbingofly.utils;

import java.util.List;

import android.graphics.Color;
import android.widget.CheckBox;

import com.my.luckbingofly.entity.Checkbox;

public class CheckBoxStateUtils {
	
	/**
	 * ����checkBox��--ѡ��--ʱ���״̬�Լ�����
	 * 
	 * @param i
	 * @param cbBox
	 */
	public static void Checked(int i, final CheckBox cbBox,List<Checkbox> checkboxEtityList) {
		cbBox.setBackgroundColor(Color.argb(221, 255, 34, 51));
		cbBox.setTextColor(Color.argb(255, 255, 255, 255));
		checkboxEtityList.get(i).setCheckState(true);
		
		
	}

	/**
	 * ����checkBox��--δѡ��--ʱ���״̬�Լ�����
	 * 
	 * @param i
	 * @param cbBox
	 */
	public static void unChecked(int i, final CheckBox cbBox,List<Checkbox> checkboxEtityList) {
		cbBox.setBackgroundColor(Color.argb(122, 255, 255, 255));
		cbBox.setTextColor(Color.argb(255, 0, 0, 0));
		checkboxEtityList.get(i).setCheckState(false);
	}

}

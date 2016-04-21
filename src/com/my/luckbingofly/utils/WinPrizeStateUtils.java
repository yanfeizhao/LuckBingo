package com.my.luckbingofly.utils;

import java.util.ArrayList;
import java.util.List;

import com.my.luckbingofly.entity.Checkbox;

public class WinPrizeStateUtils {

	/**
	 * 通过当前已按下的按钮来判断，现在是否已经中奖。
	 * 
	 * @param checkboxEtityList
	 * @return
	 */
	public static boolean winPrize(List<Checkbox> checkboxEtityList) {
		boolean flags[] = new boolean[25];
		for (int i = 0; i < checkboxEtityList.size(); i++) {
			flags[i] = checkboxEtityList.get(i).isCheckState();
		}

		if (flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
			return true;
		} else if (flags[5] && flags[6] && flags[7] && flags[8] && flags[9]) {
			return true;
		} else if (flags[10] && flags[11] && flags[12] && flags[13]
				&& flags[14]) {
			return true;
		} else if (flags[15] && flags[16] && flags[17] && flags[18]
				&& flags[19]) {
			return true;
		} else if (flags[20] && flags[21] && flags[22] && flags[23]
				&& flags[24]) {
			return true;
		} else if (flags[0] && flags[5] && flags[10] && flags[15] && flags[20]) {
			return true;
		} else if (flags[1] && flags[6] && flags[11] && flags[16] && flags[21]) {
			return true;
		} else if (flags[2] && flags[7] && flags[12] && flags[17] && flags[22]) {
			return true;
		} else if (flags[3] && flags[8] && flags[13] && flags[18] && flags[23]) {
			return true;
		} else if (flags[4] && flags[9] && flags[14] && flags[19] && flags[24]) {
			return true;
		} else if (flags[0] && flags[6] && flags[12] && flags[18] && flags[24]) {
			return true;
		} else if (flags[4] && flags[8] && flags[12] && flags[16] && flags[20]) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否中奖，并返回中奖的数字的下标(subscript)
	 * 
	 * @param checkboxEtityList
	 * @return
	 */
	public static ArrayList<Integer> winPrizeTwo(
			List<Checkbox> checkboxEtityList) {
		// int[] subscript = new int[5];
		ArrayList<Integer> subscript = new ArrayList<Integer>();

		boolean flags[] = new boolean[25];
		for (int i = 0; i < checkboxEtityList.size(); i++) {
			flags[i] = checkboxEtityList.get(i).isCheckState();
		}

		if (flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
			subscript.add(0);
			subscript.add(1);
			subscript.add(2);
			subscript.add(3);
			subscript.add(4);
			return subscript;
		} else if (flags[5] && flags[6] && flags[7] && flags[8] && flags[9]) {
			subscript.add(5);
			subscript.add(6);
			subscript.add(7);
			subscript.add(8);
			subscript.add(9);
			return subscript;
		} else if (flags[10] && flags[11] && flags[12] && flags[13]
				&& flags[14]) {
			subscript.add(10);
			subscript.add(11);
			subscript.add(12);
			subscript.add(13);
			subscript.add(14);
			return subscript;
		} else if (flags[15] && flags[16] && flags[17] && flags[18]
				&& flags[19]) {
			subscript.add(15);
			subscript.add(16);
			subscript.add(17);
			subscript.add(18);
			subscript.add(19);
			return subscript;
		} else if (flags[20] && flags[21] && flags[22] && flags[23]
				&& flags[24]) {
			subscript.add(20);
			subscript.add(21);
			subscript.add(22);
			subscript.add(23);
			subscript.add(24);
			return subscript;
		} else if (flags[0] && flags[5] && flags[10] && flags[15] && flags[20]) {
			subscript.add(0);
			subscript.add(5);
			subscript.add(10);
			subscript.add(15);
			subscript.add(20);
			return subscript;
		} else if (flags[1] && flags[6] && flags[11] && flags[16] && flags[21]) {
			subscript.add(1);
			subscript.add(6);
			subscript.add(11);
			subscript.add(16);
			subscript.add(21);
			return subscript;
		} else if (flags[2] && flags[7] && flags[12] && flags[17] && flags[22]) {
			subscript.add( 2);
			subscript.add( 7);
			subscript.add( 12);
			subscript.add( 17);
			subscript.add( 22);
			return subscript;
		} else if (flags[3] && flags[8] && flags[13] && flags[18] && flags[23]) {
			subscript.add( 3);
			subscript.add( 8);
			subscript.add( 13);
			subscript.add( 18);
			subscript.add( 23);
			return subscript;
		} else if (flags[4] && flags[9] && flags[14] && flags[19] && flags[24]) {
			subscript.add( 4);
			subscript.add( 9);
			subscript.add( 14);
			subscript.add( 19);
			subscript.add( 24);
			return subscript;
		} else if (flags[0] && flags[6] && flags[12] && flags[18] && flags[24]) {
			subscript.add( 0);
			subscript.add( 6);
			subscript.add( 12);
			subscript.add( 18);
			subscript.add( 24);
			return subscript;
		} else if (flags[4] && flags[8] && flags[12] && flags[16] && flags[20]) {
			subscript.add( 4);
			subscript.add( 8);
			subscript.add( 12);
			subscript.add( 16);
			subscript.add( 20);
			return subscript;
		} else {
			return null;
		}

	}

}

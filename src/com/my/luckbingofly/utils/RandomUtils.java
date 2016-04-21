package com.my.luckbingofly.utils;

public class RandomUtils {
	/**
	 * 产生不重复的随机数,[1,72]或者说[1,?]
	 * 
	 * @param min
	 *            ,固定值为1
	 * @param max
	 *            --最大值，
	 * @param n
	 *            ；个数：需求为24个。
	 * 
	 */
	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

}

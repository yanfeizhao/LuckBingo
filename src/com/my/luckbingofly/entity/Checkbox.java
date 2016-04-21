package com.my.luckbingofly.entity;

/**
 * ¥Ê¥¢checkbox…œµƒ÷µ£¨
 * 
 * @author zhao
 * 
 */
public class Checkbox {
	private int value;
	private boolean checkState = false;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}

}

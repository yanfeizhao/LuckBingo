package com.my.luckbingofly;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.my.luckbingofly.adapter.SpinnerAdapter;

public class MainActivity extends Activity implements OnClickListener {
	
	public static TextView mDataTextView;//�ؼ�һ�㲻Ҫ����Ϊ��̬�ģ�д��ʹ�ýӿڻص��ķ�ʽ
	public static TextView mAtortextView;
	public static TextView mCountOfCardTextView;

	private List<String> mDataSizeList;
	private List<String> mActorList;
	private List<String> mCountOfCardList;

	private Button mSureButton;
	private Button mExitButton;

	public static PopupWindow mPopupWindow;
	private SpinnerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initData() {
		// TODO Auto-generated method stub
		mDataSizeList = new ArrayList<String>();
		mActorList = new ArrayList<String>();
		mCountOfCardList = new ArrayList<String>();

		String[] datasizes = new String[] { "32", "52", "72", "92" };
		for (int i = 0; i < datasizes.length; i++) {
			mDataSizeList.add(datasizes[i]);
		}

		mActorList.add("������");
		mActorList.add("�齱��");

		String[] countOfcard = new String[] { "1", "2", "3" };
		for (int i = 0; i < countOfcard.length; i++) {
			mCountOfCardList.add(countOfcard[i]);
		}

	}

	// finish���˳���ǰ���棬exit���˳��������� system������������
	private void initView() {
		// TODO Auto-generated method stub
		mDataTextView = (TextView) findViewById(R.id.tv_datasize_sp);
		mAtortextView = (TextView) findViewById(R.id.tv_actor_sp);
		mCountOfCardTextView = (TextView) findViewById(R.id.tv_countOfCard_sp);

		mSureButton = (Button) findViewById(R.id.btn_sure);
		mExitButton = (Button) findViewById(R.id.btn_exit);
		
		// ע�������
		mDataTextView.setOnClickListener(this);
		mAtortextView.setOnClickListener(this);
		mCountOfCardTextView.setOnClickListener(this);

		mSureButton.setOnClickListener(this);
		mExitButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sure:
			 jumpTo();
			
			break;
		case R.id.btn_exit:
			finish();
			break;

		case R.id.tv_actor_sp:
			showListView(v);
			break;

		case R.id.tv_datasize_sp:
			showListView(v);
			break;

		case R.id.tv_countOfCard_sp:
			showListView(v);
			break;

		default:
			break;
		}

	}

	/**
	 *  ���ݲ�����������ת����һ��Activity.�жϻ��ɫ����һ����������ת���ĸ�ҳ�档���ݿ�Ƭ���������������Χ
	 */
	private void jumpTo() {
		Intent intent;
		 String mActor=mAtortextView.getText().toString();
		 String mDataSize=mDataTextView.getText().toString();
		 String mCountOfCard= mCountOfCardTextView.getText().toString();
		 if(mActor.equals("������")){
			 intent = new Intent(MainActivity.this, CompereActivity.class);
		 }
		 else {
			 intent = new Intent(MainActivity.this, ParticipatorActivity.class);
		 }
		
		intent.putExtra("mDataSize",mDataSize );
		intent.putExtra("mCountOfCard",mCountOfCard);
		startActivity(intent);
	}

	@SuppressWarnings("deprecation")
	private void showListView(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		View view = View.inflate(this, R.layout.popupwindow_list, null);
		int width = mAtortextView.getWidth();

		mPopupWindow = new PopupWindow(view, width,
				ViewGroup.LayoutParams.WRAP_CONTENT);// ��ƽpopwindow�ĸ߶�����Ӧ��

		// ����κ�λ�ã�mPopupWindowҪ��ʧ��[��������Ҫ����ʹ��] -----����ʾ֮ǰ��������
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());// ȱ�����������Ļ�ʧЧ
		mPopupWindow.setOutsideTouchable(true);

		initListView(view, id);

		// ��ʾ��ĳ���ؼ������棬������btn_spinner����
		switch (id) {
		case R.id.tv_actor_sp:
			mPopupWindow.showAsDropDown(mAtortextView);
			break;
		case R.id.tv_datasize_sp:
			mPopupWindow.showAsDropDown(mDataTextView);
			break;
		case R.id.tv_countOfCard_sp:
			mPopupWindow.showAsDropDown(mCountOfCardTextView);
			break;

		default:
			break;
		}

	}

	private void initListView(View view, int id) {
		// TODO Auto-generated method stub
		ListView listView = (ListView) view.findViewById(R.id.lv_spinner);

		switch (id) {
		case R.id.tv_actor_sp:
			mAdapter = new SpinnerAdapter(this, mActorList, id);
			listView.setAdapter(mAdapter);
			break;
		case R.id.tv_datasize_sp:
			mAdapter = new SpinnerAdapter(this, mDataSizeList, id);
			listView.setAdapter(mAdapter);
			break;
		case R.id.tv_countOfCard_sp:
			mAdapter = new SpinnerAdapter(this, mCountOfCardList, id);
			listView.setAdapter(mAdapter);
			break;

		default:
			break;
		}

	}

}

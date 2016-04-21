package com.my.luckbingofly.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.my.luckbingofly.MainActivity;
import com.my.luckbingofly.R;

public class SpinnerAdapter extends BaseAdapter{

	private  List<String> list; 
	private Context context;
	private int id;
	
	public SpinnerAdapter(Context context,List<String> list, int id){
		this.list=list;
		this.context=context;
		this.id=id;
		
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=View.inflate(context,R.layout.popupwindow_list_item, null);
			}
			
			ViewHolder viewHolder=(ViewHolder) convertView.getTag();//Ҫǿת����ΪTag������Object����
			if(viewHolder==null){
				viewHolder=new ViewHolder();
				viewHolder.msgTextView = (TextView) convertView.findViewById(R.id.tv_msg);
				convertView.setTag(viewHolder);
			}
			
			viewHolder.msgTextView.setText(list.get(position));
			
//			�����Ϊlistview��ĳ����Ŀ�ĵ���¼�  ����2
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Toast.makeText(context, "���"+list.get(position), Toast.LENGTH_SHORT).show();
					
					switch (id) {
					case R.id.tv_actor_sp:
						MainActivity.mAtortextView.setText(list.get(position).toString());
						MainActivity.mPopupWindow.dismiss();
						break;
					case R.id.tv_datasize_sp:
						MainActivity.mDataTextView.setText(list.get(position).toString());
						MainActivity.mPopupWindow.dismiss();
						break;
					case R.id.tv_countOfCard_sp:
						MainActivity.mCountOfCardTextView.setText(list.get(position).toString());
						MainActivity.mPopupWindow.dismiss();
						break;

					default:
						break;
					}
				
					
				}
			});
		return convertView;
	}
	
	class ViewHolder{
		TextView  msgTextView;
	}
	

}

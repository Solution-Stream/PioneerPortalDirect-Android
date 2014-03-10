package com.pioneer.portaldirect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListRowAdapter extends ArrayAdapter<String>{

	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] driverName;
	public ListRowAdapter(Activity context,
			String[] web, Integer[] imageId, String[] driverName) {
		super(context, R.layout.list_row, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.driverName = driverName;
		
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_row, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		txtTitle.setText(web[position]);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		imageView.setImageResource(imageId[position]);
		
		TextView txtSelectedDriver = (TextView) rowView.findViewById(R.id.txtReviewSelectedDriver);
		String name = driverName[position];
		try{
			if(name == "")
			{
				txtSelectedDriver.setText(driverName[position]);
			}
			else{
				txtSelectedDriver.setText("Select Driver");
			}
		}catch(Exception e){
			txtSelectedDriver.setText("Select Driver");
		}
				
		return rowView;
	}
}
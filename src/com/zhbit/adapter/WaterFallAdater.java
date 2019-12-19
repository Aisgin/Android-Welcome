package com.zhbit.adapter;


import com.zhbit.hellowelcome.R;
import com.zhbit.loader.ImageLoader;
import com.zhbit.view.ScaleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class WaterFallAdater extends ArrayAdapter<String> {
	private ImageLoader mLoader;


	public WaterFallAdater(Context context,int textViewResourceId, String[] objects) {
		super(context, textViewResourceId, objects);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(getContext());
			convertView = layoutInflator.inflate(R.layout.activity_imagelist_item,
					null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView.findViewById(R.id.star_image);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		mLoader.DisplayImage(getItem(position), holder.imageView);

		return convertView;
	}

	static class ViewHolder {
		ScaleImageView imageView;
	}
}

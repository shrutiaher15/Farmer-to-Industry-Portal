package com.example.firebase;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter1 extends BaseAdapter {

    private Context context;
   // private ArrayList<ImageModel1>compname;
    private ArrayList<ImageModel1> imageModelArrayList;
   // private ArrayList<ImageModel1>compinfo;
    public CustomAdapter1(Context context, ArrayList<ImageModel1> imglist) {
        this.context = context;
        this.imageModelArrayList=imglist;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item1, null, true);

            holder.cname = (TextView) convertView.findViewById(R.id.quuu);
            holder.cinfo = (TextView) convertView.findViewById(R.id.cinfo);
            holder.btn=convertView.findViewById(R.id.button);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.cname.setText(imageModelArrayList.get(position).getName());
        holder.cinfo.setText(imageModelArrayList.get(position).getInfo());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return convertView;
    }
    private class ViewHolder {

        protected TextView cname,cinfo;
        private Button btn;
    }
}
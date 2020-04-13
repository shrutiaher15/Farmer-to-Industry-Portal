package com.example.firebase.adaptor;

import android.Manifest;
import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.farmer_detail;

import java.util.List;

public class Farmer_nearby_adaptor extends RecyclerView.Adapter<Farmer_nearby_adaptor.GroceryProductViewHolder> implements View.OnClickListener {
    private List<farmer_detail> grocderyItemList;
    Context context;

    public Farmer_nearby_adaptor(List<farmer_detail> grocderyItemList, Context context) {
        this.grocderyItemList = grocderyItemList;
        this.context = context;
    }

    @Override
    public GroceryProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_detail_card, parent, false);
        GroceryProductViewHolder gvh = new GroceryProductViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(final GroceryProductViewHolder holder, final int position) {
        //holder.imageProductImage.setImageResource(grocderyItemList.get(position).getFarmer_name());
        holder.txtname.setText(grocderyItemList.get(position).getFarmer_name());
        holder.txtphone.setText(grocderyItemList.get(position).getFarmer_phoneno());
        holder.txtdis.setText(grocderyItemList.get(position).getDis());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_no = holder.txtphone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone_no));
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        v.getContext().startActivity(intent);
                    }
                } else {
                    Toast.makeText(v.getContext(), "Call cannot be completed", Toast.LENGTH_LONG).show();
                }

                }



        });
        holder.id.setText(grocderyItemList.get(position).getId());
        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),chatbox.class);
                String id1=holder.id.getText().toString();
                Log.d("fsdfsdf",id1);
                i.putExtra("fid",id1);
                v.getContext().startActivity(i);
            }
        });
        //holder.txtProductWeight.setText(grocderyItemList.get(position).getProductWeight());
        //holder.txtProductQty.setText(grocderyItemList.get(position).getProductQty());

      //  holder.imageProductImage.setOnClickListener(new View.OnClickListener() {

    }

    @Override
    public int getItemCount() {
        return grocderyItemList.size();
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(v.getContext(),chatbox.class);

        v.getContext().startActivity(i);
    }

    public class GroceryProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ImageView imageProductImage;
        //TextView txtProductName;
        //TextView txtProductPhone;
        //TextView txtProductWeight;
        TextView txtname;
        TextView txtphone;
        TextView txtdis;
        TextView id;
        ImageView img;
        //String id1;
        public GroceryProductViewHolder(View view) {
            super(view);
            txtname=view.findViewById(R.id.iname);
            txtphone=view.findViewById(R.id.indname);
            txtdis=view.findViewById(R.id.dis);
            id=view.findViewById(R.id.farmer_id);
            img=view.findViewById(R.id.call);
            Log.d("id of farmer",id.getText().toString());
           //String id1=id.getText().toString();
            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Id=id.getText().toString();
                    Log.d("Id of nfarmer",Id);
                    Intent i=new Intent(v.getContext(),chatbox.class);
                    i.putExtra("fId",Id);
                    v.getContext().startActivity(i);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(i);
                }
            });*/
        }

        @Override
        public void onClick(View v) {
            Intent i =new Intent(v.getContext(),chatbox.class);
            v.getContext().startActivity(i);

        }
    }
}

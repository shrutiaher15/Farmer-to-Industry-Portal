package com.example.firebase.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.firebase.Grocery;
import com.example.firebase.PostDetailActivity;
import com.example.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GroceryProductAdapter extends RecyclerView.Adapter<GroceryProductAdapter.GroceryProductViewHolder>{
    private List<Grocery> grocderyItemList;
    Context context;
    static int flag=0;
    public GroceryProductAdapter(List<Grocery> grocderyItemList, Context context) {
        this.grocderyItemList = grocderyItemList;
        this.context = context;
    }

    @Override
    public GroceryProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_card, parent, false);
        GroceryProductViewHolder gvh = new GroceryProductViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(GroceryProductViewHolder holder, final int position) {




            holder.txtProductName.setText(grocderyItemList.get(position).getProductName());
            holder.txtProductPrice.setText(grocderyItemList.get(position).getProductPrice());
            holder.txtProductDate.setText(grocderyItemList.get(position).getProductWeight());
            holder.ID.setText(grocderyItemList.get(position).getProductId());
            holder.txtProductQty.setText(grocderyItemList.get(position).getProductQty());
            holder.dis.setText(grocderyItemList.get(position).getDistance());
   holder.profit.setText(grocderyItemList.get(position).getProfit());



    }

    @Override
    public int getItemCount() {
        return grocderyItemList.size();
    }

    public class GroceryProductViewHolder extends ViewHolder {
       int flag=0;


        TextView txtProductName;
        TextView txtProductPrice;
        TextView txtProductDate;
        TextView txtProductQty;
        TextView ID;
        TextView dis;
        TextView profit;

        public GroceryProductViewHolder(View view) {

                super(view);

                txtProductName = view.findViewById(R.id.indname);
                txtProductPrice = view.findViewById(R.id.indprice);
                txtProductDate = view.findViewById(R.id.date);
                txtProductQty = view.findViewById(R.id.quuu);
                profit = view.findViewById(R.id.profit);
                //txtProductQty=view.findViewById(R)
                dis = view.findViewById(R.id.dis);
                ID = view.findViewById(R.id.farmer_id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Industry");
                        Intent i = new Intent(v.getContext(), PostDetailActivity.class);
                        String str = ID.getText().toString();
                        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        ref.child(str).child("fid").setValue(currentuser);
                        i.putExtra("Id", str);
                        v.getContext().startActivity(i);


                        // v.getContext().startActivity(new Intent(v.getContext(), PostDetailActivity.class));

                    }
                });

            }
        }
    }



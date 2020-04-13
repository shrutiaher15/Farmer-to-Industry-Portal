package com.example.firebase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class sell_part extends AppCompatActivity {

    private ListView lv;
    private CustomAdapter customAdapter;
    private ArrayList<ImageModel>imageModelArrayList;
    private int[]myImageList=new int[]{R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8};
    private String[]myImageNameList=new String[]{"sevin","sevin","carbofuran","roundup"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_part);
        lv=findViewById(R.id.list);
        imageModelArrayList=populateList();
        customAdapter=new CustomAdapter(this,imageModelArrayList);
        lv.setAdapter(customAdapter);
    }
    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setName(myImageNameList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }
}

package com.example.firebase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class importexport extends AppCompatActivity {

    private ListView lv1;
    private CustomAdapter1 customeAdapter;
    private ArrayList<ImageModel1> imageModelArrayList1;
    private String[] mycompname= new String[]{"Jyoti Trading company", "Bhaba Exports","Kalra Oversea LTD","C.R.Enterprise"};
    private String[] mycompinfo=new String[]{"Mumbai","pune","Nashik","Goa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importexport);
        lv1 = findViewById(R.id.list1);
        imageModelArrayList1 = populateList();
        customeAdapter = new CustomAdapter1(importexport.this,imageModelArrayList1);
        lv1.setAdapter(customeAdapter);
    }
    private ArrayList<ImageModel1> populateList(){
        ArrayList<ImageModel1> list1 = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            ImageModel1 imageModel = new ImageModel1(mycompname[i],mycompinfo[i]);
            list1.add(imageModel);
        }
        return list1;
    }
}

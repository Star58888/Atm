package com.star.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText edDate , edInfo , edAmount;
    private  MyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        helper = new MyDBHelper(this , "expense.db" , null , 1);
        findViews();
    }

    private void findViews() {
        edDate = findViewById(R.id.ed_date);
        edInfo = findViewById(R.id.ed_info);
        edAmount = findViewById(R.id.ed_amount);
    }

    public void Add(View v ) {
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        ContentValues values = new ContentValues();
        values.put("cdate" , cdate);
        values.put("info" , info);
        values.put("amount" , amount);
        long id = helper.getWritableDatabase().insert("exp" , null , values);

        Log.d("ADD" , id + "");
    }
}
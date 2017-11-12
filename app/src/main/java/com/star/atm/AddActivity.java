package com.star.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText edDate , edInfo , edAmount;
    private  MyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
//        helper = new MyDBHelper(this , "expense.db" , null , 1);
        helper = MyDBHelper.getInstance(this);
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
        Toast.makeText(AddActivity.this ,"您新增了項目: " + info ,Toast.LENGTH_SHORT).show();
        Log.d("ADD" , id + "");
    }
}

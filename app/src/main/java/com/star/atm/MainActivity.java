package com.star.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean logon = false;
    public static final int FUNC_LOGON = 1;  // 登入功能
    String[] func = {"餘額查詢" ,"交易明細" , "最新消息" , "投資理財" , "離開"};
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid = findViewById(R.id.grid);
        ArrayAdapter gAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 ,func);
        grid.setAdapter(gAdapter);


        final Spinner notify = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.notify_array , android.R.layout.simple_spinner_item);
        notify.setAdapter(nAdapter);

        list = findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , func);
        list.setAdapter(adapter);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);

        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this , nAdapter.getItem(position) , Toast.LENGTH_LONG).show();
                String text = notify.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (!logon) {
            Intent intent = new Intent(this , LoginActivity.class);
//
            startActivityForResult(intent , FUNC_LOGON);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FUNC_LOGON) {
            if(resultCode == RESULT_OK) {
                String uid = data.getStringExtra("LOGIN_USERID");
                String pw = data.getStringExtra("LOGIN_PASSWORD");
                Log.d("RESULT" , uid + "/" + pw);
            } else {
                    finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

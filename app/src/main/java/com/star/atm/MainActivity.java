package com.star.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    boolean logon = false;
    public static final int FUNC_LOGON = 1;  // 登入功能
    String[] func = {"餘額查詢" ,"交易明細" , "最新消息" , "投資理財" , "離開"};
    ListView list;
    int[] icons = {R.drawable.func_balance,
            R.drawable.func_history,
            R.drawable.func_new,
            R.drawable.func_finance,
            R.drawable.func_exit,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid = findViewById(R.id.grid);
        //更新使用自訂layout
        IconAdapter gAdapter = new IconAdapter();

//        ArrayAdapter gAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 ,func);
        grid.setAdapter(gAdapter);
        grid.setOnItemClickListener(this);

        final Spinner notify = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.notify_array , android.R.layout.simple_spinner_item);
//        notify.setAdapter(nAdapter);
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
        list = findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , func);
        list.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
//                R.drawable.func_balance:
                break;
            case 1:
//                R.drawable.func_new:
                break;
            case 2:
//                R.drawable.func_finance:
                break;
            case 3:
//                R.drawable.func_history:
                startActivity(new Intent(this, FinanceActivity.class));
                break;
            case 4:
//                R.drawable.func_exit:
                finish();
                break;
        }
    }

    class IconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int position) {
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if(row == null)
            {
                row = getLayoutInflater().inflate(R.layout.item_row , null);
                ImageView image = row.findViewById(R.id.item_image);
                TextView text = row.findViewById(R.id.item_text);
                image.setImageResource(icons[position]);
                text.setText(func[position]);
            }
            return row;
        }
    }
}

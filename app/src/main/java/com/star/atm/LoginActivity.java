package com.star.atm;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edUserid =  findViewById(R.id.userid);
        SharedPreferences setting = getSharedPreferences("atm" , MODE_PRIVATE);
        edUserid.setText(setting.getString("PREF_USERID",""));
    }

    public void login(View view) {
        EditText edUserid = findViewById(R.id.userid);
        EditText edPasswd = findViewById(R.id.passwd);
        String uid = edUserid.getText().toString();
        String pw = edPasswd.getText().toString();
        String url = new StringBuilder("http://atm201605.appspot.com/login?uid=")
                .append(uid).append("&pw=").append(pw).toString();
        new LoginTask().execute(url);
//        if (uid.equals("jack") && pw.equals("1234")) {
//            SharedPreferences setting = getSharedPreferences("atm" , MODE_PRIVATE);
//            setting.edit().putString("PREF_USERID", uid).commit();
//            Toast.makeText(this , " 登入成功" ,Toast.LENGTH_SHORT).show();
//            getIntent().putExtra("LOGIN_USERID" , uid);
//            getIntent().putExtra("LOGIN_PASSWD" , pw);
//            setResult(RESULT_OK , getIntent());
//            finish();
//        }
//        else {
//            new AlertDialog.Builder(this).setTitle("ATM").setMessage("登入失敗")
//                    .setPositiveButton("OK" , null).show();
//        }
    }

    public void cancel(View view) {

    }

    class LoginTask extends AsyncTask<String , Void , Boolean>
    {
        boolean logon = false;
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream is = url.openStream();
                int data = is.read();
                Log.d("HTTP" , String.valueOf(data));
                if (data == 49)
                {
                    logon = true;
                }
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return logon;
        }

        @Override
        protected void onPostExecute(Boolean logon) {
            super.onPostExecute(logon);
            if (logon)
            {
                //儲存帳號至SharedPreferences
                //結束本畫面，回到MainActivity
                Toast.makeText(LoginActivity.this , "登入成功", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK , getIntent());
                finish();
            }
            else
            {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Atm")
                        .setMessage("登入失敗")
                        .setPositiveButton("OK" , null)
                        .show();
            }
        }
    }

}

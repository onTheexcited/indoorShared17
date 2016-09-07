package cn.edu.bistu.cs.se.indoorshared17;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




import java.util.Date;



public class MainActivity extends AppCompatActivity {
    private final static String sharedPreferenceName = "config";
    private final static String key_UserName = "UserNmae";
    private final static String key_LoginDate = "LoginDate";
    private final static String key_UserType = "UserType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得SharedPreferences实例
        preferences = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);
        editor = preferences.edit();
        Button btnWrite = (Button) findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            //格式化日期，将日期按照年月日时分秒格式转换为字符串形式
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());
                //写入键值对
                editor.putString(key_UserName, "zhang san");
                editor.putString(key_LoginDate, strLoginDate);
                editor.putInt(key_UserType, 1);
                //提交修改，此处换成commit()也可以
                editor.apply();
            }
        });
        Button btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = preferences.getString(key_UserName, null);
                String strLoginDate = preferences.getString(key_LoginDate, null);
                int nUserType = preferences.getInt(key_UserType, 0);

                if (strUserName != null && strLoginDate != null) {
                    Toast.makeText(MainActivity.this, "用户名" + strUserName + "登陆时间" + strLoginDate + "用户类型" + nUserType, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}

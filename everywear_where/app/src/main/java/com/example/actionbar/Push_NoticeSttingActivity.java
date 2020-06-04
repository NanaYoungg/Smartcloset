package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/*
public class Push_NoticeSttingActivity extends Activity {
    Button btnok;
    Switch s1;
    Switch s2;

        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_setting);
        btnok = (Button)findViewById(R.id.ok_button);

            s1 = (Switch)findViewById(R.id.Clean_notice);
            s2 = (Switch)findViewById(R.id.Laundry_notice);

            //스위치
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == false) {
                        Toast.makeText(Push_NoticeSttingActivity.this, "청정관리 알림OFF", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == false) {
                        Toast.makeText(Push_NoticeSttingActivity.this, "세탁물 알림OFF", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //홈버튼
            Button home = (Button)findViewById(R.id.home_button);
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            HomeActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent); // 다음 화면으로 넘어간다
                }
            });
            //뒤로가기
            Button back = (Button) findViewById(R.id.reversed_button);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MenuActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent); // 다음 화면으로 넘어간다
                }
            });


            }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.ok_button :
                Toast.makeText(getApplicationContext(),"설정 완료",Toast.LENGTH_SHORT).show();
                break;
        }
    }
        }*/




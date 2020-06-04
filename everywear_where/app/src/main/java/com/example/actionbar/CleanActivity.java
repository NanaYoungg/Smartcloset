package com.example.actionbar;

/*import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CleanActivity extends AppCompatActivity {
    Switch swt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean);
        swt = (Switch)findViewById(R.id.Clean_auto);

        //스위치
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                           @Override
                                           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                               if (isChecked == false) {
                                                   Toast.makeText(CleanActivity.this, "자동실행OFF", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });
                //클린예약
                Button clean1 = (Button) findViewById(R.id.res_btn);
                clean1.setOnClickListener(new View.OnClickListener() {

                    @Override

                    //자동실행 토스트메세지


                    //청정관리 예약화면으로 넘어가기
                    public void onClick(View view) {
                        Intent intent = new Intent(
                                getApplicationContext(), // 현재 화면의 제어권자
                                Clean_resActivity.class); // 다음 넘어갈 클래스 지정
                        startActivity(intent); // 다음 화면으로 넘어간다
                    }
                });




            }

        }
*/

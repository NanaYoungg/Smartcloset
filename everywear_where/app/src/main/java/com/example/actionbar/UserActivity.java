package com.example.actionbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class UserActivity extends AppCompatActivity {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private String id, engSex, local1,local2,local3;
    int count=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_setting_first);  // layout xml 과 자바파일을 연결

        id = mStore.collection("user").document().getId();

        //SharedPreferences
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        final EditText edit1 = (EditText) findViewById(R.id.name2);
        final EditText edit2 = (EditText) findViewById(R.id.age2);
        Button btn = (Button) findViewById(R.id.ok_button2);

        final RadioGroup rad = (RadioGroup) findViewById(R.id.sex2);

        //저장된 값들 불러오기
        String text1 = pref.getString("edit1", "");
        String text2 = pref.getString("age2", "");

        edit1.setText(text1);
        edit2.setText(text2);

        //라디오

        final RadioGroup userSex = findViewById(R.id.userSex);
        userSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.woman:
                        engSex = "woman";
                        Toast.makeText(getApplicationContext(), "여성 입니다", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.man:
                        Toast.makeText(getApplicationContext(), "남성 입니다", Toast.LENGTH_LONG).show();
                        engSex = "man";
                        break;
                }



            }
        });







        //등록->홈화면
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                if (edit1.getText().toString().length() == 0) {
                    Toast.makeText(UserActivity.this, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show();
                    edit1.requestFocus();
                    return;
                }

                if (edit2.getText().toString().length() == 0) {
                    Toast.makeText(UserActivity.this, "나이를 입력하세요!", Toast.LENGTH_SHORT).show();
                    edit2.requestFocus();
                    return;
                }
                // 데이터베이스쪽 추가하는 부분 입니다.
                final Map<String, Object> post = new HashMap<>();

                post.put("id", id);
                post.put("name", edit1.getText().toString());
                post.put("age", edit2.getText().toString());
                post.put("rad", engSex);



                mStore.collection("user").document(id).set(post)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                Toast.makeText(getApplication(), "등록 완료", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        //빈칸 확인

    }

    //뒤로가기 금지
    public void onBackPressed() {
        //super.onBackPressed();
    }
}


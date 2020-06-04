package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class User_SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    String id, name, age, sex, engSex;
    EditText etName, etAge;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_setting);

        Button home = (Button)findViewById(R.id.home_button);
        Button btnRetouch = (Button)findViewById(R.id.ok_button2);

        etName = (EditText)findViewById(R.id.name2);
        RadioGroup rg = (RadioGroup)findViewById(R.id.sex2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        final RadioButton rbWoman = (RadioButton)findViewById(R.id.woman);
        final RadioButton rbMan = (RadioButton)findViewById(R.id.man);
        etAge = (EditText)findViewById(R.id.age2);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        HomeActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        // 처음에 화면들어오면 뿌려주기 위한 설정입니다.
        mStore.collection("user")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        for (QueryDocumentSnapshot dc : queryDocumentSnapshots) {
                            id = (String) dc.getData().get("id");
                            name = (String)dc.getData().get("name");
                            age = (String)dc.getData().get("age");
                            sex = (String)dc.getData().get("rad");
                        }
                        etName.setText(name);
                        etAge.setText(age);
                        if(sex.equals("woman")){
                            rbWoman.setChecked(true);
                        }else{
                            rbMan.setChecked(true);
                        }
                    }

                });

        btnRetouch.setOnClickListener(this);
    }
    // 수정부분입니다.
    public void onClick(View view){
        mStore.collection("user").document(id)
                .update("name" , etName.getText().toString(), "age",etAge.getText().toString(),
                        "rad", engSex)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(User_SettingActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(User_SettingActivity.this, "수정실패!", Toast.LENGTH_SHORT).show();
                    }
                });
        Toast.makeText(getApplicationContext(), "사용자 정보가 수정되었습니다", Toast.LENGTH_SHORT).show();
    }
}
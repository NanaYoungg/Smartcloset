package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.actionbar.MenuActivity;
import com.example.actionbar.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class HomeActivity  extends AppCompatActivity {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //메뉴버튼
        Button menubtn = (Button)findViewById(R.id.menu_btn);
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        MenuActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });



        //옷걸이 버튼으로
        ImageButton b1 = (ImageButton)findViewById(R.id.hanger1);
        ImageButton b2 = (ImageButton)findViewById(R.id.hanger2);
        ImageButton b3 = (ImageButton)findViewById(R.id.hanger3);
        ImageButton b4 = (ImageButton)findViewById(R.id.hanger4);
        ImageButton b5 = (ImageButton)findViewById(R.id.hanger5);
        ImageButton b6 = (ImageButton)findViewById(R.id.hanger6);
        ImageButton b7 = (ImageButton)findViewById(R.id.hanger7);
        ImageButton b8 = (ImageButton)findViewById(R.id.hanger8);
        //이미지
        ImageView img1 = (ImageView)findViewById(R.id.hang_img1);
        ImageView img2 = (ImageView)findViewById(R.id.hang_img2);
        ImageView img3 = (ImageView)findViewById(R.id.hang_img3);
        ImageView img4 = (ImageView)findViewById(R.id.hang_img4);
        ImageView img5 = (ImageView)findViewById(R.id.hang_img5);
        ImageView img6 = (ImageView)findViewById(R.id.hang_img6);




        FirebaseStorage storage = FirebaseStorage.getInstance("gs://everywear-794cd.appspot.com");
        StorageReference storageRef = storage.getReference();

      //첫번째 행거
        StorageReference pathReference = storageRef.child("WearPhotos/" + "cloth" + "_" +1);// 여기서는 1만 써서 첫번째 행거만 했는데, 숫자를 바꾸면 다른행거도 가져오기 가능해요
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img1);

        //두번째 행거
        StorageReference pathReference2 = storageRef.child("WearPhotos/" + "cloth" + "_" +2);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img2);
        //세번째 행거
        StorageReference pathReference3 = storageRef.child("WearPhotos/" + "cloth" + "_" +3);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference3)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img3);

        //네번째 행거
        StorageReference pathReference4 = storageRef.child("WearPhotos/" + "cloth" + "_" +4);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference4)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img4);

        //다섯번째 행거
        StorageReference pathReference5 = storageRef.child("WearPhotos/" + "cloth" + "_" +5);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference5)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img5);

        //여섯번째 행거
        StorageReference pathReference6 = storageRef.child("WearPhotos/" + "cloth" + "_" +6);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference6)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img6);






        //옷걸이버튼 8개 등록화면으로 넘겨주기

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","1");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });

        b2.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","2");
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","3");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","4");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","5");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","6");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","7");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("hanger","8");
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
    }





    public void onBackPressed() {
        //super.onBackPressed();
    }
}
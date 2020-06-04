package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CategoryActivity extends Activity  {
    static final int REQUEST_TAKE_PHOTO = 1;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private String id,top_k;

    RadioGroup category;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        Button btn_ok = (Button)findViewById(R.id.cate_ok);
        Button btn_no = (Button)findViewById(R.id.cate_no);
        ImageView img = (ImageView)findViewById(R.id.cateimg);
        category = findViewById(R.id.top_k);
        id = mStore.collection("category2").document().getId();
        //SharedPreferences
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

       ///라디오




        //등록버튼
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        HomeActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        //취소버튼
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ClothAddActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });


        //이미지뷰에 뿌리기
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://everywear-794cd.appspot.com");
        StorageReference storageRef = storage.getReference();

//첫번째 행거
        StorageReference pathReference1 = storageRef.child("WearPhotos/" + "cloth" + "_" +1);// 여기서는 1만 써서 첫번째 행거만 했는데, 숫자를 바꾸면 다른행거도 가져오기 가능해요
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference1)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);

        //두번째 행거
        StorageReference pathReference2 = storageRef.child("WearPhotos/" + "cloth" + "_" +2);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);
        //세번째 행거
        StorageReference pathReference3 = storageRef.child("WearPhotos/" + "cloth" + "_" +3);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference3)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);
        //네번째 행거
        StorageReference pathReference4 = storageRef.child("WearPhotos/" + "cloth" + "_" +4);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference4)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);


        //다섯번째 행거
        StorageReference pathReference5 = storageRef.child("WearPhotos/" + "cloth" + "_" +5);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference5)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);

        //여섯번째 행거
        StorageReference pathReference6 = storageRef.child("WearPhotos/" + "cloth" + "_" +6);
        Glide.with(getApplication())
                .using(new FirebaseImageLoader())
                .load(pathReference6)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .override(400, 500)
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .into(img);






       //데이터베이스쪽 추가하는 부분 입니다. 버튼클릭 -> 등록


       /* category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

               switch (checkedId) {
                    case R.id.ban:
                        top_k = "ban";
                        Toast.makeText(getApplicationContext(), "반팔 입니다", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.gin:
                        top_k = "gin";
                        break;
                    case R.id.hood:
                        top_k = "hood";
                        break;
                    case R.id.nasi:
                        top_k = "nasi";
                        break;

                }

            }
        });


*/
  /*      btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map<String, Object> post = new HashMap<>();
                post.put("top_k", top_k);


                mStore.collection("category2").document(id).set(post)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(CategoryActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CategoryActivity.this, "등록 실패!", Toast.LENGTH_SHORT).show();
                            }
                        });
                         Toast.makeText(getApplicationContext(), "등록 완료 !", Toast.LENGTH_SHORT).show();
            }

        });*/

}


    public void onBackPressed() {
        //super.onBackPressed();
    }

}

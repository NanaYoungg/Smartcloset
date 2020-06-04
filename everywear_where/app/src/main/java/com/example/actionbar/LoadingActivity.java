package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class LoadingActivity  extends Activity {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_loading);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 파이어베이스에서 데이터 있는지 찾아보고 하나 이상있으면 바로 Home으로 넘어가게 해놨어요
                mStore.collection("user")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                                for (QueryDocumentSnapshot dc : queryDocumentSnapshots) {
                                    String id = (String) dc.getData().get("id");
                                    count++;
                                }
                                if(count>=1){
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                                    startActivity(intent);
                                    finish();
                                }else if(count==0){
                                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        });
            }
        }, 2000); //2초
    }
}

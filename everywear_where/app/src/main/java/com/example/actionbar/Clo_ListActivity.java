package com.example.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Clo_ListActivity  extends AppCompatActivity {
    private ArrayList _profiles = null;

    private RecyclerView mCloRecyclerView;
    private CloAdapter mAdapter;
    private List<CloItem> mCloList;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    String kind=null, jazil=null, hanger= null;


    //내옷목록
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closth_list);

        //리스트뷰
        mCloRecyclerView = (RecyclerView) findViewById(R.id.recycler_Cloth);
        mCloList = new ArrayList<>();
        mStore.collection("category").orderBy("hanger").limit(100)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                         @Override
                                         public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                             for (QueryDocumentSnapshot dc : queryDocumentSnapshots) {
                                                 kind = (String) dc.getData().get("kind");
                                                 jazil = (String) dc.getData().get("jazil");
                                                // color = (String) dc.getData().get("color");
                                                 hanger =(String) dc.getData().get("hanger");

                                                 CloItem data = new CloItem(kind, jazil, hanger);

                                                 mCloList.add(data);
                                             }
                                             mAdapter = new CloAdapter(mCloList, getApplicationContext());
                                             mCloRecyclerView.setAdapter(mAdapter);
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

        //내옷편집
        Button editbtn = (Button)findViewById(R.id.clo_edit);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Clo_EditActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
}
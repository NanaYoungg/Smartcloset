package com.example.actionbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Clo_EditActivity extends Activity {
    final Context context = this;
    private Button button;
    private ArrayList _profiles = null;

    private RecyclerView mCloRecyclerView;
    private CloAdapter mAdapter;
    private List<CloItem> mCloList;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    String kind=null, jazil=null, hanger= null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closth_edit);
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
                        Clo_ListActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }}


       /* //리스트뷰
        ListView listView = (ListView) findViewById(R.id.closth_list);
        ListAdapter_cloedit adapter = new ListAdapter_cloedit(this);
        listView.setAdapter(adapter);


        //삭제에대한 묻기 얼랏다이얼로그
        button = (Button) findViewById(R.id.delet_clo);
        // 클릭 이벤트
        button.setOnClickListener((View.OnClickListener) this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delet_clo:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // 제목셋팅
                alertDialogBuilder.setTitle(" ");
                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("정말 삭제하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 프로그램을 종료한다
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("아니요",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();
                // 다이얼로그 보여주기
                alertDialog.show();
                break;
            default:
                break;

        }

    }
}*/
package com.example.actionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

//내옷목록 리스트
public class CloAdapter extends RecyclerView.Adapter<CloAdapter.CloViewHolder> {
    private List<CloItem> mCloList;
    private Context context;

    public CloAdapter(List<CloItem> mCloList, Context context) {
        this.mCloList = mCloList;
        this.context = context;
    }

    @NonNull
    @Override
    public CloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CloViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.clo_list_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CloViewHolder holder, int position) {
        CloItem data = mCloList.get(position);
        //holder.cloColorTextView.setText("색깔 : " + data.getColor());
        holder.cloKindTextView.setText("종류 : " +data.getKind());
        holder.cloQualityTextView.setText("재질 : "+data.getQuality());
        String hanger = data.getImage();

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://everywear-794cd.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference  = storageRef.child("WearPhotos/" + "cloth_" +hanger);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(pathReference)
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 디스크 캐시 저장 off
                .skipMemoryCache(true)// 메모리 캐시 저장 off
                .override(60, 70)
                .into(holder.cloImageView);

    }

    @Override
    public int getItemCount() {
        return mCloList.size();
    }

    class CloViewHolder extends RecyclerView.ViewHolder {

        private TextView cloColorTextView;
        private TextView cloKindTextView;
        private TextView cloQualityTextView;
        private ImageView cloImageView;

        public CloViewHolder(View itemView) {
            super(itemView);
          //  cloColorTextView = itemView.findViewById(R.id.clo_color);
            cloKindTextView = itemView.findViewById(R.id.kind);
            cloQualityTextView = itemView.findViewById(R.id.quality);
            cloImageView = itemView.findViewById(R.id.lan_photo);
        }
    }
}
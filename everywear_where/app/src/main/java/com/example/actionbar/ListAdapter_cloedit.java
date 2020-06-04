package com.example.actionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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


//내옷 편집
public class ListAdapter_cloedit extends RecyclerView.Adapter<ListAdapter_cloedit.ListViewHolder> {
    private List<ListItem_cloedit> mListedit;
    private Context context;

    public ListAdapter_cloedit(List<ListItem_cloedit> mListedit, Context context) {
        this.mListedit = mListedit;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.clo_edit_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListItem_cloedit data = mListedit.get(position);
        holder.ListKindTextView.setText("종류 : " +data.getKind());
        holder.ListQualityTextView.setText("재질 : "+data.getQuality());

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
                .into(holder.ListImageView);

    }

    @Override
    public int getItemCount() {
        return mListedit.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView ListKindTextView;
        private TextView ListQualityTextView;
        private ImageView ListImageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            ListKindTextView = itemView.findViewById(R.id.kind);
            ListQualityTextView = itemView.findViewById(R.id.quality);
            ListImageView = itemView.findViewById(R.id.lan_photo);
        }
    }
}
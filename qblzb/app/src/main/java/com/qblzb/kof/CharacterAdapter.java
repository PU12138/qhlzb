package com.qblzb.kof;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.VH> {

    public interface OnItemClickListener {
        void onItemClick(Character ch);
    }

    private List<Character> data = new ArrayList<>();
    private final OnItemClickListener listener;

    public CharacterAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Character> list) {
        this.data = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Character ch = data.get(position);
        h.tvName.setText(ch.name);
        h.tvAvatarText.setText(ch.name.substring(0, Math.min(1, ch.name.length())));
        if (ch.hidden) {
            h.tvHidden.setVisibility(View.VISIBLE);
            h.tvHidden.setText("隐藏");
        } else {
            h.tvHidden.setVisibility(View.GONE);
        }
        // 隐藏人物头像底色不同
        if (ch.hidden) {
            h.tvAvatarText.setTextColor(Color.parseColor("#ff6b6b"));
        } else {
            h.tvAvatarText.setTextColor(Color.parseColor("#f5f5f5"));
        }
        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(ch);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvHidden, tvAvatarText;
        VH(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvName);
            tvHidden = v.findViewById(R.id.tvHidden);
            tvAvatarText = v.findViewById(R.id.tvAvatarText);
        }
    }
}

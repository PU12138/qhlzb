package com.qblzb.kof;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CharacterDetailActivity extends AppCompatActivity {

    private TextView tvName, tvNameEn, tvStory, tvAvatarText;
    private LinearLayout combosContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        tvName = findViewById(R.id.tvName);
        tvNameEn = findViewById(R.id.tvNameEn);
        tvStory = findViewById(R.id.tvStory);
        tvAvatarText = findViewById(R.id.tvAvatarText);
        combosContainer = findViewById(R.id.combosContainer);

        String id = getIntent().getStringExtra("id");
        String version = getIntent().getStringExtra("version");

        Character ch = findCharacter(id, version);
        if (ch != null) {
            bindCharacter(ch);
        } else {
            tvName.setText("未找到人物");
        }
    }

    private Character findCharacter(String id, String version) {
        List<Character> list = "98".equals(version)
                ? CharacterData.get98Characters()
                : CharacterData.get97Characters();
        for (Character c : list) {
            if (c.id.equals(id)) return c;
        }
        return null;
    }

    private void bindCharacter(Character ch) {
        tvName.setText(ch.name + (ch.hidden ? "（隐藏）" : ""));
        tvNameEn.setText(ch.nameEn);
        tvStory.setText(ch.story);
        tvAvatarText.setText(ch.name.substring(0, Math.min(1, ch.name.length())));
        if (ch.hidden) {
            tvAvatarText.setTextColor(Color.parseColor("#ff6b6b"));
        }

        // 隐藏人物显示解锁方法
        if (ch.hidden && ch.unlock != null && !ch.unlock.isEmpty()) {
            addUnlockInfo(ch.unlock);
        }

        // 渲染连招列表
        for (String combo : ch.combos) {
            addComboItem(combo);
        }
    }

    private void addUnlockInfo(String text) {
        // 标题
        TextView title = new TextView(this);
        title.setText("★ 解锁方法");
        title.setTextSize(15);
        title.setTextColor(Color.parseColor("#ff6b6b"));
        title.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 12, 0, 4);
        title.setLayoutParams(lp1);
        combosContainer.addView(title);

        // 解锁内容
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(13);
        tv.setTextColor(Color.parseColor("#f5f5f5"));
        tv.setPadding(16, 8, 12, 8);
        tv.setBackgroundColor(Color.parseColor("#2d1b3d"));
        tv.setLineSpacing(3, 1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(0, 0, 0, 10);
        tv.setLayoutParams(lp2);
        combosContainer.addView(tv);
    }

    private void addComboItem(String text) {
        // 分类标题（含冒号的作为标题）
        boolean isCategory = text.contains("特殊技") || text.contains("必杀")
                || text.contains("超必杀") || text.contains("连招");

        TextView tv = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 6, 0, 6);
        tv.setLayoutParams(lp);
        tv.setText(text);

        if (isCategory) {
            tv.setTextSize(15);
            tv.setTextColor(Color.parseColor("#ffd700"));
            tv.setTypeface(null, android.graphics.Typeface.BOLD);
        } else {
            tv.setTextSize(13);
            tv.setTextColor(Color.parseColor("#f5f5f5"));
            tv.setPadding(24, 4, 12, 4);
            tv.setBackgroundColor(Color.parseColor("#0f3460"));
        }
        combosContainer.addView(tv);
    }
}

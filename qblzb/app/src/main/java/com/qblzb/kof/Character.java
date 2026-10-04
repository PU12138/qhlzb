package com.qblzb.kof;

import java.util.ArrayList;
import java.util.List;

public class Character {
    public String id;
    public String name;          // 中文名
    public String nameEn;        // 英文名
    public String version;       // "97" 或 "98"
    public boolean hidden;       // 是否隐藏人物
    public String story;         // 背景故事
    public List<String> combos;  // 连招列表

    public Character(String id, String name, String nameEn, String version,
                     boolean hidden, String story, List<String> combos) {
        this.id = id;
        this.name = name;
        this.nameEn = nameEn;
        this.version = version;
        this.hidden = hidden;
        this.story = story;
        this.combos = combos != null ? combos : new ArrayList<>();
    }
}

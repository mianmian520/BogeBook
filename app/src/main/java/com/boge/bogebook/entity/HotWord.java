package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 * 热门搜索
 */

public class HotWord {


    /**
     * hotWords : ["暖妻成瘾","Boss太嚣张：老公，结婚吧","都市奇门医圣","九幽天帝","我欲封天","余罪","绝宠妖妃：邪王，太闷骚！","娇宠令","地府朋友圈","苗疆蛊事","鬼王盛宠：纨绔医妃有点野","妖孽狂妃：邪君宠溺小妖妃","无敌秒杀系统","新夫上任，早安老婆大人！","仙武道纪","盛宠妻宝","契约婚姻，娶一赠一","百万萌妻：总裁的私密眷宠","英雄联盟之谁与争锋","惹火燃情：总裁，慢点追","豪门重生之妇贵逼人","圣魔猎手","痴傻废柴三小姐：医手遮天","绝色元素师：邪王的小野妃","318女生宿舍"]
     * ok : true
     */

    private boolean ok;
    private List<String> hotWords;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<String> getHotWords() {
        return hotWords;
    }

    public void setHotWords(List<String> hotWords) {
        this.hotWords = hotWords;
    }
}

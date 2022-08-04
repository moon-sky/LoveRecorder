/**
  * Copyright 2022 bejson.com 
  */
package com.moonvsky.testbirthday.service.diary.bean.articleinfo;
import java.util.List;

/**
 * Auto-generated: 2022-08-04 9:57:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class User {

    private long id;
    private String slug;
    private String nickname;
    private int gender;
    private String avatar;
    private String intro;
    private String intro_compiled;
    private int wordage;
    private int likes_count;
    private List<String> badges;
    private String vip;
    private boolean liked_by_user;
    private boolean liked_user;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setSlug(String slug) {
         this.slug = slug;
     }
     public String getSlug() {
         return slug;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setGender(int gender) {
         this.gender = gender;
     }
     public int getGender() {
         return gender;
     }

    public void setAvatar(String avatar) {
         this.avatar = avatar;
     }
     public String getAvatar() {
         return avatar;
     }

    public void setIntro(String intro) {
         this.intro = intro;
     }
     public String getIntro() {
         return intro;
     }

    public void setIntro_compiled(String intro_compiled) {
         this.intro_compiled = intro_compiled;
     }
     public String getIntro_compiled() {
         return intro_compiled;
     }

    public void setWordage(int wordage) {
         this.wordage = wordage;
     }
     public int getWordage() {
         return wordage;
     }

    public void setLikes_count(int likes_count) {
         this.likes_count = likes_count;
     }
     public int getLikes_count() {
         return likes_count;
     }

    public void setBadges(List<String> badges) {
         this.badges = badges;
     }
     public List<String> getBadges() {
         return badges;
     }

    public void setVip(String vip) {
         this.vip = vip;
     }
     public String getVip() {
         return vip;
     }

    public void setLiked_by_user(boolean liked_by_user) {
         this.liked_by_user = liked_by_user;
     }
     public boolean getLiked_by_user() {
         return liked_by_user;
     }

    public void setLiked_user(boolean liked_user) {
         this.liked_user = liked_user;
     }
     public boolean getLiked_user() {
         return liked_user;
     }

}
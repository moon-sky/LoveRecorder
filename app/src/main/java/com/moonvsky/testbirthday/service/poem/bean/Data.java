/**
  * Copyright 2022 bejson.com 
  */
package com.moonvsky.testbirthday.service.poem.bean;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2022-07-15 16:43:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String id;
    private Weather weather;
    private String date;
    private List<Content_list> content_list;
    private Menu menu;
    private List<String> ad;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setWeather(Weather weather) {
         this.weather = weather;
     }
     public Weather getWeather() {
         return weather;
     }

    public void setDate(String date) {
         this.date = date;
     }
     public String getDate() {
         return date;
     }

    public void setContent_list(List<Content_list> content_list) {
         this.content_list = content_list;
     }
     public List<Content_list> getContent_list() {
         return content_list;
     }

    public void setMenu(Menu menu) {
         this.menu = menu;
     }
     public Menu getMenu() {
         return menu;
     }

    public void setAd(List<String> ad) {
         this.ad = ad;
     }
     public List<String> getAd() {
         return ad;
     }

}
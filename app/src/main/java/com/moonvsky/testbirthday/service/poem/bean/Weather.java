/**
  * Copyright 2022 bejson.com 
  */
package com.moonvsky.testbirthday.service.poem.bean;
import java.util.Date;

/**
 * Auto-generated: 2022-07-15 16:43:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Weather {

    private String city_name;
    private Date date;
    private String temperature;
    private String humidity;
    private String climate;
    private String wind_direction;
    private String hurricane;
    private Icons icons;
    public void setCity_name(String city_name) {
         this.city_name = city_name;
     }
     public String getCity_name() {
         return city_name;
     }

    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
         return date;
     }

    public void setTemperature(String temperature) {
         this.temperature = temperature;
     }
     public String getTemperature() {
         return temperature;
     }

    public void setHumidity(String humidity) {
         this.humidity = humidity;
     }
     public String getHumidity() {
         return humidity;
     }

    public void setClimate(String climate) {
         this.climate = climate;
     }
     public String getClimate() {
         return climate;
     }

    public void setWind_direction(String wind_direction) {
         this.wind_direction = wind_direction;
     }
     public String getWind_direction() {
         return wind_direction;
     }

    public void setHurricane(String hurricane) {
         this.hurricane = hurricane;
     }
     public String getHurricane() {
         return hurricane;
     }

    public void setIcons(Icons icons) {
         this.icons = icons;
     }
     public Icons getIcons() {
         return icons;
     }

}
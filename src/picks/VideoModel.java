/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks;

import java.net.URLEncoder;

/**
 *
 * @author koudejian
 */
public class VideoModel {
    String ITEM = "#item#";
    String SUB_ITEM = "#slip#";
    
    String tid = "0";
    String url = null;          //视频地址
    String name = null;         //视频名称
    String image_url = null;    //视频介绍图片
    String during = "--:--";    //视频时间
    
    public VideoModel(String name, String url, String during, String image){
        this.url = url;
        this.name = name;
        this.during = during;
        this.image_url = image;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDuring(String during) {
        this.during = during;
    }

    public String getTid() {
        return tid;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDuring() {
        return during;
    }

    public String getName(){
        return name;
    }
    
    public String getUrl(){
        return url;
    }
    
    public String toString(){
        return ITEM + name + SUB_ITEM + url + SUB_ITEM + during + SUB_ITEM + image_url;
    }

}

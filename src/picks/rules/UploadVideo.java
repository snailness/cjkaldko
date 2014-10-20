/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks.rules;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import picks.Config;
import picks.HttpHelper;
import picks.VideoModel;
import picks.tasks.PickImageTask;

/**
 *
 * @author koudejian
 */
public class UploadVideo {
    
    private String URL = "http://218.244.156.133/starcraft/admin/script/api.php?action=uploadVideoInfo";
    private List<VideoModel> mVideoList = new ArrayList<VideoModel>();
    
    private String mTitle = "";
    public void setTitle(String title){
        mTitle = title;
    }
    
    public void add(VideoModel video){
        mVideoList.add(video);
    }
    public void clear(){
        mVideoList.clear();
    }
    
    public String toString(){
        String result = "";
        for(int i = 0; i < mVideoList.size(); i++){
            result += mVideoList.get(i).toString();
        }
        return result;
    }
    /**
     * 默认上传专辑
     */
    public void upload(){
        upload("", "0");
    }
    public void upload(String time, String flag){
        String url = URL
//                + "&action=uploadVideoInfo"
                + "&title=" + mTitle
                + "&data=" + toString()
                + "&time=" + time
                + "&havetime=" + flag
                ;
        Document result = HttpHelper.httpGet(URLEncoder.encode(url));
        if(result != null){
            String t = result.getElementsByTag("body").toString();
            if(t.indexOf("0") != -1){
                System.out.println("pickdone");
            }else{
                System.out.println("pickrepick");
            }
        }
    }
}

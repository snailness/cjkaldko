/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import picks.factorys.PickFunction;
import picks.rules.PickBase;

/**
 *
 * @author koudejian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length > 0){
            String url = args[0];
            if("".equals(url)){
                url = "http://www.youku.com/playlist_show/id_5654073.html?sf=10300";
                return;
            }
            PickBase task = PickFunction.get(url);
            if(task == null){
                return;
            }
            while(task.pick()){
                task.pick();
            }
        }
        /*
        else{
            String url = "";
//            url = "http://www.youku.com/playlist_show/id_5654073.html?sf=10300";
            url = "http://sc2.plu.cn/vod/gsl2011/";
//            url = "http://www.soku.com/search_playlistdetail?q=%E6%98%9F%E9%99%85%E4%BA%89%E9%9C%B82&fi=FMTg0MDAwNDM3Mg==&fs=14&mr=%E6%98%9F%E9%99%85%2C%E6%98%9F%E9%9A%9B%2C%E4%BA%89%E9%9C%B8%2C%E7%88%AD%E8%A6%87%2C2&sf=10100";
            PickBase task = PickFunction.get(url);
            if(task == null){
                return;
            }
            while(task.pick()){
                task.pick();
            }
        }
        //*/
    }
}

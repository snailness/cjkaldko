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
            }
            PickBase task = PickFunction.get(url);
            while(task.pick()){
                task.pick();
            }
        }
    }
}

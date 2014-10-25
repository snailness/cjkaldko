/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks.factorys;

import picks.rules.PickBase;
import picks.rules.PickSC2;
import picks.rules.PickSuku;
import picks.rules.PickYouKu;

/**
 *
 * @author koudejian
 */
public class PickFunction {
    
    static String YOUKU = "http://www.youku.com/playlist_show";
    static String SUKU = "http://www.soku.com/search_playlistdetail";
    static String SC2 = "http://sc2.plu.cn";
    public static PickBase get(String url){
        PickBase pickBase = null;
        if(url == null){
            
        }else if(url.startsWith(YOUKU)){
            pickBase = new PickYouKu(url);
        }else if(url.startsWith(SUKU)){
            pickBase = new PickSuku(url);
//            pickBase = null;
        }else if(url.startsWith(SC2)){
            pickBase = new PickSC2(url);
        }
        return pickBase;
    }
}

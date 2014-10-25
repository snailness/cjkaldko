/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks.rules;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import picks.models.VideoModel;
import picks.utils.HtmlUtils;

/**
 *
 * @author koudejian
 */
public class PickSuku extends PickBase{

    public PickSuku(String url) {
        super(url);
    }

    @Override
    public void pickInfo(Document document) {
        //To change body of generated methods, choose Tools | Templates.
        //*
        if(document == null){
            return;
        }
        Elements datas = document.getElementsByClass("album_item");
        if(datas == null){
            return;
        }
        for(int i = 0; i < datas.size(); i++){
            Element temp = datas.get(i);
            if(temp == null){
                continue;
            }
            String data = temp.toString();
//            System.out.println(data);
            /**
             * <div class="album_item"> 
                <div class="pic"> 
                 <i class="ico__SD"></i> 
                 <a class="link" onmouseup="return $.playlist.mouseup(this);" 
                 playfolder="true" folderid="460001093" folderindex="0" href="http://v.youku.com/v_show/id_XNzc3MzkwMTYw_type_99.html?f=460001093" 
                 target="_blank" title="[NEL]HOC-少帮主 VS WE-葫芦呼噜 炉石传说比赛" _log_ct="61"> 
                 <img src="http://g1.ykimg.com/0100641F46540DF97FB5CE04C3AFFA1C456CBF-8015-78A3-A939-B478EEB6E970" onerror="this.src='http://g2.ykimg.com/0900641F464A911EDD00000000000000000000-0000-0000-0000-00009197BA80';" /> 
                  <div class="time">
                   <span> 32:45 </span>
                  </div> </a> 
                </div> 
                <div class="txt"> 
                 <p class="tit"> <a playfolder="true" folderid="460001093" folderindex="0" href="http://v.youku.com/v_show/id_XNzc3MzkwMTYw_type_99.html?f=460001093" target="_blank" title="[NEL]HOC-少帮主 VS WE-葫芦呼噜 炉石传说比赛" _log_ct="61"> [NEL]HOC-少帮主 VS WE-葫芦呼噜 炉石传说比 </a> </p> 
                 <p>用户: <a target="_blank" href="http://i.youku.com/u/UMzE5NzMzNzM2" title="星际少帮主" _log_ct="61">星际少帮主</a></p> 
                 <p>播放: 38,623</p> 
                 <p>发布: 1月前</p> 
                </div> 
               </div>
             */
            //*
            String during = getTextStr("<div class=\"time\">", "</div>", data);
            during = HtmlUtils.delHtml(during);
            during = HtmlUtils.delBlank(during);
            
            String image = getTextStr("<img src=\"", "\"", data);
            
            String temp_str = getTextStr("<p class=\"tit\">", "</p>", data);
            String url = getTextStr("href=\"", "\"", temp_str);
            String name = getTextStr("title=\"", "\"", temp_str);
            VideoModel video = new VideoModel(name, url, during, image);
//            System.out.println(video.toString());
            //add to queue
            add(video);
            //*/ 
        }
    }

    @Override
    public void pickTitle(Document document) {
        if(document == null){
            return;
        }
        Elements element = document.getElementsByClass("title");
        String title = HtmlUtils.delHtml(element.toString());
        if(!"".equals(title)){
            setTitle(title);
        }
    }
    
    @Override
    public boolean haveNext(Document document){
        String site = "http://www.soku.com";
        boolean result = false;
        if(document != null){
            String pages_html = getTextStr("<div class=\"sk_pager\">", "</div>", document.toString());
//            System.out.println(pages_html);
            if(pages_html.contains("下一页")){
                String next = getTextStr("<li class=\"next\">", "</li>", pages_html);
                this.mUrl = getTextStr("<a href=\"", "\"", next);
                if(!this.mUrl.startsWith(site)){
                    this.mUrl = site + this.mUrl;
                }
//                System.out.println("\n\n" + this.mUrl);
                if(!"".equals(mUrl)){
                    result = true;
                }
            }else{
//                System.out.println("not contains");
            }
        }else{
//            System.out.println("null");
        }
        return result;
    }
}

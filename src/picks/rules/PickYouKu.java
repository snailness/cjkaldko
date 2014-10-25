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

/**
 *
 * @author koudejian
 */
public class PickYouKu extends PickBase{

    public PickYouKu(String url) {
        super(url);
    }

    @Override
    public void pickInfo(Document document) {
        //To change body of generated methods, choose Tools | Templates.
        if(document == null){
            return;
        }
        Elements datas = document.getElementsByClass("v");
        if(datas == null){
            return;
        }
        for(int i = 0; i < datas.size(); i++){
            Element temp = datas.get(i);
            if(temp == null){
                continue;
            }
            String data = temp.toString();
            
            String during = getTextStr("<span class=\"num\">", "</span>", data);
            String image = getTextStr("<img src=\"", "\"", data);
            String url = getTextStr("href=\"", "\"", data);
            String name = getTextStr("target=\"_blank\">", "</a>", data);
            VideoModel video = new VideoModel(name, url, during, image);
            //add to queue
            add(video);
            
        }
    }

    @Override
    public void pickTitle(Document document) {
        if(document == null){
            return;
        }
        String title = getTextStr("span class=\"name\">", "</span>", document.toString());
        if(!"".equals(title)){
            setTitle(title);
        }
    }
    
    @Override
    public boolean haveNext(Document document){
        boolean result = false;
        Elements pages = document.getElementsByClass("pageBar");
        String pages_html = pages.toString();
        if(pages_html.contains("下一页")){
            String next = getTextStrDesc("<a href=\"", "下一页", pages_html);
            this.mUrl = getTextStr(null, "\"", next);
            if(!"".equals(mUrl)){
                result = true;
            }
        }
        return result;
    }
    
}

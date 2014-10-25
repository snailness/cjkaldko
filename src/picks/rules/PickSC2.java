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
public class PickSC2 extends PickBase{

    public PickSC2(String url) {
        super(url);
    }

    @Override
    public void pickInfo(Document document) {
        //To change body of generated methods, choose Tools | Templates.
        if(document == null){
            return;
        }
        Elements datas = document.getElementsByTag("li");
        if(datas == null){
            return;
        }
        for(int i = 0; i < datas.size(); i++){
            Element temp = datas.get(i);
            if(temp == null){
                continue;
            }
            String data = temp.toString();
            if(data.length() < 200){
                continue;
            }
            /**
             * <li> <a href="http://sc2.plu.cn/vod/gsl2011/5130.html" target="_blank">
             * <img src="http://www.plu.cn/d/file/sc2/vod/gsl2011/2011-08-30/7986262d8efa2a80855a85a1df26a18f.jpg" /></a> 
             * <span><a href="http://sc2.plu.cn/vod/gsl2011/5130.html" target="_blank">超搞笑GSL踢馆节目 May 2</a></span>
             * <p>更新: 2011-08-30</p></li>
             */
            
            String during = "--:--";
            String image = getTextStr("<img src=\"", "\"", data);
            String url = getTextStr("<span><a href=\"", "\"", data);
            String name = getTextStr("target=\"_blank\">", "</a>", getTextStr("<span>", "</span>", data));
            VideoModel video = new VideoModel(name, url, during, image);
            //add to queue
            add(video);
//            System.out.println(video.toString());
            
        }
    }

    @Override
    public void pickTitle(Document document) {
        if(document == null){
            return;
        }
        String temp = getTextStr("<div id=\"mnav\">", "</div>", document.toString());
        int position = temp.lastIndexOf("</a>");
        if(position != -1){
            String title = temp.substring(position + "</a>&nbsp;&gt;&nbsp;".length(), temp.length());
            setTitle(title);
            
            if(title != null){
                //分离时间
                String temp_str = title.replaceAll("[0-9]", "");
                if(!title.equals(temp_str) && title.startsWith(temp_str)){
                    this.mTime = title.substring(temp_str.length(), title.length());
                }
            }
        }
    }
    
    @Override
    public boolean haveNext(Document document){
        boolean result = false;
        if(document != null){
            String pages_html = getTextStr("<div id=\"pagelist\">", "</div>", document.toString());
            if(pages_html.contains("下一页")){
                String next = getTextStrDesc("<a href=\"", "下一页", pages_html);
                this.mUrl = getTextStr(null, "\"", next);
                if(!"".equals(mUrl)){
                    result = true;
                }
            }
        }
        return result;
    }
    
}

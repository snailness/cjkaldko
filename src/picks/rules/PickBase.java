/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import picks.HttpHelper;
import picks.models.VideoModel;

/**
 *
 * @author koudejian
 */
public abstract class PickBase extends UploadVideo{
    
    public String mUrl = null;
    
    public PickBase(String url){
        this.mUrl = url;
    }
    
    public abstract void pickTitle(Document document);
    
    public abstract void pickInfo(Document document);
    
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
    
    public boolean pick(){
        Document document = HttpHelper.httpGet(mUrl);
        if(document == null){
            return false;
        }
        
        pickTitle(document);
        //pick
        pickInfo(document);

        //upload
        upload();
        
        return haveNext(document);
    }
    
    public int isSubString(String key, String source){
        if(source == null){
            return -1;
        }
        return source.indexOf(key);
    }
    public String getTextStr(String start, String end, String source){
        String result = null;
        if(source == null){
            return null;
        }
        if(start != null){
            int p = isSubString(start, source);
            result = source.substring(p + start.length(), source.length());
            source = result;
        }
        if(end != null){
            int p = isSubString(end, source);
            result = source.substring(0, p);
        }
        if(result != null){
//            System.out.println( "\n\n\n\n\n" + result + "\n\n\n\n\n");
        }
        return result;
    }
    public String getTextStrDesc(String start, String end, String source){
        String result = null;
        if(source == null){
            return null;
        }
        if(end != null){
            int p = isSubString(end, source);
            result = source.substring(0, p);
            source = result;
        }
        if(start != null){
            int p = source.lastIndexOf(start);
            result = source.substring(p + start.length(), source.length());
        }
        
        if(result != null){
//            System.out.println( "\n\n\n\n\n" + result + "\n\n\n\n\n");
        }
        
        return result;
    }
    public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}

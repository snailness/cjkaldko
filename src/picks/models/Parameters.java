/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picks.models;

/**
 *
 * @author koudejian
 */
public class Parameters {
    String key = "";
    String value = "";
    
    public Parameters(String key, String value){
        this.key = key;
        this.value = value;
    }
    
    public String getKey(){
        return key;
    }
    
    public String getValue(){
        return value;
    }
}

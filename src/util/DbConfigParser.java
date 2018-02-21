package util;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public final class DbConfigParser {
    
    public static String getHost(){
        return extractor("host");
    }
    
    public static String getUsername(){
        return extractor("username");
    }
    
    public static String getPassword(){
        return extractor("password");
    }
    
    public static String getDbName(){
        return extractor("db_name");
    }
    
    public static String getPort(){
        return extractor("port");
    }
    
    private static String extractor(String paramName){
        try {
            JSONObject jsonObject = JSONParserUtils
                    .extractor(new FileReader(new File("").getAbsolutePath()+"/assets/conf/db_config.json"));
            return (String) jsonObject.get(paramName);
        } catch (Exception ex) {
            util.Logger.writeLog(ex, DbConfigParser.class.getName(), "Fichier de base de donnée introuvable!");
        }
        return null;
    }
}

package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserUtils {
    public static JSONObject extractor(Reader in){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(in);
 
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (IOException ex) {
            util.Logger.writeLog(ex, JSONParserUtils.class.getName(), null);
        } catch (ParseException ex) {
            util.Logger.writeLog(ex, JSONParserUtils.class.getName(), null);
        }
        return null;
    }
}

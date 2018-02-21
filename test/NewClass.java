import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Elyes
 */
public class NewClass {

    public static void main(String[] args) throws IOException, InterruptedException {
//        Date birthDate = new Date(1418984496227l);
//        /*System.out.println(LocalDate.of(birthDate.getYear()+1900, birthDate.getMonth() + 1,
//                birthDate.getDate()).toString());*/
//        System.out.println(Period.between(LocalDate.of(birthDate.getYear()+1900, birthDate.getMonth() + 1,
//                birthDate.getDate()), LocalDate.now()).getYears());

//        File f =  new File("D:\\HeadQuarters\\Esprit\\3ème Année\\PI-DEV\\javafx\\MySoulMate\\test\\photo.png");
//             String encodstring = encodeFileToBase64Binary(f);
//
//             PrintWriter out = new PrintWriter("file");
//             out.print(encodstring);
        ProcessBuilder builder = new ProcessBuilder(
        "cmd","/c","start D:\\HeadQuarters\\Esprit\\\"3ème Année\"\\PI-DEV\\javafx\\MySoulMate\\test\\bonjour.bat");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        
        /*HttpRequests httpRequests = new HttpRequests("7p70tlaZpQjhZCnjpyEwtI1ZJydvRQaV", "-5V3kEa1fBewWawr1ZyffJwnuuFaebrO");
        PostParameters postParameters =
      new PostParameters()
          .setImg(f)
          .setAttribute("all");
        httpRequests.detectionDetect(postParameters);
        JSONObject result = httpRequests.detectionDetect(postParameters);
        String gender = result.getJSONArray("face").getJSONObject(0).getJSONObject("attribute")
     .getJSONObject("gender").toString();
        System.out.println(gender);*/
    }
    private static String encodeFileToBase64Binary(File file){
            String encodedfile = null;
            try {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = Base64.getEncoder().encodeToString(bytes);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return encodedfile;
        }
}

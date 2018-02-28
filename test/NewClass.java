
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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

    public static void main(String[] args) throws IOException{
        /*DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Chrome", new Integer(42));
        pieDataset.setValue("Explorer", new Integer(24));
        pieDataset.setValue("Firefox", new Integer(24));
        pieDataset.setValue("Safari", new Integer(12));
        pieDataset.setValue("Opera", new Integer(8));
        JFreeChart chart = ChartFactory.createPieChart3D(
            "Browser Popularity", // Title
            pieDataset, // Dataset
            true, // Show legend
            true, // Use tooltips
            false // Configure chart to generate URLs?
        );
        List<JFreeChart> charts = new ArrayList();
        charts.add(chart);
        PDFPrinter.printPDF(charts);*/
//        String absolutePath = FileSystems.getDefault().getPath("src/view/assets/img/chart.png").normalize().toAbsolutePath().toString();
//        System.out.println(absolutePath);
//        Date date = new Date();
//        System.out.println("" + (date.getYear() + 1900) + "-" + date.getMonth() + 1 + "-" + date.getDate() + " " + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds());
//    File screenshot = new File("D:\\chart.png");
//    PDDocument document = new PDDocument();
//    PDPage page = new PDPage();
//    document.addPage(page);
//    PDImageXObject pdi = PDImageXObject.createFromFileByContent(screenshot ,document);
//    PDPageContentStream contentStream = new PDPageContentStream(document, page);
//    contentStream.drawImage(pdi,100,100);
//    contentStream.close(); // do this before saving!
//
//    document.save("C:/Users/Elyes/Documents/sample.pdf");
//    document.close();
//    }
//Document document = new Document();
//    String input = "c:/temp/capture.png"; // .gif and .jpg are ok too!
//    String output = "c:/temp/capture.pdf";
//    try {
//      FileOutputStream fos = new FileOutputStream(output);
//      PdfWriter writer = PdfWriter.getInstance(document, fos);
//      writer.open();
//      document.open();
//      document.add(Image.getInstance(input));
//      document.close();
//      writer.close();
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
}}

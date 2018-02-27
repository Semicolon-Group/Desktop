/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Date;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Chart;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
//import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.jfree.chart.ChartUtilities;

/**
 *
 * @author Elyes
 */
public class PDFPrinter {
    public static void printPDF(List<Chart> charts) throws IOException{
        PDDocument document = new PDDocument();
        String absolutePath = FileSystems.getDefault().getPath("src/view/assets/img/chart.png").normalize().toAbsolutePath().toString();
        File file = new File(absolutePath);
        for(Chart chart : charts){
            AnchorPane pane = new AnchorPane();
            pane.getChildren().add(chart);
            Scene scene = new Scene(pane);
            WritableImage image = scene.snapshot(null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PDPage page = new PDPage();
            document.addPage(page);
            PDImageXObject pdi = PDImageXObject.createFromFileByContent(file ,document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(pdi,100,100);
            contentStream.close();
        }
        Date date = new Date();
        String time = (date.getYear() + 1900) + "-" + date.getMonth() + 1 + "-" + date.getDate();
        time += " " + date.getHours() + "h" + date.getMinutes() + "m" + date.getSeconds() + "s";
        document.save("C:\\Users\\Public\\chart " + time + ".pdf");
        document.close();
    }
}

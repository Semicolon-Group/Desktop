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
import java.nio.file.FileSystems;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Chart;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.jfree.chart.ChartUtilities;

/**
 *
 * @author Elyes
 */
public class PDFPrinter {
    public static void printPDF(List<Chart> charts) throws IOException, COSVisitorException{
        PDDocument document = new PDDocument();
        for(Chart chart : charts){
            WritableImage image = chart.snapshot(new SnapshotParameters(), null);
            File file = new File("/views/assets/img/chart.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PDPage page = new PDPage();
            document.addPage(page);

            String absolutePath = FileSystems.getDefault().getPath("/views/assets/img/chart.jpg").normalize().toAbsolutePath().toString();
            InputStream in = new FileInputStream(new File(absolutePath));            
            PDJpeg img = new PDJpeg(document, in);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(img, 10, 300);
            contentStream.close();
        }
        document.save("pathway/to/save.pdf");
    }
}

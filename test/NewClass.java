
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import util.PDFPrinter;



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

    public static void main(String[] args) throws IOException, COSVisitorException{
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
    }
}

package mat.unical.it.progettouid2022.controller;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mat.unical.it.progettouid2022.view.ChartsHandler;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class scrollChartsController {

    @FXML
    private LineChart<?, ?> averageChart;

    @FXML
    private BarChart<?, ?> cfuDistrChart;

    @FXML
    private BarChart<?, ?> voteDistrChart;

    @FXML
    private LineChart<?, ?> votesChart;
    @FXML
    private VBox vbox;
    @FXML
    private NumberAxis distrVotesYAxis;
    @FXML
    private CategoryAxis distrVotesXAxis;
    @FXML
    private NumberAxis distrCfuYAxis;
    @FXML
    private Button averageDownloadButton;
    @FXML
    private Button votesDistrDownloadButton;
    @FXML
    private Button votesDownloadButton;
    @FXML
    private Button cfuDownloadButton;

    @FXML
    void initialize() {
        ChartsHandler.getInstance().setHomeChart(votesChart);
        ChartsHandler.getInstance().setAverageChart(averageChart);
        ChartsHandler.getInstance().setVotesDistributionChart(voteDistrChart, distrVotesYAxis);
        ChartsHandler.getInstance().setCFUDistributionChart(cfuDistrChart, distrCfuYAxis);
        setIcon("mdi2d-download-circle-outline", averageDownloadButton);
        setIcon("mdi2d-download-circle-outline", votesDownloadButton);
        setIcon("mdi2d-download-circle-outline", votesDistrDownloadButton);
        setIcon("mdi2d-download-circle-outline", cfuDownloadButton);
        vbox.getStyleClass().add("vbox");
    }


    @FXML
    void averageDownloadClicked(MouseEvent event) throws IOException {
        convertChartToPdf(averageChart);
    }

    @FXML
    void cfuDownloadClicked(MouseEvent event) throws IOException {
        convertChartToPdf(cfuDistrChart);
    }

    @FXML
    void votesDistrDownloadsClicked(MouseEvent event) throws IOException {
        convertChartToPdf(voteDistrChart);
    }

    @FXML
    void votesDownloadClicked(MouseEvent event) throws IOException {
        convertChartToPdf(votesChart);
    }

    private void convertChartToPdf(Chart chart) throws IOException {
        File outputfile = SceneHandler.getInstance().savePDFFile();
        if(outputfile != null){
            String dest = outputfile.getAbsolutePath();

            // Creating a PdfWriter
            PdfWriter writer = new PdfWriter(dest);

            // Creating a PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Creating a Document
            Document document = new Document(pdf);

            // Creating an ImageData object
            WritableImage image = chart.snapshot(new SnapshotParameters(), null);
            BufferedImage awtImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(awtImage, "png", baos);
            byte[] bytes = baos.toByteArray();
            ImageData data = ImageDataFactory.create(bytes);
            Image img = new Image(data);
            // Adding image to the document
            document.add(img);
            // Closing the document
            document.close();
        }
        else{
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING,"Scegliere una destinazione prima di salvare il file","Attenzione");
        }
    }

    private void setIcon(String iconCode, Button button) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(25);
        icon.getStyleClass().add("iconButton");
        button.setGraphic(icon);
    }
}


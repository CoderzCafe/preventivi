package simpletest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class AddingImage {

    public static void main(String[] args) {

        Document document = new Document();
        File file = new File("/home/shine/Desktop/add image in pdf.pdf");
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            Paragraph paragraph = new Paragraph("Adding image on a pdf file example");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            Font font = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
            paragraph.setFont(font);
            document.add(paragraph);

            /** add image on pdf **/
            Image image = Image.getInstance("/home/shine/Desktop/Screenshot.png");
            image.setAbsolutePosition(30, 650);
            image.scaleAbsolute(100, 100);
            document.add(image);

            Image image1 = Image.getInstance("/home/shine/Desktop/images.png");
            image1.setAbsolutePosition(30, 530);
            image1.scaleAbsolute(100, 100);
            document.add(image1);

            Image image2 = Image.getInstance(new URL("http://news.mit.edu/sites/mit.edu.newsoffice/files/images/2016/MIT-Earth-Dish_0.jpg"));
            image2.scaleAbsolute(100, 100);
            image2.setAlignment(Element.ALIGN_RIGHT);
            document.add(image2);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

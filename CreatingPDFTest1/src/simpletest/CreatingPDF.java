package simpletest;
import  com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreatingPDF {

    public static void main(String[] args) {
        File file = new File("/home/shine/Desktop/hello.pdf");
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.add(new Paragraph("Hello World"));
            document.add(new Paragraph("Hello Java"));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

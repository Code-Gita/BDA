package iText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class TestTable {
 
	public static final String DEST = "pdf/simple_rowspan_colspan.pdf";
	 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TestTable().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(6);
        table.setWidths(new float[] { 2,1, 7, 1.2f, 1.2f, 2 });
        
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Bill No : "+"1"));
        cell.setRowspan(4);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Sno"));
        cell.setColspan(1);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Description"));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Qty"));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Rate"));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Amount"));
        cell.setColspan(1);
        table.addCell(cell);
        
        table.addCell("1");
        table.addCell("1");
        table.addCell("qty");
        table.addCell("rate");
        table.addCell("amount");
        
        table.addCell("1");
        table.addCell("2");
        table.addCell("qty");
        table.addCell("rate");
        table.addCell("amount");
        
        table.addCell("1");
        table.addCell("3");
        table.addCell("qty");
        table.addCell("rate");
        table.addCell("amount");
        
        
        
        //table.addCell("3");
        /*PdfPCell cell;
        cell = new PdfPCell(new Phrase("S/N"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Name"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Age"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("SURNAME");
        table.addCell("FIRST NAME");
        table.addCell("MIDDLE NAME");
        table.addCell("1");
        table.addCell("James");
        table.addCell("Fish");
        table.addCell("Stone");
        table.addCell("17");
        */
        
        document.add(table);
        document.close();
    }
    }


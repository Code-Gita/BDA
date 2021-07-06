package iText;


/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20145742/spacing-leading-pdfpcells-elements
 * 
 * Cell in composite mode, containing different paragraphs with a different leading.
 */

 
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 

 


public class Test {
	    public static final String DEST = "F:\\Project\\eclipseworkspace\\Temp\\pdf\\Test\\leading_in_cell.pdf";
	 
	    public static void main(String[] args) throws IOException,
	            DocumentException {
	        File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new Test().createPdf(DEST);
	    }
	 
	    public void createPdf(String dest) throws IOException, DocumentException {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        PdfPTable table = new PdfPTable(1);
	        PdfPCell cell = new PdfPCell();
	        Paragraph p;
	        p = new Paragraph(16, "paragraph 1: leading 16");
	        cell.addElement(p);
	        p = new Paragraph(32, "paragraph 2: leading 32");
	        cell.addElement(p);
	        p = new Paragraph(10, "paragraph 3: leading 10");
	        cell.addElement(p);
	        p = new Paragraph(18, "paragraph 4: leading 18");
	        cell.addElement(p);
	        p = new Paragraph(40, "paragraph 5: leading 40");
	        cell.addElement(p);
	        table.addCell(cell);
	        document.add(table);
	        document.close();
	    }
	}


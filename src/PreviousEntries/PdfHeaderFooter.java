package PreviousEntries;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class PdfHeaderFooter  extends PdfPageEventHelper{
	
	private String billPayee;
	
	public PdfHeaderFooter(String billPayee)
	{
		this.billPayee=billPayee;
	}
	

	 public void onStartPage(PdfWriter writer, Document document) {
	        //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 50, 750, 0);
		 ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Bhatnagar Dental Art"), 80, 800, 0);
		 ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("To : "+this.billPayee), 80, 780, 0);
	        //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 750, 0);
	    }

	    public void onEndPage(PdfWriter writer, Document document) {
	        //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("http://www.xxxx-your_example.com/"), 110, 30, 0);
	        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + (document.getPageNumber()-1)), 550, 30, 0);
	    }

	
}

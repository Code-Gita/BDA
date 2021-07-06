package iText;



//import itext-1.3;
//import java.io.FileOutputStream;

//import java.io.OutputStream;

//import javax.swing.text.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lowagie.text.Phrase;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
//import com.lowagie.text;
import com.lowagie.text.pdf.*;

public class TempConverter {

	static int count = 1;

	@SuppressWarnings("unchecked")
	static BaseFont font(String myFont) {
		BaseFont andalus_r = null;
		BaseFont arialRounded;
		BaseFont arialUnicode;
		BaseFont calibriLight;
		BaseFont arial;
		try {
			// create a font that will be embedded
			HashMap<String, BaseFont> hm = new HashMap<>();
			andalus_r = BaseFont.createFont("c:/windows/fonts/andlso.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arialRounded = BaseFont.createFont("c:/windows/fonts/ARLRDBD.TTF", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arialUnicode = BaseFont.createFont("c:/windows/fonts/ARIALUNI.TTF", BaseFont.WINANSI, BaseFont.EMBEDDED);
			calibriLight = BaseFont.createFont("c:/windows/fonts/CalibriL.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arial = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			hm.put(new String("andalus"), andalus_r);
			hm.put(new String("arialRounded"), arialRounded);
			hm.put(new String("arialUnicode"), arialUnicode);
			hm.put(new String("calibriLight"), calibriLight);
			hm.put(new String("arial"), arial);
			Set<Entry<String, BaseFont>> set = hm.entrySet();
			Iterator<Entry<String, BaseFont>> i = set.iterator();
			while (i.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry) i.next();
				if (me.getKey().equals(myFont)) {
					return (BaseFont) me.getValue();
				}

			}
			// }

		} catch (DocumentException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			// System.exit(1);
		}
		// Font HdFont=new Font(andalus_r,24)

		return andalus_r;

	}

	private static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);

	public static void main(String[] args) throws Exception {

		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		DateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		// System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48

		try {
			String OUTPUT_FILE = "F:\\Project\\eclipseworkspace\\Temp\\pdf\\Test" + dateFormat.format(date) + ".pdf";
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_FILE));

			document.open();

			/* Contact Start */
			Phrase contactLine1 = new Phrase(new Chunk("Ph. : 0120-2413808", new Font(font("calibriLight"), 11)));
			Phrase contactLine2 = new Phrase(new Chunk("Mobile : 09891368772", new Font(font("calibriLight"), 11)));
			Phrase contactLine3 = new Phrase(new Chunk(" 09910840543", new Font(font("calibriLight"), 11)));
			PdfContentByte canvas = writer.getDirectContentUnder();
			ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, contactLine1, 600, 750, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, contactLine2, 600, 735, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, contactLine3, 600, 725, 0);
			/* Contact Ends */

			/* Heading Starts */

			Phrase heading = new Phrase();
			heading.add(new Chunk("BHATNAGAR DENTAL ART", new Font(font("andalus"), 24)));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, heading, 180, 675, 0);
			Phrase heading_2 = new Phrase();
			heading_2.add(new Chunk("Ceramics, Chrome Nickel, Crown & Bridge, Chrome-Cobalt Skeleton",
					new Font(font("andalus"), 13)));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, heading_2, 130, 650, 0);
			Phrase heading_3 = new Phrase();
			heading_3.add(new Chunk("All Types of Acrylic Work, Orthoplates and Flexible Dentures",
					new Font(font("andalus"), 13)));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, heading_3, 150, 630, 0);
			Phrase heading_4 = new Phrase();
			heading_4.add(new Chunk("H-37, SECTOR-22, NOIDA-201301 (U.P.)", new Font(font("andalus"), 14)));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, heading_4, 195, 600, 0);
			/* Heading Ends */

			/* Bill Number and Date Starts */
			Phrase billNum = new Phrase();
			billNum.add(new Chunk("Bill Number : ", new Font(font("arialRounded"), 10)));
			billNum.add(new Chunk("100 ", new Font(font("arialRounded"), 10)));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, billNum, 70, 560, 0);

			Phrase billDate = new Phrase();
			billDate.add(new Chunk("Date : ", new Font(font("arialRounded"), 10)));
			billDate.add(dateformat1.format(date));
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, billDate, 470, 560, 0);
			/* Bill Number and Date Ends */

			/* Add Bill To */
			// Paragraph BillInfo = new Paragraph();
			Phrase billTo = new Phrase();
			billTo.add(new Chunk("M/s : ", new Font(font("arialRounded"), 10)));
			billTo.add(new Chunk(" Dr. ABC ", new Font(font("arialRounded"), 10)));
			// BillInfo.add(billTo);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, billTo, 70, 530, 0);
			/* Add Bill To */

			/* Invoice Table Starts */
			// PdfPTable table = new PdfPTable(5);
			
			PdfPTable table = new PdfPTable(new float[]{1,8,2,2,3});
			table.setTotalWidth(504);
			//table.setWidthPercentage(100);
			// the cell object
			table.setHeaderRows(1);
			PdfPCell cell;
			table.addCell("SNo.");
			table.addCell("Description");
			table.addCell("Qty.");
			table.addCell("Rate");
			table.addCell("Amount");
			cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
			cell.setColspan(5);
			int page_count=1;
			
			
			for(int i=0; i<=27;i++)
			{
				if((500-table.getTotalHeight())>36)
				{
					table.addCell(""+i);
					table.addCell("Denture "+i);
					table.addCell("2.0");
					table.addCell("5.00");
					table.addCell("10.0");
					System.out.println("Value of I :"+i);
				}
				else{
						page_count++;
						break;
					}
						
					//document.newPage();
					//document.add(new Chunk("abc"));
					//break;
				}
			
			System.out.println("Page Size y : "+(500-table.getTotalHeight()));
			if(page_count==1)
			{
				System.out.println("Page count : "+page_count);
				table.writeSelectedRows(0, -1, 60, 500, canvas);
				Phrase billTotal = new Phrase();
				billTotal.add(new Chunk("Total : ", new Font(font("arial"), 14)));
				billTotal.add(new Chunk(" 2400.00 ", new Font(font("arial"), 14)));
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, billTotal, 410,(500-table.getTotalHeight()-15) , 0);
			}
			else if(page_count>1)
			{
				throw new Exception("No code for Pages >1");
				//System.out.println("No code for Pages >1");
			}
			/* Invoice Table Ends */
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

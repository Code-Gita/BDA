package PreviousEntries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTable;

//import com.lowagie.text.Chunk;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.ColumnText;
//import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfTable;
//import com.lowagie.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;

import DailyEntries.PdfController;
import DailyEntries.PdfControls;

//import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfTable;
import com.itextpdf.text.Font.FontFamily;

public class PdfCreator {

	public PdfCreator() {
		
	}
	
	String billPayee="";
	String startDate="";
	String endDate="";
	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhss");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	public PdfCreator(String billPayee,String startDate, String endDate) {
		this.billPayee=billPayee;
		this.startDate=startDate;
		this.endDate=endDate;
	}
	
	static String tempBillId = "";
	
	//String OUTPUT_PATH = "F:\\Project\\eclipseworkspace\\BillingManagement\\pdf\\";
	String OUTPUT_PATH = "./Output/BillingManagement/pdf/";
	String OUTPUT_FILE_NAME="Det_"+dateFormat.format(new Date())+".pdf";
	String FONT_PATH = "/Library/Fonts";
	String ArialFont = FONT_PATH + "/Arial Unicode.ttf";
	
	Document document = new Document();
	
	PdfWriter writer;
	PdfPTable detailedTable = new PdfPTable(6);
	PdfPCell detailedCell = new PdfPCell();
	
	PdfPTable dailyTable = new PdfPTable(5);
	PdfPCell dailyCell = new PdfPCell();
	
	Float calcTotalAmount=0f;
	DecimalFormat twoDecimalPlaces = new DecimalFormat("#.00");
	/*Pdf Document Properties Ends*/

	public PdfContentByte ContactHead(PdfWriter writer,Document document) throws DocumentException {
		
		BaseFont calibriLight = null;
		try {
			//calibriLight = BaseFont.createFont("c:/windows/fonts/CalibriL.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			calibriLight = BaseFont.createFont(ArialFont, BaseFont.WINANSI, BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Paragraph contactLine =new Paragraph("Ph. : 0120-2413808",new Font(calibriLight,11));
		contactLine.add(new Phrase("\n Mobile : 09891368772", new Font(calibriLight, 11)));
		contactLine.add(new Phrase("\n 09910840543",new Font( calibriLight, 11)));
		PdfContentByte canvas = writer.getDirectContent();
		canvas.setFontAndSize(calibriLight, 12);
		contactLine.setAlignment(Element.ALIGN_RIGHT);
		document.add(contactLine);
		document.add(new Paragraph("\n \n"));
		return canvas;
	}
	
	public void Heading(PdfWriter writer, PdfContentByte canvas,Document document, String billPayee) throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		BaseFont andalus_r = null;
		try {
			//andalus_r = BaseFont.createFont("c:/windows/fonts/andlso.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			andalus_r = BaseFont.createFont(ArialFont, BaseFont.WINANSI, BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Paragraph heading = new Paragraph();
		heading.add(new Phrase(10, "BHATNAGAR DENTAL ART", new Font(andalus_r, 24)));
		heading.add(new Phrase("\n Ceramics, Chrome Nickel, Crown & Bridge, Chrome-Cobalt Skeleton",	new Font(andalus_r, 13)));
		heading.add(new Phrase("\n All Types of Acrylic Work, Orthoplates and Flexible Dentures", new Font(andalus_r, 13)));
		heading.add(new Phrase("\n H-37, SECTOR-22, NOIDA-201301 (U.P.) \n \n", new Font(andalus_r, 14)));
		heading.setAlignment(Element.ALIGN_CENTER);
		document.add(heading);
		
		//System.out.println("document.getClass() : "+document.getClass());
		//System.out.println("this.getClass() : "+this.getClass());
		
		if(!this.billPayee.isEmpty())
		{
			Paragraph startnendInfo = new Paragraph();
			startnendInfo.add(new Phrase("(Detailed Bill Information) \n",new Font(FontFamily.TIMES_ROMAN,14)));
			startnendInfo.add(new Phrase("From "+startDate.substring(startDate.length()-2,startDate.length())+"/"+startDate.substring(5,7)+"/"+startDate.substring(0, 4)+"    ",new Font(FontFamily.TIMES_ROMAN,14)));
			startnendInfo.add(new Phrase("To "+endDate.substring(endDate.length()-2,endDate.length())+"/"+endDate.substring(5,7)+"/"+endDate.substring(0, 4)+"    ",new Font(FontFamily.TIMES_ROMAN,14)));
			startnendInfo.setAlignment(Element.ALIGN_CENTER);
			document.add(startnendInfo);
		}
		
		Paragraph DateNTime = new Paragraph();
		DateNTime.add(new Phrase(" Name : "+billPayee+"\n",new Font(FontFamily.HELVETICA,14)));
		DateNTime.add(new Phrase(" Date    : "+sdf.format(new Date())+"\n\n",new Font(FontFamily.HELVETICA,13)));
		DateNTime.setAlignment(Element.ALIGN_LEFT);
		document.add(DateNTime);
	}

	public void openFile() throws DocumentException, FileNotFoundException
	{
		//PdfHeaderFooter header = new PdfHeaderFooter(this.billPayee);
		//System.out.println(billPayee);
		CheckFilePath(billPayee);
		//writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_PATH+"\\"+this.billPayee+"\\"+OUTPUT_FILE_NAME));
		writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_PATH+"/"+this.billPayee+"/"+OUTPUT_FILE_NAME));
		document.open();
		
		//writer.setPageEvent((PdfPageEvent) header);
		PdfContentByte canv = ContactHead(writer,this.document);
		Heading(writer, canv,this.document,this.billPayee);
	}
	
	
	public void closeDoc()
	{
		try {
			document.add(detailedTable);
			Paragraph Final = new Paragraph();
			//Final.add(new Phrase("Total Amount (Rs.) "+twoDecimalPlaces.format(calcTotalAmount)+"         \n\n\n\n"));
			Final.add(new Phrase("Total Amount (Rs.) "+NumberFormat.getCurrencyInstance(Locale.US).format(calcTotalAmount)+"         \n\n\n\n"));
			Final.add(new Phrase("Auth. Sign (Bhatnagar Dental Art) \n"));
            Final.add("______________________________________________________________________________\n");
			Final.setAlignment(Element.ALIGN_RIGHT);
			document.add(Final);
			//Paragraph EndOfBill = new Paragraph();
			//EndOfBill.add(new Phrase("End Of Bill"));
			//EndOfBill.setAlignment(Element.ALIGN_CENTER);
			//document.add(EndOfBill);
		} catch (DocumentException e) {
			System.out.println("Exception: "+e);
		}
		document.close();
		JOptionPane.showMessageDialog(null, "Please find the output file at : "+OUTPUT_PATH+"\n Total Amount : "+calcTotalAmount);
	}
	
	/*
	 * Called for Detailed bill
	 * */
	public void writeInDetailedPDF(int billIdCount,String billId,String billDate,String billPayee, String billSno,String billDescription,String billQty,String billRate, String billAmount,String billTotalAmt) throws DocumentException
	{
		
		try {
			detailedTable.setWidths(new float[] { 2,1, 7, 1.2f, 1.2f, 2 });
			detailedTable.setWidthPercentage(100);
			detailedTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
		} catch (com.itextpdf.text.DocumentException e) {
			e.printStackTrace();
		}
		PdfPCell cell;
		
		if(!billId.equals(tempBillId))
		{
			cell = new PdfPCell(new Phrase("Bill No : "+billId));
		    cell.setRowspan(billIdCount+1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
		    detailedTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("SNo"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    detailedTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("PRODUCT"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    detailedTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("QTY."));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    detailedTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("RATE (Rs.)"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    detailedTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("AMOUNT (Rs.)"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    detailedTable.addCell(cell);
		    
		    detailedTable.addCell(billSno);
		    detailedTable.addCell(billDescription);
		    detailedTable.addCell(billQty);
		    detailedTable.addCell(billRate);
		    detailedTable.addCell(billAmount);
			
		    calcTotalAmount = calcTotalAmount+Float.parseFloat(billAmount);
		 }
		else
		{
			detailedTable.addCell(billSno);
			detailedTable.addCell(billDescription);
			detailedTable.addCell(billQty);
			detailedTable.addCell(billRate);
			detailedTable.addCell(billAmount);
			calcTotalAmount = calcTotalAmount+Float.parseFloat(billAmount);
		}
		tempBillId = billId;
	}
	
	public void CheckFilePath(String billpayee)
	{
		//System.out.println("Inside CheckFilePath "+OUTPUT_PATH);
		//File OpDIr=new File(OUTPUT_PATH+"\\"+billpayee);
		File OpDIr=new File(OUTPUT_PATH+"/"+billpayee);
		if (!OpDIr.exists()) {
            if (OpDIr.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
	}
	
	/*
	 * Called for Daily bill
	 * */
	//public void writeDailyBill(String billSno,String billDescription,String billQty,String billRate,String billAmount)
	public void writeDailyBill(JTable table, Document document)
	{
		try {
			dailyTable.setWidths(new float[] { 1, 8, 2, 2, 3 });
			dailyTable.setWidthPercentage(100);
			//dailyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
			dailyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			dailyTable.setHeaderRows(1);
			dailyTable.addCell("SNo.");
			dailyTable.addCell("PRODUCT");
			dailyTable.addCell("QTY.");
			dailyTable.addCell("RATE(Rs.)");
			dailyTable.addCell("AMOUNT (Rs.)");
		} catch (com.itextpdf.text.DocumentException e) {
			e.printStackTrace();
		}
		PdfPCell cell;
		/*
		if(!billId.equals(tempBillId))
		{
			cell = new PdfPCell(new Phrase("Bill No : "+billId));
		    cell.setRowspan(billIdCount+1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
		    dailyTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("SNo"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    dailyTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("DESCRIPTION"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    dailyTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("QTY."));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    dailyTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("RATE"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    dailyTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("AMOUNT"));
		    cell.setColspan(1);
		    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    dailyTable.addCell(cell);
		    
		    dailyTable.addCell(billSno);
		    dailyTable.addCell(billDescription);
		    dailyTable.addCell(billQty);
		    dailyTable.addCell(billRate);
		    dailyTable.addCell(billAmount);
			
		    calcTotalAmount = calcTotalAmount+Float.parseFloat(billAmount);
		 }
		else
		{
			dailyTable.addCell(billSno);
			dailyTable.addCell(billDescription);
			dailyTable.addCell(billQty);
			dailyTable.addCell(billRate);
			dailyTable.addCell(billAmount);
			//calcTotalAmount = calcTotalAmount+Float.parseFloat(billAmount);
		}*/
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j<table.getColumnCount(); j++) {
				//if ((500 - da.getTotalHeight()) > 36) {
				if(table.getModel().getValueAt(i, j) == null)
				{
					dailyTable.addCell(" ");
				}
				else
				{
					dailyTable.addCell(table.getModel().getValueAt(i, j).toString());
				}
					
				//}
				//else{
					//page_count++;
					//break;
				}
				
			}
		try {
			document.add(dailyTable);
		} catch (DocumentException e) {
			System.out.println("Exception : "+e);
			e.printStackTrace();
		}
		//}
		//tempBillId = billId;
	
		
	}
	
	public void OpenPdf() {
		try {

			if ((new File(OUTPUT_PATH+"\\"+this.billPayee+"\\"+OUTPUT_FILE_NAME)).exists()) {
				Process p = Runtime.getRuntime()
						.exec("rundll32 url.dll,FileProtocolHandler " + (OUTPUT_PATH+"\\"+this.billPayee+"\\"+OUTPUT_FILE_NAME));
				p.waitFor();
			}
			// else {
			// System.out.println("File is not exists");
			// }
			// System.out.println("Done");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

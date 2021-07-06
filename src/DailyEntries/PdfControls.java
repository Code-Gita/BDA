package DailyEntries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import PreviousEntries.PdfCreator;

public class PdfControls {

	private String billNumber;
	private String billDate;
	private String billPayee;
	private JTable table;
	private String billTotalAmt;
	
	private JLabel JbillNumber;
	private JTextField JbillDate;
	//private JTextField JbillPayee;
	private JComboBox<String> JbillPayee;
	private JTextField JbillTotalAmt;

	PdfCreator pdf = new PdfCreator();

	//public PdfControls(JLabel billNum, JTextField billDate, JTextField billPayee, JTable table, JTextField billTotalAmt) {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public PdfControls(JLabel billNum, JTextField billDate, JComboBox<String> billPayee, JTable table, JTextField billTotalAmt) {
		
		this.billNumber = billNum.getText();
		this.billDate = billDate.getText();
		this.table = table;
		this.billTotalAmt = billTotalAmt.getText();
		this.JbillNumber = billNum;
		this.JbillDate = billDate;
		this.JbillPayee = billPayee;
		this.JbillTotalAmt = billTotalAmt;
		this.billPayee = billPayee.getSelectedItem().toString();

		//OUTPUT_PATH = "F:\\Project\\eclipseworkspace\\Output\\pdf\\" + this.billPayee + "\\";
		OUTPUT_PATH = "./Output/pdf/" + this.billPayee + "/";
		OUTPUT_FILE_NAME = this.billNumber + ".pdf";
		CheckFilePath();
	}
	
//	public PdfControls() {
//
//	}

	PdfController pdfFunction = new PdfController();

	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	DateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String OUTPUT_PATH;
	String OUTPUT_FILE_NAME;
	File OpDir;
	PdfWriter writer;
	Document document = new Document();

	public void createPdf() {

		try {
			String op = OUTPUT_PATH + OUTPUT_FILE_NAME;
			writer = PdfWriter.getInstance(document, new FileOutputStream(op));

			document.open();
			PdfContentByte canv = pdf.ContactHead(writer, this.document);
			pdf.Heading(writer, canv, this.document, this.billPayee);
			pdf.writeDailyBill(this.table, this.document);
			closeDoc();
			OpenPdf();

			InsertInDB ins = new InsertInDB(JbillNumber, JbillDate, JbillPayee, table, JbillTotalAmt);
			ins.insert();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception..! : " + e);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("Exception..! : " + e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception..! : " + e);
		}

	}

	public void OpenPdf() {
		try {

//			if ((new File(OUTPUT_PATH + OUTPUT_FILE_NAME)).exists()) {
//				Process p = Runtime.getRuntime()
//						.exec("rundll32 url.dll,FileProtocolHandler " + OUTPUT_PATH + OUTPUT_FILE_NAME);
//				p.waitFor();
//			}
			if ((new File(OUTPUT_PATH + OUTPUT_FILE_NAME)).exists()) {
				Process p = Runtime.getRuntime()
						.exec("open " + OUTPUT_PATH + OUTPUT_FILE_NAME);
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

	public void CheckFilePath() {
		//System.out.println("Inside CheckFilePath");
		//logger.info("Inside Check File Path");
		File OpDIr = new File(OUTPUT_PATH);
		if (!OpDIr.exists()) {
			if (OpDIr.mkdir()) {
				System.out.println("Directory is created!");
				//logger.info("Dir Created");
			} else {
				System.out.println("Failed to create directory!");
				//logger.info("Failed to create dir");
			}
		}
	}

	public void closeDoc() {
		try {
			Paragraph Final = new Paragraph();
			Final.add(new Phrase("Total Amount (Rs.) " + this.billTotalAmt + "       \n\n\n\n"));
			Final.add(new Phrase("Auth Sign (Bhatnagar Dental Art) \n"));
			Final.add("______________________________________________________________________________\n");
			Final.setAlignment(Element.ALIGN_RIGHT);
			document.add(Final);
			

		} catch (DocumentException e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}

		/* Table code Ends */

		document.close();
	}

}

package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ArticlesPrix;
import dao.Commandes;
import services.ArticlesPrixService;
import services.ArticlesPrixServicesImp;

// class de generation facture au format pdf utilisant ITEXT
public class InvoiceGenerator {

	public InputStream createPDF (Commandes commande,String user){
		HttpServletResponse response = ServletActionContext.getResponse();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ArticlesPrix ap = new ArticlesPrix();
        ArticlesPrixService Imp = new ArticlesPrixServicesImp();
    	ap = Imp.findById(commande.getCodeArt());
    	InputStream inputStream = null;
		try {

			Document document = new Document();
			PdfWriter.getInstance(document, buffer);


			PdfPTable irdTable = new PdfPTable(2);
			irdTable.addCell(getIRDCell("Commande Numero"));
			irdTable.addCell(getIRDCell("Date de Commande"));
			irdTable.addCell(getIRDCell(""+commande.getCodeCmd()));
			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(commande.getDateCmd());
			irdTable.addCell(getIRDCell(date)); // pass invoice date				

			PdfPTable irhTable = new PdfPTable(3);
			irhTable.setWidthPercentage(100);

			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("Facture", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			PdfPCell invoiceTable = new PdfPCell (irdTable);
			invoiceTable.setBorder(0);
			irhTable.addCell(invoiceTable);

			FontSelector fs = new FontSelector();
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
			fs.addFont(font);
			Phrase bill = fs.process("Facture pour Mr/Mme: "); // customer information
			Paragraph name = new Paragraph(user);
			name.setIndentationLeft(20);

			PdfPTable billTable = new PdfPTable(5); //one page contains 15 records 
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] { 1, 2,5,2,1 });
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("Code Article"));
			billTable.addCell(getBillHeaderCell("Nom Article"));
			billTable.addCell(getBillHeaderCell("Description Article"));
			billTable.addCell(getBillHeaderCell("Prix Unitaire"));
			billTable.addCell(getBillHeaderCell("Quantite Demandé"));

			billTable.addCell(getBillRowCell(""+ap.getCodeArt()));
			billTable.addCell(getBillRowCell(""+ap.getNomArt()));
			billTable.addCell(getBillRowCell(""+ap.getDescArt()));
			billTable.addCell(getBillRowCell(""+ap.getPrixPdt()));
			billTable.addCell(getBillRowCell(""+commande.getQteCmd()));

			

			billTable.addCell(getBillRowCell(" "));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));


			PdfPTable validity = new PdfPTable(1);
			validity.setWidthPercentage(100);   
			PdfPCell summaryL = new PdfPCell (validity);
			summaryL.setColspan (3);
			billTable.addCell(summaryL);
			int total = ap.getPrixPdt() * commande.getQteCmd(); 
			PdfPTable accounts = new PdfPTable(2);
			accounts.setWidthPercentage(100);
			accounts.addCell(getAccountsCell("Total"));
			accounts.addCell(getAccountsCellR(""+total));			
			PdfPCell summaryR = new PdfPCell (accounts);
			summaryR.setColspan (2);         
			billTable.addCell(summaryR);  


			document.open();//PDF document opened........	

			document.add(irhTable);
			document.add(bill);
			document.add(name);		
			document.add(billTable);

			document.close();



		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] bytes = null;
        bytes = buffer.toByteArray();
        response.setContentLength(bytes.length);

        
		if(bytes!=null){
            inputStream = new ByteArrayInputStream ( bytes );
        }
        return inputStream;
	}

	public static void setHeader() {

	}


	public static PdfPCell getIRHCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		/*	font.setColor(BaseColor.GRAY);*/
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		return cell;
	}

	public static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		return cell;
	}

	public static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorder(0);
		return cell;
	}

	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		return cell;
	}
	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
		cell.setPadding (5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);	
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}

}
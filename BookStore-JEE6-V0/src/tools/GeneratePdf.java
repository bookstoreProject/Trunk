package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Order;
import entities.OrderItem;

public class GeneratePdf {

	private static final long serialVersionUID = 1L;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public StreamedContent GeneratePdfFromOrder(Order order) {
		// TODO Auto-generated method stub
		Document document = new Document();
		StreamedContent file = null;
		try {
			PdfWriter.getInstance(document, new FileOutputStream("facture.pdf"));
			document.open();
			addTitlePage(document, order);
			document.close();
			InputStream stream = new FileInputStream("temp.pdf");
			file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_file.pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return file;
	}

	private static void addTitlePage(Document document, Order order) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		// Lets write a big header
		preface.add(new Paragraph("Facture", catFont));
		document.add(preface);
		document.add(preface = new Paragraph(" "));
		Paragraph subPara = new Paragraph("Synthèse", subFont);
		document.add(subPara);

		document.add(subPara = new Paragraph(" "));

		subPara = new Paragraph();
		createTable(subPara, order);
		document.add(subPara);
		document.add(subPara = new Paragraph(" "));
		subPara = new Paragraph("Total : " + order.getTotal().toString());
		document.add(subPara);
	}

	private static void createTable(Paragraph subPara, Order order) throws BadElementException {

		PdfPTable table = new PdfPTable(5);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Titre"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Catégorie"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Quantité"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prix unitaire"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prix total"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		for (OrderItem item : order.getItems()) {
			item.setTotal(new BigDecimal(item.getBook().getPrice().doubleValue() * item.getQuantity()));
			table.addCell(item.getBook().getTitle());
			table.addCell(item.getBook().getCategory().toString());
			table.addCell(Integer.toString(item.getQuantity()));
			table.addCell(item.getBook().getPrice().toString());
			table.addCell(item.getTotal().toString());
		}

		subPara.add(table);

	}
}

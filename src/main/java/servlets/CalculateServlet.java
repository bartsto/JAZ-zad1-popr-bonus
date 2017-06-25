package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kwota;
	private String liczbaRat;
	private String oprocentowanie;
	private String oplataStala;
	private String rodzajRat;
	private String action;
	private float rataKapitalowa = 0;
	private Document document;
	private PdfPTable table;
	private Font font;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;  charset=UTF-8");
		kwota = request.getParameter("kwota");
		liczbaRat = request.getParameter("liczbaRat");
		oprocentowanie = request.getParameter("oprocentowanie");
		oplataStala = request.getParameter("oplataStala");
		rodzajRat = request.getParameter("rodzajRat");
		action = request.getParameter("action");

		if (action == null || action.equals("") || kwota == null || kwota.equals("") || liczbaRat == null
				|| liczbaRat.equals("") || oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("") || rodzajRat == null || rodzajRat.equals("")) {
			response.sendRedirect("/");
		} else if (action.equals("Oblicz!")) {

			if (rodzajRat.equals("stala")) {
				generateConstantInstallment(request, response);
			} else if (rodzajRat.equals("malejaca")) {
				generateDecreaseInstallment(request, response);
			}
		} else if (action.equals("Generuj PDF!")) {

			document = new Document();

			try {
				response.setContentType("application/pdf");
				PdfWriter.getInstance(document, response.getOutputStream());
				document.open();
				BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
				font = new Font(helvetica, 25);
				document.add(new Paragraph("Harmonogram spłat", font));
				document.add(new Paragraph(" "));
				table = new PdfPTable(5);
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 10f, 20f, 20f, 20f, 25f });
				font = new Font(helvetica, 12);
				table.addCell(new Phrase("Nr raty", font));
				table.addCell(new Phrase("Kwota kapitału", font));
				table.addCell(new Phrase("Kwota odsetek", font));
				table.addCell(new Phrase("Opłata stała", font));
				table.addCell(new Phrase("Całkowita kwota raty", font));

				if (rodzajRat.equals("stala")) {
					generateConstantInstallmentToPDF();
				} else {
					if (rodzajRat.equals("malejaca")) {
						generateDecreaseInstallmentToPDF();
					}
				}
				document.add(table);
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
		}
	}

	private void generateConstantInstallment(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.getWriter().println("<h1> Harmonogram spłat </h1>");
		rataKapitalowa = Float.parseFloat(kwota) / Float.parseFloat(liczbaRat);
		float odsetkiRaty = rataKapitalowa * (Float.parseFloat(oprocentowanie) / 100f);
		response.getWriter().println(
				"<table cellpadding='10' ><tr><th>Nr raty</th><th>Kwota kapitału</th><th>Kwota odsetek</th><th>Opłata stała</th><th>Całkowita kwota raty</th></tr>");
		for (int i = 0; i < Integer.parseInt(liczbaRat); i++) {
			response.getWriter()
					.println("<tr><td>" + (i + 1) + "</td><td>" + Math.round(rataKapitalowa * 100f) / 100f
							+ " zł</td><td>" + Math.round(odsetkiRaty * 100f) / 100f + " zł</td><td>" + oplataStala
							+ " zł</td><td>"
							+ Math.round((rataKapitalowa + odsetkiRaty + Float.parseFloat(oplataStala)) * 100f) / 100f
							+ " zł</td>");
		}
		response.getWriter().println("</table>");
	}

	private void generateConstantInstallmentToPDF() {
		rataKapitalowa = Float.parseFloat(kwota) / Float.parseFloat(liczbaRat);
		float odsetkiRaty = rataKapitalowa * (Float.parseFloat(oprocentowanie) / 100f);
		for (int i = 0; i < Integer.parseInt(liczbaRat); i++) {
			table.addCell((i + 1) + "");
			table.addCell(new Phrase(Math.round(rataKapitalowa * 100f) / 100f + " zł", font));
			table.addCell(new Phrase(Math.round(odsetkiRaty * 100f) / 100f + " zł", font));
			table.addCell(new Phrase(oplataStala + " zł", font));
			table.addCell(new Phrase(
					Math.round((rataKapitalowa + odsetkiRaty + Float.parseFloat(oplataStala)) * 100f) / 100f + " zł",
					font));
		}
	}

	private void generateDecreaseInstallment(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.getWriter().println("<h1> Harmonogram spłat </h1>");
		response.getWriter().println(
				"<table cellpadding='10' ><tr><th>Nr raty</th><th>Kwota kapitału</th><th>Kwota odsetek</th><th>Opłata stała</th><th>Całkowita kwota raty</th></tr>");
		rataKapitalowa = Float.parseFloat(kwota) / Float.parseFloat(liczbaRat);
		for (int i = 0; i < Integer.parseInt(liczbaRat); i++) {
			float rata_z_oprocentowaniem = rataKapitalowa * (1
					+ (Float.parseFloat(liczbaRat) - (i + 1) + 1) * (Float.parseFloat(oprocentowanie) / 100f) / 12f);
			response.getWriter().println("<tr><td>" + (i + 1) + "</td><td>" + Math.round(rataKapitalowa * 100f) / 100f
					+ " zł</td><td>" + Math.round((rata_z_oprocentowaniem - rataKapitalowa) * 100f) / 100f
					+ " zł</td><td>" + oplataStala + " zł</td><td>"
					+ Math.round((rata_z_oprocentowaniem + Float.parseFloat(oplataStala)) * 100f) / 100f + " zł</td>");
		}
		response.getWriter().println("</table>");
	}

	private void generateDecreaseInstallmentToPDF() {
		rataKapitalowa = Float.parseFloat(kwota) / Float.parseFloat(liczbaRat);
		for (int i = 0; i < Integer.parseInt(liczbaRat); i++) {
			float rata_z_oprocentowaniem = rataKapitalowa * (1
					+ (Float.parseFloat(liczbaRat) - (i + 1) + 1) * (Float.parseFloat(oprocentowanie) / 100f) / 12f);
			table.addCell((i + 1) + "");
			table.addCell(new Phrase(Math.round(rataKapitalowa * 100f) / 100f + " zł", font));
			table.addCell(
					new Phrase(Math.round((rata_z_oprocentowaniem - rataKapitalowa) * 100f) / 100f + " zł", font));
			table.addCell(new Phrase(oplataStala + " zł", font));
			table.addCell(new Phrase(
					Math.round((rata_z_oprocentowaniem + Float.parseFloat(oplataStala)) * 100f) / 100f + " zł", font));
		}
	}

}
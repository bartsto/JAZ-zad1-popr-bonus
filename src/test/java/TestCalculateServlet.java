import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.CalculateServlet;

@WebServlet("/calculate")
public class TestCalculateServlet extends Mockito {

	@Test
	public void servlet_should_not_calculate_if_the_kwota_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("kwota")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_liczbaRat_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("liczbaRat")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_oprocentowanie_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("oprocentowanie")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_oplata_stala_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("oplataStala")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_rodzaj_rat_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("rodzajRat")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_action_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("action")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_not_calculate_if_the_kwota_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("kwota")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_liczba_rat_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("liczbaRat")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_oprocentowanie_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("oprocentowanie")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_oplata_stala_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("oplataStala")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_rodzaj_rat_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("rodzajRat")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}

	@Test
	public void servlet_should_not_calculate_if_the_action_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		CalculateServlet servlet = new CalculateServlet();

		when(request.getParameter("action")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_calculate_when_rodzaj_rat_is_stala() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);

		when(request.getParameter("rodzajRat")).thenReturn("stala");
		when(request.getParameter("kwota")).thenReturn("1000");
		when(request.getParameter("oprocentowanie")).thenReturn("10");
		when(request.getParameter("oplataStala")).thenReturn("10");
		when(request.getParameter("liczbaRat")).thenReturn("10");
		when(request.getParameter("action")).thenReturn("Oblicz!");
		when(response.getWriter()).thenReturn(writer);

		new CalculateServlet().doPost(request, response);

		verify(writer).println("<h1> Harmonogram spłat </h1>");
		verify(writer).println(
				"<table cellpadding=\'10\' ><tr><th>Nr raty</th><th>Kwota kapitału</th><th>Kwota odsetek</th><th>Opłata stała</th><th>Całkowita kwota raty</th></tr>");
		verify(writer).println("<tr><td>1</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>2</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>3</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>4</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>5</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>6</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>7</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>8</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>9</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("<tr><td>10</td><td>100.0 zł</td><td>10.0 zł</td><td>10 zł</td><td>120.0 zł</td>");
		verify(writer).println("</table>");

	}

	@Test
	public void servlet_should_calculate_when_rodzaj_rat_is_malejaca() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);

		when(request.getParameter("rodzajRat")).thenReturn("malejaca");
		when(request.getParameter("kwota")).thenReturn("1000");
		when(request.getParameter("oprocentowanie")).thenReturn("10");
		when(request.getParameter("oplataStala")).thenReturn("10");
		when(request.getParameter("liczbaRat")).thenReturn("10");
		when(request.getParameter("action")).thenReturn("Oblicz!");
		when(response.getWriter()).thenReturn(writer);

		new CalculateServlet().doPost(request, response);

		verify(writer).println("<h1> Harmonogram spłat </h1>");
		verify(writer).println(
				"<table cellpadding=\'10\' ><tr><th>Nr raty</th><th>Kwota kapitału</th><th>Kwota odsetek</th><th>Opłata stała</th><th>Całkowita kwota raty</th></tr>");
		verify(writer).println("<tr><td>1</td><td>100.0 zł</td><td>8.33 zł</td><td>10 zł</td><td>118.33 zł</td>");
		verify(writer).println("<tr><td>2</td><td>100.0 zł</td><td>7.5 zł</td><td>10 zł</td><td>117.5 zł</td>");
		verify(writer).println("<tr><td>3</td><td>100.0 zł</td><td>6.67 zł</td><td>10 zł</td><td>116.67 zł</td>");
		verify(writer).println("<tr><td>4</td><td>100.0 zł</td><td>5.83 zł</td><td>10 zł</td><td>115.83 zł</td>");
		verify(writer).println("<tr><td>5</td><td>100.0 zł</td><td>5.0 zł</td><td>10 zł</td><td>115.0 zł</td>");
		verify(writer).println("<tr><td>6</td><td>100.0 zł</td><td>4.17 zł</td><td>10 zł</td><td>114.17 zł</td>");
		verify(writer).println("<tr><td>7</td><td>100.0 zł</td><td>3.33 zł</td><td>10 zł</td><td>113.33 zł</td>");
		verify(writer).println("<tr><td>8</td><td>100.0 zł</td><td>2.5 zł</td><td>10 zł</td><td>112.5 zł</td>");
		verify(writer).println("<tr><td>9</td><td>100.0 zł</td><td>1.67 zł</td><td>10 zł</td><td>111.67 zł</td>");
		verify(writer).println("<tr><td>10</td><td>100.0 zł</td><td>0.83 zł</td><td>10 zł</td><td>110.83 zł</td>");
		verify(writer).println("</table>");

	}
	
}

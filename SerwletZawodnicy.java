package pl.alx.kpij.serwlet_zawodnicy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SerwletZawodnicy
 */
@WebServlet("/SerwletZawodnicy")
public class SerwletZawodnicy extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerwletZawodnicy() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.getWriter().println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Pobieranie danych</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<form method=\"post\">\r\n" + 
				"		<h1>Wybierz jakie dane chcesz wyświetlić</h1>\r\n" + 
				"		<input style=\"margin: 30px\" type=\"submit\" name=\"select\" value=\"Lista zawodnikow\" />\r\n" + 
				"		<input style=\"margin: 30px\" type=\"submit\" name=\"select\" value=\"Lista trenerow\" />\r\n" + 
				"		<input style=\"margin: 30px\" type=\"submit\" name=\"select\" value=\"Lista skoczni\" />\r\n" + 
				"	</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String server = "jdbc:postgresql://localhost:5432/zawodnicy";
		String login = "postgres";
		String password = "Post12345";
		String listSelected = request.getParameter("select");
		String list = "";
		
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		
		if (listSelected.equals("Lista zawodnikow")) {
			
			String sqlQuery = "select * from zawodnicy";
			
			try (
					
					Connection c = DriverManager.getConnection(server, login, password);
					Statement stmt = c.createStatement();
					ResultSet result = stmt.executeQuery(sqlQuery);) {
				
				while (result.next()) {
					String firstName = result.getString("imie");
					String lastName = result.getString("nazwisko");
					String country = result.getString("kraj");
					String fullAdd = String.format("</br>%d. %s %s (%s)\n", counter, firstName, lastName, country);
					sb.append(fullAdd);
					counter++;
				}
			} catch (SQLException e) {
				System.out.println(e);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			list = sb.toString();
		}
		if (listSelected.equals("Lista trenerow")) {
			
			String sqlQuery = "select * from trenerzy";
			
			try (
					
					Connection c = DriverManager.getConnection(server, login, password);
					Statement stmt = c.createStatement();
					ResultSet result = stmt.executeQuery(sqlQuery);) {
				
				while (result.next()) {
					String firstName = result.getString("imie_t");
					String lastName = result.getString("nazwisko_t");
					String country = result.getString("kraj");
					String fullAdd = String.format("</br>%d. %s %s (%s)\n", counter, firstName, lastName, country);
					sb.append(fullAdd);
					counter++;
				}
			} catch (SQLException e) {
				System.out.println(e);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			list = sb.toString();
		}
		if (listSelected.equals("Lista skoczni")) {
			
			String sqlQuery = "select * from skocznie";
			
			try (
					
					Connection c = DriverManager.getConnection(server, login, password);
					Statement stmt = c.createStatement();
					ResultSet result = stmt.executeQuery(sqlQuery);) {
				
				while (result.next()) {
					String country = result.getString("kraj_s");
					String city = result.getString("miasto");
					String name = result.getString("nazwa");
					String fullAdd = String.format("</br>%d. %s %s - %s\n", counter, country, city, name);
					sb.append(fullAdd);
					counter++;
				}
			} catch (SQLException e) {
				System.out.println(e);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			list = sb.toString();
		}

		//Błąd
		//INFO  [stdout] (default task-1) java.sql.SQLException: No suitable driver found for jdbc:postgresql://localhost:5432/zawodnicy
		
		
		response.getWriter().println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Pobieranie danych</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<form method=\"post\">\r\n" + 
				"		<h1 style=\" margin: 0px 0px 0px 30px;\">Dane zawarte w bazie danych</h1>\r\n" + 
				"		<p style=\" margin: 20px 0px 0px 50px;\">Lista zawodników</p>\r\n" + 
				"		<p style=\" margin: 0px 0px 0px 80px;\">" + list + "</p>\r\n" + 
				"	</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	
	}

}

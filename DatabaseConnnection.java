package pl.alx.kpij.serwlet_zawodnicy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnnection {

	private static String server = "jdbc:postgresql://localhost:5432/zawodnicy";
	private static String login = "postgres";
	private static String password = "Post12345";
	private static String resultString;

	public static void main(String[] args) {
		getContestantsList();
	}
	
	public static void getContestantsList() {
		
		System.out.println("Lista wszystkich ogłoszeń wczytana z bazy danych:");
		String sqlQuery = "select * from zawodnicy";
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		try (Connection c = DriverManager.getConnection(server, login, password);
				Statement stmt = c.createStatement();
				ResultSet result = stmt.executeQuery(sqlQuery);) {
			while (result.next()) {
				String firstName = result.getString("imie");
				String lastName = result.getString("nazwisko");
				String country = result.getString("kraj");
				String fullAdd = String.format("%d. %s %s (%s)\n", counter, firstName, lastName, country);
				sb.append(fullAdd);
				counter++;
			}
		} catch (SQLException e) {
			System.out.println(e);

		}

		System.out.println(sb.toString());
		
		resultString = sb.toString();
		
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

}

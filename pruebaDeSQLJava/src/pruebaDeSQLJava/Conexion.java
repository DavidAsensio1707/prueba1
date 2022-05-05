package pruebaDeSQLJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	static String db = "pizza";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:3306/" + db + "?useSSL=false";
	Connection conn = null;

	public static void main(String[] args) {
		//llamo al constructor
		Conexion dbc = new Conexion();
		
	}
	
	public Conexion() {
		/*
		 * Base de datos local/remota A continuaci�n se muestra un ejemplo de uso, para
		 * una base de datos llamada pizzas. El SGBD se encuentra en la m�quina local
		 * (localhost) y las credenciales de usuario son pizzauser/Perro20.
		 */
		try {
			conectar();
			if (conn != null) {
				//inserci�n
				
				//consulta
				ResultSet rs = consultas();
				
				System.out.println("\nNOMBRE\tCODIGO");
				while (rs.next()) {
					imprimirConsulta(rs);
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido un error en la conexi�n:");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido cualquier otro error");
			e.printStackTrace();
		}
	}

	public void conectar() throws SQLException {
		conn = DriverManager.getConnection(url, login, password);
	}

	public void imprimirConsulta(ResultSet rs) throws SQLException {
		System.out.print(rs.getString("tipo")+"\t");
		System.out.print(rs.getInt("codigo"));
		System.out.println();
	}

	public ResultSet consultas() throws SQLException {
		System.out.println("Conexi�n ok: " + conn);
		ResultSet rs = conn.prepareStatement("SELECT * FROM pizzas").executeQuery();
		return rs;
	}

	public void insercion(int codigo, String nombre) throws SQLException {
		int totalRows= conn.prepareStatement("INSERT INTO pizzas values("+codigo+", '"+nombre+"')").executeUpdate();
		System.out.println(totalRows+" filas modificadas");
	}
	
}
package Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

public class empresa {
	ArrayList<Empleado> listaEmpleado = new ArrayList<>();

	public ArrayList<Empleado> getListaEmpleado(ArrayList<Empleado> listaEmpleados) {
		return listaEmpleados;
	}

	private Connection conexion;

	public void conectarBD() {

		try {
			// Conectamos con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleados", "root", "");
			System.out.println((conexion != null) ? "CONEXION OK" : "CONEXION FALLIDA");

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public ResultSet consultar(String dniInsertado) {
		ResultSet resultado;
		try {
			PreparedStatement sentencia = conexion
					.prepareStatement("SELECT * from empleados where dni = '" + dniInsertado + "'");
			resultado = sentencia.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resultado;
	}

	public boolean insertar(Empleado emp) {
		System.out.println("Procedemos a insertar datos.");
		String dni = emp.getDni();
		String nombre = emp.getNombre();
		String apellidos = emp.getApellido();
		int sueldo = emp.getSueldo();
		try {
			PreparedStatement sentenciaInsertar = conexion.prepareStatement(
					"INSERT INTO EMPLEADOS ('"+dni+"','"+nombre+"','"+apellidos+"',"+sueldo+")");
			sentenciaInsertar.setString(1, dni);
			sentenciaInsertar.setString(2, nombre);
			sentenciaInsertar.setString(3, apellidos);
			sentenciaInsertar.setInt(4, sueldo);
			

			ResultSet rs = sentenciaInsertar.executeQuery();
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
				return true;
			}
			rs.close();

		} catch (Exception e) {
			System.out.println("no conecta");

		}
		return false;

	}

	/*
	 * "insert into empleados values('" + empleado.getDni() + "', '" +
	 * empleado.getNombre() + "', '" + empleado.getApellido() + "', " +
	 * empleado.getSueldo() + ", "+empleado.getId();
	 */

	public boolean ejecutar(String cambio) {
		try {
			PreparedStatement sentencia = conexion.prepareStatement(cambio);
			int totalRows = sentencia.executeUpdate(); // devuelve el número de tuplas modificadas
			System.out.println("Se han modificado: " + totalRows + " tuplas");
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void imprimirResultado(ResultSet resultado) {
		try {
			System.out.println("\nDNI\tNOMBRE\tAPELLIDO\tSUELDO\tID");
			System.out.println("--------------------------------");
			while (resultado.next()) {
				System.out.println(resultado.getString("dni") + "\t" + resultado.getString("nombre") + "\t"
						+ resultado.getString("apellidos") + "\t" + +resultado.getInt("sueldo") + "\t"
						+ resultado.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
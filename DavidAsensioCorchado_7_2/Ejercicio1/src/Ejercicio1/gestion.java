package Ejercicio1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class gestion {

	public static void main(String[] args) {

		Empleado emp1 = new Empleado("13467954B", "Juan", "Arias", 1350);
		Empleado emp2 = new Empleado("26485914J", "Paco", "White", 1502);
		
		
		empresa dbc = new empresa();
		dbc.conectarBD();
		
		
		ArrayList<Empleado> listaEmpresa = new ArrayList<>();
		listaEmpresa.add(emp1);
		listaEmpresa.add(emp2);
		System.out.println(dbc.getListaEmpleado(listaEmpresa));
		
		
		dbc.insertar(emp1);
		dbc.insertar(emp2);
		dbc.consultar("13467954B");

	}

}

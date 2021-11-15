package BD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
public class Main {

	public static void main(String[] args) {
		String datosnba;
		
		try {
			//General
			BufferedWriter wr = new BufferedWriter(new FileWriter(new File("datosdb.txt")));
			Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/nba","root","admin");
			DatabaseMetaData datos = conexion.getMetaData();
			Statement s = conexion.createStatement();
			//Apartado 3
			ResultSet rs = s.executeQuery("show tables from nba");
			
			
			wr.write("Nombre db: "+datos.getCatalogs()+"\n");
			wr.write("Version conector db: "+datos.getDriverVersion()+"\n");
			wr.write("Nombre del conector "+datos.getDriverName()+"\n");
			
			while(rs.next()) {
				
				wr.write(rs.getString(1)+"\n");
				
			}
			System.out.println("Ejecucion terminada: Fichero datosdb.txt escrito");		
			wr.close();
			
			//Apartado 4
			BufferedWriter aux = new BufferedWriter(new FileWriter(new File("crud.txt")));
			
			//Insertar datos
			String ins1 = "insert into equipos(Nombre,Ciudad,Conferencia,Division) values('Blackbulls','Madrid','SP','2DIV')";
			s.executeUpdate(ins1);
			String ins2 = "insert into equipos(Nombre,Ciudad,Conferencia,Division) values('bulls','Madrid','BU','2DIV')";
			s.executeUpdate(ins2);
			String ins3 = "insert into equipos(Nombre,Ciudad,Conferencia,Division) values('Black','Madrid','BK','2DIV')";
			s.executeUpdate(ins3);
			
			datosnba = "select * from equipos";
			
			rs = s.executeQuery(datosnba);
			aux.write("Apartado 4: insertar datos"+"\n");
			while(rs.next()) {
				aux.write("Nombre de equipo -->"+rs.getString(1)+" Ciudad -->"+rs.getString(2)+" Conferencia-->"+rs.getString(3)+" Division-->"+rs.getString(4)+"\n");
			}
			aux.newLine();
			//Eliminar datos
			String el = "DELETE FROM equipos WHERE ('Nombre' = 'Black')";
			s.executeUpdate(el);
			
			rs = s.executeQuery(datosnba);
			aux.write("Apartado 4: Eliminar equipo black");
			while(rs.next()) {
				aux.write("Nombre de equipo -->"+rs.getString(1)+" Ciudad -->"+rs.getString(2)+" Conferencia-->"+rs.getString(3)+" Division-->"+rs.getString(4)+"\n");
			}
			aux.newLine();
			
			//Modificar datos
			//Es aquí
			String upda = "UPDATE equipos SET Conferencia = 'EU' WHERE (Nombre = 'bulls')";
			s.executeUpdate(upda);
			
			rs = s.executeQuery(datosnba);
			aux.write("Apartado 4: Modificar equipo bulls conferencia");
			while(rs.next()) {
				aux.write("Nombre de equipo -->"+rs.getString(1)+" Ciudad -->"+rs.getString(2)+" Conferencia-->"+rs.getString(3)+" Division-->"+rs.getString(4)+"\n");
			}
			
			
			
			aux.close();
			
			
		}catch(SQLException |IOException ex) {
			ex.printStackTrace();
		}
		
	}

}

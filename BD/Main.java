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
		
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(new File("datosdb.txt")));
			Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/nba","root","admin");
			DatabaseMetaData datos = conexion.getMetaData();
			ResultSet rs = datos.getTables(null, null, ";", args);
			wr.write(rs.getString(3)+"\n");
			
			wr.write("Nombre db: "+datos.getCatalogs()+"\n");
			wr.write("Version conector db: "+datos.getDriverVersion()+"\n");
			
			
			
			
			
			wr.close();
			
			
			System.out.println("Ejecucion terminada");
		}catch(SQLException |IOException ex) {
			ex.printStackTrace();
		}
		
	}

}

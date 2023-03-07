import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Ehdokkaat;


public class EhdokkaatSql {
public static Connection getConnection() {
    Connection conn = null;  
    
    
    try {
		Class.forName("com.mysql.jdbc.Driver");
    	String url="jdbc:mysql://localhost:3306/vaalikone";  
        conn=DriverManager.getConnection(url, "root", "Aje531008"); 

	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    return conn;
    
	
	
	
}
public static int Create(Ehdokkaat e) {
int e1=0;

	
	
	try {
		String sql = "INSERT INTO EHDOKKAAT (SUKUNIMI, ETUNIMI, PUOLUE, KOTIPAIKKAKUNTA, IKA, MIKSI_EDUSKUNTAAN, MITA_ASIOITA_HALUAT_EDISTAA, AMMATTI) VALUES (?,?,?,?,?,?,?,?)";
		Connection con=EhdokkaatSql.getConnection();
		
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1,e.getSukunimi());
		statement.setString(2,e.getEtunimi());
		statement.setString(3,e.getPuolue());
		statement.setString(4,e.getKotipaikkakunta());
		statement.setLong(5,e.getIka());
		statement.setString(6,e.getMiksiEduskuntaan());
		statement.setString(7,e.getMitaAsioitaHaluatEdistaa());
		statement.setString(8,e.getAmmatti());
		statement.execute();
//		e1=statement.executeUpdate();
		con.close();

		
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
    
	return e1;


}
public static int Update(Ehdokkaat e) {
int e1=0;

try {
    String sql = "UPDATE EHDOKKAAT set SUKUNIMI =?, ETUNIMI=?, PUOLUE=?, KOTIPAIKKAKUNTA=?, IKA=?, MIKSI_EDUSKUNTAAN=?, MITA_ASIOITA_HALUAT_EDISTAA=?, AMMATTI=? WHERE EHDOKAS_ID=?";
	Connection con=EhdokkaatSql.getConnection();
	
	PreparedStatement statement=  con.prepareStatement(sql);

	statement.setString(1,e.getSukunimi());
	statement.setString(2,e.getEtunimi());
	statement.setString(3,e.getPuolue());
	statement.setString(4,e.getKotipaikkakunta());
	statement.setLong(5,e.getIka());
	statement.setString(6,e.getMiksiEduskuntaan());
	statement.setString(7,e.getMitaAsioitaHaluatEdistaa());
	statement.setString(8,e.getAmmatti());
	statement.setInt(9,e.getEhdokasId());
	e1=statement.executeUpdate();
	con.close();

} catch (SQLException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}


return e1;

}


public static int Delete(int ehdokasId) {
	int e1=0;
	String sql ="DELETE FROM EHDOKKAAT WHERE EHDOKAS_ID=?";
	Connection con=EhdokkaatSql.getConnection();

    try {
    	PreparedStatement statement=  con.prepareStatement(sql);
    	statement.setInt(1,ehdokasId);
        statement.execute();
        statement.executeUpdate();
        con.close();


	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	return e1;
	
}
public static Ehdokkaat getEhdokasbyId(int ehdokasid) {
	Ehdokkaat ehdokas=new Ehdokkaat();
	
	try {
		String sql ="SELECT * FROM EHDOKKAAT WHERE EHDOKAS_ID = ?";
		Connection con=EhdokkaatSql.getConnection();
		PreparedStatement statement=con.prepareStatement(sql);
		statement.setInt(1, ehdokasid);
	    ResultSet result = statement.executeQuery();
	    
		if(result.next()) {
		    ehdokas.setEhdokasId(result.getInt(1));
			ehdokas.setSukunimi(result.getString(2));
			ehdokas.setEtunimi(result.getString(3));
			ehdokas.setPuolue(result.getString(4));
			ehdokas.setKotipaikkakunta(result.getString(5));
			ehdokas.setIka(result.getLong(6));
			ehdokas.setMiksiEduskuntaan(result.getString(7));
			ehdokas.setMitaAsioitaHaluatEdistaa(result.getString(8));
			ehdokas.setAmmatti(result.getString(9));

		}
		con.close();
	} catch (SQLException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
    
	return ehdokas;

}


public static List<Ehdokkaat> getEhdokkaitta() {
    List<Ehdokkaat> list= new ArrayList<Ehdokkaat>();

	String sql="SELECT * FROM EHDOKKAAT";
	Connection con=EhdokkaatSql.getConnection();

	PreparedStatement statement;
	try {

		statement = con.prepareStatement(sql);
	    ResultSet result;
		result=statement.executeQuery(sql);



	    while(result.next()) {
	    	
	    	Ehdokkaat ehdokas =new Ehdokkaat();


	    	ehdokas.setEhdokasId(result.getInt(1));
	    	ehdokas.setSukunimi(result.getString(2));
	    	ehdokas.setEtunimi(result.getString(3));
	    	ehdokas.setPuolue(result.getString(4));
	    	ehdokas.setKotipaikkakunta(result.getString(5));
	    	ehdokas.setIka(result.getInt(6));
	    	ehdokas.setMiksiEduskuntaan(result.getString(7));
	    	ehdokas.setMitaAsioitaHaluatEdistaa(result.getString(8));
	    	ehdokas.setAmmatti(result.getString(9));

	        list.add(ehdokas);

	    }

		con.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  

    
    

		return list;
}

}







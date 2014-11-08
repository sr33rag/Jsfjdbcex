package net.codesree.common;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userData",eager=true)
@SessionScoped
public class UserData implements Serializable {

  public UserData() {
	try {
	  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	} catch(ClassNotFoundException cnfex) {
	  System.out.println("Exception: "+cnfex.getMessage());
	  cnfex.printStackTrace();
	} catch(Exception ex) {
	  System.out.println("Exception: "+ex.getMessage());
	  ex.printStackTrace();
	}
  }

  public List<Author> getAuthors() {
	Author author=null;
	List<Author> authorList=null;
	ResultSet rs=null;
	Connection conn=null;
	PreparedStatement ps=null;
	try {
	  conn=connect();
	  ps=conn.prepareStatement("SELECT * FROM author");
	  rs=ps.executeQuery();
	  authorList=new ArrayList<Author>();
	  while(rs.next()) {
		author=new Author(rs.getInt("id"),rs.getString("name"));
		authorList.add(author);
	  }
	} catch (SQLException sqlex) {
	  System.out.println("SQLException: "+sqlex.getMessage());
	  sqlex.printStackTrace();
	} catch (Exception ex) {
	  System.out.println("Exception: "+ex.getMessage());
	  ex.printStackTrace();
	} /*finally {
	  try {
		rs.close();
		ps.close();
		conn.close();
	  } catch (SQLException sqlex2) {
		 System.out.println("Exception2: "+sqlex2.getMessage());
	  }
	}*/
	return authorList;
  }

  private Connection connect() {
	Connection con=null;
	try {
	  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	  con=DriverManager.getConnection("jdbc:derby:/home/sree25/derbydbs/jsfdb","user12","34klq*");
	  //con=DriverManager.getConnection("jdbc:derby:D:/Sree19/custom/derbydbs/jsfdb","user12","34klq*");
	} catch(SQLException sqlex) {
	  System.out.println("Connection error: "+sqlex.getMessage());
	  sqlex.printStackTrace();
	} catch(Exception ex) {
	  System.out.println("Error: "+ex.getMessage());
	  ex.printStackTrace();
	}
	return con;
  }

}



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ehdokkaat;




@WebServlet("ViewCandidate")
public class ViewCandidate extends HttpServlet {
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
        out.println("<a href='index.html'>Add New Candidate</a>");  
        out.println("<h1>Candidates List</h1>");  
	    
        List<Ehdokkaat> list=EhdokkaatSql.getEhdokkaitta();
        
        out.println("<table border='2' width='100%'");
        out.println("<tr><th>EhdokasId</th><th>Sukunimi</th>"+"<th>Etunimi</th><th>Puolue</th>"+"<th>KOTIPAIKKAKUNTA</th><th>IKA</th>"+"<th>miksi_eduskuntaan</th><th>Mita_haluat_edistaa</th><th>Ammatti</th></tr>");
        for(Ehdokkaat ehdokas:list) {
        	out.print("<tr><td>"+ehdokas.getEhdokasId()+"</td><td>"+ehdokas.getSukunimi()+"</td><td>"+ehdokas.getEtunimi()+"</td><td>"+ehdokas.getPuolue()+"</td><td>"+ehdokas.getKotipaikkakunta()+"</td><td>"+ehdokas.getIka()+"</td><td>"+ehdokas.getMiksiEduskuntaan()+"</td><td>"+ehdokas.getMitaAsioitaHaluatEdistaa()+"</td><td>"+ehdokas.getAmmatti()+"</td><td><a href='UpdateCandidate?ehdokasId="+ehdokas.getEhdokasId()+"'>edit</a></td><td><a href='DeleteCandidate?ehdokasId="+ehdokas.getEhdokasId()+"'>delete</a></td></tr>");
        }
        
        out.print("</table>");
        out.print("</body>");
        out.close();

	    
	    
		
		
		
	}

	
	
}

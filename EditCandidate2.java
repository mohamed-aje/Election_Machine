

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ehdokkaat;

/**
 * Servlet implementation class EditCandidate2
 */
@WebServlet("EditCandidate2")
public class EditCandidate2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCandidate2() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	            response.setContentType("text/html");  
	            PrintWriter out=response.getWriter();
		        int ehdokasId = Integer.parseInt(request.getParameter("ehdokasId"));

			    String sukunimi=request.getParameter("sukunimi");
			    String etunimi=request.getParameter("etunimi");
			    String puolue=request.getParameter("puolue");
			    String KOTIPAIKKAKUNTA=request.getParameter("KOTIPAIKKAKUNTA");
			    int ika = Integer.parseInt(request.getParameter("ika"));
			    String miksi_eduskuntaan=request.getParameter("miksi_eduskuntaan");
			    String Mita_haluat_edistaa=request.getParameter("Mita_haluat_edistaa");
			    String Ammatti=request.getParameter("Ammatti");
			   
	        
	        Ehdokkaat e=new Ehdokkaat(); 
			e.setEhdokasId(ehdokasId);
	        e.setSukunimi(sukunimi);  
	        e.setEtunimi(etunimi);  
	        e.setPuolue(puolue);  
	        e.setKotipaikkakunta(KOTIPAIKKAKUNTA); 
	        e.setIka(ika);
	        e.setMiksiEduskuntaan(miksi_eduskuntaan);
	        e.setMitaAsioitaHaluatEdistaa(Mita_haluat_edistaa); 
	        e.setAmmatti(Ammatti);

	        int status =EhdokkaatSql.Update(e); 
	        if(status>0){  
	            response.sendRedirect("ViewCandidate");  
	        }else{  
	            out.println("Sorry! unable to update record");  
	        }  
	          
	        out.close();  		
		
		
		
		
	}


}

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ehdokkaat;

@WebServlet(
    name = "SaveCandidate",
    urlPatterns = {"SaveCandidate"}
)
public class SaveCandidate extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter printWriter=response.getWriter();
    
    
    String sukunimi=request.getParameter("sukunimi");
    String etunimi=request.getParameter("etunimi");
    String puolue=request.getParameter("puolue");
    String KOTIPAIKKAKUNTA=request.getParameter("KOTIPAIKKAKUNTA");
    int ika = Integer.parseInt(request.getParameter("ika"));
    String miksi_eduskuntaan=request.getParameter("miksi_eduskuntaan");
    String Mita_haluat_edistaa=request.getParameter("Mita_haluat_edistaa");
    String Ammatti=request.getParameter("Ammatti");

    
    
    Ehdokkaat ehdokas= new Ehdokkaat();
    ehdokas.setSukunimi(sukunimi);
    ehdokas.setEtunimi(etunimi);
    ehdokas.setPuolue(puolue);
    ehdokas.setKotipaikkakunta(KOTIPAIKKAKUNTA);
    ehdokas.setMiksiEduskuntaan(miksi_eduskuntaan);
    ehdokas.setMitaAsioitaHaluatEdistaa(Mita_haluat_edistaa);
    ehdokas.setAmmatti(Ammatti);
    ehdokas.setIka(ika);
    int num=EhdokkaatSql.Create(ehdokas);
    
    if (num>0){
    	printWriter.println("<h2>Saved Successfully</h2>");
    	request.getRequestDispatcher("index.html");
    }
    else {
    
        response.sendRedirect("ViewCandidate"); 

    }
    	
  }
}
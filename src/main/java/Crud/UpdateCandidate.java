

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ehdokkaat;

/**
 * Servlet implementation class UpdateCandidate
 */
@WebServlet("UpdateCandidate")
public class UpdateCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String ehdokasId=request.getParameter("ehdokasId");
		Ehdokkaat e1=EhdokkaatSql.getEhdokasbyId(Integer.parseInt(ehdokasId));
        
		out.println("<h1>Update Ehdookkaat Info</h1>");

		out.print("<form action='EditCandidate2' method='post'>");
		out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='ehdokasId' value='"+e1.getEhdokasId()+"'/></td></tr>");  

		out.print("<tr><td>Sukunimi:</td><td><input type='text' name='sukunimi' value='" + e1.getSukunimi()+ "'/></td></tr>");
		out.print("<tr><td>Etunimi:</td><td><input type='text' name='etunimi' value='" + e1.getEtunimi()+ "'/>  </td></tr>");
		out.print("<tr><td>Puolue:</td><td><input type='text' name='puolue' value='" + e1.getPuolue()+ "'/></td></tr>");
		out.print("<tr><td>Kotipaikkakunta:</td><td><select name='KOTIPAIKKAKUNTA' style='width:150px'>");
        out.print("<option>Tampere</option>");  
        out.print("<option>Turku</option>");  
        out.print("<option>Helsinki</option>");
        out.print("<option>other</option>");  
        out.print("</select>"); 
        out.print("</td></tr>");


		out.print("<tr><td>Ika:</td><td><input type='number' name='ika' value='" + e1.getIka() + "'/></td></tr>");
		out.print("<tr><td>MIKSI_EDUSKUNTAAN:</td><td><input type='text' name='miksi_eduskuntaan' value='"+ e1.getMiksiEduskuntaan() + "'/></td></tr>");
		out.print("<tr><td>MITA_ASIOITA_HALUAT_EDISTAA:</td><td><input type='text' name='Mita_haluat_edistaa' value='"+ e1.getMitaAsioitaHaluatEdistaa() + "'/></td></tr>");
		out.print("<tr><td>Ammatti:</td><td><input type='text' name='Ammatti' value='" + e1.getAmmatti()+ "'/></td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");

		out.close();

	}
	}



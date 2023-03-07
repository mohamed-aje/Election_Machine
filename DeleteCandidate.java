

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCandidate
 */
@WebServlet("DeleteCandidate")
public class DeleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	   String EhdokasId=request.getParameter("ehdokasId");
	   int ehdokasId=Integer.parseInt(EhdokasId);
	    EhdokkaatSql.Delete(ehdokasId);
        response.sendRedirect("ViewCandidate"); 
		
		
	}



}

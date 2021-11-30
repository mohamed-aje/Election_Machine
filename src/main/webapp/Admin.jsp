<%@page import="persist.Ehdokkaat"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.PersistenceContext"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<%
	Object error = request.getAttribute("messag");
%>

<%
	//     if (session.getAttribute("admin") != "admin") {

//         request.getRequestDispatcher("LogAdm.jsp")
//                 .forward(request, response);

//     }
//     EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
//   EntityManager em = emf.createEntityManager();
EntityManagerFactory emf = null;
EntityManager em = null;
try {
	emf = Persistence.createEntityManagerFactory("vaalikones");
	em = emf.createEntityManager();
} catch (Exception e) {
	response.getWriter().println("EMF+EM Not Working");

	e.printStackTrace(response.getWriter());

	return;
}
int limit = 0;

//HttpSession sessions = request.getSession();
//int total = 0;
//String temp = (String) session.getAttribute("limit-total");
// int total = Integer.parseInt(session.getAttribute("limit-total").toString());
// int limitCached = Integer.parseInt(session.getAttribute("limitCached").toString());
Integer total = (Integer) session.getAttribute("limit-total");
Integer limitCached = (Integer) session.getAttribute("limitCached");
// response.getWriter().println(temp);
// if (temp != null) {
// 	total = Integer.parseInt(temp);
// }

String paged = request.getParameter("paged");
//response.getWriter().println(paged);
if (paged != null) {
	limit = Integer.parseInt(paged);
}
if (limit == 0) {
	limit = 1;
} else if (limit == 1) {
	total = 0;
} else {
	if (limit != limitCached) {
		total = (limit < limitCached) ? total - 10 : total + 10;
	}
	if (total < 0) {
		total = 0;
	}
}

session.setAttribute("limit-total", total);
session.setAttribute("limitCached", limit);
Query qE = em.createQuery("SELECT e FROM Ehdokkaat e ORDER BY e.ehdokasId").setFirstResult(total).setMaxResults(10);
List<Ehdokkaat> ehdokasList = qE.getResultList();

int count = ((Number) em.createNativeQuery("SELECT count(*) FROM Ehdokkaat").getSingleResult()).intValue();

em.close();
%>

<!-- ____________________________________________________ -->
<!-- _________________<!DOCTYPE html>____________________ -->

<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
    <%if (error != null) {%>
    var msg = "<%= error %>"
	alert(msg);
<%}%>
	
</script>
<body>


	<h1>List of Existing Candidate:</h1>
	<form method="post" name="frm" action="searchview.jsp">
		<table border="0" width="300" align="center">
			<tr>
				<td colspan=2 style="font-size: 12pt;" align="center">
					<h2>Search Candidate</h2>
				</td>
			</tr>

			<td><input type="text" name="pid" id="pid" placeholder="Name">
			</td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="submit" name="submit"
					value="Search"></td>
			</tr>

		</table>
		<br>
	</form>

	<a href="Create.jsp" class="button">Add & Delete Candidate</a>

	<!----------------- LOGOUT SESSION ------------>

	<form action="Logout">
		<input class="buton Logout" type="submit" value="Logout" name="Logout" />
	</form>

	<table>
		<thead>
			<tr>

				<th>View</th>
				<th>Id</th>
				<th>Nimi</th>
				<th>Puolue</th>
				<th>Kotipaikkakunta</th>
				<th>Ika</th>
				<!--<th>Miksi</th> -->
				<!--<th>Mita</th> -->
				<th>Ammatti</th>

			</tr>
		</thead>
		<tbody>
			<%
				// List all candidate on the machine
			for (int i = 1; i <= ehdokasList.size(); i++) {
				Ehdokkaat one = ehdokasList.get(i - 1);
			%>

			<tr>

				<td><a class="buton"
					href="View.jsp?user=<%=one.getEhdokasId()%>">View</a> <a
					class="buton" href="Edit.jsp?user=<%=one.getEhdokasId()%>">Edit</a></td>
				<td><%=one.getEhdokasId()%></td>
				<td><%=one.getEtunimi() + " " + one.getSukunimi()%></td>
				<td><%=one.getPuolue()%></td>
				<td><%=one.getKotipaikkakunta()%></td>
				<td><%=one.getIka()%></td>
				<%--<td><%=ehdokasList.get(i - 1).getMiksiEduskuntaan()%></td> --%>
				<%--<td><%=ehdokasList.get(i - 1).getMitaAsioitaHaluatEdistaa()%></td> --%>
				<td><%=one.getAmmatti()%></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>





	<div class="pagination">

		<%
			if (limit > 1) {
			int p = (limit > 1) ? limit - 1 : 1;
		%>

		<a href="/Admin.jsp?paged=<%=p%>"> &lArr; previous </a>


		<%
			}
		%>

		<span style="margin: 15px;"><%=limit%></span>
		<%
			if (total < (count - 10)) {
		%>

		<a href="/Admin.jsp?paged=<%=limit + 1%>"> next &rArr; </a>

		<%
			}
		%>
	</div>

	</div>
</body>
</html>
<%
	//Comprueba si se est� autorizado a ver la p�gina
	boolean autorizado = false;
	if(session.getAttribute("usuario")==null)
	{
		response.sendRedirect("./errorPages/errorNoAutor.jsp");
	}
	else
	{
		es.jahernandez.datos.ConUsuVO cVO = (es.jahernandez.datos.ConUsuVO) session.getAttribute("usuario");
		if(cVO.getNivelAcceso() >= 5)
		{
			autorizado= true;
		}
		if(! autorizado)
		{
			response.sendRedirect("./errorPages/errorNoAutor.jsp");
		}
	}
%>
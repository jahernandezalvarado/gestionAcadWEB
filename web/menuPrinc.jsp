
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.jahernandez.datos.ConUsuVO"%>

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<%@ include file="controlAcceso/includeComAut.jsp"%>
<%
    boolean      mostrarPendientes = false; //Indica si se muestran los recibos pendientes
    //ControlRecVO contRecVO         = new ControlRecVO();
    String       mesActual         = new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));   
    String       annoActual        = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));   
    ConUsuVO     conUsVO = new ConUsuVO();

    conUsVO = (ConUsuVO) session.getAttribute("usuario");  

    //contRecVO.setFecha(annoActual + mesActual);

    //mostrarPendientes = ControlRecDAO.generadosRecMes(contRecVO.getFecha(), conUsVO.getIdCentro());
%>
<title>Gestor de Centros Académicos</title>
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="./js/bibliotecas/jquery-ui-1.9.0.custom/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="./js/bibliotecas/jquery-ui-1.9.0.custom/js/jquery-ui-1.9.0.js"></script>
<script type="text/javascript" src="./js/bibliotecas/jquery-ui-1.9.0.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></script>
    
<link rel="stylesheet" type="text/css" href="./js/bibliotecas/jquery-ui-1.9.0.custom/development-bundle/themes/custom-theme16/jquery.ui.all.css"/>

<link href="./css/disenno.css" rel="stylesheet" type="text/css" />


<!--Funciones jquery ui-->
<script>
    $(function() {
        $( "#menuPrincipal" ).accordion({
            collapsible: true,
			active: false,
			heightStyle: "content"
        });
    });
</script>

</head>

<body class="fondoFormularios">

<table width="" border="0">
  <tr>
   <td>
       <%//if (! mostrarPendientes){%>
				<input name="btnImpRec" type="button" onClick="window.open('./ImpRecAluServlet', 'rg','');" value="Imprimir Recibos" />
		<%//}else{%>
                <!--a href="./impRecPenAluServlet" target="rp">Imprimir Pendientes</a-->                
       <%//}%>
       
   </td>
  </tr>
</table>
<div id="menuPrincipal">
	<span class="tdDef">Alumnos</span>
	<div>
  		<table width="90%" border="0" class="formatoMenu">
          <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./interesados/busquedaInter.jsp' , 'fraPrincipal' , '')">
            <td width="5%">&nbsp;</td>
            <td width="95%">Buscar</td>
          </tr>
        </table>
	</div>  
    <span class="tdDef">Aulas</span>
    <div>
        <table width="90%" border="0" class="formatoMenu">
          <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./aulas/anaAula.jsp' , 'fraPrincipal' , '')">
            <td width="5%">&nbsp;</td>
            <td width="95%">Añadir</td>
          </tr>
          <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./aulas/busAula.jsp' , 'fraPrincipal' , '')">
            <td>&nbsp;</td>
            <td>Editar</td>
          </tr>
        </table>  
  </div>
    <span class="tdDef">Cursos</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
            <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/altaCurso.jsp' , 'fraPrincipal' , '')">
        		<td>&nbsp;</td>
        		<td>Añadir</td>
      		</tr>
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/busCurso.jsp' , 'fraPrincipal' , '')">
        		<td>&nbsp;</td>
        		<td>Editar</td>
      		</tr>
 			<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/visAluCur.jsp' , 'fraPrincipal' , '')">
                <td width="5%">&nbsp;</td>
                <td width="95%">Interesados</td>
 	        </tr>
            <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./ResumenEdicionesServlet' , '_rescur' , '')">
                <td>&nbsp;</td>
                <td>Resumen Ediciones</td>
            </tr>
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./ediciones/histMatAlu.jsp' , 'fraPrincipal' , '')">
        		<td>&nbsp;</td>
        		<td>Histórico Matrículas</td>
      		</tr>
            <tr>
            	<td>&nbsp;</td>
              <td>
                <div id="menuTipoCurso1" class="CollapsiblePanel">
          				<div class="CollapsiblePanelTab" tabindex="0"><span class="colorTextoBotPest">Tipo Curso</span></div>
          				<div class="CollapsiblePanelContent">
                        	<table width="90%" border="0" class="formatoMenu">
                                <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/altaTipCur.jsp' , 'fraPrincipal' , '')">
                                    <td width="5%">&nbsp;</td>
                                    <td width="95%">Añadir</td>
                                </tr>
                                <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/ediTipCur.jsp' , 'fraPrincipal' , '')">
                                    <td>&nbsp;</td>
                                    <td>Editar</td>
                                </tr>
          					</table>
		          	   </div>
        			</div>

              </td>
    	    </tr>
      </table>
    </div>
    <span class="tdDef">&Aacute;reas</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
      <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/altaArea.jsp' , 'fraPrincipal' , '')">
        <td width="5%">&nbsp;</td>
        <td width="95%">Añadir</td>
      </tr>
       <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./cursos/ediArea.jsp' , 'fraPrincipal' , '')">
        <td>&nbsp;</td>
        <td>Editar</td>
      </tr>
    </table>	
  	</div>
    <span class="tdDef">Profesores</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./profesores/altaProfesor.jsp' , 'fraPrincipal' , '')">
        		<td width="5%">&nbsp;</td>
        		<td width="95%">Añadir</td>
      		</tr>
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./profesores/busqProf.jsp' , 'fraPrincipal' , '')">
        		<td>&nbsp;</td>
        		<td>Buscar</td>
      		</tr>
    	</table>
    </div>
    <span class="tdDef">Empresas</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./empresas/altaEmp.jsp' , 'fraPrincipal' , '')">
        		<td width="5%">&nbsp;</td>
        		<td width="95%">Alta</td>
      		</tr>
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./empresas/busqEmp.jsp' , 'fraPrincipal' , '')">
        		<td>&nbsp;</td>
        		<td>Buscar</td>
      		</tr>
    	</table>
    </div>
    <span class="tdDef">Recibos</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
      		<tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./ediciones/busRec.jsp' , 'fraPrincipal' , '')">
        		<td width="5%">&nbsp;</td>
        		<td width="95%">Histórico Recibos</td>
      		</tr>
    	</table>
    </div>
    <%if(conUsVO.getNivelAcceso() >= 5){%> 
    <span class="tdDef">Centros</span>
    <div>
    	<table width="90%" border="0" class="formatoMenu">
      <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./centros/altaCentro.jsp' , 'fraPrincipal' , '')">
        <td width="5%">&nbsp;</td>
        <td width="95%">Añadir</td>
      </tr>
      <tr onmouseover="this.className='filaresaltada';"  onmouseout="this.className='';" onclick="window.open('./centros/ediCentro.jsp' , 'fraPrincipal' , '')">
        <td width="5%">&nbsp;</td>
        <td width="95%">Editar</td>
      </tr>
    </table>
    </div>
    <%}%>
</div>    
   		
                     

<p>&nbsp; </p>
<p>&nbsp; </p>
<script type="text/javascript">
var CollapsiblePanel21 = new Spry.Widget.CollapsiblePanel("menuTipoCurso1", {contentIsOpen:false});
  </script>
</body>
</html>
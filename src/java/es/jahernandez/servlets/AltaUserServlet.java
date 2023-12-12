/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jahernandez.servlets;

import es.jahernandez.accesodatos.ConUsoDAO;
import es.jahernandez.datos.ConUsuVO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author JuanAlberto
 */
public class AltaUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        HttpSession   sesion       = request.getSession();
        ConUsuVO      userVO       = new ConUsuVO();
        int           resultadoAlt = 0;
        String        indPrevio    = "";
        
        Logger               log      = null;
        ConUsuVO             conUsoVO = null;
        
        //Cargamos atributos de log
        if(sesion.getAttribute("logControl") != null && sesion.getAttribute("usuario") != null)
        {
            log = (Logger) sesion.getAttribute("logControl");
            conUsoVO = (ConUsuVO) sesion.getAttribute("usuario");
            
            log.info((conUsoVO.getUsuario() + "               " ).substring(0,10) + "" );
               
        }
        
        // Se comprueba que se hayan pasado los parámetros y se inicializan valores
        if(request.getParameter("txtUser") != null)
        {
            userVO.setUsuario(request.getParameter("txtUser").trim());
        }
       
        if(request.getParameter("txtPass") != null)
        {
            userVO.setPassword(request.getParameter("txtPass").trim());
        }

        if(request.getParameter("lstNivAcc") != null)
        {
            userVO.setNivelAcceso(new Integer(request.getParameter("lstNivAcc").trim()).intValue());
        }
        
        if(request.getParameter("txtNombre") != null)
        {
            userVO.setNombre(request.getParameter("txtNombre").trim());
        }
        
        if(request.getParameter("lstCentros") != null)
        {
            userVO.setIdCentro(new Integer(request.getParameter("lstCentros").trim()).intValue());
        }
        
        if(request.getParameter("chkActivo") != null)
        {
            if(request.getParameter("chkActivo").equals("true"))
            {
                userVO.setActivo(true);
            }
        }
        
        if(request.getParameter("ind") != null)
        {
            indPrevio = request.getParameter("ind").trim();
        }
        
        
        
        resultadoAlt = ConUsoDAO.guardarUser(userVO,"");
        
        if(resultadoAlt <= 0)
        {
            
            //Redireccionar a gestión usuarios
            response.sendRedirect("control/listaUsers.jsp?ind="        + indPrevio 
                                                      + "&codErrAlta=" + resultadoAlt);
        }
        else
        {            
            //Redireccionar a gestión usuarios
            response.sendRedirect("control/listaUsers.jsp?ind=" + indPrevio);
                                                      
                                                         
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Alta usuario servlet";
    }// </editor-fold>
}

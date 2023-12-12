/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jahernandez.servlets;

import es.jahernandez.accesodatos.ConUsoDAO;
import es.jahernandez.datos.ConUsuVO;
import es.jahernandez.datos.InformacionConf;
import es.jahernandez.utilidades.ControlLog;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Alberto
 */
public class ControlAccesoServlet extends HttpServlet
{
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("static-access")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        ConUsuVO  cuEntrada  = new ConUsuVO();
        ConUsuVO  cuSalida   = new ConUsuVO();
        ConUsoDAO gestUsu    = new ConUsoDAO();
        
        ServletContext   sc  = null;

        HttpSession sesion = request.getSession();
        
        //Control de log
        ControlLog logObject = null;
        Logger     log       = null;

        if(request.getParameter("txtUser") != null)
        {
            cuEntrada.setNombre((String)request.getParameter("txtUser"));
        }
        else
        {
            cuEntrada.setNombre("");
        }

        if(request.getParameter("txtPass") != null)
        {
            cuEntrada.setPassword((String)request.getParameter("txtPass"));
        }
        else
        {
            cuEntrada.setPassword("");
        }

        sc = getServletContext();
        //Cargamos datos de configuración del archivo de propiedades
        try
        {             
            //se crea una instancia a la clase Properties             
            Properties propiedades = new Properties();             
            
            FileInputStream in = new FileInputStream(sc.getRealPath(File.separator + "conf" + File.separator + "conf.ini"));
            propiedades.load(in);
            in.close();
            
            //si el archivo de propiedades NO esta vacio retornan las propiedes leidas             
            if (! propiedades.isEmpty())              
            {                                 
                //Cargamos las propiedades
                InformacionConf.dsn          = propiedades.getProperty("dsn");
                InformacionConf.url          = propiedades.getProperty("url");
                InformacionConf.pwd          = propiedades.getProperty("pwd");
                InformacionConf.user         = propiedades.getProperty("user");
                InformacionConf.director     = propiedades.getProperty("dir");
                InformacionConf.logo         = propiedades.getProperty("logo");
                InformacionConf.nombEmp      = propiedades.getProperty("nomEmp");
                InformacionConf.dirEmp       = propiedades.getProperty("dirEmp");
                InformacionConf.CIFEmp       = propiedades.getProperty("CIFEmp");
                InformacionConf.pobEmp       = propiedades.getProperty("pobEmp");
                InformacionConf.mailInfo     = propiedades.getProperty("mailInfo");  
                InformacionConf.mailEnvio    = propiedades.getProperty("email");  
                InformacionConf.servSalida   = propiedades.getProperty("serSal");  
                InformacionConf.protTransp   = propiedades.getProperty("protocolTranspo");
                InformacionConf.autorSmtp    = propiedades.getProperty("autorSmtp");
                InformacionConf.puertoSmtp   = propiedades.getProperty("puertoSmtp");
                InformacionConf.smtpStartTls = propiedades.getProperty("smtpStartTls");
                InformacionConf.mailUser     = propiedades.getProperty("mailUser");
                InformacionConf.mailPass     = propiedades.getProperty("mailPassword");
            }             
            else 
            {
                //sino  retornara NULL                 
                System.out.println("Archivo de propiedades vacio");       
            }
            
            //Cargamos informacion de creación de logs
            logObject = new ControlLog(sc.getRealPath(File.separator + "logs") +  File.separator );
            log = logObject.getLog();
            
            sesion.setAttribute("logControl", log);
            
        } 
        catch (IOException ex) 
        {
            System.out.println("Error cargando propiedades"); 
        } 
        
        
        System.out.println("usuario" + cuEntrada.getNombre());
        System.out.println("pasword" + cuEntrada.getPassword());

        cuSalida = gestUsu.buscarUsuario(cuEntrada.getNombre(),cuEntrada.getPassword());

        System.out.println("Salida--->" + cuSalida);
        if (cuSalida == null)
        {
            //Página de error usuario no válido
            response.sendRedirect("./errorPages/errorNoAutor.jsp");
        }
        else
        {
            sesion.setAttribute("usuario", cuSalida);
            response.sendRedirect("./paginaPrincipal.jsp"); //Abre la página principal de gestión
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Servlet de control de acceso a la aplicación";
    }// </editor-fold>

}

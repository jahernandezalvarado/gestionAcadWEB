/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jahernandez.utilidades;

import java.io.IOException;
import java.text.SimpleDateFormat; 
import java.util.Date;
import org.apache.log4j.*;

/**
 *
 * @author JuanAlberto
 */
public class ControlLog  
{

      private Date   fecha = new Date();
      private Logger log;

      public ControlLog(String workspace) throws IOException 
      {
          log = Logger.getLogger(ControlLog.class);

          // Formato de la hora
          SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
          String fechaAc = formato.format(fecha);

          // Patrón que seguirá las lineas del log

          PatternLayout defaultLayout = new PatternLayout("%p: %d{dd/MM/yyyy HH:mm:ss:SSS} --> %m%n");
          RollingFileAppender rollingFileAppender = new RollingFileAppender();

          //Definimos el archivo dónde irá el log (la ruta)
          rollingFileAppender.setFile(workspace  + fechaAc + ".log", true, false, 0);
          rollingFileAppender.setLayout(defaultLayout);

          log.removeAllAppenders();
          log.addAppender(rollingFileAppender);
          log.setAdditivity(false);
      }
      
      public Logger getLog() 
      {
          return log;
      }
      public void setLog(Logger log) 
      {
          this.log = log;
      }

  }

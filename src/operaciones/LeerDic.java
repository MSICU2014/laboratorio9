package operaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class LeerDic {
	   
	 final static BufferedReader leer(String archivo) {
	      File dic = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         dic = new File (archivo);
	         fr = new FileReader (dic);
	         br = new BufferedReader(fr);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               //fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return br;
	   }
	 
}
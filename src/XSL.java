import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Esta clase esta hecha para asociar el archivo XML con el XSL y 
 * transformarlo en un HTML.
 * Además abre el HTML automáticamente.
 **/
public class XSL {

	/**
	 * crea que el archivo HTML
	 * @param html: lo creo para que antes de crear el .html,
	 * elimine si hay alguno. Para evitar que pueda dar algún error.
	 **/
	public static void toHTML(String stHtml, String stXml, String stXsl)
	{	
		
			File html = new File(stHtml);
			if (html.exists()) html.delete();
			Source xml = new StreamSource(new File(stXml));
			Source xslt = new StreamSource(new File(stXsl));
	
		StringWriter sw = new StringWriter();
		
		try (FileWriter fw = new FileWriter(html)){			
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transform = tFactory.newTransformer(xslt);
			transform.transform(xml, new StreamResult(sw));
			fw.write(sw.toString());
//		    
			abrirHTML(html);

		}catch (IOException | TransformerConfigurationException e) {
			System.out.println("ERROR: " + e.getMessage());
		}catch (TransformerFactoryConfigurationError e) {
			System.out.println("ERROR: " + e.getMessage());
		}catch (TransformerException e) {
			System.out.println("ERROR: " + e.getMessage());
		}catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	/**
	 * Método para abrir un html
	 **/
	public static void abrirHTML(File html) {		
		
		if (!html.exists()) {
	        System.out.println("No se encuentra el archivo HTML: " + html);
	        return;
	    }

	    try {
	        ProcessBuilder pb = new ProcessBuilder("xdg-open", html.getAbsolutePath());

	        // Descarta los mensajes que escriba el navegador en la terminal
	        pb.redirectOutput(Redirect.DISCARD);
	        pb.redirectError(Redirect.DISCARD);

	        pb.start();

	        System.out.println("Abriendo el HTML en el navegador...");

	    } catch (IOException e) {
	        System.out.println("ERROR al abrir el HTML: " + e.getMessage());
	    }	
	}
}

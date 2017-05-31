import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TesteData {
	public static void main(String[] args) {
		// SimpleDateFormat fDate = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
		// fDate.setLenient(false);
		// fDate.setTimeZone(TimeZone.getTimeZone("GMT-3"));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
			public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
				StringBuffer toFix = super.format(date, toAppendTo, pos);
				return toFix.insert(toFix.length() - 2, ':');
			};
		};

		String dt = dateFormat.format(new Date());
		System.out.println(dt);
		
		new TesteData().testeProps();
	}
	
	
	public void testeProps() {
		
		System.out.println("diretorio atual:"+new File("").getAbsolutePath());
		System.out.println("protection domain:"+getClass().getProtectionDomain().getCodeSource().getLocation().toString());
		System.out.println("--------------------------- um -----------------------");
	    try {
	    	System.out.println("Buscando elettro: elettro.properties");
	    	InputStream is = getClass().getClassLoader().getResourceAsStream("elettro.properties");
	    	Properties baseProps = new Properties();
	    	baseProps.load(is);
//	        System.out.println("Reading... " + System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties");
//	        String x = System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties";
	        String x = baseProps.getProperty("props.location");
	        System.out.println("Reading... " + x);
	        System.out.println("Teste..:" + baseProps.getProperty("teste"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("------------------------- outro -----------------------");
	    try {
	    	java.net.URL url = new java.net.URL(getClass().getProtectionDomain().getCodeSource().getLocation(), "../elettro.properties");
	    	System.out.println("Buscando elettro: "+url.getFile());
	    	InputStream is = url.openStream();
	    	Properties baseProps = new Properties();
	    	baseProps.load(is);
//	        System.out.println("Reading... " + System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties");
//	        String x = System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties";
	        String x = baseProps.getProperty("props.location");
	        System.out.println("Reading... " + x);
	        System.out.println("Teste..:" + baseProps.getProperty("teste"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

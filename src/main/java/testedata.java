import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class testedata {
public static void main(String[] args) {
//	SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
//    fDate.setLenient(false);
//    fDate.setTimeZone(TimeZone.getTimeZone("GMT-3"));
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
        public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
            StringBuffer toFix = super.format(date, toAppendTo, pos);
            return toFix.insert(toFix.length()-2, ':');
        };
    };
    
    
    String dt = dateFormat.format(new Date());
    System.out.println(dt);
}
}

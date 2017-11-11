package present.hack2innovate.demo.hack2innovatedemoapp.constants;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by anweshmishra on 11/11/17.
 */

public class AppConstants {
    public static final Map<String,Character> currencyMap = new HashMap(){{
        put("INR",'\u20B9');
        put("DOLLAR",'$');
        put("POUND",'\u00A3');
    }};
}

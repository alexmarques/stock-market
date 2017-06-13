package in.com.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {
	
	private static final NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
	
	public static String format(BigDecimal value) {
		return formatter.format(value.doubleValue());
	}

}

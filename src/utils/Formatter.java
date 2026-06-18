package utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getInstance(new Locale("id", "ID"));

    public static String toRupiah(int amount) {
        return "Rp " + CURRENCY_FORMAT.format(amount);
    }

    public static String toDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT);
    }
}
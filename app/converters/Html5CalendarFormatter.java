package converters;

import play.data.format.Formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by haroldo on 31/07/14.
 */
public class Html5CalendarFormatter extends Formatters.SimpleFormatter<Calendar> {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Calendar parse(String value, Locale locale) throws ParseException {
        if (!value.trim().isEmpty()) {
            Date date = formatter.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
        return null;
    }

    @Override
    public String print(Calendar value, Locale locale) {
        if (value != null) {
            return formatter.format(value.getTime());
        }
        return "";
    }


}

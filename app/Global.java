import converters.Html5CalendarFormatter;
import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;

import java.util.Calendar;

/**
 * Created by haroldo on 31/07/14.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Formatters.register(Calendar.class, new Html5CalendarFormatter());
    }
}

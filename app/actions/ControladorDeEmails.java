package actions;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;
import models.Evento;
import play.twirl.api.Html;

/**
 * Created by haroldo on 01/08/14.
 */
public class ControladorDeEmails {
    public static void informaNovo(Evento evento){

        MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
        mail.setSubject("mailer");
        mail.setFrom("Agendatech2 <agendatech@gmail.com>");
        mail.setRecipient("Haroldo Ramirez <haroldoramirez@gmail.com>");
        Html render = views.html.email.novo.render(evento);
        mail.sendHtml(render.body());

    }
}

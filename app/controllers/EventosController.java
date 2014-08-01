package controllers;

import com.avaje.ebean.Ebean;
import models.Evento;
import org.apache.commons.io.FileUtils;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by haroldo on 31/07/14.
 */
public class EventosController extends Controller {

    private static Form<Evento> eventoForm = Form.form(Evento.class);

    public static Result novo() {
       return ok(views.html.eventos.novo.render(eventoForm));
    }

    public static Result cria() throws IOException {
        Form<Evento> formFromRequest = eventoForm.bindFromRequest();
        if (formFromRequest.hasErrors()) {
            return badRequest(views.html.eventos.novo.render(formFromRequest));
        }
        File destino = gravaDestaque();
        Evento evento = formFromRequest.get();
        evento.setCaminhoImagem(destino.getName());
        try {
            Ebean.save(evento);
        } catch (RuntimeException exception) {
            destino.delete();
        }
        return redirect(controllers.routes.EventosController.lista());

    }

    public static Result lista() {
        List<Evento> eventos = Ebean.find(Evento.class).findList();
        return ok(views.html.eventos.lista.render(eventos));
    }

    private static File gravaDestaque() throws IOException {
        Http.RequestBody requestBody = request().body();
        Http.MultipartFormData body = requestBody.asMultipartFormData();
        Http.MultipartFormData.FilePart filePart = body.getFile("destaque");
        File destaque = filePart.getFile();
        File destino = arquivoDeDestino(filePart);
        FileUtils.moveFile(destaque, destino);
        return destino;
    }

    private static File arquivoDeDestino(Http.MultipartFormData.FilePart destaque) {
        return new File("public/images/destaques", System.currentTimeMillis()
                + "_" + destaque.getFilename());
    }
}

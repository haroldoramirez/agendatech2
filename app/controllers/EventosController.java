package controllers;

import actions.ControladorDeEmails;
import com.avaje.ebean.Ebean;
import models.Evento;
import org.apache.commons.io.FileUtils;
import persistencia.Eventos;
import play.api.http.MediaRange;
import play.cache.Cache;
import play.data.Form;
import play.libs.Json;
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
        ControladorDeEmails.informaNovo(evento);
        return redirect(controllers.routes.EventosController.lista());

    }

    public static Result lista() {
        List<Evento> aprovados = Eventos.aprovados(true);
        MediaRange media = request().acceptedTypes().get(0);
        String mediaType = media.toString();
        
        if(Cache.get("home_"+mediaType)!=null) {
            Status status = (Status) Cache.get("home_"+mediaType);
            return status;
        }
        if(request().accepts("text/html")){
            Status status = ok(views.html.eventos.lista.render(aprovados));
            Cache.set("home_text/html",status);
            return status;
        }
        if(request().accepts("application/json")) {
            Status status = ok(Json.toJson(aprovados));
            Cache.set("home_application/json", status);
            return status;
        }
        return status(NOT_ACCEPTABLE);
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

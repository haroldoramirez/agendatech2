package controllers.admin;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import models.Evento;
import persistencia.Eventos;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 * Created by haroldo on 01/08/14.
 */
public class TodosEventosController extends Controller{

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result todos() {
        if(session().containsKey("email")){
            List<Evento> aprovados = Eventos.aprovados(true);
            List<Evento> naoAprovados = Eventos.aprovados(false);
            return ok(views.html.eventos.admin.todos_eventos.render(naoAprovados,
                    aprovados));
        }
        return redirect(controllers.routes.LoginController.form());
    }

    public static Result aprova(Integer id){
        Evento evento = Ebean.find(Evento.class,id);
        evento.setAprovado(true);
        Ebean.update(evento);
        return redirect(controllers.admin.routes.TodosEventosController.todos());
    }
}

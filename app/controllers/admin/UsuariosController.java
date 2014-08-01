package controllers.admin;

import akka.util.Crypt;
import com.avaje.ebean.Ebean;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by haroldo on 01/08/14.
 */
public class UsuariosController extends Controller{

    private static Form<DynamicForm.Dynamic> form = Form.form();

    public static Result cria(){
        Form<DynamicForm.Dynamic> formPreenchido = form.bindFromRequest();
        String email = formPreenchido.data().get("email");
        String senha = Crypt.sha1(formPreenchido.data().get("senha"));
        Usuario novo = new Usuario(email,senha);
        Ebean.save(novo);
        return ok();
    }

    public static Result form(){
        return ok(views.html.usuarios.novo.render());
    }

}
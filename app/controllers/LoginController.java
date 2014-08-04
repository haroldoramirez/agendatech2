package controllers;

import akka.util.Crypt;
import models.Usuario;
import persistencia.Usuarios;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by haroldo on 04/08/14.
 */
public class LoginController extends Controller {

    private static DynamicForm form = Form.form();

    public static Result loga() {
        Form<DynamicForm.Dynamic> requestForm = form.bindFromRequest();
        String email = requestForm.data().get("email");
        String senha = requestForm.data().get("senha");
        F.Option<Usuario> talvezUmUsuario = Usuarios.existe(email,
                Crypt.sha1(senha));

        if(talvezUmUsuario.isDefined()){
            session().put("email",talvezUmUsuario.get().getEmail());
            return redirect(controllers.admin.routes.TodosEventosController.todos());
        }

        DynamicForm formDeErro = form.fill(requestForm.data());
        formDeErro.reject("O login ou senha não existem");
        return forbidden(views.html.login.render(formDeErro));

    }

    public static Result logout(){
        session().clear();
        return redirect(controllers.routes.LoginController.loga());
    }

    public static Result form(){
        return ok(views.html.login.render(form));
    }
}
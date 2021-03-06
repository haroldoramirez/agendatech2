package actions;

import controllers.routes;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by haroldo on 04/08/14.
 */
public class PlayAuthenticatedSecured extends Security.Authenticator{

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        ctx.flash().put("nao_logado","Para continuar precisa estar logado");
        return redirect(routes.LoginController.form());
    }

}
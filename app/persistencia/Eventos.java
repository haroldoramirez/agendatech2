package persistencia;

import com.avaje.ebean.Ebean;
import models.Evento;

import java.util.List;

/**
 * Created by haroldo on 01/08/14.
 */
public class Eventos {
    public static List<Evento> aprovados(boolean situacao) {
        return Ebean.find(Evento.class).where().eq("aprovado", situacao)
                .findList();
    }
}

package models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import validations.FromNow;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by haroldo on 31/07/14.
 */

@Entity
public class Evento {

    @Id
    @GeneratedValue
    private Integer id;
    @Constraints.Email
    private String emailParaContato;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(columnDefinition = "text")
    @NotBlank
    private String descricao;
    @URL
    private String site;
    private String twitter;
    @NotBlank
    private String nome;
    @FromNow
    private Calendar dataDeInicio;
    private Calendar dataDeFim;
    private String caminhoImagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailParaContato() {
        return emailParaContato;
    }

    public void setEmailParaContato(String emailParaContato) {
        this.emailParaContato = emailParaContato;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Calendar getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(Calendar dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataDeFim() {
        return dataDeFim;
    }

    public void setDataDeFim(Calendar dataDeFim) {
        this.dataDeFim = dataDeFim;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public List<ValidationError> validate() {
        ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
        if (dataDeFim == null) {
            this.dataDeFim = (Calendar) dataDeInicio.clone();
            return null;
        }
        if (!dataDeFim.after(dataDeInicio)) {
            errors.add(new ValidationError("dataDeFim", "O fim deve ser após o inicio"));
        }

        return errors.isEmpty() ? null : errors;
    }
}

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
    private boolean aprovado;

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

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
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

	@Override
	public String toString() {
		return "Evento [id=" + id + ", emailParaContato=" + emailParaContato
				+ ", estado=" + estado + ", descricao=" + descricao + ", site="
				+ site + ", twitter=" + twitter + ", nome=" + nome
				+ ", dataDeInicio=" + dataDeInicio + ", dataDeFim=" + dataDeFim
				+ ", caminhoImagem=" + caminhoImagem + ", aprovado=" + aprovado
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aprovado ? 1231 : 1237);
		result = prime * result
				+ ((caminhoImagem == null) ? 0 : caminhoImagem.hashCode());
		result = prime * result
				+ ((dataDeFim == null) ? 0 : dataDeFim.hashCode());
		result = prime * result
				+ ((dataDeInicio == null) ? 0 : dataDeInicio.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((emailParaContato == null) ? 0 : emailParaContato.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (aprovado != other.aprovado)
			return false;
		if (caminhoImagem == null) {
			if (other.caminhoImagem != null)
				return false;
		} else if (!caminhoImagem.equals(other.caminhoImagem))
			return false;
		if (dataDeFim == null) {
			if (other.dataDeFim != null)
				return false;
		} else if (!dataDeFim.equals(other.dataDeFim))
			return false;
		if (dataDeInicio == null) {
			if (other.dataDeInicio != null)
				return false;
		} else if (!dataDeInicio.equals(other.dataDeInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (emailParaContato == null) {
			if (other.emailParaContato != null)
				return false;
		} else if (!emailParaContato.equals(other.emailParaContato))
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		return true;
	}
    
}

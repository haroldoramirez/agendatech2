# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table evento (
  id                        integer not null,
  email_para_contato        varchar(255),
  estado                    varchar(16),
  descricao                 text,
  site                      varchar(255),
  twitter                   varchar(255),
  nome                      varchar(255),
  data_de_inicio            timestamp,
  data_de_fim               timestamp,
  caminho_imagem            varchar(255),
  aprovado                  boolean,
  constraint ck_evento_estado check (estado in ('ACRE','ALAGOAS','AMAZONAS','BAHIA','CEARÁ','DISTRITO_FEDERAL','ESPÍRITO_SANTO','GOIÁS','MINAS_GERAIS','MATO_GROSSO','MATO_GROSSO_SUL','PARÁ','PARAÍBA','PERNAMBUCO','PIAUÍ','PARANÁ','RIO_DE_JANEIRO','RONDÕNIA','RIO_GRANDE_SUL','RORAIMA','SANTA_CATARINA','SERGIPE','SÃO_PAULO','TOCANTINS')),
  constraint pk_evento primary key (id))
;

create table usuario (
  email                     varchar(255) not null,
  senha                     varchar(255),
  constraint pk_usuario primary key (email))
;

create sequence evento_seq;

create sequence usuario_seq;




# --- !Downs

drop table if exists evento cascade;

drop table if exists usuario cascade;

drop sequence if exists evento_seq;

drop sequence if exists usuario_seq;


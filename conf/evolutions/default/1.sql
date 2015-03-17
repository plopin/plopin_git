# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table public.user (
  pk                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  constraint pk_user primary key (pk))
;

create sequence public.user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists public.user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists public.user_seq;


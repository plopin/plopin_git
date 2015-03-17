# --- Sample dataset

# --- !Ups

insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Pavel','Lopin','plopin@gmail.com','925-123-4567');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'John','Smith','jsmith@gmail.com','925-123-4321');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Paul','McCartney','pmccartney@gmail.com','925-222-3333');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'John','Lennon','jlennon@gmail.com','925-333-4444');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Ringo','Starr','rstarr@gmail.com','925-444-5555');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Michael','Jackson','mjackson@gmail.com','925-555-6666');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'George','Harrison','jharrison@gmail.com','925-666-7777');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Elton','John','ejohn@gmail.com','925-777-8888');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Celine','Dion','cdion@gmail.com','925-888-9999');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Yoko','Ono','yono@gmail.com','925-999-0000');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Dow','Jones','djones@gmail.com','925-000-1111');
insert into public.user (pk,first_name,last_name,email,phone) values(nextval('user_seq'),'Bill','Gates','bgates@gmail.com','000-000-0000');

# --- !Downs

delete from user;

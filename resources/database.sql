CREATE SEQUENCE public.tarefa_id_seq;


CREATE TABLE public.tarefa
(
    nome character varying(255) ,
    responsavel character varying(255) ,
    status character varying(255) ,
    id integer NOT NULL DEFAULT nextval('tarefa_id_seq'::regclass),
    CONSTRAINT tarefa_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
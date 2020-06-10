--DOMAINS
CREATE DOMAIN DM_TEXTO VARCHAR(40);
CREATE DOMAIN DM_CPFCNPJ VARCHAR(15);
CREATE DOMAIN DM_VALOR NUMERIC(15,2);
CREATE DOMAIN DM_TELEFONE VARCHAR(20);

--TABLES
CREATE TABLE AD_profissional(
id integer PRIMARY KEY NOT NULL default NEXTVAL('AD_SEQ_PROFISSIONAL'),
nome DM_TEXTO,
sobreNome DM_TEXTO,
senha DM_TEXTO,
email DM_TEXTO,
areaAtuacao integer,
cpfCnpj DM_CPFCNPJ
);

CREATE TABLE AD_cliente(
id integer PRIMARY KEY NOT NULL default NEXTVAL('AD_SEQ_CLIENTE'),
nome DM_TEXTO,
sobreNome DM_TEXTO,
senha DM_TEXTO,
email DM_TEXTO,
cpf DM_CPFCNPJ
);

CREATE TABLE AD_AreaAtuacao(
id integer PRIMARY KEY NOT NULL,
nome DM_TEXTO
);

CREATE TABLE AD_local(
id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('AD_SEQ_LOCAL'),
id_profissional INTEGER NOT NULL,
nomeLocal DM_TEXTO,
ruaLocal DM_TEXTO,
telefone DM_TELEFONE,
informacoesLocal TEXT,
id_tipoLocal INTEGER,
FOREIGN KEY(id_profissional) REFERENCES ad_profissional	
);

CREATE TABLE AD_tipoLocal(
id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('AD_SEQ_TIPOLOCAL'),
tipo DM_TEXTO
);

--SEQUENCES
CREATE SEQUENCE AD_SEQ_PROFISSIONAL;
CREATE SEQUENCE AD_SEQ_CLIENTE;
CREATE SEQUENCE AD_SEQ_LOCAL;
CREATE SEQUENCE AD_SEQ_TIPOLOCAL;

--GATILHOS
CREATE OR REPLACE FUNCTION AD_SP_PROFISSIONAL_ID()
RETURNS TRIGGER
AS $$
BEGIN
NEW.id := NEXTVAL('AD_SEQ_PROFISSIONAL');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION AD_SP_CLIENTE_ID()
RETURNS TRIGGER
AS $$
BEGIN
NEW.id := NEXTVAL('AD_SEQ_CLIENTE');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION AD_SP_LOCAL_ID()
RETURNS TRIGGER
AS $$
BEGIN
NEW.id := NEXTVAL('AD_SEQ_LOCAL');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION AD_SP_TIPOLOCAL_ID()
RETURNS TRIGGER
AS $$
BEGIN
NEW.id := NEXTVAL('AD_SEQ_TIPOLOCAL');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--TRIGGERS
CREATE TRIGGER AD_TG_PROFISSIONAL BEFORE INSERT ON AD_PROFISSIONAL FOR
EACH ROW EXECUTE PROCEDURE AD_SP_PROFISSIONAL_ID();

CREATE TRIGGER AD_TG_CLIENTE BEFORE INSERT ON AD_CLIENTE FOR
EACH ROW EXECUTE PROCEDURE AD_SP_CLIENTE_ID();

CREATE TRIGGER AD_TG_LOCAL BEFORE INSERT ON AD_LOCAL FOR
EACH ROW EXECUTE PROCEDURE AD_SP_LOCAL_ID();

CREATE TRIGGER AD_TG_TIPOLOCAL BEFORE INSERT ON AD_TIPOLOCAL FOR
EACH ROW EXECUTE PROCEDURE AD_SP_TIPOLOCAL_ID();


--VIEWS

CREATE OR REPLACE VIEW VW_LOCAL_LOCAIS
(NOME_PROPRIETARIO, NOME_LOCAL,RUA_LOCAL,TELEFONE_LOCAL,TIPO_LOCAL) AS
SELECT pro.nome, l.nomeLocal, l.ruaLocal, l.telefone, tl.tipo
FROM AD_local l
JOIN ad_tipoLocal tl ON l.id_tipoLocal = tl.id
JOIN ad_profissional pro ON pro.id = l.id_profissional

--Retirar o Default 

alter table ad_cliente alter id drop default;
alter table ad_profissional alter id drop default;
alter table ad_cliente alter id drop default;
alter table ad_TIPOLOCAL alter id drop default;

-------------------------------------------------------------------------------------------
select * from ad_cliente
select * from ad_profissional
select * from ad_local
select * from ad_tipolocal
select * from VW_LOCAL_LOCAIS

insert into AD_tipolocal (tipo) values('Aniversário');
insert into AD_tipolocal (tipo) values('Casamento');
insert into AD_tipolocal (tipo) values('Confraternização');
insert into AD_tipolocal (tipo) values('Festa');
insert into AD_tipolocal (tipo) values('Lazer');

insert into AD_profissional values(1, 'a', 'a', 'a', 'a', 1, '1')
alter table ad_cliente alter id drop default;
alter table ad_profissional alter id drop default;
alter table ad_cliente alter id drop default;
alter table ad_TIPOLOCAL alter id drop default;
delete from ad_profissional
drop table AD_tipoLocal

delete from ad_cliente


SELECT l.id, l.nomeLocal, l.ruaLocal, l.telefone, tl.tipo FROM AD_local l
JOIN ad_tipoLocal tl ON l.id_tipoLocal = tl.id where l.id_profissional =1
 
 
SELECT pro.id, pro.email, pro.senha, l.id, l.nomeLocal, l.ruaLocal, l.telefone FROM AD_local l
JOIN ad_profissional pro ON l.id_profissional = pro.id where l.id_profissional =1

select * from ad_sp_selecionar_locais(1);

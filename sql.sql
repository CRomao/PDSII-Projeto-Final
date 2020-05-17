select * from ad_profissional

insert into AD_profissional values(1, 'a', 'a', 'a', 'a', 1, '1')
alter table ad_profissional alter id drop default;
delete from ad_profissional

CREATE DOMAIN DM_TEXTO VARCHAR(40);
CREATE DOMAIN DM_CPFCNPJ VARCHAR(15);
CREATE DOMAIN DM_VALOR NUMERIC(15,2);

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
id integer PRIMARY KEY NOT NULL,
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


CREATE SEQUENCE AD_SEQ_PROFISSIONAL;

CREATE OR REPLACE FUNCTION AD_SP_PROFISSIONAL_ID()
RETURNS TRIGGER
AS $$
BEGIN
NEW.id := NEXTVAL('AD_SEQ_PROFISSIONAL');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER AD_TG_PROFISSIONAL BEFORE INSERT ON AD_PROFISSIONAL FOR
EACH ROW EXECUTE PROCEDURE AD_SP_PROFISSIONAL_ID();

select * from ex_cliente
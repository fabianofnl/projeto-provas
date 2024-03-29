DROP TABLE IF EXISTS provasRealizadas;
DROP TABLE IF EXISTS agenda;
--DROP TABLE IF EXISTS vincularApostilas;
DROP TABLE IF EXISTS apostilas;
--DROP TABLE IF EXISTS montarProvas;
DROP TABLE IF EXISTS opcoes;
DROP TABLE IF EXISTS questoes;
DROP TABLE IF EXISTS provas;
DROP TABLE IF EXISTS temas;
DROP TABLE IF EXISTS equipes;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS perfil;

CREATE TABLE perfil (
	id SERIAL NOT NULL PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	roleName VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
	usuario VARCHAR(100) NOT NULL PRIMARY KEY,
	senha VARCHAR(100) NOT NULL,
	perfilId INTEGER NOT NULL REFERENCES perfil(id)
);

CREATE TABLE funcionario (
	matricula INTEGER NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	funcao VARCHAR (100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	status VARCHAR(10) DEFAULT 'Ativo',
	usuario VARCHAR(100) NOT NULL REFERENCES usuario(usuario)
);

CREATE TABLE equipes (
	matgerente INTEGER NOT NULL REFERENCES funcionario(matricula),
	matcolaborador INTEGER NOT NULL REFERENCES funcionario(matricula)
);

CREATE TABLE temas (
	temaId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255) NOT NULL,
	descricao TEXT
);

CREATE TABLE provas (
	provaId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255) NOT NULL
);

CREATE TABLE questoes (
	questaoId SERIAL NOT NULL PRIMARY KEY,
	provaId INTEGER NOT NULL REFERENCES provas(provaId),
	titulo VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	temaId INTEGER REFERENCES temas(temaId)
);

CREATE TABLE opcoes (
	opcaoId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255),
	flag BOOLEAN DEFAULT FALSE, -- Indica se � a op��o correta ou n�o. False = Incorreta, True = Correta
	questaoId INTEGER REFERENCES questoes(questaoId)
);

-- NOTA: Essa tabela n�o ser� mais necess�rio
-- CREATE TABLE montarProvas (
--	provaId INTEGER NOT NULL REFERENCES provas(provaId),
--	questaoId INTEGER NOT NULL REFERENCES questoes(questaoId)
-- );

CREATE TABLE apostilas (
	apostilaId SERIAL NOT NULL PRIMARY KEY,
	provaId INTEGER NOT NULL REFERENCES provas(provaId),
	nome VARCHAR(255) NOT NULL,
	extensao VARCHAR(10),
	hashName VARCHAR(50) NOT NULL,
	serverPath VARCHAR(255) NOT NULL
);

-- Ambos os atributos n�o ser�o referenciado pelo banco, 
-- mas o sistema ir� controlar a referencia, para que
-- n�o fique amarrado prova com apostila.

-- Ser� permitido exclus�o da Prova (demais informa��es 
-- ser�o armazenados para hist�rico

-- NOTA: N�o ser� mais necess�rio essa tabela
-- CREATE TABLE vincularApostilas (
--	apostilaId INTEGER NOT NULL, -- Referencia Apostilas(apostilaId)
--	provaId INTEGER NOT NULL -- Referencia Provas(provaId)
-- );

-- Ambos os atributos n�o ser�o referenciado pelo banco, 
-- mas o sistema ir� controlar a referencia, para que
-- n�o fique amarrado prova com funcionario.

-- Ser� permitido exclus�o da Prova (demais informa��es 
-- ser�o armazenados para hist�rico
CREATE TABLE agenda (
	agendaId SERIAL NOT NULL PRIMARY KEY,
	matcolaborador INTEGER NOT NULL, -- Referencia Funcionario(matricula)
	provaId INTEGER NOT NULL, -- Referencia Provas(provaId)
	dataProva DATE NOT NULL,
	flag BOOLEAN DEFAULT FALSE -- Indica se o colaborador realizou a prova. True = Realizou, False = N�o realizou	
);

CREATE TABLE provasRealizadas (
	provaRealizadaId SERIAL NOT NULL PRIMARY KEY,
	agendaId INTEGER NOT NULL REFERENCES agenda(agendaId),
	provaId INTEGER NOT NULL,
	tituloProva VARCHAR(255) NOT NULL,
	dataHoraInicio TIMESTAMP,
	dataHoraFim TIMESTAMP,
	dataHoraFinalizado TIMESTAMP,
	quantidadeQuestoes INTEGER,
	quantidadeAcertos INTEGER
);

INSERT INTO perfil (descricao, roleName) VALUES ('Administrador', 'ROLE_ADMIN'); -- 1
INSERT INTO perfil (descricao, roleName) VALUES ('Instrutor', 'ROLE_INSTRUTOR'); -- 2
INSERT INTO perfil (descricao, roleName) VALUES ('Gerente', 'ROLE_GERENTE'); -- 3
INSERT INTO perfil (descricao, roleName) VALUES ('Colaborador', 'ROLE_COLABORADOR'); -- 4

INSERT INTO usuario (usuario, senha, perfilId) VALUES ('jsilva', MD5('123'), 1);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('mjoana', MD5('123'), 2);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('coliveira', MD5('123'), 3);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('apaula', MD5('123'), 4);

INSERT INTO funcionario (matricula, nome, funcao, email, usuario) VALUES (1111,'Jo�o Silva','Executivo de �rea','jsilva@teste.com','jsilva');
INSERT INTO funcionario (matricula, nome, funcao, email, usuario) VALUES (2222,'Maria Joana','Analista de sistema','mjoana@teste.com','mjoana');
INSERT INTO funcionario (matricula, nome, funcao, email, usuario) VALUES (3333,'Carlos Oliveira','Gerente de Projetos','coliveira@teste.com','coliveira');
INSERT INTO funcionario (matricula, nome, funcao, email, usuario) VALUES (4444,'Ana Paula','Programador','apaula@teste.com','apaula');


--SELECT * FROM perfil;
--SELECT * FROM usuario;
--SELECT * FROM funcionario;
--SELECT * FROM temas;
--SELECT * FROM questoes
--SELECT * FROM opcoes
--SELECT * FROM apostilas
--SELECT * FROM vincularApostilas
--SELECT * FROM agenda
--SELECT * FROM provasRealizadas
--SELECT * FROM provas

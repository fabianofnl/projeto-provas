DROP TABLE IF EXISTS agenda;
DROP TABLE IF EXISTS vincularApostilas;
DROP TABLE IF EXISTS apostilas;
DROP TABLE IF EXISTS montarProvas;
DROP TABLE IF EXISTS provas;
DROP TABLE IF EXISTS opcoes;
DROP TABLE IF EXISTS questoes;
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

CREATE TABLE questoes (
	questaoId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	temaId INTEGER REFERENCES temas(temaId)
);

CREATE TABLE opcoes (
	opcaoId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255),
	flag BOOLEAN DEFAULT FALSE, -- Indica se é a opção correta ou não. False = Incorreta, True = Correta
	questaoId INTEGER REFERENCES questoes(questaoId)
);

CREATE TABLE provas (
	provaId SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(255) NOT NULL
);

CREATE TABLE montarProvas (
	provaId INTEGER NOT NULL REFERENCES provas(provaId),
	questaoId INTEGER NOT NULL REFERENCES questoes(questaoId)
);

CREATE TABLE apostilas (
	apostilaId SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(150) NOT NULL,
	extensao VARCHAR(10),
	hashName VARCHAR(50) NOT NULL,
	serverPath VARCHAR(255) NOT NULL
);

-- Ambos os atributos não serão referenciado pelo banco, 
-- mas o sistema irá controlar a referencia, para que
-- não fique amarrado prova com apostila.

-- Será permitido exclusão da Prova (demais informações 
-- serão armazenados para histórico
CREATE TABLE vincularApostilas (
	apostilaId INTEGER NOT NULL, -- Referencia Apostilas(apostilaId)
	provaId INTEGER NOT NULL -- Referencia Provas(provaId)
);

-- Ambos os atributos não serão referenciado pelo banco, 
-- mas o sistema irá controlar a referencia, para que
-- não fique amarrado prova com funcionario.

-- Será permitido exclusão da Prova (demais informações 
-- serão armazenados para histórico
CREATE TABLE agenda (
	agendaId SERIAL NOT NULL PRIMARY KEY,
	matcolaborador INTEGER NOT NULL, -- Referencia Funcionario(matricula)
	provaId INTEGER NOT NULL, -- Referencia Provas(provaId)
	dataProva DATE NOT NULL,
	flag BOOLEAN DEFAULT FALSE -- Indica se o colaborador realizou a prova. True = Realizou, False = Não realizou	
);

INSERT INTO perfil (descricao, roleName) VALUES ('Administrador', 'ROLE_ADMIN'); -- 1
INSERT INTO perfil (descricao, roleName) VALUES ('Instrutor', 'ROLE_INSTRUTOR'); -- 2
INSERT INTO perfil (descricao, roleName) VALUES ('Gerente', 'ROLE_GERENTE'); -- 3
INSERT INTO perfil (descricao, roleName) VALUES ('Colaborador', 'ROLE_COLABORADOR'); -- 4

INSERT INTO usuario (usuario, senha, perfilId) VALUES ('jsilva', MD5('123'), 1);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('mjoana', MD5('123'), 2);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('coliveira', MD5('123'), 3);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('apaula', MD5('123'), 4);

INSERT INTO funcionario (matricula, nome, funcao, email, usuario) VALUES (1111,'João Silva','Executivo de Área','jsilva@teste.com','jsilva');
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


-- Demais querys

UPDATE funcionario SET email = 'fabianofnl2@gmail.com'

SELECT * FROM perfil p, usuario u, funcionario f WHERE p.id = u.perfilId AND u.usuario = f.usuario AND 
u.usuario = 'jsilva' AND u.senha = MD5('123')

SELECT *, (SELECT COUNT(q.questaoId) FROM questoes q WHERE t.temaId = q.temaId) AS quantidade FROM temas t ORDER BY t.titulo

SELECT o.*, (SELECT COUNT(m.provaId) FROM montarProvas m, questoes q WHERE m.questaoId = q.questaoId AND q.questaoId = o.questaoId) AS quantidadeProvas FROM opcoes o WHERE o.questaoId = 8

SELECT * FROM montarProvas m, questoes q WHERE m.questaoId = q.questaoId AND q.questaoId = 8 

INSERT INTO provas (titulo) VALUES ('Prova 1')
INSERT INTO montarProvas (provaId, questaoId) VALUES (1, 7)

SELECT * FROM montarProvas mp, provas p WHERE mp.provaId = p.provaId
SELECT * FROM questoes q, montarProvas mp WHERE q.questaoId = mp.questaoId AND mp.provaId = 1

SELECT DISTINCT ON(t.temaId) t.*  FROM provas p, montarProvas mp, questoes q, temas t 
WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = p.provaId AND p.provaId = 1

SELECT q.* FROM temas t, questoes q, montarProvas mp
WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = 1 AND t.temaId = 6

SELECT q.* FROM questoes q 
WHERE q.questaoId NOT IN (SELECT mp.questaoId FROM montarProvas mp WHERE mp.provaId = 2)  AND q.questaoId IN (SELECT o.questaoId FROM opcoes o)

SELECT f.*, p.*, a.* FROM funcionario f, provas p, agenda a
WHERE a.matcolaborador = f.matricula AND a.provaId = p.provaId
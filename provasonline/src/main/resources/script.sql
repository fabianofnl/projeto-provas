DROP TABLE IF EXISTS provasRealizadas;
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
	provaId INTEGER NOT NULL REFERENCES provas(provaId),
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

-- NOTA: Essa tabela não será mais necessário
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

-- Ambos os atributos não serão referenciado pelo banco, 
-- mas o sistema irá controlar a referencia, para que
-- não fique amarrado prova com apostila.

-- Será permitido exclusão da Prova (demais informações 
-- serão armazenados para histórico

-- NOTA: Não será mais necessário essa tabela
-- CREATE TABLE vincularApostilas (
--	apostilaId INTEGER NOT NULL, -- Referencia Apostilas(apostilaId)
--	provaId INTEGER NOT NULL -- Referencia Provas(provaId)
-- );

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
--SELECT * FROM provasRealizadas
--SELECT * FROM provas

--delete from provasRealizadas

--update agenda set flag = false where agendaid in (12,26)


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

SELECT f.*, p.*, a.*, 
(SELECT COUNT(a1.agendaId) as pendente FROM agenda a1 WHERE a1.dataProva >= current_date AND a1.flag = FALSE AND a.agendaId = a1.agendaId),
(SELECT COUNT(a2.agendaId) as vencido FROM agenda a2 WHERE a2.dataProva < current_date AND a2.flag = FALSE AND a.agendaId = a2.agendaId) 
FROM funcionario f, provas p, agenda a
WHERE a.matcolaborador = f.matricula AND a.provaId = p.provaId

SELECT f.*, p.*, a.*, 
(SELECT COUNT(a1.agendaId) as vencido FROM agenda a1 WHERE a1.dataProva <= CURRENT_DATE AND a.agendaId = a1.agendaId)
FROM funcionario f, provas p, agenda a
WHERE a.matcolaborador = f.matricula AND a.provaId = p.provaId

SELECT * FROM agenda a WHERE dataProva > NOW() AND flag = false

SELECT * FROM agenda a WHERE dataProva < NOW() AND flag = false

SELECT p.*, a.*, (SELECT COUNT(a1.agendaId) FROM agenda a1 WHERE a1.dataProva = CURRENT_DATE AND a1.agendaId = a.agendaId) as hoje 
FROM agenda a, funcionario f, provas p WHERE f.matricula = a.matcolaborador AND
a.provaId = p.provaId AND f.matricula = 4444 AND a.dataProva >= CURRENT_DATE AND a.flag = false

SELECT DISTINCT ON(ap.apostilaId, ap.nome) ap.* FROM apostilas ap, vincularApostilas vp, provas p, agenda a
WHERE ap.apostilaId = vp.apostilaId AND vp.provaId = p.provaId AND p.provaId = a.provaId
AND a.dataProva > CURRENT_DATE AND a.flag = false AND a.matcolaborador = 4444 ORDER BY ap.nome

SELECT pr.* FROM provasRealizadas pr, agenda a, funcionario f
WHERE pr.agendaId = a.agendaId AND a.matcolaborador = f.matricula AND pr.dataHoraFim > NOW() 
AND pr.dataHoraFinalizado IS NULL AND f.matricula = 4444

SELECT dataHoraFim FROM provasRealizadas WHERE provaRealizadaId = 16

SELECT * FROM provasRealizadas

SELECT a.agendaId FROM agenda a, funcionario f, provas p WHERE f.matricula = a.matcolaborador
AND f.matricula = 4444 AND a.provaId = p.provaId AND p.provaId = 2 AND a.dataProva = '2014-11-06'

-- Testes
SELECT CASE WHEN EXISTS
		(SELECT a.agendaId FROM agenda a, funcionario f WHERE f.matricula = a.matcolaborador
		AND f.matricula = 4444 AND a.dataProva = '2014-11-05')
	THEN TRUE
	ELSE FALSE
END
		 
SELECT EXISTS (SELECT a.agendaId FROM agenda a, funcionario f WHERE f.matricula = a.matcolaborador
AND f.matricula = 4444 AND a.dataProva = '2014-11-06')

SELECT pr.* FROM funcionario f, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId
AND f.matricula = 4444

SELECT SUM(pr.quantidadeAcertos) as acertos, SUM(pr.quantidadeQuestoes) as questoes
FROM funcionario f, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId
AND f.matricula != 1001

SELECT 
(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Ativo') AS qtdFuncionariosAtivos,
(SELECT COUNT(matricula) FROM funcionario WHERE status = 'Inativo') AS qtdFuncionariosInativos,
(SELECT COUNT(DISTINCT matgerente) FROM equipes) AS qtdEquipes,
(SELECT COUNT(agendaId) FROM agenda WHERE flag = false AND dataProva > CURRENT_DATE) AS qtdProvasAgendadas,
(SELECT COUNT(agendaId) FROM agenda WHERE flag = true) AS qtdProvasRealizadas,
(SELECT COUNT(agendaId) FROM agenda WHERE flag = false) AS qtdProvasNaoRealizadas,
(SELECT COUNT(temaId) FROM temas) AS qtdTemas, 
(SELECT COUNT(provaId) FROM provas) AS qtdProvas, 
(SELECT COUNT(questaoId) FROM questoes) AS qtdQuestoes, 
(SELECT COUNT(opcaoId) FROM opcoes) AS qtdOpcoes, 
(SELECT COUNT(apostilaId) FROM apostilas) AS qtdApostilas


SELECT DISTINCT ON (matgerente) e.matgerente, f.nome FROM equipes e, funcionario f WHERE e.matgerente = f.matricula

SELECT SUM(pr.quantidadeAcertos) as acertos, SUM(pr.quantidadeQuestoes) as questoes
FROM funcionario f, equipes e, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId
AND f.matricula = e.matcolaborador AND e.matgerente = 3333

SELECT * FROM provasRealizadas
SELECT * FROM agenda

SELECT pr.*, f.matricula
FROM funcionario f, equipes e, agenda a, provasRealizadas pr WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId
AND f.matricula = e.matcolaborador AND e.matgerente = 7777

SELECT f.matricula, f.nome FROM equipes e, funcionario f WHERE f.matricula = e.matcolaborador AND matgerente = 3333 -- 4444 5555 1001
SELECT * FROM agenda

SELECT SUM(pr.quantidadeQuestoes) as questoes, SUM(pr.quantidadeAcertos) as acertos FROM provasRealizadas pr, agenda a
WHERE a.agendaId = pr.agendaId AND a.matcolaborador = 4444

SELECT * FROM usuario u, funcionario f WHERE u.usuario = f.usuario AND u.usuario = 'jsilva' AND f.email = 'fabianofnl2@gmail.com'

--Apagar dados de duas tabelas ao mesmo tempo
BEGIN; 
	DELETE FROM opcoes WHERE questaoId = ?; 
	DELETE FROM questoes WHERE questaoId = ?; 
COMMIT;

INSERT INTO apostilas (provaid, nome, extensao, hashname, serverpath) VALUES (6, 'aaa', 'exe', 'qwe', 'algum lugar'); 

DELETE FROM apostilas WHERE apostilaid = xx RETURNING serverpath || hashname || nome AS fileFullPath

SELECT q.questaoId FROM questoes q WHERE q.provaId = 6

DELETE FROM opcoes o WHERE o.questaoId IN (SELECT q.questaoId FROM questoes q WHERE q.provaId = 10);

BEGIN; 
	DELETE FROM apostilas WHERE provaId = 10;
	DELETE FROM opcoes o WHERE o.questaoId IN (SELECT q.questaoId FROM questoes q WHERE q.provaId = 10);
	DELETE FROM questoes WHERE provaId = 10;
	DELETE FROM provas WHERE provaId = 10; 
COMMIT;
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

SELECT * FROM agenda WHERE flag = false AND dataprova >= CURRENT_DATE;

INSERT into agenda (matcolaborador, provaid, dataprova, flag) values (1111, 2, '2015-01-26', false);

SELECT COUNT(provaId) as quantidade FROM agenda WHERE provaId = 2 AND flag = false AND dataprova >= CURRENT_DATE;

SELECT *,
(SELECT COUNT(a1.agendaId) FROM agenda a1 WHERE a1.dataProva = CURRENT_DATE AND a1.agendaId = a.agendaId) as hoje
FROM agenda a, provas p, funcionario f
WHERE a.provaId = p.provaId AND a.matcolaborador = f.matricula
ORDER BY dataprova DESC

SELECT f.matricula, f.nome
FROM equipes e, funcionario f
WHERE f.matricula = e.matcolaborador AND matgerente = 3333

SELECT
SUM(pr.quantidadeQuestoes) AS questoes, SUM(pr.quantidadeAcertos) AS acertos
FROM provasRealizadas pr, agenda a 
WHERE a.agendaId = pr.agendaId AND a.matcolaborador = 4444

SELECT * FROM provasRealizadas pr, agenda a, funcionario
WHERE a.agendaId = pr.agendaId AND a.matcolaborador = 4444

SELECT sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float)/ count(a.agendaId) as media 
FROM provasRealizadas pr, agenda a
WHERE a.agendaId = pr.agendaId AND a.matcolaborador = 4444

SELECT sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float)/ count(a.agendaId) as media 
FROM provasRealizadas pr, agenda a, funcionario f, equipes e 
WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId AND f.matricula = e.matcolaborador AND e.matgerente = 3333

SELECT * FROM provasRealizadas pr, agenda a WHERE a.agendaId = pr.agendaId AND a.matcolaborador in (4444, 1001)

SELECT f.matricula, f.nome,  sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float)/ count(a.agendaId) as media
FROM provasRealizadas pr, agenda a, equipes e, funcionario f
WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId AND f.matricula = e.matcolaborador AND e.matgerente = 3333
GROUP BY f.matricula, f.nome

SELECT pr.*, sum(((pr.quantidadeAcertos :: float / pr.quantidadeQuestoes :: float)*100):: float) as media 
FROM funcionario f, agenda a, provasRealizadas pr
WHERE f.matricula = a.matcolaborador AND a.agendaId = pr.agendaId
AND f.matricula = 4444
GROUP BY pr.provarealizadaid

UPDATE usuario SET senha = MD5('123') FROM funcionario
WHERE usuario.usuario = 'apaula' AND funcionario.email = '123' 
RETURNING usuario.usuario

SELECT * FROM usuario
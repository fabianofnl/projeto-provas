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
	email VARCHAR(100) NOT NULL,
	usuario VARCHAR(100) NOT NULL REFERENCES usuario(usuario)
);

INSERT INTO perfil (descricao, roleName) VALUES ('Administrador', 'ROLE_ADMIN'); -- 1
INSERT INTO perfil (descricao, roleName) VALUES ('Instrutor', 'ROLE_INSTRUTOR'); -- 2
INSERT INTO perfil (descricao, roleName) VALUES ('Gerente', 'ROLE_GERENTE'); -- 3
INSERT INTO perfil (descricao, roleName) VALUES ('Colaborador', 'ROLE_COLABORADOR'); -- 4

INSERT INTO usuario (usuario, senha, perfilId) VALUES ('jsilva', MD5('123'), 1);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('mjoana', MD5('123'), 2);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('coliveira', MD5('123'), 3);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('apaula', MD5('123'), 4);

INSERT INTO funcionario (matricula, nome, email, usuario) values (1111,'João Silva','jsilva@teste.com','jsilva');
INSERT INTO funcionario (matricula, nome, email, usuario) values (2222,'Maria Joana','mjoana@teste.com','mjoana');
INSERT INTO funcionario (matricula, nome, email, usuario) values (3333,'Carlos Oliveira','coliveira@teste.com','coliveira');
INSERT INTO funcionario (matricula, nome, email, usuario) values (4444,'Ana Paula','apaula@teste.com','apaula');

--SELECT * FROM perfil;
--SELECT * FROM usuario;
--SELECT * FROM funcionario;

SELECT * FROM perfil p, usuario u, funcionario f WHERE p.id = u.perfilId AND u.usuario = f.usuario AND 
u.usuario = 'jsilva' AND u.senha = MD5('123')


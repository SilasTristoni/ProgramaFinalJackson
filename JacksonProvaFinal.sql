CREATE DATABASE JacksonFinal;

USE JacksonFinal;

CREATE TABLE Locais (
    idLocal INT AUTO_INCREMENT PRIMARY KEY,
    descricaoLocal VARCHAR(255),
    capacidade INT
);

CREATE TABLE Participante (
    idParticipante INT AUTO_INCREMENT PRIMARY KEY,
    nomeParticipante VARCHAR(255),
    telefoneParticipante VARCHAR(20),
    emailParticipante VARCHAR(255)
);

CREATE TABLE Organizador (
    idOrganizador INT AUTO_INCREMENT PRIMARY KEY,
    nomeOrganizador VARCHAR(255),
    telefone VARCHAR(20),
    emailOrganizador VARCHAR(255)
);

CREATE TABLE Evento (
    idEvento INT AUTO_INCREMENT PRIMARY KEY,
    nomeEvento VARCHAR(255),
    descricaoEvento TEXT,
    dataEvento DATE,
    idOrganizadorEvento INT,
    idLocalEvento INT,
    FOREIGN KEY (idOrganizadorEvento) REFERENCES Organizador(idOrganizador),
    FOREIGN KEY (idLocalEvento) REFERENCES Locais(idLocal)
);

CREATE TABLE EventoParticipante (
    idEvento INT,
    idParticipante INT,
    PRIMARY KEY (idEvento, idParticipante),
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
    FOREIGN KEY (idParticipante) REFERENCES Participante(idParticipante)
);

select * from organizador;
select * from locais;
select * from evento;
select * from participante;
select * from eventoparticipante;

CREATE TABLE Lisukkeet
(Nimi			CHAR(80)	NOT NULL	PRIMARY KEY,
 Ruokalaji		CHAR(80)	NOT NULL,
 Kuvaus			CHAR(255));

CREATE TABLE Kayttaja
(Nimi			CHAR(80)	NOT NULL	PRIMARY KEY,
 Tunnus			CHAR(80)	NOT NULL,
 Salasana		CHAR(80)	NOT NULL,
 Oikeudet		int		NOT NULL);

CREATE TABLE Ruokalaji
(Nimi			CHAR(80)	NOT NULL	PRIMARY KEY,
 Kehittaja		CHAR(80)	NOT NULL	references Kayttaja(Nimi),
 Tyyppi			CHAR(80)	NOT NULL,
 Raaka_aineet		CHAR(80),
 Resepti		CHAR(255)	NOT NULL,
 Kuvaus			CHAR(255));

CREATE TABLE Ruokalajin_lisukkeet
(Ruokalaji_nimi		CHAR(80)	NOT NULL	references Ruokalaji(Nimi),
 Lisuk_nimi		CHAR(80)	NOT NULL	references Lisukkeet(Nimi),
 PRIMARY KEY (Ruokalaji_nimi, Lisuk_nimi));


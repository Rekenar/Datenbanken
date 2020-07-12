create table Beherbergungsbetrieb (
BeherbergungsID INTEGER PRIMARY KEY NOT NULL,
BeherbergungsName VARCHAR(255),
Adresse VARCHAR(255),
HNR INTEGER,
Tel VARCHAR(60),
email VARCHAR(60),
Fax VARCHAR(60),
Website VARCHAR(255),
Sterne VARCHAR(1),
Typ VARCHAR(255)
);

create table Gast(
GastID INTEGER PRIMARY KEY NOT NULL,
vorname VARCHAR(255),
nachname VARCHAR(255),
Titel VARCHAR(255),
Adresse VARCHAR(255),
HNR INTEGER,
geburtsdatum DATE,
Tel VARCHAR(60),
email VARCHAR(60),
BeherbergungsID INTEGER references Beherbergungsbetrieb(BeherbergungsID)
);

create table Kategorie(
KategorieID INTEGER PRIMARY KEY NOT NULL,
Bezeichnung VARCHAR(255), 
Beschreibungstext VARCHAR(512), 
AnzahlBetten INTEGER, 
AnzahlGesamtbetten INTEGER, 
BeherbergungsID INTEGER references Beherbergungsbetrieb(BeherbergungsID)
);

create table Einheit(
EinheitID INTEGER PRIMARY KEY NOT NULL,
bezeichnung VARCHAR(255),
KategorieID INTEGER references Kategorie(KategorieID)
);

create table Buchung(
BuchungsID INTEGER PRIMARY KEY NOT NULL,
Anreise DATE not null, 
Abreise DATE not null, 
AnzahlErwachsene INTEGER, 
AnzahlKinder INTEGER, 
Gastid INTEGER Constraint unq_gastemail UNIQUE, 
EinheitID INTEGER references Kategorie(KategorieID)
);

create table Gültigkeitszeitraum(
VonDatum DATE, 
BisDatum DATE, 
pensionsart VARCHAR(60),
KategorieID INTEGER references Kategorie(KategorieID),
	Constraint Gültigkeitszeitraumkey primary key(VonDatum, BisDatum, pensionsart, KategorieID)
);

create table beziehung(
GastID INTEGER references Gast(GastID),
GastbezID INTEGER references Gast(GastID),
Art VARCHAR(60),
	CONSTRAINT beziehungkey primary key(GastID, GastbezID)
);

create table reistMit(
GastID INTEGER references Gast(GastID),
BuchungsID INTEGER references Buchung(BuchungsID),
	CONSTRAINT reistmitkey primary key(GastID, BuchungsID)
); 

insert into Beherbergungsbetrieb(beherbergungsid, beherbergungsname, adresse, tel, email, fax, website, sterne, typ) values (1, 'hotel1', 'Arbeitergasse', 9, '06605481782', 'hotel1@hotmail.de', '022 555 11 1234', 'www.hotel1.com', '4', 'Hotel');
insert into Beherbergungsbetrieb(beherbergungsid, beherbergungsname, adresse, tel, email, fax, website, typ) values (2, 'ferienwohnung1', 'Bärengasse', 15, '06641984858', 'ferienwohnung@gmx.at', '022 555 22 1234', 'www.ferienwohnung.com', 'Ferienwohnung');
insert into Beherbergungsbetrieb(beherbergungsid, beherbergungsname, adresse, tel, email, fax, website, sterne, typ) values (3, 'hotel2', 'Diehlgasse', 3, '06761584858', 'hotel2@outlook.com', '022 555 33 1234', 'www.hotel2.com', '5', 'Hotel');
insert into Beherbergungsbetrieb(beherbergungsid, beherbergungsname, adresse, tel, email, fax, website, typ) values (4, 'pension', 'Falcostiege', 8,'06605846984', 'pension@gmail.com', '022 555 44 1234', 'www.pension.com', 'Pension');
insert into Beherbergungsbetrieb(beherbergungsid, beherbergungsname, adresse, tel, email, fax, website, typ) values (5, 'ferienhaus1', 'Leitgebgasse', 16, '06642158465', 'ferienhaus@yahoo.com', '022 555 00 1234', 'www.ferienhaus.com', 'Ferienhaus');

insert into gast(gastID, vorname, nachname, titel, adresse, geburtsdatum, tel, email, beherbergungsid) values (1,'Jürgen','Kowalski','Dr','Nevillegasse', 8,'1998-02-12','0664813068','Jürgen@gmail.com',2);
insert into gast(gastID, vorname, nachname, titel, adresse, geburtsdatum, tel, email, beherbergungsid) values (2,'Marcus','Tief','Mag','Ramperstorffergasse', 33,'1997-05-05','06641181545','Marcus.hotmail.de',3);
insert into gast(gastID, vorname, nachname, adresse, geburtsdatum, tel, email, beherbergungsid) values (3,'Philipp','Gier','Scalagasse', 48,'1999-03-18','0676184165','Philipp@yahoo.com',4);
insert into gast(gastID, vorname, nachname, titel, adresse, geburtsdatum, tel, email, beherbergungsid) values (4,'Nicolas','Winkl','Ing','Siebenbrunnenplatz', 7,'1990-01-24','0660498461','Nicolas@gmx.at',2);
insert into gast(gastID, vorname, nachname, adresse, geburtsdatum, tel, email, beherbergungsid) values (5,'Andreas','Baisan','Vogelsanggasse', 8,'1989-05-27','0664584654','Anreas@gmail.com',1);

insert into kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) values (1, 'a', 'ad', 45, 60, 2);
insert into kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) values (2, 'b', 'ad', 18, 23, 1);
insert into kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) values (3, 'c', 'ad', 23, 30, 3);
insert into kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) values (4, 'd', 'ad', 50, 53, 1);
insert into kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) values (5, 'e', 'ad', 80, 88, 5);

insert into einheit(einheitid, bezeichnung, kategorieid) values (1, 'S213', 2);
insert into einheit(einheitid, bezeichnung, kategorieid) values (2, 'S214', 1);
insert into einheit(einheitid, bezeichnung, kategorieid) values (3, 'S215', 3);
insert into einheit(einheitid, bezeichnung, kategorieid) values (4, 'S216', 5);
insert into einheit(einheitid, bezeichnung, kategorieid) values (5, 'S217', 4);

insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (1, '2020-02-12', '2020-02-20', 2, 1, 1, 1);
insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (2, '2020-03-06', '2020-03-12', 1, 0, 2, 2);
insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (3, '2020-04-18', '2020-04-24', 2, 2, 3, 3);
insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (4, '2020-05-24', '2020-05-31', 2, 1, 4, 4);
insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (5, '2020-06-01', '2020-06-10', 2, 2, 5, 5);

insert into gültigkeitszeitraum(vondatum, bisdatum, pensionsart, kategorieid) values ('2020-01-01', '2020-12-31', 'Vollpension', 1);
insert into gültigkeitszeitraum(vondatum, bisdatum, pensionsart, kategorieid) values ('2020-01-01', '2020-12-31', 'Halbpension', 2);
insert into gültigkeitszeitraum(vondatum, bisdatum, pensionsart, kategorieid) values ('2020-01-01', '2020-12-31', 'Vollpension', 3);
insert into gültigkeitszeitraum(vondatum, bisdatum, pensionsart, kategorieid) values ('2020-01-01', '2020-12-31', 'Halbpension', 4);
insert into gültigkeitszeitraum(vondatum, bisdatum, pensionsart, kategorieid) values ('2020-01-01', '2020-12-31', 'Halbpension', 5);

insert into beziehung(gastid, gastbezid, art) values (1, 2, 'Freundschaft');
insert into beziehung(gastid, gastbezid, art) values (2, 1, 'Freundschaft');
insert into beziehung(gastid, gastbezid, art) values (3, 4, 'Freundschaft');
insert into beziehung(gastid, gastbezid, art) values (4, 3, 'Freundschaft');
insert into beziehung(gastid, gastbezid, art) values (5, 1, 'Freundschaft');
insert into beziehung(gastid, gastbezid, art) values (1, 5, 'Freundschaft');

insert into reistmit(gastid, buchungsid) values (1, 1);
insert into reistmit(gastid, buchungsid) values (2, 2);
insert into reistmit(gastid, buchungsid) values (3, 3);
insert into reistmit(gastid, buchungsid) values (4, 4);
insert into reistmit(gastid, buchungsid) values (5, 5);



CREATE OR REPLACE FUNCTION createBuchung() RETURNS TRIGGER AS $$
BEGIN
IF NEW.anreise < (SELECT anreise FROM buchung) AND NEW.abreise < (SELECT anreise FROM buchung) OR NEW.anreise > (SELECT anreise FROM buchung) AND NEW.abreise > (SELECT anreise FROM buchung)
THEN 
insert into buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, gastid, einheitid) values (NEW.buchungsid, NEW.anreise, NEW.abreise, NEW.anzahlerwachsene, NEW.anzahlkinder, NEW.gastid, NEW.einheitid);
RETURN NEW;
END IF
END

$$ LANGUAGE plpgsql;

CREATE TRIGGER anreise_name
before insert on buchung
for each row
execute procedure createBuchung();





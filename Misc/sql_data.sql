INSERT INTO COURSE(CourseID, Code, Name) 
VALUES

(1, 'TDT4140', 'Software Engineering'),

(2, 'TDT4145', 'Data Modelling, Databases and Database Management Systems'),

(3, 'TDT4180', 'Human-Computer Interaction'),

(4, 'TMA4140', 'Discrete Mathematics');

INSERT INTO LECTURE(LectureID, CourseID, Number, Name) 
VALUES

(1, 1, 1, 'The essence of Software Engineering (1)'),

(2, 1, 2, 'Software development methodologies'),

(3, 1, 3, 'The essence of Software Engineering (2)'),

(4, 1, 4, 'Agile Project Plan'),

(5, 2, 1, 'Introduksjon til faget og databaser '),

(6, 2, 2, 'Datamodellering (1)'),

(7, 2, 3, 'Datamodellering (2)'),

(8, 2, 4, 'Relasjonsmodellen'),

(9, 2, 5, 'Modelloversetting og Relasjonsalgebra'),

(10, 3, 1, 'Fagintroduksjon'),

(11, 3, 2, 'Brukskvalitet'),

(12, 3, 3, 'Brukersentrert design - Intro'),

(13, 3, 4, 'MMI-historie'),

(20, 1, 5, 'The Display');

INSERT INTO QUESTION(QuestionID, TopicID, UserID, Question) 
VALUES

(1, 10, 1, 'Hvor mange øvinger må man ha godkjent?');



INSERT INTO RATING(RatingID, TopicID, UserID, Stars) 
VALUES

(112, 10, 1, 3),

(113, 6, 1, 1),

(114, 11, 1, 5),

(115, 10, 1, 3),

(116, 1, 1, 1),

(117, 1, 1, 4),

(118, 1, 1, 1);

INSERT INTO TOPIC(TopicID, LectureID, Number, Name) 
VALUES

(1, 1, 1, 'Course Information'),

(2, 1, 2, 'The Semat Kernel'),

(3, 2, 1, 'The Cynefin model'),

(4, 2, 2, 'Waterfall life cycle'),

(5, 2, 3, 'Extreme Programming: Change'),

(6, 2, 4, 'Agile Manifesto'),

(7, 3, 1, 'Semat'),

(8, 4, 1, 'Agile project planning'),

(9, 4, 2, 'Extreme Programming Explained'),

(10, 5, 1, 'Fagintroduksjon'),

(11, 5, 2, 'Databaseintroduksjon'),

(12, 6, 1, 'Entitetsklasser'),

(13, 6, 2, 'Relasjonsklasser'),

(14, 6, 3, 'Atributt'),

(15, 6, 4, 'N?kkler'),

(16, 6, 5, 'Restriksjoner'),

(17, 6, 6, 'Subklasser og kategorier'),

(18, 7, 1, 'Modelleringsprossesen'),

(19, 8, 1, 'ER-modellering'),

(20, 8, 2, 'Oppgave a) fra eksamen mai 2013'),

(21, 9, 1, 'RelasjonsDB'),

(22, 9, 2, 'Mapping av ER til RDB'),

(23, 9, 3, 'Using mySQL'),

(24, 10, 1, 'Fagintroduksjon'),

(25, 11, 1, 'Brukskvalitet'),

(26, 11, 2, 'Brukskontekst'),

(27, 11, 3, 'Brukeropplevelse'),

(28, 12, 1, 'Brukersentrert design'),

(29, 12, 2, 'ISO 9241-11'),

(30, 12, 3, 'ISO 9241-210'),

(31, 12, 4, 'Iterativt design'),

(32, 13, 1, 'MMI-historie'),

(39, 20, 1, 'Tull');


INSERT INTO USER(UserID, Name) 
VALUES

(1, 'Preference'),

(5, 'Trygve'),

(6, 'Trygve2'),

(7, 'Henke'),

(8, 'Preferences'),

(9, 'Christian'),

(10, 'Demo'),

(11, 'Preference ss'),

(12, 'ee'),

(13, 'tets');
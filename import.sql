
INSERT INTO `konto` (`login`, `email`, `haslo`, `kodPocztowy`, `maxPozycji`, `miasto`, `skorka`, `stan`, `telefon`, `ulica`, `uprawnienia`) VALUES
('admin', 'dupa@dupa.pl', 'admin', 'dupa', 20, 'dupa', 'sk1', 0, 12345678, 'dupa', 1);
INSERT INTO `ustawienie`(`nazwa`, `wartosc`) VALUES ("ile_plikow",3);
INSERT INTO `ustawienie`(`nazwa`, `wartosc`) VALUES ("rozmiar",5555);

INSERT INTO `kategoria` (`ID`, `nazwa`, `ojciec_id`) VALUES
(1, 'Motoryzacja', 0),
(2, 'Nieruchomoœci', 0),
(3, 'Zabawki', 0),
(4, 'Elektronika', 0),
(5, 'Us³ugi', 0),
(6, 'Samochody', 1),
(7, 'Motocykle', 1),
(8, 'Osobowe', 6),
(9, 'Ciê¿arowe', 6),
(12, 'Meble', 0),
(13, 'Krzes³a', 12),
(14, 'Szafy', 12),
(15, 'Sto³y', 12),
(16, 'Sportowe', 6);

INSERT INTO `slowo` (`slowo`) VALUES
('dópa'),
('dópe'),
('dupa'),
('dupe'),
('gówno'),
('gunwo'),
('guwno'),
('kótas'),
('kutas');

INSERT INTO `notatka` (`id`, `tresc`) VALUES
(2, 'Jakaœtam notatka'),
(3, 'Jakaœ inna notatka'),
(8, 'sdasdasdasdasdasdasdasdadasdad a dasd asd asdada'),
(9, 'Dupa XD'),
(10, 'asdadsasdfsadasdasdasdadfsdfs');

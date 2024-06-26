CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (test_id) REFERENCES Test(test_id),
    FOREIGN KEY (chart_id) REFERENCES NatalChart(chart_id),
    FOREIGN KEY (genres_id) REFERENCES PrefferedGenres(genres_id),
    FOREIGN KEY (diary_id) REFERENCES Diary(diary_id),
    username VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    height DECIMAL(3,2) NOT NULL,
    location VARCHAR(20) NOT NULL,
    gender ENUM('Male', 'Female', 'Prefer not to say')
)

CREATE TABLE Test (
    test_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    FOREIGN KEY (question_id) REFERENCES Questions(question_id),
    FOREIGN KEY (result_id) REFERENCES Result(result_id)
)

CREATE TABLE Questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question_values ENUM('Who is mitio the gun', 'do you prefer kondio or shaban shaulic', 'Da zeim da si hoim ili da hoim da si zeim'),
    FOREIGN KEY (test_id) REFERENCES Test(test_id),
    FOREIGN KEY (answear_id) REFERENCES Answears(answear_id)
)

CREATE TABLE Answears(
    answear_id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(20) NOT NULL,
    weight SMALLINT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES Questions(question_id)
)

CREATE TABLE Result (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    result VARCHAR(20) NOT NULL,
    FOREIGN KEY (test_id) REFERENCES Test(test_id)
)

CREATE TABLE NatalChart(
    chart_id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (input_data_id) REFERENCES InputData(input_data_id),
    FOREIGN KEY (planets_id) REFERENCES Planets(planets_id),
    FOREIGN KEY (houses_id) REFERENCES Houses(houses_id)
)

CREATE TABLE InputData(
    input_data_id INT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(20) NOT NULL,
    city VARCHAR(20) NOT NULL,
    date_of_birth DATETIME() NOT NULL,
    hour_of_birth TINYINT() NOT NULL,
    minutes_of_birth TINYINT() NOT NULL,
    name VARCHAR(20) NOT NULL
)

CREATE TABLE Planets(
    planets_id INT AUTO_INCREMENT PRIMARY KEY,
    sun VARCHAR(12),
    moon VARCHAR(12),
    mercury VARCHAR(12),
    venus VARCHAR(12),
    mars VARCHAR(12),
    jupiter VARCHAR(12),
    saturn VARCHAR(12),
    uranus VARCHAR(12),
    neptune VARCHAR(12),
    pluto VARCHAR(12),
    true_node VARCHAR(12),
    chiron VARCHAR(12)
)

CREATE TABLE Houses(
    houses_id INT AUTO_INCREMENT PRIMARY KEY,
    ac VARCHAR(12),
    second_house VARCHAR(12),
    third_house VARCHAR(12),
    fourth_house VARCHAR(12),
    fifth_house VARCHAR(12),
    sixth_house VARCHAR(12),
    seventh_house VARCHAR(12),
    eight_house VARCHAR(12),
    nineth_house VARCHAR(12),
    tenth_hous VARCHAR(12),
    eleventh_house VARCHAR(12),
    twelveth_house VARCHAR(12)
)

CREATE TABLE PrefferedGenres(
    genres_id INT AUTO_INCREMENT PRIMARY KEY,
    genres TEXT() NOT NULL
)

CREATE TABLE Diary(
    diary_id INT AUTO_INCREMENT PRIMARY KEY,
    song TEXT NOT NULL,
    date_entry DATETIME NOT NULL,
    is_private BOOLEAN,
    keyword TEXT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
)

CREATE TABLE Category(
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    custom_category_name VARCHAR(20) NOT NULL,
    title VARCHAR(20) NOT NULL
)

--The stored procedure

DELIMITER $$

CREATE PROCEDURE GetAccountInfo()

BEGIN
    SELECT
        username,
        email,
        location,
        height,
        gender
    FROM
        Account
    ORDER BY username,
END$$
DELIMITER ;

CALL GetAccountInfo();

-- Aggregate Function

SELECT
    AVG(height) average_high_for_zodiac_sign where NatalChart.sun == 'Virgo'
FROM
    Account;

--gotta add one more operation...

--Account Table value insertion
INSERT INTO Account (test_id, chart_id, genres_id, diary_id, username, email, password, height, location, gender)
VALUES (1, 1, 1, 1, 'tmy', 'tomcom@abv.bg', 'mitioTheGun123', 177, 'Sadovo', 'Male')
INSERT INTO Account (test_id, chart_id, genres_id, diary_id, username, email, password, height, location, gender)
VALUES (2, 2, 2, 2, 'GoshoOtPo4ivka', 'ailqka@abv.bg', 'iloveKOKAkolao_fc', 164, 'Plovdiv', 'Male')
INSERT INTO Account (test_id, chart_id, genres_id, diary_id, username, email, password, height, location, gender)
VALUES (3, 3, 3, 3, 'MalkaPista', 'bigD@abv.bg', 'MnogoMiePluten', 176, 'Plovdiv', 'Male')

-- Test table val insertion
INSERT INTO Test (title, question_id, result_id)
VALUES('BDSM Test', 1, 1)
INSERT INTO Test (title, question_id, result_id)
VALUES('Enneagram Test', 2, 2)
INSERT INTO Test (title, question_id, result_id)
VALUES('MBTI Test', 3, 3)

-- Questions table val insertion
INSERT INTO Questions (question_values, test_id, answear_id)
VALUES ('Who is mitio the gun', 1, 1)
INSERT INTO Questions (question_values, test_id, answear_id)
VALUES ('do you prefer kondio or shaban shaulic', 3, 3)
INSERT INTO Questions (question_values, test_id, answear_id)
VALUES ('Da zeim da si hoim ili da hoim da si zeim', 2, 2)

-- Answears table val insertion
INSERT INTO Answears( text, weight, question_id)
VALUES('A Bulgarian freedom fighter', 69, 1)
INSERT INTO Answears( text, weight, question_id)
VALUES('Shaban', 420, 3)
INSERT INTO Answears( text, weight, question_id)
VALUES('da minem da si zeim', 69, 2)

-- Result table val insertion
INSERT INTO Result(result, test_id)
VALUES ('ENTP', 3)
INSERT INTO Result(result, test_id)
VALUES ('8w7', 2)
INSERT INTO Result(result, test_id)
VALUES ('100% igra4', 1)

-- NatalChart table val insertion
INSERT INTO NatalChart (input_data_id, planets_id, houses_id)
VALUES(1,1,1)
INSERT INTO NatalChart (input_data_id, planets_id, houses_id)
VALUES(2,2,2)
INSERT INTO NatalChart (input_data_id, planets_id, houses_id)
VALUES(3,3,3)

-- InputData table val insertion
INSERT INTO InputData(country, city, date_of_birth, hour_of_birth, minutes_of_birth, name)
VALUES ('Bulgaria', 'Plovdiv', '2002-08-26', 1, 50, 'Tom')
INSERT INTO InputData(country, city, date_of_birth, hour_of_birth, minutes_of_birth, name)
VALUES ('Bulgaria', 'Plovdiv', '2001-03-15', 11, 45, 'WantedToFuckHerRightInTheP')
INSERT INTO InputData(country, city, date_of_birth, hour_of_birth, minutes_of_birth, name)
VALUES ('Bulgaria', 'Plovdiv', '2002-05-08', 10, 50, 'Maria')

-- Planets table val insertion
INSERT INTO Planets(sun, moon, mercury, venus, mars, jupiter, saturn, uranus, neptune, pluto, true_node, chiron)
VALUES ('Virgo', 'Aries', 'Virgo', 'Libra', 'Leo', 'Leo', 'Gemini', 'Aquarius', 'Aquarius', 'Saggitarius', 'Gemini', 'Capricorn')
INSERT INTO Planets(sun, moon, mercury, venus, mars, jupiter, saturn, uranus, neptune, pluto, true_node, chiron)
VALUES ('Pisces', 'Saggitarius', 'Aquarius', 'Aries', 'Saggitarius', 'Gemini', 'Taurus', 'Aquarius', 'Aquarius', 'Saggitarius', 'Cancer', 'Saggitarius')
INSERT INTO Planets(sun, moon, mercury, venus, mars, jupiter, saturn, uranus, neptune, pluto, true_node, chiron)
VALUES ('Taurus', 'Saggitarius', 'Aries', 'Gemini', 'Libra', 'Leo', 'Taurus', 'Aquarius', 'Virgo', 'Saggitarius', 'Scorpio', 'Scorpio')

-- Houses table val insertion
INSERT INTO Houses(ac, second_house, third_house, fourth_house, fifth_house, sixth_house, seventh_house, eight_house, nineth_house, tenth_hous, eleventh_house, twelveth_house)
VALUES ('Cancer', 'Cancer', 'Leo', 'Virgo', 'Libra', 'Scorpio', 'Capricorn', 'Capricorn', 'Aquarius', 'Pisces', 'Aries', 'Taurus')
INSERT INTO Houses(ac, second_house, third_house, fourth_house, fifth_house, sixth_house, seventh_house, eight_house, nineth_house, tenth_hous, eleventh_house, twelveth_house)
VALUES ('Cancer', 'Cancer', 'Leo', 'Virgo', 'Libra', 'Scorpio', 'Capricorn', 'Capricorn', 'Aquarius', 'Pisces', 'Aries', 'Taurus')
INSERT INTO Houses(ac, second_house, third_house, fourth_house, fifth_house, sixth_house, seventh_house, eight_house, nineth_house, tenth_hous, eleventh_house, twelveth_house)
VALUES ('Leo', 'Taurus', 'Leo', 'Pisces', 'Libra', 'Libra', 'Saggitarius', 'Aries', 'Aquarius', 'Pisces', 'Aries', 'Taurus')

-- Preffered genres table val insertion
INSERT INTO PrefferedGenres(genres)
VALUES ('Drake & Azis type of shit, Opera, Chalga')
INSERT INTO PrefferedGenres(genres)
VALUES ('Grucko, Retro Chalga, Vladi Markov')
INSERT INTO PrefferedGenres(genres)
VALUES ('House, RNB Soul')

-- Diary table val insertion
INSERT INTO Diary(song, date_entry, is_private, keyword, category_id)
VALUES ('https://www.youtube.com/watch?v=dQw4w9WgXcQ', '2023-11-15', 'True', 1)
INSERT INTO Diary(song, date_entry, is_private, keyword, category_id)
VALUES ('https://www.youtube.com/watch?v=Tryhi8eY9bM', '2023-08-26', 'True', 15)
INSERT INTO Diary(song, date_entry, is_private, keyword, category_id)
VALUES ('https://www.youtube.com/watch?v=weRHyjj34ZE', '2023-03-15', 'True', 3)

-- Category table val insertion
INSERT INTO Category(custom_category_name, title)
VALUES ('Starogrucki mitove i legendi', 'Hudojestvena literatura')
INSERT INTO Category(custom_category_name, title)
VALUES ('Stoqn Kolev type of istorii', 'Avtobiografiq - hroniki')
INSERT INTO Category(custom_category_name, title)
VALUES ('Tinder shit madafaka', 'Lubovni - Romanti4ni')

-- https://github.com/pkyurkchiev/software-modeling-and-analysis-se/blob/master/course-work/README.md
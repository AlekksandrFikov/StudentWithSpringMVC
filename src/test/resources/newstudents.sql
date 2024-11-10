
TRUNCATE `student_test_db`.`student_seq`;
TRUNCATE `student_test_db`.`student`;

INSERT INTO `student_test_db`.`student`
(`id`,
`ferstname`,
`lastname`,
`dateofberth`,
`email`)
VALUES
("1","Alex", "Fikov1", "1979-10-24", "fikov1@list.ru"),
("2","Alex", "Fikov2", "1979-10-24", "fikov2@list.ru"),
("3","Alex", "Fikov3", "1979-10-24", "fikov3@list.ru"),
("4","Alex", "Fikov4", "1979-10-24", "fikov4@list.ru");

INSERT INTO `student_test_db`.`student_seq`
(`next_val`)
VALUES
(100);
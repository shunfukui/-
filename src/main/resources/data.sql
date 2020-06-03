/* 従業員テーブルのデータ（第３章で作成） */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* ユーザーマスタのデータ（ADMIN権限） */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');

/* ユーザーマスタのデータ（一般権限） */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('tamura@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田村', '1986-11-05', 31, false, 'ROLE_GENERAL');





/* ユーザーテーブルのデータ */  
INSERT INTO users (user_name,sex,age,mail_address,password,license,role)
VALUES('福井隼', 'true', 29,'shun@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa',true,'ROLE_GENERAL');


/* 相談テーブルのデータ */  
INSERT INTO consultations (user_name,title,content)
VALUES('福井隼','熱があります','病院に行った方が良いでしょうか');



/* 回答テーブルのデータ */  
INSERT INTO answers (content)
VALUES('病院に行き、薬を飲み、安静にしていましょう');

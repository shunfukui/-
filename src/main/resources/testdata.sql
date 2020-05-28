
/* ユーザーテーブルのデータ */  
INSERT INTO users (user_name,sex,age,mail_address,password,license,role)
VALUES('福井隼', 'true', 29,'shun@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa',true,'ROLE_GENERAL');
VALUES('aaaa', 'true', 10,'aaaa@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa',true,'ROLE_GENERAL');


/* 相談テーブルのデータ */  
INSERT INTO consultations (title,content)
VALUES('熱があります','病院に行った方が良いでしょうか');
VALUES('熱があります','病院に行った方が良いでしょうか');



/* 回答テーブルのデータ */  
INSERT INTO answers (user_name,title,content)
VALUES('福井隼','熱があります','病院に行き、薬を飲み、安静にしていましょう');
VALUES('あああ','いいい','薬局に行き、薬をもらいましょう');
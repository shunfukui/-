
/* ユーザーテーブルのデータ */  
INSERT INTO users (user_name,sex,age,mail_address,password,license,role,is_deleted)
VALUES('ccc', true, 29,'shun@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa',true,'ROLE_GENERAL',false);
INSERT INTO users (user_name,sex,age,mail_address,password,license,role,is_deleted)
VALUES('ddd', true, 10,'aaaa@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa',true,'ROLE_GENERAL',false);


/* 相談テーブルのデータ */  
INSERT INTO consultations (user_name, title,content)
VALUES('ccc', '熱があります','病院に行った方が良いでしょうか');
INSERT INTO consultations (user_name, title,content)
VALUES('ccc', '熱があります','病院に行った方が良いでしょうか');



/* 回答テーブルのデータ */  
INSERT INTO answers (user_name,title,content)
VALUES('ccc','熱があります','病院に行き、薬を飲み、安静にしていましょう');
INSERT INTO answers (user_name,title,content)
VALUES('ccc','いいい','薬局に行き、薬をもらいましょう');
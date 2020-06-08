/* 従業員テーブル（第３章で作成） */
CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    age INT
);

/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    user_name VARCHAR(50),
    birthday DATE,
    age INT,
    marriage BOOLEAN,
    role VARCHAR(50)
);

/* ユーザー */
CREATE TABLE IF NOT EXISTS users (
    user_name VARCHAR(30),
    sex BOOLEAN,
    age INT,
    mail_address VARCHAR(50),
    password VARCHAR(100),
    license BOOLEAN,
    role VARCHAR(50),
    is_deleted BOOLEAN DEFAULT false,
    PRIMARY KEY (user_name)
);

/* 相談 */
CREATE TABLE IF NOT EXISTS consultations (
    title VARCHAR(100),
    content VARCHAR(1000),
    user_name VARCHAR(30),
    CONSTRAINT FK__consultations__user_name FOREIGN KEY (user_name) REFERENCES users(user_name) 
);

/* 回答 */
CREATE TABLE IF NOT EXISTS answers (
	title VARCHAR(100),
    content VARCHAR(1000),
    user_name VARCHAR(30),
    CONSTRAINT FK__answers__user_name FOREIGN KEY(user_name) REFERENCES users(user_name) 
);



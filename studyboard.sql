DROP TABLE IF EXISTS board;

# board 게시판 생성
CREATE TABLE board(
    no          INTEGER         AUTO_INCREMENT      PRIMARY KEY,
    title       VARCHAR(200)    NOT NULL,
    content     LONGTEXT,
    writer      VARCHAR(50)     NOT NULL,
    reg_date    DATETIME        DEFAULT             CURRENT_TIMESTAMP,
    update_date DATETIME        DEFAULT             CURRENT_TIMESTAMP
);

# board에 목업 데이터 추가
INSERT INTO board(title, content, writer) VALUE ('첫 게시글 테스트', '첫 게시글 테스트 내용', 'ADMIN');
INSERT INTO board(title, content, writer)
    VALUES
        ('테스트 제목 1', '테스트 내용1', 'user01'),
        ('테스트 제목 2', '테스트 내용2', 'user02'),
        ('테스트 제목 3', '테스트 내용3', 'user03');

SELECT * FROM board;

# 가장 마지막 글을 먼저 보이게
SELECT * FROM board order by no desc;

# User List table
DROP TABLE IF EXISTS USERS;
CREATE TABLE users(
    no          INTEGER         AUTO_INCREMENT      PRIMARY KEY,
    name        VARCHAR(200)    NOT NULL,
    ninkname    VARCHAR(200)    NOT NULL,
    sex         VARCHAR(50)     NOT NULL,
    birthday    INTEGER         NOT NULL,
    callnumber  VARCHAR(100)    NOT NULL,
    email       VARCHAR(200)    NOT NULL,
    joindate    DATETIME        DEFAULT             CURRENT_TIMESTAMP
);


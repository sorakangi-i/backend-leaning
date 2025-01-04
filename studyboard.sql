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


#첨부파일
DROP TABLE IF EXISTS board_attachment;
CREATE TABLE board_attachment (
    no              INTEGER         AUTO_INCREMENT      PRIMARY KEY,
    filename        VARCHAR(256)    NOT NULL,
    path            VARCHAR(256)    NOT NULL,
    content_type    VARCHAR(56)     NOT NULL,
    size            INTEGER,
    bno             INTEGER         NOT NULL,
    reg_date        DATETIME        DEFAULT             now(),
    CONSTRAINT FOREIGN KEY(bno) REFERENCES board(no)
);

#out join
SELECT b.*,
       a.no as ano,
       a.bno, a.filename, a.path, a.content_type, a.size,
       a.reg_date as a_reg_date
from board b left outer join board_attachment a on b.no = a.bno
where b.no = 1
order by filename;

SELECT * FROM board_attachment;
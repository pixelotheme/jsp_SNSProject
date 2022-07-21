DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
CREATE TABLE grade(
gradeNo NUMBER(2) PRIMARY KEY,
gradeName VARCHAR2(20)
);
CREATE TABLE member(
id VARCHAR2(20) PRIMARY KEY,
pw VARCHAR2(20) NOT NULL,
name VARCHAR2(30) NOT NULL,
gender VARCHAR2(6) NOT NULL CHECK (gender in('����', '����')),
birth DATE NOT NULL,
tel VARCHAR2(13),
email VARCHAR2(50) NOT NULL,
regDate DATE DEFAULT SYSDATE,
conDate DATE DEFAULT SYSDATE,
status VARCHAR2(6) DEFAULT '����' CHECK (status in ('����','����','Ż��','�޸�')) ,
photo VARCHAR2(50) DEFAULT '/upload/member/noImage.jpg',
gradeNo NUMBER(2) DEFAULT 1 REFERENCES grade(gradeNo)
);
INSERT INTO grade VALUES(1, '�Ϲ�ȸ��');
INSERT INTO grade VALUES(9, '������');
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
VALUES('admin', '1111', '������', '����', '1950-01-01', '010-1111-2222', 'admin@naver.com','/upload/member/admin.jpg', 9);
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES('test9', '1111', '9', '����', '1994-01-01', '010-3333-4444', 'hong@naver.com','/upload/member/test.jpg');
COMMIT;
select * from grade;
select * from member;
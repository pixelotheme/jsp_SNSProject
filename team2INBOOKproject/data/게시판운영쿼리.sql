-- SQL �Խ��� � ����
-- 1. ����Ʈ(R) - ��ȣ, ����, �ۼ���, �ۼ���, �ؽ��±�, ���� ��ȸ��
-- SELECT : ������ ��������(����). from �ڿ� �����Ͱ� �ִ� ���(board=table)
-- order by : �������� ���� -> ASC - ��������(����), DESC - ��������(�����Ұ���)
-- to_char(date, ����) - ��¥->���ڿ�, ����->���ڿ�
-- ���� : - yyyy: ��, mm:��, dd:��, hh:��, mi:��, ss:��
-- �Լ��� ������ �������� ������ ����� ���� ����. ��� ����ϴ� ��Ī(Alias)
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate, b.hashtag, b.fileName, m.photo, b.hit, br.no FROM board b, member m, board_reply br 
WHERE (b.id = m.id) and (b.no = br.no)
ORDER BY b.id;

-- ��۾��� ����
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate, b.hashtag, b.fileName, m.photo, b.hit FROM board b, member m 
WHERE b.id = m.id
ORDER BY b.id;

-- ��ȸ�� 1 ����
UPDATE board SET hit = hit + 1 WHERE no = 22;
COMMIT;

SELECT no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, hashtag, fileName, hit FROM board WHERE no = 3;

-- 2. �ۺ���
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate,  b.fileName, m.photo, b.hit, br.no 
FROM board b, member m, board_reply br
WHERE (no = '22') and (b.id = m.id) and (b.no = br.no);

-- ��۾��� ����
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate,  b.fileName, m.photo, b.hit
FROM board b, member m
WHERE (no = '22') and (b.id = m.id);

-- 3. �۾���(C) -> �۹�ȣ(�ڵ� - ��ü:������), ����, ����, ���̵�, �ؽ��±�, ���ϳ���
INSERT INTO board(no, title, content, id, hashtag, fileName)
VALUES (BOARD_SEQ.nextval, '�ȳ�', '�׽�Ʈ�ϴ���', 'test', '#�����λ� #�׽�Ʈ', '/upload/image/test.jpg');
COMMIT;

-- 4. �ۼ���(U)
-- 2����(DB�� �ִ� ���߿� �����Ϸ��� ���� ����)�� ������ ��������. - ����, ����, �ۼ���, �ؽ��±�, ����
UPDATE board SET title = '�ݰ����ϴ�.', content = '�׽�Ʈ �Ϸ��մϴ�.', id = 'test3', hashtag = '#�׽�Ʈ�Ϸ�', filename = '/upload/image/test1.jpg' WHERE no = 3;
COMMIT;

-- 5. �ۻ���(D)
-- ������ �۹�ȣ�� Ȯ���ϰ� ���� ���� : 2
DELETE FROM board WHERE no = 3;
COMMIT;

-- Ʈ������ ó��
-- �۾��� ���� ��� ��Ű��(������ ������ �Ͼ ���) : ROLLBACK
-- �۾��� ������ ���� �����Ű�� : commit
ROLLBACK;


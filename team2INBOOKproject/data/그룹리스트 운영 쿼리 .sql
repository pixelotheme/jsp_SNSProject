
SELECT g.no, g.title, g.content, m.id, to_char(g.writeDate, 'yyyy-mm-dd') writeDate, g.hashtag, g.fileName, m.photo, g.hit, br.no FROM groupList g, member m, board_reply br 
WHERE (g.id = m.id) and (g.no = br.no)
ORDER BY g.id;

-- ��ȸ�� 1 ����
UPDATE groupList SET hit = hit + 1 WHERE no = 3;
COMMIT;

SELECT no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, hashtag, fileName, hit FROM groupList WHERE no = 3;

-- 2. �ۺ���
SELECT g.no, g.title, g.content, m.id, to_char(g.writeDate, 'yyyy-mm-dd') writeDate, g.hashtag,  g.fileName, m.photo, g.hit, br.no 
FROM groupList g, member m, board_reply br
WHERE (g.id = 'test1') and (g.id = m.id) and (g.no = br.no);

-- 3. �۾���(C) -> �۹�ȣ(�ڵ� - ��ü:������), ����, ����, ���̵�, �ؽ��±�, ���ϳ���
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES (GROUPLIST_SEQ.nextval, '�ȳ��Ͻʴϱ�', '�׽�Ʈ�ϴ� ���Դϴ�.', 'test', '#�����λ� #�׽�Ʈ', '/upload/image/test.jpg');
COMMIT;

-- 4. �ۼ���(U)
-- 2����(DB�� �ִ� ���߿� �����Ϸ��� ���� ����)�� ������ ��������. - ����, ����, �ۼ���, �ؽ��±�, ����
UPDATE groupList SET title = '�ݰ����ϴ�.', content = '�׽�Ʈ �Ϸ��մϴ�.', id = 'test3', hashtag = '#�׽�Ʈ�Ϸ�', filename = '/upload/image/test1.jpg' WHERE no = 3;
COMMIT;

-- 5. �ۻ���(D)
-- ������ �۹�ȣ�� Ȯ���ϰ� ���� ���� : 2
DELETE FROM groupList WHERE no = 5;
COMMIT;

-- Ʈ������ ó��
-- �۾��� ���� ��� ��Ű��(������ ������ �Ͼ ���) : ROLLBACK
-- �۾��� ������ ���� �����Ű�� : commit
ROLLBACK;

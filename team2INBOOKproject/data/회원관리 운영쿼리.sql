-- ȸ������ � ��Ű��
-- C - ȸ������
-- R - ȸ������Ʈ, ȸ������(����������), �α���, ���̵�ã��, ��й�ȣ ã��
-- U - ȸ������ ����, ��޼���, ���¼���
-- D - ������� �ʴ´�. : ȸ��Ż�� - ȸ�� ���� Ż��� �ٲ� �ش�. -> U

-- 1. ����Ʈ - ���̵�, �̸�, �������, ����ó, ��޹�ȣ, ��޸�, ����������
SELECT m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
FROM member m, grade g
WHERE m.gradeNo = g.gradeNo
ORDER BY m.id;
-- 2. ����
SELECT m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, m.conDate, m.status, m.gradeNo, g.gradeName
FROM member m, grade g 
WHERE (id = 'test') AND (m.gradeNo = g.gradeNo);
-- 3. ���� (ȸ������)
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES ('angel', '1111', '������', '����', '1993-03-01', '010-1004-1004',
'angel@naver.com', '/upload/member/angel.jpg');
COMMIT;
-- 4. ����
UPDATE member SET id = '���', name = '���', birth = '1992-11-00=1', tel = '010-2222-1111', email = 'gsagag@naver.com' WHERE id = test1;


-- 5. ����
delete
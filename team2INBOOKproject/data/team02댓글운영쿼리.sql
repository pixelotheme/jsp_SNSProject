-- ��� ����Ʈ(��������)
-- 1. ���� ������
SELECT br.rno, br.no, br.content, m.photo, br.id, br.writeDate
FROM board_reply br, member m
WHERE (no = 3) and (br.id = m.id)
ORDER BY rno DESC
;

-- 2. ���� ��ȣ ���̱�
SELECT rownum rnum, rno, no, content, photo, id, writeDate
FROM(
    SELECT br.rno, br.no, br.content, m.photo, br.id, br.writeDate
    FROM board_reply br, member m
    WHERE (no = 3) and (br.id = m.id)
    ORDER BY rno DESC
)
;

-- 3. �������� �´� ������ ��������
SELECT rnum, rno, no, content, photo, id, writeDate
FROM(
    SELECT rownum rnum, rno, no, content, photo, id, writeDate
    FROM(
        SELECT br.rno, br.no, br.content, m.photo, br.id, br.writeDate
        FROM board_reply br, member m
        WHERE (no = 3) and (br.id = m.id)
        ORDER BY rno DESC
    )
)
WHERE rnum between 1 and 10;
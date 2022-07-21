-- 댓글 리스트(가져오기)
-- 1. 원본 데이터
SELECT br.rno, br.no, br.content, m.photo, br.id, br.writeDate
FROM board_reply br, member m
WHERE (no = 3) and (br.id = m.id)
ORDER BY rno DESC
;

-- 2. 순서 번호 붙이기
SELECT rownum rnum, rno, no, content, photo, id, writeDate
FROM(
    SELECT br.rno, br.no, br.content, m.photo, br.id, br.writeDate
    FROM board_reply br, member m
    WHERE (no = 3) and (br.id = m.id)
    ORDER BY rno DESC
)
;

-- 3. 페이지에 맞는 데이터 가져오기
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
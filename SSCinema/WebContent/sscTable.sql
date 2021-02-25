create table ssc_theater(
    t_num number(4) primary key,
    t_name varchar2(20) not null
);

create table ssc_user(
    u_num number(4) primary key,
    u_id varchar2(20) not null,
    u_name varchar2(20) not null,
    u_pwd varchar2(20) not null,
    u_mile number(6) default 0,
    u_phone varchar2(15)
);

create table ssc_room(
    r_num number(4) primary key,
    t_num_fk number(4) references ssc_theater(t_num),
    r_name varchar2(20)
);

create table ssc_seat(
    r_num_fk number(4) references ssc_room(r_num),
    s_num number(3) not null
);

create table ssc_movie_info(
    m_num number(4) primary key,
    m_name varchar2(20) not null,
    m_cont varchar2(1000),
    m_image varchar2(100)
);

create table ssc_food(
    f_num number(4) primary key,
    f_name varchar2(20) not null,
    f_price number(5) not null,
    f_image varchar2(100)
);

create table ssc_food_buy(
    b_num number(4) primary key,
    f_num_fk number(4) references ssc_food(f_num),
    u_num_fk number(4) references ssc_user(u_num),
    b_count number(2),
    b_price number(6)
);

create table ssc_admin(
    a_num number(2) primary key,
    a_id varchar2(20),
    a_pwd varchar2(20)
);

create table ssc_faq(
    q_no number(5) primary key,       -- 게시판 글번호
	q_writer varchar2(20) not null,   -- 게시판 글 작성자
	q_title varchar2(100) not null,   -- 게시판 글제목
	q_cont varchar2(2000) not null,   -- 게시판 글내용
	q_pwd varchar2(20) not null,      -- 게시판 글 비밀번호
	q_hit number(5) default 0,        -- 게시판 글 조회수
	q_date date,                      -- 게시판 글 작성일자
	q_group number(4),                -- 게시판 글 그룹
	q_step number(4),                 -- 게시판 글 답변 단계 
	q_indent number(4)                -- 게시판 답변글 들여쓰기
);

create table ssc_playing_info(
    p_num number(4) primary key,
    m_num_fk number(4) references ssc_movie_info(m_num),
    t_num_fk number(4) references ssc_theater(t_num),
    r_num_fk number(4) references ssc_room(r_num),
    p_date date
);

create table ssc_reserv(
    re_num number(4) primary key,
    p_num_fk number(4) references ssc_playing_info(p_num),
    m_num number(4),
    u_num_fk number(4) references ssc_user(u_num),
    r_num_fk number(4) references ssc_room(r_num),
    s_num number(3)
);

create sequence ssc_admin_seq
increment by 1
start with 1
nocache;

create sequence ssc_user_seq
increment by 1
start with 1000
nocache;

create sequence ssc_theater_seq
increment by 1
start with 2000
nocache;

create sequence ssc_room_seq
increment by 1
start with 3000
nocache;

create sequence ssc_movie_seq
increment by 1
start with 4000
nocache;

create sequence ssc_playing_seq
increment by 1
start with 5000
nocache;

create sequence ssc_reserv_seq
increment by 1
start with 6000
nocache;

create sequence ssc_food_seq
increment by 1
start with 7000
nocache;

create sequence ssc_food_buy_seq
increment by 1
start with 8000
nocache;

create sequence ssc_faq_seq
increment by 1
start with 9000
nocache;

-- 극장
insert into ssc_theater values(ssc_theater_seq.nextval,'SSC홍대입구');
insert into ssc_theater values(ssc_theater_seq.nextval,'SSC신도림');

-- 관리자
insert into ssc_admin values(ssc_admin_seq.nextval,'admin1','1234');
insert into ssc_admin values(ssc_admin_seq.nextval,'admin2','1234');

-- 회원
insert into ssc_user values(ssc_user_seq.nextval,'id1','홍길동','1111',default,'010-1111-1234');
insert into ssc_user values(ssc_user_seq.nextval,'id2','김연아','2222',default,'010-2222-2345');

-- 영화정보
insert into ssc_movie_info values(ssc_movie_seq.nextval,'런','아니쉬 차간티 감독','/upload/run.jpg');
insert into ssc_movie_info values(ssc_movie_seq.nextval,'조제','김종관 감독','/upload/josee.jpg');

-- 상영관
insert into ssc_room values(ssc_room_seq.nextval,2000,'일반관');
insert into ssc_room values(ssc_room_seq.nextval,2000,'IMAX관');
insert into ssc_room values(ssc_room_seq.nextval,2001,'4DX관');
insert into ssc_room values(ssc_room_seq.nextval,2001,'SCREEN-X관');

-- 좌석정보
insert into ssc_seat values(3000,1);
insert into ssc_seat values(3000,2);
insert into ssc_seat values(3001,1);
insert into ssc_seat values(3001,2);
insert into ssc_seat values(3002,1);
insert into ssc_seat values(3002,2);
insert into ssc_seat values(3003,1);
insert into ssc_seat values(3003,2);

-- 상영정보
insert into ssc_playing_info values(ssc_playing_seq.nextval,4000,2000,3000,'2020-12-14');
insert into ssc_playing_info values(ssc_playing_seq.nextval,4001,2000,3001,'2020-12-14');
insert into ssc_playing_info values(ssc_playing_seq.nextval,4000,2001,3002,'2020-12-15');
insert into ssc_playing_info values(ssc_playing_seq.nextval,4001,2001,3003,'2020-12-15');

-- 예매
insert into ssc_reserv values(ssc_reserv_seq.nextval,5000,4000,1000,3000,1);
insert into ssc_reserv values(ssc_reserv_seq.nextval,5003,4001,1001,3003,2);

-- 매점
insert into ssc_food values(ssc_food_seq.nextval,'ssc콤보','8500','/upload/ssccombo.jpg');
insert into ssc_food values(ssc_food_seq.nextval,'콜라','2500','/upload/cocacola.jpg');

-- 음식구매
insert into ssc_food_buy values(ssc_food_buy_seq.nextval,7000,1000,1,8500);
insert into ssc_food_buy values(ssc_food_buy_seq.nextval,7001,1001,1,2500);


-- 수정한 것들
alter table ssc_reserv add t_num_fk number(4) default '0' not null;
update ssc_reserv set t_num_fk = 2000 where re_num = 6000;
update ssc_reserv set t_num_fk = 2001 where re_num = 6001;

select * from ssc_reserv re 
left join  ssc_room r on r.r_num = re.r_num_fk
left join ssc_user u on u.u_num = re.u_num_fk
left join ssc_theater t on t.t_num = re.t_num_fk
left join ssc_movie_info i on i.m_num = re.m_num;

create table ssc_notice(
n_num number(4) primary key,
n_title varchar2(100) not null,
n_cont varchar2(2000) not null,
n_hit number(5) default 0,
n_date date
);

alter table ssc_user add u_status varchar(2);

alter table ssc_seat add s_name varchar2(4) default 'A';
alter table ssc_seat add row_num varchar2(2) default 'A';
alter table ssc_reserv add s_name varchar2(4) default 'A';
alter table ssc_reserv add row_num varchar2(2) default 'A';

create sequence ssc_notice_seq
increment by 1
start with 10000
nocache;


--20201228추가
alter table ssc_movie_info add m_director varchar2(30);
alter table ssc_movie_info add m_actor varchar2(50);
alter table ssc_movie_info add m_genre varchar2(30);
alter table ssc_movie_info add m_time number(3);
alter table ssc_movie_info add m_playdate date;


update ssc_movie_info set m_cont = '가장 안전했던 그곳이 가장 위험한 공간이 된다! 
태어날 때부터 장애 때문에 휠체어를 타고 외딴 집에서 엄마와 함께 살며 일상을 보내는 ‘클로이’.
딸을 사랑으로 돌보는 엄마 덕분에 힘들지만 매일을 긍정적으로 살아간다.
그러던 어느 날 식탁에 놓인 장바구니에서 하나의 물건을 발견하게 되고
믿었던 모든 일상이 흔들리기 시작하는데…', m_director = '아니쉬 차간티', m_actor = '사라 폴슨 ,  키에라 앨런',
m_genre = '미스터리, 스릴러', m_time = 89, m_playdate = '2020-11-20' where m_num = 4000;
update ssc_movie_info set m_cont = '자신을 ‘조제’로 불러달라는 그녀 
처음 만난 그날부터 ‘조제’는 ‘영석’에게 잊을 수 없는 이름으로 남는다. 
할머니와 단둘이 사는 집, 그곳에서 책을 읽고 상상하며 
자신만의 세계를 살고 있는 ‘조제’.
우연히 만난 그녀에게 특별한 감정을 느끼기 시작한 ‘영석’은 
천천히, 그리고 솔직하게 다가가기 시작한다. 
하지만 처음 경험해보는 사랑이 설레는 한편 가슴 아픈 조제는 
자신에게 찾아온 낯선 감정을 밀어내는데…
기억할 거야 
너와 함께한 모든 순간을 ', m_director = '김종관', m_actor = '한지민, 남주혁',
m_genre = '로맨스, 멜로, 드라마', m_time = 117, m_playdate = '2020-12-10' where m_num = 4001;

COMMIT;

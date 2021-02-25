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
    t_num_fk number(4) not null
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

commit;

insert into ssc_user values(ssc_user_seq.nextval,'testID','아무개','1234',default,'010-0000-0000');

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

alter table ssc_reserv add t_num_fk number(4) default '0' not null;
update ssc_reserv set t_num_fk = 2000 where re_num = 6000;
update ssc_reserv set t_num_fk = 2001 where re_num = 6001;

-- 매점
insert into ssc_food values(ssc_food_seq.nextval,'ssc콤보','8500','/upload/ssccombo.jpg');
insert into ssc_food values(ssc_food_seq.nextval,'콜라','2500','/upload/cocacola.jpg');

-- 음식구매
insert into ssc_food_buy values(ssc_food_buy_seq.nextval,7000,1000,1,8500);
insert into ssc_food_buy values(ssc_food_buy_seq.nextval,7001,1001,1,2500);
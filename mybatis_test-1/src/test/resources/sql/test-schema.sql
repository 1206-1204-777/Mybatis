-- ===========================================
-- テスト用DDL
-- ===========================================
-- 
-- このファイルは各テスト実行時に自動的に実行され、
-- H2インメモリデータベースにテーブルを作成します。

--既存テーブルの削除
drop table if exists attendances cascade;
drop table if exists users cascade;
drop table if exists holidays cascade;
drop table if exists personal_holidays cascade;
drop table if exists company_holidays cascade;
drop table if exists weekly_summary cascade;
drop table if exists monthly_summary cascade;

--===========================
-- ユーザーテーブル
--===========================
create table users (
    id bigint primary key auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    role varchar(50) not null,
    location_id bigint,
    default_start_time time,
    default_end_time time,
    foreign key (location_id) references locations(id)
);
--===========================
-- ロケーションテーブル
--===========================
create table locations (
    id bigint primary key auto_increment,
    name varchar(255) not null,
    satart_time time not null,
    end_time time not null,
    created_by bigint
);

--===========================  
-- attendancesテーブル
--===========================
create table attendances (
    id bigint primary key auto_increment,
    user_id bigint not null,
    date date not null,
    clock_in timestamp,
    clock_out timestamp,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    total_work_min bigint,
    overtime_min bigint,
    foreign key (user_id) references users(id)
);

--===========================
-- working_hoursテーブル
--===========================
create table working_hours (
    id bigint primary key auto_increment,
    location_id bigint not null,
    standard_working_minites Integer not null,
    break_minites Integer not null,
    overtime_threshold_minutes  Integer not null,
    foreign key (location_id) references locations(id)
);

--===========================
-- holidaysテーブル     
--===========================
create table holidays (
    id bigint primary key auto_increment,
    name varchar(255) not null,
    date date not null,
    type varchar(50) not null, --  'company', 'personal'
);

--===========================
-- personal_holidaysテーブル    
--===========================
create table personal_holidays (
    id bigint primary key auto_increment,
    user_id bigint not null,
    date date not null,
    reson varchar(500) not null,
    approved boolean default false,-- 承認済みかどうか
    foreign key (user_id) references users(id),
);

--===========================  
-- company_holidaysテーブル
--===========================
create table company_holidays (
    id bigint primary key auto_increment,
    name varchar(255) not null,
    satart_date date not null,
    end_date date not null,
    description varchar(500)
);

--===========================  
-- weekly_summaryテーブル
--===========================
create table weekly_summary (
    id bigint primary key auto_increment,  
    user_id bigint not null,   
    week_start_date date not null,
    total_work_min bigint default 0,
    total_overtime_min bigint default 0,
    work_days_count integer default 0,
    foreign key (user_id) references users(id)
);

--===========================  
-- monthly_summaryテーブル
--===========================
create table monthly_summary (
    id bigint primary key auto_increment,
    user_id bigint not null,    
    year integer not null,
    month integer not null,
    total_work_min bigint default 0,
    total_overtime_min bigint default 0,   
    work_days integer default 0,
    absence_days integer default 0,
    foreign key (user_id) references users(id)
);
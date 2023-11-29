drop database rentalapidb;
drop user pintarrental;
create user pintarrental with password 'password';
create database rentalapidb with template=template0 owner=pintarrental;
\connect rentalapidb;
alter default privileges grant all on tables to pintarrental;
alter default privileges grant all on sequences to pintarrental;

create type status_user as enum('admin', 'user');
create table users (
    id_user integer primary key not null,
    status status_user default 'user',
    nama varchar(50) not null,
    alamat text,
    email varchar(50) unique not null,
    password text not null
);

create table kendaraan (
    id_kendaraan integer primary key not null,
    nama_kendaraan text not null,
    tipe_kendaraan text not null,
    harga_sewa integer not null,
    jumlah_ketersediaan integer not null
);

create type status_sewa as enum('belum_bayar', 'pakai', 'dikembalikan');
create table sewa (
    id_sewa integer primary key not null,
    id_user integer not null,
    id_kendaraan integer not null,
    tanggal_sewa bigint not null,
    lama_sewa integer not null,
    total_harga_sewa integer not null,
    status_sewa status_sewa default 'belum_bayar'
);

alter table sewa add constraint sewa_user_fk
foreign key (id_user) references users(id_user);
alter table sewa add constraint sewa_kendaraan_fk
foreign key (id_kendaraan) references kendaraan(id_kendaraan);

create sequence users_seq increment 1 start 1;
create sequence kendaraan_seq increment 1 start 1;
create sequence sewa_seq increment 1 start 1;

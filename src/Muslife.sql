Create database MusLife;
use MusLife;

create table user(
	user_id int auto_increment primary key,
    user_name text,
    user_email text,
    user_pass text,
    user_img text
);


CREATE TABLE Performer (
    per_id INT AUTO_INCREMENT PRIMARY KEY,
    per_img TEXT,
    per_name TEXT,
    per_type text
);
SELECT * FROM Performer;
create table Per_med(
	media_id int,
    per_id int,
	constraint foreign key (media_id) references media(id)  ON DELETE CASCADE,
	constraint foreign key (per_id) references Performer(per_id)  ON DELETE CASCADE
);

insert into Performer(per_name, per_img, per_type) VALUES ("JVKE", "images/performers/JVKE_profile.jpg","Singer"),
("Trung Quân Idol","images/performers/trung_quan_idol_profile.jpg","Singer"),
("Madihu","images/performers/madihu_profile.jpg","Singer");


insert into Per_med values 
(6, 3),
(10, 3),
(9, 2),
(15, 7);


SELECT Per_med.per_id, media.id , media.img_path, media.media_name 
FROM Per_med right join media on Per_med.media_id = media.id where Per_med.per_id = 1 ;

insert into user(user_name,user_email,user_pass ,user_img) values("AdminAccount","AdminPageMusLife@gmail.com","876b97c826857505ab230e51a741c259", "images/anh.jpg");
insert into user(user_name,user_email,user_pass) values("Quannichan1308","tranminhquan130804@gmail.com","c129cbe0c8044a025b916b56af6241e8");
insert into user(user_name,user_email,user_pass) values("Thaithihongphuc1603","phuctth22ns@vku.udn.vn","e19625a25d65b1403535dbb482c6a612");
insert into user(user_name,user_email,user_pass) values("Tranminhquan22NS056","quantm.22ns@vku.udn.vn","c129cbe0c8044a025b916b56af6241e8");

update user set user_img= "images/anh.jpg" where user_id=1;
insert into user(user_img) value ("images/anh.jpg");
delete user from user where user_img = "images/anh.jpg";
delete from user;

create table media(
	id int auto_increment primary key,
	img_path text,
	media_name text,
	performer text,
    file_path text,
    media_song_categories text,
    year text,
    types text
);
SELECT * FROM media;
insert into media(img_path,media_name,performer,file_path,media_song_categories,year,types) values
('images/song/ai_chung_tinh_duoc_mai.jpg','Ai chung tình được mãi','Trung Quân Idol','song/V_POP/AiChungTinhDuocMaiCover-TrungQuanIdol-7293429.mp3','V_POP','2022','song'),
('images/song/k_c_e.jpg','Không còn em','Madihu','song/V_POP/Khong_con_em.mp3','V_POP','2023','song'),
('images/song/co_em.jpg','Có em','Madihu','song/V_POP/Co_em.mp3','V_POP','2021','song'),
('images/song/golden_hour.jpg','Golden hour','JVKE','song/POP/JVKE_GOLDEN_HOUR.mp3','POP','2022','song');

CREATE table med_cate(
	id int auto_increment primary key,
    cate_name text
);

insert into med_cate (cate_name) values ("V_POP"),("POP"),("K_POP"),("BOLERO");

create table playlist(
	playlist_id int auto_increment primary key,
    playlist_img_path text,   
	playlist_name text,
    playlist_script text,
    playlist_type text
);

create table FavPlaylist(
	playlist_id int,
    user_id int,
    constraint foreign key (user_id) REFERENCES user(user_id)  ON DELETE CASCADE,
    constraint foreign key (playlist_id) REFERENCES playlist(playlist_id)  ON DELETE CASCADE
);

insert into FavPlaylist values(4,33);

create table CreatePlaylist(
	Pll_cre_id nvarchar(1000) primary key,
    PLL_name text,
    PLL_number text,
    user_id int,
    constraint foreign key (user_id) REFERENCES user(user_id)  ON DELETE CASCADE
);
SELECT * FROM CreatePlaylist; 
DELETE FROM CreatePlaylist;

insert into media_in_playlist(media_id, playlist_id) value (1,'AISsJdfPDTytRmSbS5Hc6S5344dAE0CyAAG5stnqAH2Fquewo5SL94pKicD4Qw#6_Quannichan_33');
SELECT media_id, img_path, media_name, performer,media_song_categories,year, types FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id = "tuA40PjTZj2IJKPBddOeav0phLt34DbeksM2oZQU8FH6IeFDWyks9S6bmmNpJF#10_Quannichan_33";
create table mediaFav(
	media_id int,
    user_id int,
	constraint foreign key (user_id) REFERENCES user(user_id)  ON DELETE CASCADE,
    constraint foreign key (media_id) REFERENCES media(id)  ON DELETE CASCADE
);

insert into playlist(playlist_img_path,playlist_name,playlist_script,playlist_type) VALUES
('images/playlist/Best_2022.jpg','Best Of 2022','Comeback to Top hits of 2022','song'),
('images/playlist/chill.jpg','Chill Playlist For You','Chill your day','song'),
('images/playlist/today_top.jpg','Today Top Hits','Today top hits','song'),
('images/playlist/V_pop.jpg','Vpop không thể thiếu','Vietnamese pop music','song'),

('images/playlist/ielts.jpg','Ielts Podcast','Improve listening skill with Ielts podcast','podcast'),
('images/playlist/Nguyenhuutri.png','Nguyen Huu Tri Podcast','Nguyen Huu Tri podcast','podcast'),
('images/playlist/sunhee.jpg','Sunhee Podcast','Sunhee podcast','podcast'),
('images/playlist/toeic.jpg','Toeic Podcast','Toeic podcast','podcast');

update playlist set playlist_img_path = "images/podcast/ielts.jpg" where playlist_img_path = "images/playlist/ielts.jpg";
update playlist set playlist_img_path = "images/podcast/Nguyenhuutri.png" where playlist_img_path = "images/playlist/Nguyenhuutri.png";
update playlist set playlist_img_path = "images/podcast/sunhee.jpg" where playlist_img_path = "images/playlist/sunhee.jpg";
update playlist set playlist_img_path = "images/podcast/toeic.jpg" where playlist_img_path = "images/playlist/toeic.jpg";
update playlist set playlist_script = "Toeic podcast" where playlist_img_path = "images/podcast/toeic.jpg";
update media set file_path = 'song/POP/JVKE_GOLDEN_HOUR.mp3' where id = 4;

create table media_in_playlist(
	media_id int,
    playlist_id text,
	foreign key (media_id) references media(id)  ON DELETE CASCADE
);


INSERT INTO media_in_playlist VALUES(9,11),
(6,11),
(10,11),
(9,12),
(6,12),
(7,12),
(8,9),
(8,10);


DELETE FROM muslife.media_in_playlist;
delete from Muslife.mediaFav;
SELECT * FROM per_med;
SELECT * FROM user;
SELECT * FROM playlist;
SELECT * FROM media_in_playlist;
SELECT * FROM media;
SELECT * FROM Performer;
SELECT * FROM Muslife.mediaFav;
SELECT * from Media where id Not in (6,8,9,10) AND types = "song";

SELECT *
FROM media_in_playlist
RIGHT JOIN media ON media_in_playlist.media_id = media.id;

Select * from FavPlaylist;

SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type
from playlist right join FavPlaylist on FavPlaylist.playlist_id = playlist.playlist_id
where user_id = 33;
DELETE from performer where per_name = 'Wren Evans';

Insert INTO mediaFav VALUES(4, 33);
SELECT * FROM media WHERE types = 'song' AND media_song_categories = 'V_POP';
SELECT media_id, img_path, media_name, performer,media_song_categories,year, types  FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id =4 ;
SELECT * FROM playlist where playlist_type = 'song' and playlist_name like '%playlist%';
SET FOREIGN_KEY_CHECKS=0;
DELETE FROM user where user_id = 35;
UPDATE Performer set per_name = 'quan' where per_id = '2';
select id from media order by id DESC limit 1 ;
SELECT media.id, media.media_name from media_in_playlist right join media on media_in_playlist.media_id = media.id where media_in_playlist.playlist_id = 12;
SELECT * FROM Media where media_name LIKE '%co%'  order by id DESC;
SELECT media.id, media.media_name, media.img_path, media.performer, media.media_song_categories, media.types FROM media where id NOT IN (10) AND types = 'song' AND media.media_name LIKE '"+search+"'
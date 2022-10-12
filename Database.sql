CREATE DATABASE SE160798_Assignment_SU3W
GO

USE SE160798_Assignment_SU3W
GO

CREATE TABLE tblRoles(
	roleId nvarchar(10),
	roleName nvarchar(30),
	CONSTRAINT PK_tblRoles PRIMARY KEY (roleId)
);
GO

CREATE TABLE tblUpdateInfo(
	updateId nvarchar(10),
	updateDate	DATE,
	updateAdminId nvarchar(10),
	CONSTRAINT PK_tblUpdateInfo PRIMARY KEY (updateId),
	CONSTRAINT FK_tblUpdateInfo_tblUsers FOREIGN KEY (updateAdminId) REFERENCES tblUsers(userId)
);
GO

CREATE TABLE tblUsers(
	userId nvarchar(10),
	userName nvarchar(30),
	userPhone nvarchar(11) CHECK(userPhone LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' 
		OR userPhone LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	userEmail nvarchar(30) CHECK(userEmail LIKE '%[A-Za-z0-9]@[A-Za-z0-9]%.[A-Za-z0-9]%'),
	userRoleId nvarchar(10),
	[password] nvarchar(20),
	CONSTRAINT PK_tblUsers PRIMARY KEY (userId),
	CONSTRAINT FK_tblUsers_tblRoles FOREIGN KEY (userRoleId) REFERENCES tblRoles(roleId)
)
GO

CREATE TABLE tblCourseTypes(
	courseTypeId nvarchar(10),
	courseTypeName nvarchar(30),
	CONSTRAINT PK_tblTypes PRIMARY KEY (courseTypeId)
)
GO

CREATE TABLE tblCourses(
	courseId nvarchar(10),
	courseTypeId nvarchar(10),
	courseName nvarchar(30),
	courseDesc nvarchar(100),
	courseFee DECIMAL(10,2),
	courseQuantity INT,
	courseImg nvarchar(255),
	courseStatus nvarchar(10),
	startDate DATE,
	endDate DATE,
	CONSTRAINT PK_tblCourses PRIMARY KEY (courseId),
	CONSTRAINT FK_tblCourses_tblCourseTypes FOREIGN KEY (courseTypeId) REFERENCES tblCourseTypes(courseTypeId)
)
GO

CREATE TABLE tblPaymentTypes(
	paymentTypeId nvarchar(10),
	paymentTypeName nvarchar(30),
	CONSTRAINT PK_tblPaymentTypes PRIMARY KEY (paymentTypeId)
)
GO

CREATE TABLE tblOrders(
	orderId nvarchar(10),
	cusName nvarchar(30),
	cusPhone nvarchar(11) CHECK(cusPhone LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' 
		OR cusPhone LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	cusEmail nvarchar(30) CHECK(cusEmail LIKE '%[A-Za-z0-9]@[A-Za-z0-9]%.[A-Za-z0-9]%'),
	orderDate DATE CHECK(YEAR(orderDate) - YEAR(GETDATE()) <= 1),
	orderTotal DECIMAL(10,2),
	paymentTypeId nvarchar(10),
	paymentStatus bit,
	CONSTRAINT PK_tlbOrders PRIMARY KEY (orderId),
	CONSTRAINT FK_tblOrders_tblPaymentTypes FOREIGN KEY (paymentTypeId) REFERENCES tblPaymentTypes(paymentTypeId)
)
GO

CREATE TABLE tblOrderDetails(
	detailId nvarchar(10),
	orderId nvarchar(10),
	courseId nvarchar(10),
	courseFee DECIMAL(10,2),
	orderQuantity INT,
	CONSTRAINT PK_tblOrderDetails PRIMARY KEY (detailId),
	CONSTRAINT FK_tblOrderDetails_tblOrders FOREIGN KEY (orderId) REFERENCES tblOrders(orderId),
	CONSTRAINT FK_tblOrders_tblCourses FOREIGN KEY (courseId) REFERENCES tblCourses(courseId)
)
GO

INSERT INTO tblCourses (courseId, courseTypeId, courseImg, courseName, courseDesc, courseFee, startDate, endDate, courseQuantity, courseStatus)
VALUES ('C01', 'T01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRByanowfEf7-rribzvbryOh20BYYWu5oWDEA&usqp=CAU', 'Piano for Kids', 'Good for kids', 50.99, '2022-08-20', '2022-09-20', 50, 'active'),
       ('C02', 'T01', 'https://trungtamamnhac.com/wp-content/uploads/2020/04/Hoc-dan-piano.jpg', 'Electric Piano', 'Useful for young children', 65.50, '2022-08-20', '2022-10-05', 45, 'active'),
       ('C03', 'T01', 'https://vietthuong.edu.vn/wp-content/uploads/2017/05/khoa-hoc-dan-piano-cho-nguoi-moi-bat-dau-1.jpg', 'Highschool Piano', 'Inspire young talents', 70.99, '2022-10-01', '2022-12-01', 20, 'active'),
       ('C04', 'T01', 'https://cdnmozart.mozartproject.org/how-ryo-fukui-learned-piano-.jpg', 'Classical Piano Lover', 'Spread passion to soft music lovers', 30.50, '2022-11-20', '2022-12-20', 15, 'active'),
	   ('C05', 'T01', 'https://media.istockphoto.com/photos/rock-musician-playing-keyboard-on-stage-picture-id921776676?k=20&m=921776676&s=170667a&w=0&h=8YRVV5ReXepDMfaqxMv5ICVwfJDnYjWS8JH54xgCVYA=', 'Strongtaste Pianoholic', 'Born to seek creative strong-taste pianist', 45.99, '2023-02-01', '2023-02-28', 10, 'active'),
	   ('C06', 'T01', 'https://imgs.classicfm.com/images/247981?crop=16_9&width=660&relax=1&signature=yqy2niCSFeHqOJVnuoAXGbQOXbw=', 'Soft Rythm Piano', 'Middle-age might be interested in soft rythms', 15.50, '2022-09-01', '2022-09-25', 35, 'inactive'),

	   ('C07', 'T02', 'https://media1.nguoiduatin.vn/media/dinh-lac-thanh/2020/08/13/minh2.jpg', 'Romantic Saxophone', 'Music of love for romance', 55.99, '2022-08-03', '2022-08-27', 20, 'active'),
	   ('C08', 'T02', 'https://image.thanhnien.vn/w1024/Uploaded/2022/zsfe/2022_03_12/1-1682.jpg', 'Festive Saxy', 'Suited for festival and carnival', 29.50, '2022-09-20', '2022-10-25', 20, 'inactive'),
	   ('C09', 'T02', 'https://static.ybox.vn/2021/8/4/1628754315590-TMT%202_thumb.jpg', 'Saxo of Dynamic Energy', 'Good way to get your children exposed to an interesting instrument', 65.50, '2022-08-20', '2022-10-5', 25, 'inactive'),
	   ('C10', 'T02', 'https://assets.htv.com.vn/Images/TAP%20CHI%20HTV/XEM%20GI%20HOM%20NAY/THAO/2019/Thang%2011/9.11quyen/HINH%203.jpg', 'Professional Saxers', 'Enhance profession of saxophonists', 99.99, '2022-10-01', '2022-12-24', 30, 'active'),

	   ('C11', 'T03', 'https://bluesrockreview.com/wp-content/uploads/2020/12/playingdrums.jpg', 'Hypebeast Drummers', 'Rock n Roll for hypebeast drummers', 50.00, '2022-09-22', '2022-10-22', 25, 'active'),
	   ('C12', 'T03', 'https://valottastudios.com/wp-content/uploads/2018/07/kid-playing-drums-300x298.jpg', 'Kiddy Drum Newbie', 'Grow up young talents', 20.50, '2022-08-01', '2022-10-01', 60, 'active'),

	   ('C13', 'T04', 'https://i.guim.co.uk/img/media/b0cc3b05df4b50ec6a46402a0ee9338cbfab3ec6/0_1039_4798_2875/master/4798.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=af1d1d0a4ee5c2577bedb6bb1ffc490a', 'Amateur Violin Class', 'Everyone can begin to play an instrument', 30.99, '2023-05-20', '2023-06-05', 30, 'active'),
	   ('C14', 'T04', 'https://img.freepik.com/free-photo/side-view-male-artist-playing-violin_23-2148680364.jpg?size=626&ext=jpg', 'Shuffle Violin', 'Explore creators in violin', 75.50, '2022-09-15', '2022-10-15', 20, 'inactive'),
	   ('C15', 'T04', 'https://static01.nyt.com/images/2019/07/25/obituaries/17Rosand1/merlin_19948779_f50cf64c-86fc-47bb-9658-9bcbb5010161-superJumbo.jpg', 'ModernVio', 'A combination of classical and modern violin rythms', 35.50, '2022-08-25', '2022-09-25', 15, 'active'),
	   ('C16', 'T04', 'https://www.ostwestmusikfest.at/wp-content/uploads/Beethoven-Astana-1.jpg', 'Violin Expert', 'A place for expert to create new beat', 105.00, '2023-02-01', '2023-04-28', 45, 'active'),

	   ('C17', 'T05', 'http://www.gradeinfinity.com/wp-content/uploads/2012/08/guitarkid.jpg', 'Kid Guitar', 'Get your kid exposed to guitar asap', 15.50, '2022-09-20', '2022-10-20', 50, 'active'),
	   ('C18', 'T05', 'https://thumbs.dreamstime.com/b/happy-group-friends-playing-guitar-14052309.jpg', 'Guitar with Friends', 'Enjoy learning in friendly group class', 25.99, '2022-11-01', '2022-11-29', 25, 'active'),
	   ('C19', 'T05', 'https://img.freepik.com/premium-photo/talented-guitarist-handsome-young-man-playing-guitar-while-sitting-windowsill_425904-15703.jpg', 'Guitar Career', 'For those who pursue profession in guitar', 85.50, '2022-08-12', '2022-10-12', 25, 'active'),
	   ('C20', 'T05', 'https://danviet.mediacdn.vn/2021/4/16/125215100101587281930194314283395500265150889n-16185585748661301150080.jpg', 'Adventourous Guitaaaa', 'Dynamic way to explore your talent in instruments', 35.50, '2022-08-02', '2022-10-12', 20, 'inactive'),
	   ('C21', 'T05', 'https://nld.mediacdn.vn/2017/web-1494084590879.jpg', 'Safe and Sound Guitar Class', 'Romantic songs cover tutorials', 30.50, '2022-12-01', '2022-12-25', 35, 'active'),
	   ('C22', 'T05', 'https://media.istockphoto.com/photos/seniors-european-man-are-playing-a-guitar-at-outdoor-green-garden-in-picture-id1165993594?k=20&m=1165993594&s=612x612&w=0&h=UdEh3__xA7UkxQ1mDjafBZbfy8-rY3FvRnKvsC2Z18o=', 'Elder Relaxation Guitar Course', 'A way for elderly people to relax', 10.00, '2022-08-20', '2022-10-5', 20, 'active')
GO
                            
INSERT INTO tblCourseTypes (courseTypeId, courseTypeName)
VALUES	('T01', 'Piano'),
		('T02', 'Saxophone'),
		('T03', 'Drum'),
		('T04', 'Violin'),
		('T05', 'Guitar')
GO

INSERT INTO tblRoles (roleId, roleName)
VALUES	('AD', 'Administrator'),
		('US', 'User')
GO

INSERT INTO tblUsers(userId, userName, userPhone, userEmail, userRoleId, [password])
VALUES ('admin', 'Truong Le Minh', '0789849878', 'tlminh2104@gmail.com', 'AD', '1'),
	   ('us', 'Minh Truong', '0123456789', 'studentnumber1@fpt.edu.vn', 'US', '1'),
	   ('ad', 'Administrator', '0234564879', 'admin@hotmail.com', 'AD', '123'),
	   ('mickey', 'Chu chuot ti hon', '0907262337', 'mkmouse@yahoo.com.vn', 'US', '12345'),
	   ('tinhim', 'Teacher Good', '0903763967', 'teacherpro@fe.edu.vn', 'US', '123')
GO

INSERT INTO tblPaymentTypes(paymentTypeId, paymentTypeName)
VALUES ('P01', 'cash'),
	   ('P02', 'online')
GO
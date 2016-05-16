drop table if exists dbUser;
drop table if exists dbFriend;
drop table if exists dbNotification;
drop table if exists dbInteractionType;
drop table if exists dbuserLocation;
drop table if exists dbState;
drop table if exists dbCity;
drop table if exists dbUniversity;
drop table if exists dbHighschool;
drop table if exists dbuserUniversity;
drop table if exists dbuserHighschool;
drop table if exists dbGroup;
drop table if exists dbGroupMembers;
drop table if exists dbPost;
drop table if exists dbComment;
drop table if exists dbImage;
drop table if exists dbLike;
drop table if exists dbDislike;
drop table if exists dbReportedPost;
drop table if exists dbReportType;
drop table if exists dbAdminOfReportType;
drop view if exists getPost;

CREATE TABLE dbUser (
	userID int(10) NOT NULL AUTO_INCREMENT,
	userName varchar(50) NOT NULL,
	userLastName varchar(50) NOT NULL,
	userBirthdate date NOT NULL,
	userProfilePicURL varchar(300) NOT NULL,
	userEmail varchar(300) NOT NULL,
	userPassword varchar(300) NOT NULL,
	constraint PKuser primary key (userID)
);

CREATE TABLE dbFriend (
	userID int(10) NOT NULL,
	userID_ofFriend int(10) NOT NULL,
	constraint PKfriend primary key (userID, userID_ofFriend)
);

CREATE TABLE dbNotification (
	affected_userID int(10) NOT NULL,
	interactionBy_userID int(10) NOT NULL,
	interacted_postID int(10) NOT NULL,
	interactionTypeID tinyint NOT NULL,
	notificationDate datetime NOT NULL,
	isRead tinyint(1) NOT NULL default 0
);

CREATE TABLE dbInteractionType (
	interactionTypeID tinyint NOT NULL,
	interactionTypeDesc varchar(100) NOT NULL
);

CREATE TABLE dbuserLocation (
	userID int(10) NOT NULL,
	stateID int(10),
	cityID int(10) NOT NULL,
	constraint PKuserLocation primary key (userID)
);

CREATE TABLE dbState (
	stateID int(10) NOT NULL,
	stateName varchar(50) NOT NULL,
	constraint PKstate primary key (stateID)
);

CREATE TABLE dbCity (
	cityID int(10) NOT NULL,
	cityName varchar(50) NOT NULL,
	constraint PKcity primary key (cityID)
);

CREATE TABLE dbUniversity (
	universityID int(10) NOT NULL,
	universityName varchar(100) NOT NULL,
	graduationDate datetime NOT NULL,
	constraint PKuniversity primary key (universityID)
);

CREATE TABLE dbHighschool (
	highschoolID int(10) NOT NULL,
	highschoolName varchar(100) NOT NULL,
	graduationDate datetime NOT NULL,
	constraint PKhighschool primary key (highschoolID)
);

CREATE TABLE dbuserUniversity (
	userID int(10) NOT NULL,
	universityID int(10) NOT NULL
);

CREATE TABLE dbuserHighschool (
	userID int(10) NOT NULL,
	highschoolID int(10) NOT NULL
);

CREATE TABLE dbGroup (
    groupID int(10) NOT NULL AUTO_INCREMENT,
    groupName varchar(100) NOT NULL,
    createdBy_userID int(10) NOT NULL,
    groupProfilePicURL varchar(300),
    constraint PKgroup primary key (groupID)
);

CREATE TABLE dbGroupMembers (
	groupID int(10) NOT NULL,
	userID_ofMember int(10) NOT NULL
);

CREATE TABLE dbPost (
	postID int(10) NOT NULL AUTO_INCREMENT,
	userID int(10) NOT NULL,
	groupID int(10),
	postText varchar(200),
	postDate datetime NOT NULL,
	constraint PKpost primary key (postID)
);

CREATE TABLE dbComment (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	commentDateTime datetime NOT NULL,
	userComment varchar(200) NOT NULL,
	constraint PKcomment primary key (postID,userID,commentDateTime)
);

CREATE TABLE dbImage (
	postID int(10) NOT NULL,
	imageURL varchar(300) NOT NULL,
	constraint PKimage primary key (postID,imageURL)
);

CREATE TABLE dbLike (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	constraint PKlike primary key (postID,userID)
);

CREATE TABLE dbDislike (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	constraint PKdislike primary key (postID,userID)
);

CREATE TABLE dbReportedPost (
	reportID int(11) NOT NULL AUTO_INCREMENT,
	postID int(10) NOT NULL,
	reportedBy_userID int(10) NOT NULL,
	typeID tinyint NOT NULL,
	constraint PKreportedPost primary key (reportID)
);

CREATE TABLE dbReportType (
	typeID tinyint NOT NULL,
	reportDesc varchar(100) NOT NULL,
	constraint PKreportType primary key (typeID)
);

CREATE TABLE dbAdminOfReportType (
	userID int(10) NOT NULL,
	typeID tinyint NOT NULL,
	constraint PKadminOfReportType primary key (userID,typeID)
);

alter table dbUser add constraint uniqueEmail 
    UNIQUE (userEmail);
	
alter table dbFriend add constraint FKfriend
		foreign key (userID) references dbUser (userID);
		
alter table dbNotification add constraint FKnotificationUserID
		foreign key (affected_userID) references dbUser (userID);

alter table dbNotification add constraint FKnotificationType
		foreign key (interactionTypeID) 
		references dbInteractionType (interactionTypeID);

alter table dbuserLocation add constraint FKuserLocationUserID
		foreign key (userID) references dbUser (userID);
		
alter table dbuserLocation add constraint FKuserLocationState
		foreign key (stateID) references dbState (stateID);
		
alter table dbuserLocation add constraint FKuserLocationCity
		foreign key (cityID) references dbCity (cityID);
		
alter table dbuserUniversity add constraint FKuserUniversityUserID
		foreign key (userID) references dbUser (userID);

alter table dbuserUniversity add constraint FKuserUniversityUni
		foreign key (universityID) references dbUniversity (universityID);

alter table dbuserHighschool add constraint FKuserHighschoolUserID
		foreign key (userID) references dbUser (userID);

alter table dbuserHighschool add constraint FKuserHighschoolHS
		foreign key (highschoolID) references dbHighschool (highschoolID);

alter table dbGroup add constraint FKgroup
		foreign key (createdBy_userID) references dbUser (userID);
		
alter table dbGroupMembers add constraint FKgroupMembersGroupID
		foreign key (groupID) references dbGroup (groupID);
		
alter table dbPost add constraint FKpostUserID
		foreign key (userID) references dbUser (userID);

alter table dbPost add constraint FKpostGroupID
		foreign key (groupID) references dbGroup (groupID);
		
alter table dbComment add constraint FKcommentPostID
		foreign key (postID) references dbPost (postID);

alter table dbImage add constraint FKimagePostID
		foreign key (postID) references dbPost (postID);
		
alter table dbLike add constraint FKlikepostID
		foreign key (postID) references dbPost (postID);
		
alter table dbDislike add constraint FKdislikePostID
		foreign key (postID) references dbPost (postID);

alter table dbReportedPost add constraint FKreportedpostUserID
		foreign key (reportedBy_userID) references dbUser (userID);

alter table dbReportedPost add constraint FKreportedpostPostID
		foreign key (postID) references dbPost (postID);

alter table dbReportedPost add constraint FKreportedpostTypeID
		foreign key (typeID) references dbReportType (typeID);
		
	
-- VIEWS
	
drop view if exists postUserDetails;
create view postUserDetails as
select dbPost.postID, dbPost.userID, dbUser.userName, dbUser.userLastName,
				dbUser.userProfilePicURL
				from dbPost
				inner join dbUser on dbPost.userID = dbUser.userID;


	
	
drop view if exists postLikeCount;
create view postLikeCount as
select COUNT(dbLike.userID)-1 as likeCount
				from dbPost
				inner join dbLike on dbPost.postID = dbLike.postID
				WHERE dbPost.postID = 1;				
	
	

drop view if exists postDislikeCount;
create view postDislikeCount as
select COUNT(dbDislike.userID)-1 as dislikeCount
				from dbPost
				inner join dbDislike on dbPost.postID = dbDislike.postID				
				WHERE dbPost.postID = 1;
	

drop view if exists postCommentCount;
create view postCommentCount as
select COUNT(dbComment.userID)-1 as commentCount
				from dbPost
				inner join dbComment on dbPost.postID = dbComment.postID
				WHERE dbPost.postID = 1;				
	
	

drop view if exists getPost;	
create view getPost as
		
select dbPost.postID, dbPost.userID, postUserDetails.userName, postUserDetails.userLastName,
		   postUserDetails.userProfilePicURL, dbPost.groupID, dbPost.postText, 
		   dbPost.postDate, postLikeCount.likeCount, postDislikeCount.dislikeCount, postCommentCount.commentCount
	from dbPost
	inner join postUserDetails on dbPost.postID = postUserDetails.postID
	inner join postLikeCount on dbPost.postID = postLikeCount.postID
	inner join postDislikeCount on dbPost.postID = postDislikeCount.postID
	inner join postCommentCount on dbPost.postID = postCommentCount.postID;


-- MOCKUP DATA

INSERT INTO dbUser (userName,userLastName,userBirthdate,userProfilePicURL,userEmail,userPassword) VALUES
("Doga","Ozkaraca","1997-11-25","http://dogaozkaraca.com/user_images/abc.png","doga.ozkaraca@gmail.com","1234567"),
("Ayse","Korkmaz","1998-05-25","http://dogaozkaraca.com/user_images/abc.png","ayse@gmail.com","1234567"),
("Berk","Atici","1987-11-23","http://dogaozkaraca.com/user_images/abc.png","berk@gmail.com","1234567"),
("Merve","Yaramaz","1993-04-12","http://dogaozkaraca.com/user_images/abc.png","merve@gmail.com","1234567"),
("Cansu","Saygin","1988-11-03","http://dogaozkaraca.com/user_images/abc.png","cansu@gmail.com","1234567"),
("Ali","Veli","1994-09-13","http://dogaozkaraca.com/user_images/abc.png","blabla@gmail.com","1234567");


INSERT INTO dbPost (userID,groupID,postText,postDate) VALUES
(1,NULL,"post","2016-05-15 11:57:32"),
(2,NULL,"deneme 1 2","2016-05-15 11:58:32"),
(2,NULL,"deneme","2016-05-15 11:59:32"),
(1,NULL,"baska bir post","2016-05-15 10:57:32"),
(1,NULL,"baska baska bir post","2016-05-15 09:57:32"),
(3,NULL,"merhaba","2016-05-15 09:57:32");

INSERT INTO dbFriend (userID,userID_ofFriend) VALUES
(1,2),
(1,3),
(2,3);
-- user ID zeros inserted for every post.
-- because when we do inner join while getting post likes,dislikes,comments
-- inner join returns null postIDs and query fails.
INSERT INTO dbLike (userID,postID) VALUES
(0,1),
(0,2),
(0,3),
(0,4),
(0,5),
(0,6),
(1,2),
(1,3);

INSERT INTO dbDislike (userID,postID) VALUES
(0,1),
(0,2),
(0,3),
(0,4),
(0,5),
(0,6),
(1,2),
(1,6),
(2,6),
(1,3);


INSERT INTO dbComment (userID,postID,commentDateTime,userComment) VALUES
(0,1,"0000-00-00 00:00:00","placeholder"),
(0,2,"0000-00-00 00:00:00","placeholder"),
(0,3,"0000-00-00 00:00:00","placeholder"),
(0,4,"0000-00-00 00:00:00","placeholder"),
(0,5,"0000-00-00 00:00:00","placeholder"),
(0,6,"0000-00-00 00:00:00","placeholder"),
(1,6,"2016-05-15 11:57:32","sanada merhaba");





















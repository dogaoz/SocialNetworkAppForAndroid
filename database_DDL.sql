drop table if exists dbUser
drop table if exists dbFriend
drop table if exists dbNotification
drop table if exists dbInteractionType
drop table if exists dbuserLocation
drop table if exists dbState
drop table if exists dbCity
drop table if exists dbUniversity
drop table if exists dbHighschool
drop table if exists dbuserUniversity
drop table if exists dbuserHighschool
drop table if exists dbGroup
drop table if exists dbGroupMembers
drop table if exists dbPost
drop table if exists dbComment
drop table if exists dbImage
drop table if exists dbLike
drop table if exists dbDislike
drop table if exists dbReportedPost
drop table if exists dbReportType
drop table if exists dbAdminOfReportType

CREATE TABLE dbUser (
	userID int(10) NOT NULL AUTO_INCREMENT,
	userName varchar(50) NOT NULL,
	userLastName varchar(50) NOT NULL,
	userBirthdate date NOT NULL,
	userProfilePicURL varchar(300) NOT NULL,
	userEmail varchar(300) NOT NULL,
	userPassword varchar(300) NOT NULL,
	constraint PKuser primary key (userID)
)

CREATE TABLE dbFriend (
	userID int(10) NOT NULL,
	userID_ofFriend int(10) NOT NULL,
	constraint PKfriend primary key (userID, userID_ofFriend)
)

CREATE TABLE dbNotification (
	affected_userID int(10) NOT NULL,
	interactionBy_userID int(10) NOT NULL,
	interacted_postID int(10) NOT NULL,
	interactionTypeID tinyint NOT NULL,
	notificationDate datetime NOT NULL,
	isRead tinyint(1) NOT NULL
)

CREATE TABLE dbInteractionType (
	interactionTypeID tinyint NOT NULL,
	interactionTypeDesc varchar(100) NOT NULL
)

CREATE TABLE dbuserLocation (
	userID int(10) NOT NULL,
	stateID int(10),
	cityID int(10) NOT NULL,
	constraint PKuserLocation primary key (userID)
)

CREATE TABLE dbState (
	stateID int(10) NOT NULL,
	stateName varchar(50) NOT NULL,
	constraint PKstate primary key (stateID)
)

CREATE TABLE dbCity (
	cityID int(10) NOT NULL,
	cityName varchar(50) NOT NULL,
	constraint PKcity primary key (cityID)
)

CREATE TABLE dbUniversity (
	universityID int(10) NOT NULL,
	universityName varchar(100) NOT NULL,
	graduationDate datetime NOT NULL,
	constraint PKuniversity primary key (universityID)
)

CREATE TABLE dbHighschool (
	highschoolID int(10) NOT NULL,
	highschoolName varchar(100) NOT NULL,
	graduationDate datetime NOT NULL,
	constraint PKhighschool primary key (highschoolID)
)

CREATE TABLE dbuserUniversity (
	userID int(10) NOT NULL,
	universityID int(10) NOT NULL
)

CREATE TABLE dbuserHighschool (
	userID int(10) NOT NULL,
	highschoolID int(10) NOT NULL
)

CREATE TABLE dbGroup (
    groupID int(10) NOT NULL AUTO_INCREMENT,
    groupName varchar(100) NOT NULL,
    createdBy_userID int(10) NOT NULL,
    groupProfilePicURL varchar(300),
    constraint PKgroup primary key (groupID)
)

CREATE TABLE dbGroupMembers (
	groupID int(10) NOT NULL,
	userID_ofMember int(10) NOT NULL
)

CREATE TABLE dbPost (
	postID int(10) NOT NULL AUTO_INCREMENT,
	userID int(10) NOT NULL,
	groupID int(10),
	postText varchar(200),
	postDate datetime NOT NULL,
	constraint PKpost primary key (postID)
)

CREATE TABLE dbComment (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	commentDateTime datetime NOT NULL,
	userComment varchar(200) NOT NULL,
	constraint PKcomment primary key (userID,commentDateTime)
)

CREATE TABLE dbImage (
	postID int(10) NOT NULL,
	imageURL varchar(300) NOT NULL,
	constraint PKimage primary key (postID,imageURL)
)

CREATE TABLE dbLike (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	constraint PKlike primary key (postID,userID)
)

CREATE TABLE dbDislike (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	constraint PKdislike primary key (postID,userID)
)

CREATE TABLE dbReportedPost (
	reportID int(11) NOT NULL AUTO_INCREMENT,
	postID int(10) NOT NULL,
	reportedBy_userID int(10) NOT NULL,
	typeID tinyint NOT NULL,
	constraint PKreportedPost primary key (reportID)
)

CREATE TABLE dbReportType (
	typeID tinyint NOT NULL,
	reportDesc varchar(100) NOT NULL,
	constraint PKreportType primary key (typeID)
)

CREATE TABLE dbAdminOfReportType (
	userID int(10) NOT NULL,
	typeID tinyint NOT NULL,
	constraint PKadminOfReportType primary key (userID,typeID)
)

alter table dbFriend add constraint FKfriend
		foreign key (userID) references dbUser (userID)
		
alter table dbNotification add constraint FKnotificationUserID
		foreign key (affected_userID) references dbUser (userID)

-- this FK is not on the diagram		
alter table dbNotification add constraint FKnotificationType
		foreign key (interactionTypeID) 
		references dbInteractionType (interactionTypeID)
--------		
alter table dbuserLocation add constraint FKuserLocationUserID
		foreign key (userID) references dbUser (userID)
		
alter table dbuserLocation add constraint FKuserLocationState
		foreign key (stateID) references dbState (stateID)
		
alter table dbuserLocation add constraint FKuserLocationCity
		foreign key (cityID) references dbCity (cityID)
		
alter table dbuserUniversity add constraint FKuserUniversityUserID
		foreign key (userID) references dbUser (userID)

alter table dbuserUniversity add constraint FKuserUniversityUni
		foreign key (universityID) references dbUniversity (universityID)

alter table dbuserHighschool add constraint FKuserHighschoolUserID
		foreign key (userID) references dbUser (userID)

alter table dbuserHighschool add constraint FKuserHighschoolHS
		foreign key (highschoolID) references dbHighschool (highschoolID)

alter table dbGroup add constraint FKgroup
		foreign key (createdBy_userID) references dbUser (userID)
		
alter table dbGroupMembers add constraint FKgroupMembersGroupID
		foreign key (groupID) references dbGroup (groupID)
		
alter table dbPost add constraint FKpostUserID
		foreign key (userID) references dbUser (userID)

alter table dbPost add constraint FKpostGroupID
		foreign key (groupID) references dbGroup (groupID)
		
alter table dbComment add constraint FKcommentPostID
		foreign key (postID) references dbPost (postID)
----- these FKs are not on the diagram
alter table dbComment add constraint FKcommentUserID
		foreign key (userID) references dbUser (userID)
		
alter table dbLike add constraint FKlikeUserID
		foreign key (userID) references dbUser (userID)
		
alter table dbDislike add constraint FKdislikeUserID
		foreign key (userID) references dbUser (userID)		
--------------
alter table dbImage add constraint FKimagePostID
		foreign key (postID) references dbPost (postID)
		
alter table dbLike add constraint FKlikepostID
		foreign key (postID) references dbPost (postID)
		
alter table dbDislike add constraint FKdislikePostID
		foreign key (postID) references dbPost (postID)	

----- this FK is not on the diagram
alter table dbReportedPost add constraint FKreportedpostUserID
		foreign key (reportedBy_userID) references dbUser (userID)
--------------

alter table dbReportedPost add constraint FKreportedpostPostID
		foreign key (postID) references dbPost (postID)

alter table dbReportedPost add constraint FKreportedpostTypeID
		foreign key (typeID) references dbReportType (typeID)



				
					
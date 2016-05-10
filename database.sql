drop table if exists dbUser
drop table if exists Friend
drop table if exists Notification
drop table if exists InteractionType
drop table if exists userLocation
drop table if exists dbState
drop table if exists dbCity
drop table if exists dbUniversity
drop table if exists dbHighschool
drop table if exists userUniversity
drop table if exists userHighschool
drop table if exists dbGroup
drop table if exists dbGroupMembers
drop table if exists dbPost
drop table if exists Comment
drop table if exists Image
drop table if exists dbLike
drop table if exists dbDislike
drop table if exists ReportedPost
drop table if exists ReportType
drop table if exists AdminOfReportType

CREATE TABLE dbUser (
	userID int(10),
	userName varchar(50),
	userLastName varchar(50),
	userBirthdate date,
	userProfilePicURL varchar(300),
	userEmail varchar(300),
	userPassword varchar(300)
)

CREATE TABLE Friend (
	userID int(10) NOT NULL,
	userID_ofFriend int(10) NOT NULL
)

CREATE TABLE Notification (
	affected_userID int(10) NOT NULL,
	interactionBy_userID int(10),
	interacted_postID int(10),
	interactionTypeID tinyint,
	notificationDate datetime
	isRead tinyint(1)
)

CREATE TABLE InteractionType (
	interactionTypeID tinyint,
	interactionTypeDesc varchar(100)
)

CREATE TABLE userLocation (
	userID int(10) NOT NULL,
	stateID int(10),
	cityID int(10) NOT NULL
)

CREATE TABLE dbState (
	stateID int(10),
	stateName varchar(50)
)

CREATE TABLE dbCity (
	cityID int(10),
	cityName varchar(50)
)

CREATE TABLE dbUniversity (
	universityID int(10),
	universityName varchar(100),
	graduationDate datetime
)

CREATE TABLE dbHighschool (
	highschoolID int(10),
	highschoolName varchar(100),
	graduationDate datetime
)

CREATE TABLE userUniversity (
	userID int(10),
	universityID int(10)
)

CREATE TABLE userHighschool (
	userID int(10),
	highschoolID int(10)
)

CREATE TABLE dbGroup (
    groupID int(10),
    groupName varchar(100),
    createdBy_userID int(10),
    groupProfilePicURL varchar(300)
)

CREATE TABLE dbGroupMembers (
	groupID int(10),
	userID_ofMember int(10)
)

CREATE TABLE Post (
	postID int(10) NOT NULL,
	userID int(10) NOT NULL,
	groupID int(10),
	postText varchar(200),
	postDate datetime
)

CREATE TABLE Comment (
	postID int(10),
	userID int(10),
	commentDateTime datetime,
	userComment varchar(200)
)

CREATE TABLE Image (
	postID int(10),
	imageURL varchar(300)
)

CREATE TABLE dbLike (
	postID int(10),
	userID int(10)
)

CREATE TABLE dbDislike (
	postID int(10),
	userID int(10)
)

CREATE TABLE ReportedPost (
	reportID int(11),
	postID int(10),
	reportedBy_userID int(10),
	typeID tinyint
)

CREATE TABLE ReportType (
	typeID tinyint,
	reportDesc varchar(100)
)

CREATE TABLE AdminOfReportType (
	userID int(10),
	typeID tinyint
)
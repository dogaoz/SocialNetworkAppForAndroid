<?php
ini_set('display_errors', 'On');
// █████╗ ██╗   ██╗████████╗██╗  ██╗
//██╔══██╗██║   ██║╚══██╔══╝██║  ██║
//███████║██║   ██║   ██║   ███████║
//██╔══██║██║   ██║   ██║   ██╔══██║
//██║  ██║╚██████╔╝   ██║   ██║  ██║
//╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝
	function login($email,$password)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT userID,userName,userLastName,userBirthDate,userEmail FROM dbUser WHERE (userEmail = ? AND userPassword = ?) ');
		$query->execute(array($email,$password));
		$result = $query->fetchAll(PDO::FETCH_ASSOC);
		
		if (COUNT($result) == 0)
		{
			return 1;
		}
		else
		{
			
		$users = array();
		foreach ($result as $user)
		{
		    $users[] = $user;
		}
		return $users;
		
		}
		
		
		
	}

//███╗   ███╗ █████╗ ██╗███╗   ██╗
//████╗ ████║██╔══██╗██║████╗  ██║
//██╔████╔██║███████║██║██╔██╗ ██║
//██║╚██╔╝██║██╔══██║██║██║╚██╗██║
//██║ ╚═╝ ██║██║  ██║██║██║ ╚████║
//╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝
                                
	function loadFeed($userID)
	{
		
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM getPost ORDER BY postDate DESC');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;

	}
	
//██████╗ ██████╗  ██████╗ ███████╗██╗██╗     ███████╗
//██╔══██╗██╔══██╗██╔═══██╗██╔════╝██║██║     ██╔════╝
//██████╔╝██████╔╝██║   ██║█████╗  ██║██║     █████╗  
//██╔═══╝ ██╔══██╗██║   ██║██╔══╝  ██║██║     ██╔══╝  
//██║     ██║  ██║╚██████╔╝██║     ██║███████╗███████╗
//╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚══════╝
	
	function myProfile($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT userName, userLastName, userBirthDate, userProfilePicURL,
										 userEmail FROM dbUser WHERE userID= ?');
		$query->execute(array($userID));
		$profileDetails  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$details = array();

		foreach ($profileDetails as $detail)
		{
			$details[] = $detail;
		}
	
		return $details;
	}
	
	function  addLocation($userID,$stateID,$cityID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT dbUserLocation
											(userID,stateID,cityID) VALUES (?,?,?) ');
		$query->execute(array($stateID,$cityID,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	}
	
	function addUniversity($userID,$universityID,$graduationDate)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT dbUserUniversity
											(userID,universityID,graduationDate) VALUES (?,?,?) ');
		$query->execute(array($userID,$universityID,$graduationDate));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	}
	
	function addHighschool($userID,$highschoolID,$graduationDate)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT dbUserHighschool
											(userID,highschoolID,graduationDate) VALUES (?,?,?)');
		$query->execute(array($userID,$highschoolID,$graduationDate));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;	
	}
	function  editLocation($userID,$stateID,$cityID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('UPDATE dbUserLocation
											SET stateID= ?, cityID= ?
											WHERE userID = ? ');
		$query->execute(array($stateID,$cityID,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	}
	
	function editUniversity($userID,$universityID,$graduationDate)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('UPDATE dbUserUniversity
											SET universityID = ?, graduationDate = ?
											WHERE userID = ? ');
		$query->execute(array($universityID,$graduationDate,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	}
	
	function editHighschool($userID,$highschoolID,$graduationDate)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('UPDATE dbUserHighschool
											SET highschoolID = ?, graduationDate = ?
											WHERE userID = ? ');
		$query->execute(array($highschoolID,$graduationDate,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;	
	}
	
//███╗   ██╗ ██████╗ ████████╗██╗███████╗██╗ ██████╗ █████╗ ████████╗██╗ ██████╗ ███╗   ██╗
//████╗  ██║██╔═══██╗╚══██╔══╝██║██╔════╝██║██╔════╝██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║
//██╔██╗ ██║██║   ██║   ██║   ██║█████╗  ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║
//██║╚██╗██║██║   ██║   ██║   ██║██╔══╝  ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║
//██║ ╚████║╚██████╔╝   ██║   ██║██║     ██║╚██████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║
//╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝╚═╝     ╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝
                                                                                         	
	function myNotifications($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbNotification WHERE affected_userID= ? ORDER BY notificationDate DESC');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;
	}
	
	function createNotification($interactionByUserID,$affectedUserID,$interactedPostID,$interactionTypeID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbNotification (affected_userID,interactionByUserID,interactedPostID,notificationDate)
										VALUES (?,?,?,?) ');
		$query->execute(array($affectedUserID,$interactionByUserID,$interactedPostID,$notificationDate));
		
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	
	}
	
	function markNotificationRead($interactionByUserID,$affectedUserID,$interactedPostID,$notificationDate)
	{
	
		global $dbConnection;
		$query = $dbConnection->prepare('UPDATE dbNotification SET isRead=1 WHERE (affected_userID= ? AND interactionByUserID= ? AND interactedPostID= ? AND notificationDate = ?) ');
		$query->execute(array($affectedUserID,$interactionByUserID,$interactedPostID,$notificationDate));
		
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	}
	
//███████╗██████╗ ██╗███████╗███╗   ██╗██████╗ 
//██╔════╝██╔══██╗██║██╔════╝████╗  ██║██╔══██╗
//█████╗  ██████╔╝██║█████╗  ██╔██╗ ██║██║  ██║
//██╔══╝  ██╔══██╗██║██╔══╝  ██║╚██╗██║██║  ██║
//██║     ██║  ██║██║███████╗██║ ╚████║██████╔╝
//╚═╝     ╚═╝  ╚═╝╚═╝╚══════╝╚═╝  ╚═══╝╚═════╝ 
  
  	function myFriends($userID)
  	{
  		global $dbConnection;
		$query = $dbConnection->prepare('SELECT DISTINCT dbUser.userID, dbUser.userName,dbUser.userLastName,dbUser.userProfilePicURL FROM dbUser INNER JOIN dbFriend on (( dbFriend.userID = ? AND dbFriend.userID_ofFriend = dbUser.userID) or ( dbFriend.userID = dbUser.userID AND dbFriend.userID_ofFriend=?))');
		$query->execute(array($userID,$userID));
		$friend_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$friends = array();

		foreach ($friends_fromQ as $friend)
		{
		    $friends[] = $friend;
		}

		return $friends;
  	
  	
  	}
  	
  	function addFriend($userID,$friendUserID_toAdd)
  	{
  		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO Friend VALUES (?,?)');
		$query->execute(array($userID,$friendUserID_toAdd));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
  	
  	}
  	
  	function removeFriend($userID,$friendUserID_toRemove)
  	{
  		global $dbConnection;
		$query = $dbConnection->prepare('DELETE FROM Friend WHERE (userID = ? AND userID_ofFriend = ?)');
		$query->execute(array($userID,$friendUserID_toRemove));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
  	
  	}
	
	function friendCount($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT COUNT(*) as friendCount FROM dbFriend WHERE userID=?');
		$query->execute(array($userID));
		$query_result  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$resultArray = array();

		foreach ($query_result as $result_row)
		{
		    $resultArray[] = $result_row;
		}

		return $resultArray;
		
	}
  	
// ██████╗ ██████╗  ██████╗ ██╗   ██╗██████╗ 
//██╔════╝ ██╔══██╗██╔═══██╗██║   ██║██╔══██╗
//██║  ███╗██████╔╝██║   ██║██║   ██║██████╔╝
//██║   ██║██╔══██╗██║   ██║██║   ██║██╔═══╝ 
//╚██████╔╝██║  ██║╚██████╔╝╚██████╔╝██║     
// ╚═════╝ ╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  
	function myGroups($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM Group INNER JOIN GroupMembers on Group.groupID = GroupMembers.groupID WHERE GroupMembers.userID_ofMember = ? ');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;
	
	}
	
	function getGroupPosts($groupID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbPost WHERE groupID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;
	
	}
	
	function createGroup($userID,$groupName,$groupImageURL)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbGroup (groupName,createdBy_userID,groupProfilePicURL) VALUES (?,?,?)');
		$query->execute(array($groupName,$userID,$groupImageURL));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
		
	}
	
	function addMemberToGroup($groupID,$userID_toadd)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbGroupMembers (groupID,userID_ofMember) VALUES (?,?)');
		$query->execute(array($groupID,$userID_toadd));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
		
	}
	
	function removeMemberFromGroup($groupID,$userID_toremove)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('DELETE FROM dbGroupMembers WHERE (groupID = ? AND userID_ofMember = ?)');
		$query->execute(array($groupID,$userID_toRemove));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
		
	}
	
	function removeGroup($groupID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('DELETE FROM dbGroup WHERE (groupID = ?)');
		$query->execute(array($groupID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
		
	}

	function groupDetails($groupID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbGroup WHERE groupID= ?');
		$query->execute(array($groupID));
		$groupQuery  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$groupDetails = array();

		foreach ($groupQuery as $detail)
		{
		    $groupDetails[] = $detail;
		}
		
		$query2 = $dbConnection->prepare('SELECT COUNT(*) AS groupMemberCount FROM dbGroupMembers WHERE groupID= ?');
		$query2->execute(array($groupID));
		$groupQuery2  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		foreach ($groupQuery2 as $detail)
		{
		    $groupDetails[] = $detail;
		}
		

		return $groupDetails;
			
	}
  	                                           
                                             
//██████╗  ██████╗ ███████╗████████╗
//██╔══██╗██╔═══██╗██╔════╝╚══██╔══╝
//██████╔╝██║   ██║███████╗   ██║   
//██╔═══╝ ██║   ██║╚════██║   ██║   
//██║     ╚██████╔╝███████║   ██║   
//╚═╝      ╚═════╝ ╚══════╝   ╚═╝   
    function newPost($userID,$groupID,$postText,$imageURLarray) 
    {
 
    	global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbPost (userID,groupID,postText,postDate) VALUES (?,?,?, NOW())');
		$query->execute(array($userID,$groupID,$postText));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
    
    
    }                         	
	function myPosts($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM getPost WHERE userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;
	}
	
	function viewPost($postID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM getPost WHERE postID = ?');
		$query->execute(array($postID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;
	
	
	}
	
	function likePost($userID,$postID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbLike (postID,userID) VALUES (?,?)');
		$query->execute(array($postID,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	}
	
	function dislikePost($userID,$postID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbDislike (postID,userID) VALUES (?,?)');
		$query->execute(array($postID,$userID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	
	}
	
	function commentPost($userID,$postID,$userComment)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbComment (postID,userID,commentDateTime,userComment) VALUES (?,?, NOW(), ?)');
		$query->execute(array($postID,$userID,$userComment));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;

	}
	
// █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗
//██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║
//███████║██║  ██║██╔████╔██║██║██╔██╗ ██║
//██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║
//██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║
//╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝	

	function reportPost($userID,$postID,$reportReason)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO dbReportedPost (postID,reportedBy_userID,typeID) VALUES (?,?,?)');
		$query->execute(array($postID,$userID,$reportReason));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	}
	
	function getReportedPosts($admin_UserID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbReportedPost WHERE typeID=(SELECT typeID FROM AdminOfReportType WHERE $userID= ?)');
		$query->execute(array($admin_UserID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return $posts;	
	
	}
	
	function deletePost($postID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('DELETE FROM Post WHERE postID = ?');
		$query->execute(array($postID));
		
		$result = array();

		if ($query)
		{
			$result[] = 'success';
		}
		else
		{
			$result[] = 'failure';	
		}

		return $result;
	
	}

	
	
	

?>

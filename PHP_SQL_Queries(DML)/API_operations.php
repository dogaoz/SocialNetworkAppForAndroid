<?php
//ini_set('display_errors', 'On');

//███╗   ███╗ █████╗ ██╗███╗   ██╗
//████╗ ████║██╔══██╗██║████╗  ██║
//██╔████╔██║███████║██║██╔██╗ ██║
//██║╚██╔╝██║██╔══██║██║██║╚██╗██║
//██║ ╚═╝ ██║██║  ██║██║██║ ╚████║
//╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝
                                
	function loadFeed($userID)
	{
		
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbPost INNER JOIN dbFriend ON dbFriend.userID_ofFriend = dbPost.userID WHERE dbFriend.userID = ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);

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
			$details[] = $detail
		}
	
		return json_encode($details);
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
		$query = $dbConnection->prepare('SELECT * FROM dbNotification WHERE affected_userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);
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

		return json_encode($result);
	
	
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

		return json_encode($result);
	
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
		$query = $dbConnection->prepare('SELECT dbUser.userName,dbUser.userLastName,dbUser.userProfilePicURL FROM dbUser INNER JOIN dbFriend on dbFriend.userID = dbUser.userID WHERE userID= ? ');
		$query->execute(array($userID));
		$friend_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$friends = array();

		foreach ($friends_fromQ as $friend)
		{
		    $friends[] = $friend;
		}

		return json_encode($friends);
  	
  	
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

		return json_encode($result);
  	
  	
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

		return json_encode($result);
  	
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

		return json_encode($posts);
	
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

		return json_encode($posts);
	
	}
  
  	                                           
                                             
//██████╗  ██████╗ ███████╗████████╗
//██╔══██╗██╔═══██╗██╔════╝╚══██╔══╝
//██████╔╝██║   ██║███████╗   ██║   
//██╔═══╝ ██║   ██║╚════██║   ██║   
//██║     ╚██████╔╝███████║   ██║   
//╚═╝      ╚═════╝ ╚══════╝   ╚═╝   
    function newPost($userID,$groupID,$postText,$imageURLarray) 
    {
    	//TODO:add image urls to database
    	global $dbConnection;
		$query = $dbConnection->prepare('INSERT INTO Post (userID,groupID,postText,postDate) VALUES (?,?,?, NOW())');
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

		return json_encode($result);
    
    
    }                         	
	function myPosts($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbPost WHERE userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);
	}
	
	function viewPost($postID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM dbPost WHERE postID = ?');
		$query->execute(array($postID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);
	
	
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

		return json_encode($result);
	
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

		return json_encode($result);
	
	
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

		return json_encode($result);

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

		return json_encode($result);
	
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

		return json_encode($posts);	
	
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

		return json_encode($result);
	
	}

	
	
	

?>

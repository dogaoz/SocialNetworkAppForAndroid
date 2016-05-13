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
		//TODO : do inner join with friends' userIDs
		//where clause is wrong
		//select friends's post between current date time and 2 days ago.
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

  
  	                                           
                                             
//██████╗  ██████╗ ███████╗████████╗
//██╔══██╗██╔═══██╗██╔════╝╚══██╔══╝
//██████╔╝██║   ██║███████╗   ██║   
//██╔═══╝ ██║   ██║╚════██║   ██║   
//██║     ╚██████╔╝███████║   ██║   
//╚═╝      ╚═════╝ ╚══════╝   ╚═╝   
                                  	
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
	
// █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗
//██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║
//███████║██║  ██║██╔████╔██║██║██╔██╗ ██║
//██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║
//██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║
//╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝	

	
	
	

?>

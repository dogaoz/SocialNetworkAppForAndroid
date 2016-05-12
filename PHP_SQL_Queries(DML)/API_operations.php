<?php
//ini_set('display_errors', 'On');


	function loadFeed($userID)
	{
		
		global $dbConnection;
		//TODO : do inner join with friends' userIDs
		//where clause is wrong
		//select friends's post between current date time and 2 days ago.
		$query = $dbConnection->prepare('SELECT * FROM Post WHERE userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);

	}
	
	function myProfile($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT userName, userLastName, userBirthDate, userProfilePicURL,
										 userEmail FROM User WHERE userID= ?');
		$query->execute(array($userID));
		$profileDetails  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$details = array();

		foreach ($profileDetails as $detail)
		{
			$details[] = $detail
		}
	
		return json_encode($details);
	}
	
	function myNotifications($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM Notification WHERE affected_userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);
	}
	
	function myPosts($userID)
	{
		global $dbConnection;
		$query = $dbConnection->prepare('SELECT * FROM Post WHERE userID= ?');
		$query->execute(array($userID));
		$posts_fromQ  = $query->fetchAll(PDO::FETCH_ASSOC);
		
		$posts = array();

		foreach ($posts_fromQ as $post)
		{
		    $posts[] = $post;
		}

		return json_encode($posts);
	}
	
	

	
	
	

?>

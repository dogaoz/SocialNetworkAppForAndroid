<?php
//ini_set('display_errors', 'On');
require 'dbconfig.php';
require 'API_operations.php';
session_start();
header('Content-Type: application/json');

if ($_SESSION['USERID'])
{


  $aResult = array();

if( !isset($_POST['functionname']) ) { $aResult['error'] = 'Error : empty request!'; }

	$userID = $_SESSION['USERID'];
	$friendUserID_toAdd = $_POST['friendUserID'];
	$friendUserID_toRemove = $_POST['friendUserID'];


    if( !isset($aResult['error']) ) {
        switch($_POST['functionname']) {

			//Homepage Actions
            case 'loadFeed':
               	$aResult['result'] = loadFeed($userID);
                break; 
            //Profile Actions
            case 'myProfile':
               	$aResult['result'] = myProfile($userID);
               	break;
			case 'addLocation':
				$aResult['result'] = editLocation($userID,$stateID,$cityID);
				break;
			case 'addUniversity':
				$aResult['result'] = editUniversity($userID,$universityID);
				break;
			case 'addHighschool':
				$aResult['result'] = editHighschool($userID,$highschoolID);
				break;
			case 'editLocation':
				$aResult['result'] = editLocation($userID,$stateID,$cityID);
				break;
			case 'editUniversity':
				$aResult['result'] = editUniversity($userID,$universityID);
				break;
			case 'editHighschool':
				$aResult['result'] = editHighschool($userID,$highschoolID);
				break;
            //Notification Actions
            case 'myNotifications':
            	$aResult['result'] = myNotifications($userID);
                break;
            case 'createNotification':
            	$aResult['result'] = createNotification($interactionByUserID,$affectedUserID,$interactedPostID,$interactionTypeID);
            	break;
            case 'markNotificationRead':
            	$aResult['result'] = markNotificationRead($interactionByUserID,$affectedUserID,$interactedPostID,$notificationDate);
            	break;
            //Friend Actions
            case 'myFriends':
               	$aResult['result'] = myFriends($userID);
                break;
			case 'addFriend':
			   $aResult['result'] = addFriend($userID,$friendUserID_toAdd);
                break;
			case 'removeFriend':
			   $aResult['result'] = removeFriend($userID,$friendUserID_toRemove);
                break;
			case 'friendCount':
				$array['result'] = friendCount($userID);
				break;
            //Group Actions
            case 'myGroups':
               	$aResult['result'] = myGroups($userID);
                break;
            case 'getGroupPosts':
            	$aResult['result'] = getGroupPosts($groupID);
            	break;
            case 'createGroup':
            	$aResult['result'] = createGroup($userID,$groupName,$groupImageURL);
            	break;
            case 'addMemberToGroup':
            	$aResult['result'] = addMemberToGroup($groupID,$userID_toadd);
            	break;
            case 'removeMemberFromGroup':
            	$aResult['result'] = removeMemberFromGroup($groupID,$userID_toremove);
				break;
			case 'deleteGroup':
            	$aResult['result'] = removeGroup($groupID);
            	break;          
			case 'groupDetails':
				$array['result'] = groupDetails($groupID);
				break;
        	//Post Actions
        	case 'newPost':
				$groupID = null; //Temporary
				$imageURLarray = null; //Temporary
				$postText = $_POST['postText'];
            	$aResult['result'] = newPost($userID,$groupID,$postText,$imageURLarray);
            	break;
        	case 'myPosts':
               	$aResult['result'] = myPosts($userID);
           		break;
        	case 'viewPost':
            	$aResult['result'] = viewPost($postID);
            	break;
            case 'likePost':
            	$aResult['result'] = likePost($userID,$postID);
            	break;
            case 'dislikePost':
            	$aResult['result'] = dislikePost($userID,$postID);
            	break;
            case 'commentPost':
            	$aResult['result'] = commentPost($userID,$postID,$userComment);
            	break;
            
            case 'reportPost':
            	$aResult['result'] = reportPost($userID,$postID,$reportReason);
            	break;
			//Admin Actions
            case 'getReportedPosts':
            	$aResult['result'] = getReportedPosts($admin_UserID);
            	break;
            case 'deletePost':
            	$aResult['result'] = deletePost($postID);
            	break;
			case 'givePrivilege':
				$aResult['result'] = givePrivilege($userID_toMakeAdmin,$privilegeType);
            	break;
			case 'removePrivilege':
				$aResult['result'] = removePrivilege($userID_toRemoveAdmin,$privilegeType);
            	break;
            case 'logout':
				unset($_SESSION['USERID']);
				$result = array();
				$result[] = "Logged out!";
				$aResult['result'] = $result;
				break;
                        
            default:
				$result = array();
				$result[] = "API Request is empty!";
				$aResult['result'] = $result;
               break;
        }
    	

    }
    
    
echo json_encode($aResult);
}
else
{ // User not logged in 
	$result = array();

	if(isset($_POST['functionname']) AND $_POST['functionname'] == "login" AND isset($_POST['email']) AND isset($_POST['password']) )
	{
		$login = login($_POST['email'],$_POST['password']);
		if ($login == 1)
		{
		$result[] = "Error: access denied!";

		}
		else
		{
		$result = $login;
		global $dbConnection;	
		$q = "SELECT userID FROM dbUser WHERE userEmail=?;";
		$query = $dbConnection->prepare($q);
		$query->execute(array($_POST['email']));
		
		$rows = $query->fetchAll(PDO::FETCH_ASSOC);
			foreach( $rows as $row ) 
			{
			$_SESSION['USERID'] = $row['userID'];
			}
		}
	}
	else if(isset($_POST['functionname']) AND $_POST['functionname'] == "createAccount" 
				AND isset($_POST['email']) 
				AND isset($_POST['password'])
				AND isset($_POST['birthdate'])
				AND isset($_POST['name'])
				AND isset($_POST['lastname']))
	{
		global $dbConnection;
		$query = $dbConnection->prepare('INSERT dbUser
											(userName,userLastName,userBirthdate,userEmail,userPassword) 
											VALUES (?,?,?,?,?) ');
		$query->execute(array($_POST['name'],$_POST['lastname'],$_POST['birthdate'],$_POST['email'],$_POST['password']));
		
		$result = array();

		if ($query)
		{
			$result[] = 'Account successfully created!';
		}
		else
		{
			$result[] = 'failure';	
		}
		
	}
	else
	{
		
	$result[] = "Error: access denied!";
		
		
	}
	echo json_encode($result);
	
}


?>

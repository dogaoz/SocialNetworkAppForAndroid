<?php
//ini_set('display_errors', 'On');
require 'dbconfig.php';
require 'API_operations.php';
session_start();
header('Content-Type: application/json');


  $aResult = array();

if( !isset($_POST['functionname']) ) { $aResult['error'] = 'Error bp908!'; }

if(isset($_POST['functionname']) AND $_POST['functionname'] == 'addFriend' AND !isset($_POST['friendUserID']) )
	{
		$aResult['error'] = 'Error bp915!';
	}

if(isset($_POST['functionname']) AND $_POST['functionname'] == 'removeFriend' AND !isset($_POST['friendUserID']) )
	{ 
		$aResult['error'] = 'Error bp916!'; 
	}

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
            //TODO : profile editing actions location city state etc
               	
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
            	$aResult['result'] = removeGroup($userID,$groupID);
            	break;          

        	//Post Actions
        	case 'newPost':
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
            //Admin Actions
            case 'reportPost':
            	$aResult['result'] = reportPost($userID,$postID,$reportReason);
            	break;
            case 'getReportedPosts':
            	$aResult['result'] = getReportedPosts($admin_UserID);
            	break;
            case 'deletePost':
            	$aResult['result'] = deletePost($postID);
            	break;
            
                        
            default:
               //$aResult['error'] = function not found : '.$_POST['functionname'].'!';
               break;
        }
    	

    }
    
    
echo json_encode($aResult);

?>

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
            case 'loadFeed':
               $aResult['result'] = loadFeed($userID);
               break;
            case 'myProfile':
               $aResult['result'] = myProfile($userID);
               break;
            case 'myGroups':
               $aResult['result'] = myGroups($userID);
               break;
            case 'myFriends':
               $aResult['result'] = myFriends($userID);
               break;
			case 'addFriend':
			   $aResult['result'] = myFriends($userID,$friendUserID_toAdd);
               break;
			 case 'removeFriend':
			   $aResult['result'] = myFriends($userID,$friendUserID_toRemove);
               break;
            default:
               //$aResult['error'] = function not found : '.$_POST['functionname'].'!';
               break;
        }
    	

    }
    
    
echo json_encode($aResult);

?>

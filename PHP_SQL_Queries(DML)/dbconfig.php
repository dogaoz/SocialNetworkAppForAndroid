<?php
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'dogaozka_snDB');    // DB username
define('DB_PASSWORD', 'dbTest12345');    // DB password
define('DB_DATABASE', 'dogaozka_snDB');      // DB name
$connection = mysql_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD) or die( "Unable to connect");
$database = mysql_select_db(DB_DATABASE) or die( "Unable to select database");
mysql_query("SET NAMES UTF8"); //for Turkish characters and also other unicode characters
?>
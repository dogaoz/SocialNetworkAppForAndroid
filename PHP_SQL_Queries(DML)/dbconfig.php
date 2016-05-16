<?php
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'dogaozka_snDB');    // DB username
define('DB_PASSWORD', '1dbTest12345');    // DB password
define('DB_DATABASE', 'dogaozka_socialDB');      // DB name
$dbConnection = new PDO('mysql:host=localhost;dbname='.DB_DATABASE, DB_USERNAME, DB_PASSWORD);
$dbConnection->query("SET NAMES UTF8"); //for Turkish characters and also other unicode characters
?>
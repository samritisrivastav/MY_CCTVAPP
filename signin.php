<?php
// reading data which is sent from app in jsonobject form
$data = file_get_contents('php://input');
// decoding the json object
$decoded_data = json_decode($data , true);
$email = $decoded_data['name']; // give keys which are given in app while creating json object
$password = $decoded_data['password'];// give keys which are given in app while creating json object
$type = $decoded_data['type'];
// creating connection with database and saving in variable  
$connection = mysqli_connect('localhost' , 'root' , '');
// selecting database user 
mysqli_select_db($connection , 'cctv');

if($type == "user")
{
	$result = mysqli_query($connection , "select email_address , password from user where email_address = '$email' and password = '$password' ");
// fetching number of rows returned in query 
$rows = mysqli_num_rows($result);
// if now row found mean no such data sent by user found in database
if($rows == 0)
	
	{
		// making key value pair with key named 'key' and value "not done"
		$response['key'] = "not done";
		// converting key value pair into jsonobject form
		echo json_encode($response);
	}
	
	// if some row found mean data found in database
	else {
		// making key value pair with key named 'key' and value "not done"
		$response['key'] = "done";
		// converting key value pair into jsonobject form
		echo json_encode($response);
	}
}

else {
	$result = mysqli_query($connection , "select email_address , password from admin where email_address = '$email' and password = '$password' ");
// fetching number of rows returned in query 
$rows = mysqli_num_rows($result);
// if now row found mean no such data sent by user found in database
if($rows == 0)
	
	{
		// making key value pair with key named 'key' and value "not done"
		$response['key'] = "not done";
		// converting key value pair into jsonobject form
		echo json_encode($response);
	}
	
	// if some row found mean data found in database
	else {
		// making key value pair with key named 'key' and value "not done"
		$response['key'] = "done";
		// converting key value pair into jsonobject form
		echo json_encode($response);
	}
}

?>
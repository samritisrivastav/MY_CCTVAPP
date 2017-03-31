<?php
$data = file_get_contents('php://input');

$data_decoded = json_decode($data , true);

$name = $data_decoded['name_key'];
$mobile = $data_decoded['mobile_key'];
$email = $data_decoded['email_key'];
$password = $data_decoded['password_key'];

$connection = mysqli_connect('localhost' , 'root' , '');

mysqli_select_db($connection , 'cctv');

$result = mysqli_query($connection , "select email_address from user where email_address = '$email'");

$rows_found = mysqli_num_rows($result);

if($rows_found == 0){
	
mysqli_query($connection, "insert into user (name , email_address , password, mobile) 
values ('$name' , '$email' , '$mobile' , '$password') " );


        $response['key'] = "1";
	
	echo json_encode($response);
}
 
else {
	
	$response['key'] = "0";
	
	echo json_encode($response);
}
?>























?>
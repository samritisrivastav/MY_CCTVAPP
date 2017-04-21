<?php
$data =file_get_contents('php://input');

$data_decoded= json_decode($data,true);




$connection = mysqli_connect('localhost','root','');

mysqli_select_db($connection ,'cctv');

$result = mysqli_query($connection , "select * from add_device ");

while($row = mysqli_fetch_assoc($result))
	$output[] = $row;

$response['result'] = $output;
echo json_encode($response);





?>
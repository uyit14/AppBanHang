<?php
	include "connect.php";
	$customername = $_POST['customername'];
	$email = $_POST['email'];
	$phone = $_POST['phone'];
	if (strlen($customername) > 0 && strlen($email) > 0 && strlen($phone) > 0) {
	 	$query = "INSERT INTO bill(billid, customername, email, phone) VALUES(null, '$customername', '$email', '$phone')";
	 	if(mysqli_query($conn, $query)){
	 		$billid = $conn->insert_id;
	 		echo $billid;
	 	}else{
	 		echo "fail";
	 	}
	 }else{
	 	echo "fail 2";
	 }
 ?>
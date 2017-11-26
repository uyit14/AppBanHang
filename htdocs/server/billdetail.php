<?php
include "connect.php";
$json = $_POST['json'];
$data = json_decode($json, true);
foreach ($data as $value) {
	$billid = $value['billid'];
	$productid = $value['productid'];
	$productname = $value['productname'];
	$price = $value['price'];
	$number = $value['number'];
	$query = "INSERT INTO billdetail(detailid, billid, productid, productname, price, number) 
	VALUES (null, '$billid','$productid','$productname','$price','$number')";
	$Data = mysqli_query($conn, $query);
}
if($Data){
	echo "1";
} else{
	echo "0";
}

 ?>
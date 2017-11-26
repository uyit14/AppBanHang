<?php
	include "connect.php";
	$query = "SELECT * FROM product ORDER BY productid DESC LIMIT 6";
	$data = mysqli_query($conn, $query);
	$arrnewproduct = array();
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($arrnewproduct, new Newproduct(
			$row['productid'],
			$row['productname'],
			$row['price'],
			$row['image'],
			$row['detail'],
			$row['categoryid']));
	}
	echo json_encode($arrnewproduct);

	class Newproduct{
		function Newproduct($productid, $productname, $price, $image, $detail, $categoryid){
			$this->productid=$productid;
			$this->productname=$productname;
			$this->price=$price;
			$this->image=$image;
			$this->detail=$detail;
			$this->categoryid=$categoryid;
		}
	}
 ?>
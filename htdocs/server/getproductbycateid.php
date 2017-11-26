<?php
	include "connect.php";
	$page = $_GET['page'];
	$cateid = $_POST['categoryid'];
	$space = 5;
	$limit = ($page - 1) * $space;
	$arrproduct = array();
	$query = "SELECT * FROM product WHERE categoryid = $cateid LIMIT $limit, $space";
	$data = mysqli_query($conn, $query);
	while ($row=mysqli_fetch_assoc($data)){
		array_push($arrproduct, new ProductbyCateID(
			$row['productid'],
			$row['productname'],
			$row['price'],
			$row['image'],
			$row['detail'],
			$row['categoryid']));
	}

	echo json_encode($arrproduct);
	class ProductbyCateID{
		function ProductbyCateID($productid, $productname, $price, $image, $detail, $categoryid){
			$this->productid=$productid;
			$this->productname=$productname;
			$this->price=$price;
			$this->image = $image;
			$this->detail = $detail;
			$this->categoryid=$categoryid;
		}
	} 
 ?>
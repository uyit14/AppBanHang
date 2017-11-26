<?php
	include "connect.php";
	$query = "SELECT * FROM category";
	$data = mysqli_query($conn, $query);
	$arrcate = array();
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($arrcate, new Category(
			$row["categoryid"],
			$row['categoryname'],
			$row['image']));
	}
	echo json_encode($arrcate);
	class Category{
		function Category($categoryid, $categoryname, $image){
			$this->categoryid=$categoryid;
			$this->categoryname=$categoryname;
			$this->image=$image;
		}
	} 
 ?>
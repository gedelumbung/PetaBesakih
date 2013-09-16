<?php include("koneksi.php"); ?>
<?php
	header("content-type: application/json");
	$judul = str_replace("_", " ", $_GET['judul']);
	$kueri = "SELECT * FROM dlmbg_sitemap where judul='".$judul."'";
	$STH = $DBH->prepare($kueri);
	$STH->execute();
	 
	while($data = $STH->fetch())
	{
		$arr[] = $data;
	}

		echo '{"info":'. json_encode($arr).'}'; 
?>
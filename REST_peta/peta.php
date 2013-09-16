<?php header("content-type: application/json");
	include("koneksi.php"); 

	$kueri = "SELECT judul, SUBSTRING(keterangan,1,200) as keterangan, koordinat_lang, koordinat_lat FROM dlmbg_sitemap";
	$STH = $DBH->prepare($kueri);
	$STH->execute();
	 
	while($data = $STH->fetch())
	{
		$arr[] = $data;
	}

		echo '{"info":'. json_encode($arr).'}'; 
?>
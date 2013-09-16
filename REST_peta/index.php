<html>
<head>
<title>Peta Besakih</title>
<body>
<?php include("koneksi.php"); ?>
<?php
	$kueri = "SELECT * FROM dlmbg_sitemap";
	$STH = $DBH->prepare($kueri);
	$STH->execute();
	
	$i = 1;
	echo '<table border=1 style="border-collapse:collapse; width:100%" cellpadding=10>';
		echo '<tr>
		<td>Nomor</td>
		<td>Judul</td>
		<td>Longitude</td>
		<td>Latitude</td>
		<td colspan=2><a href="tambah.php">Tambah</a></td>
		<tr>';
	while($data = $STH->fetch())
	{
		echo '<tr>
		<td>'.$i.'</td>
		<td>'.$data['judul'].'</td>
		<td>'.$data['koordinat_lang'].'</td>
		<td>'.$data['koordinat_lat'].'</td>
		<td><a href="edit.php?id='.$data['id_peta'].'">Edit</a></td>
		<td><a href="hapus.php?id='.$data['id_peta'].'">Hapus</a></td>
		<tr>';
		$i++;
	}
	echo '</table>';
?>
</body>
</html>

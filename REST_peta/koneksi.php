<?php
try 
{
    $DBH = new PDO("mysql:host=localhost;dbname=srikmcom_peta", "srikmcom_lumbung", "lumbung32");
    $DBH->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
     
     
} catch (PDOException $e) {
    echo "Terjadi error..";
    echo $e->getMessage();
}

?>

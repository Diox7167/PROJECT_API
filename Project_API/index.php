<?php

header('Content-Type: application/json');


try {

	$pdo = new PDO('mysql:host=localhost;port=3306;dbname=test;','root','');

} catch (Exception $e) {

	$resultat["state"] = false;
	$resultat["message"] = "Echec de la connexion au serveur MYSQL";
	
}

if (!empty($_GET["nom"]))
{
	$req = $pdo->prepare("select * from student where nom like :nom");
	$req->bindParam(':nom',$_GET['nom']);
	$req->execute();
	
	$resultat["state"] = true;
    $resultat["message"] = "Voici le resulta de votre demande";

    $resu = $req->fetchAll();


	$resultat["result"]["nb"] = count($resu);
	$resultat["result"]["student"] = $resu ;
}
else
{
	//$req = $pdo->prepare("select * from student");
	//$req->execute();
	$resultat["state"] = false;
    $resultat["message"] = "Demande non correct";

}





echo json_encode($resultat);

//var_dump($resultat);


/*

$resultat = array();
$resultat["state"]   = true;
$resultat["message"] = "ok";
$resultat["reponse"]["vols"] = array("Paris-Lyon","Conakry-Dubreka");


*/
?>
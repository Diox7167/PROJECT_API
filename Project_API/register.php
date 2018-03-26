<?php
header('Content-Type: application/json');

$db = new PDO("mysql:host=localhost;dbname=project_api","root","");
$results["error"] = false;
$results["message"] = [];

/*
$_POST['pseudo'] = "Test2";
$_POST['email'] = "Test2@test.fr";
$_POST['password'] = "Test";
$_POST['password2'] = "Test";
*/

if(isset($_POST)){
	
	if(!empty($_POST['pseudo']) && !empty($_POST['email']) && !empty($_POST['password']) && !empty($_POST['password2'])) 
	{

		$pseudo = $_POST['pseudo'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$password2 = $_POST['password2'];


		//vérification du pseudo
		if (strlen($pseudo) < 2 || !preg_match("/^[a-zA-Z-0-9 _-]+$/", $pseudo) || strlen($pseudo) > 60){ 
			$results["error"] = true;
			$results['message']["pseudo"] = "Pseudo invalide";
		}
		else
		{
			//Vérifier que le pseudo n'est pas déjà pris
			$requete = $db->prepare("SELECT id FROM users WHERE pseudo = :pseudo");
			$requete->execute([':pseudo' => $pseudo]);
			$row = $requete->fetch();
			if ($row) {
				$results["error"] = true;
				$results['message']["pseudos"] = "Le pseudo est deja pris";
			}
		}

		//Vérification de l'email
		if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
			$results["error"] = true;
			$results['message']["email"] = "Email invalide";
		}
		else
		{
			//Vérifier que l'email n'éxiste pas
			$requete = $db->prepare("SELECT id FROM users WHERE email = :email");
			$requete->execute([':email' => $email]);
			$row = $requete->fetch();
			if ($row) {
				$results["error"] = true;
				$results['message']["email"] = "L'email existe deja";
			}
		}

		//Vérification des password
		if ($password !== $password2) {
			$results["error"] = true;
			$results['message']["password"] = "Les mots de passes doivent etre identiques";
		}

		if($results["error"] === false){

			$password = password_hash($password, PASSWORD_BCRYPT);
			
			//Insertion BDD
			$sql = $db->prepare("INSERT INTO users(pseudo, email, password) VALUES(:pseudo, :email, :password)");

			$sql->execute([":pseudo" => $pseudo, ":email" => $email, ":password" => $password]);

			if (!$sql) {
				$results["error"] = true;
				$results["message"] = "Erreur lors de l'inscription";
			}

		}
		

	}
	else
	{
		$results["error"] = true;
		$results["message"] = "Veuillez remplir tous les champs";
	}

	echo json_encode($results);
}

?>
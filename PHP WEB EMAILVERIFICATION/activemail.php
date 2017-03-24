<?php
$query = "INSERT INTO registered_users (user_name, first_name, last_name, password, email, gender) VALUES
('" . $_POST["userName"] . "', '" . $_POST["firstName"] . "', '" . $_POST["lastName"] . "', '" . md5($_POST["password"]) . "', '" . $_POST["userEmail"] . "', '" . $_POST["gender"] . "')";
$current_id = $db_handle->insertQuery($query);
if(!empty($current_id)) {
	$actual_link = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]"."activate.php?id=" . $current_id;
	$toEmail = $_POST["userEmail"];
	$subject = "User Registration Activation Email";
	$content = "Click this link to activate your account. <a href='" . $actual_link . "'>" . $actual_link . "</a>";
	$mailHeaders = "From: Admin\r\n";
	if(mail($toEmail, $subject, $content, $mailHeaders)) {
		$message = "You have registered and the activation mail is sent to your email. Click the activation link to activate you account.";	
	}
	unset($_POST);
}

?>

<?php
	require_once("dbcontroller.php");
	$db_handle = new DBController();
	if(!empty($_GET["id"])) {
	$query = "UPDATE registered_users set status = 'active' WHERE id='" . $_GET["id"]. "'";
	$result = $db_handle->updateQuery($query);
		if(!empty($result)) {
			$message = "Your account is activated.";
		} else {
			$message = "Problem in account activation.";
		}
	}
	?>
	
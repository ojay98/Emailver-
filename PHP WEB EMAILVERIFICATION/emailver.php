<!DOCTYPE HTML>  
<html>
<head>
<style>
.error {color: #FF0000;}
</style>
</head>
<body style="background-color:lightblue;">  





<?php

// define variables and set to empty values
$nameErr = $emailErr = $genderErr = $websiteErr = "";
$name = $email = $gender = $comment =  $website = "";

 if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $message = "Name is required , it will help make your accont much more trusted by potential buyers or sellers";
echo "<script type='text/javascript'>alert('$message');</script>";
  } else {
    $name = test_input($_POST["name"]);
    // check if name only contains letters and whitespace
    if (!preg_match("/^[a-zA-Z ]*$/",$name)) {
      $nameErr = "Only letters and white space allowed"; 
    }
	
  }
  
  $email = $_POST['email'];
  $allowed = array('my.brunel.ac.uk');
  
   if (empty($_POST["email"])) {
    $emailEr = "Email is required";
	echo "<script type='text/javascript'>alert('$emailEr');</script>";
  } else {
    $email = test_input($_POST["email"]);
  }
    // check if e-mail address is well-formed
   if (filter_var($email, FILTER_VALIDATE_EMAIL))
{
    $explodedEmail = explode('@', $email);
    $domain = array_pop($explodedEmail);

    if ( ! in_array($domain, $allowed))
    {
        
    }
	if ( in_array($domain, $allowed)){
		
	}
	
}

$explodedEmail = explode('@', $email);
    $domain = array_pop($explodedEmail);
  

if(isset($_POST['submit'])&& in_array($domain, $allowed) &&  $email = ($_POST["email"]) ){

//$message ='this is a Confirmation email to verify your account, please click the link below to verify your account, 
//However if you did not ask for this service please ignore this E-mail, Thank you.';
//$to = "email";
//$subject = 'Email confirmation';
//$name = 'name';
//$headers = 'From: DoNotReply@WINKEL.com' . "\r\n" ;
//mail($email, $subject , $message, $headers);
require 'PHPMailer/PHPMailerAutoload.php';
//$token = sha1($time.$email.$salt).dechex(time()).dechex($name);
//$link = "http://".$domain."/restorepass/?token=$token";

$mail = new PHPMailer;

//$mail->SMTPDebug = 3;                               // Enable verbose debug output

$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = ' smtp.gmail.com';  // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = 'michaelbaba89@gmail.com';                 // SMTP username
$mail->Password = 'glassglow';                           // SMTP password
$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
$mail->Port = 587;                                    // TCP port to connect to

$mail->setFrom('from@example.com', 'WINKEL');
$mail->addAddress($email, $name);     // Add a recipient
$mail->addAddress('');               // Name is optional
$mail->addReplyTo('');
$mail->addCC('');
$mail->addBCC('');

$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name
$mail->isHTML(true);                                  // Set email format to HTML2

$mail->Subject = $email ;
$mail->Body    = 'this is a Confirmation email to verify your account , please click the link below and if you did not request for this service please ignore this E-mail, Thank you .';
$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';
if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Message has been sent';
}
}
else if ( ! in_array($domain, $allowed)){
	 $domerr = "Incorrect domain name, please enter correct domain name :)";
echo "<script type='text/javascript'>alert('$domerr');</script>";
}





  }

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

<h2>WINKEL E-MAIL VERIFICATION</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">  
  Name: <input type="text" name="name">
  <span class="error">* <?php echo $nameErr;?></span>
  <br><br>
  E-mail: <input type="text" name="email">
  <span class="error">* <?php echo $emailErr;?></span>
  <br><br>
 
  <input type="submit" name="submit" value="Submit">  
</form>

<?php
echo "<h2>Your Input:</h2>";
echo $name;
echo "<br>";
echo $email;
echo "<br>";

?>

</body>
</html>
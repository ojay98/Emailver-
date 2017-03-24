<html>
   
   <head>
      <title>Sending HTML email using PHP</title>
   </head>
   
   <body>
      
      <?php
	  
 //$con = mysqli_connect("localhost", "id738172_michaelbaba89", "Mikejack0123", "	id738172_emailver");
    $con = mysqli_connect("db668545831.db.1and1.com", "dbo668545831", "Mikejack0123", "db668545831");
   
     $name = $_POST["name"];
    $email = $_POST["mail"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, " INSERT INTO WINKEL (name, mail, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $name, $email, $password);
	  
	  
	  
	  
	  
	  require_once('phpmailer/PHPMailerAutoload.php');
	 // $email = "1524316@my.brunel.ac.uk";
$mail = new PHPMailer;
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
	  
	  
$mail->Subject = $email ;
$mail->Body    = 'this is a Confirmation email to verify your account , please click the link below and if you did not request for this service please ignore this E-mail, Thank you .';
$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';	  

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Message has been sent';
}
	  
	  
	  
	  
	  // $emailFrom = "michaelbaba89@gmail.com"; // match this to the domain you are sending email from
//$email = "1524316@my.brunel.ac.uk";
//$subject = "Email Request";
//$headers = 'From:' . $emailFrom . "\r\n";
//$headers .= "Reply-To: " . $email . "\r\n";
//$headers .= "Return-path: " . $email;
//$message = "Your password is";
//mail($email, $subject, $message, $headers);




//mail($email, $subject, $message, $headers);


      ?>
      
   </body>
</html>
<?php
     // $con = mysqli_connect("localhost", "i", "", "	id738172_emailver");
     $con = mysqli_connect(", "dbo668545831", "", "db668545831");  //password and hostname deleted because git repository is public 
   
    $name = $_POST["name"];
    $email = $_POST["mail"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, " INSERT INTO WINKEL (name, mail, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $name, $email, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
   //  require_once('registertest.php');
    echo json_encode($response);
?>
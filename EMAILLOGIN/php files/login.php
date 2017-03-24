<?php
   // $con = mysqli_connect("localhost", "i", "", "	id738172_emailver");
     $con = mysqli_connect(", "dbo668545831", "", "db668545831");  //password and hostname deleted because git repository is public 
     $name = $_POST["name"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM WINKEL WHERE name = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $email, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["mail"] = $email;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>
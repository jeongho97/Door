<?php 
    $con = mysqli_connect("localhost", "root", "", "door");
    mysqli_query($con,'SET NAMES utf8');

    $hotelName = $_POST["hotelName"];
    $hotelAddress = $_POST["hotelAddress"];
    $hotelNumber = $_POST["hotelNumber"];
    $hotelCapacity = $_POST["hotelCapacity"];
    $hotelCheckIn = $_POST["hotelCheckIn"];
    $hotelCheckOut = $_POST["hotelCheckOut"];
    $hotelPrice = $_POST["hotelPrice"];
    $hotelUserID = $_POST["hotelUserID"];

    $statement = mysqli_prepare($con, "INSERT INTO room_information VALUES (?,?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssissss", $hotelName, $hotelAddress, 
    $hotelNumber, $hotelCapacity,$hotelCheckIn,$hotelCheckOut,$hotelPrice,$hotelUserID);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);
?>
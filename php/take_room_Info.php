<?php
   header("Content-Type: text/html; charset=uft8");
   $conn= new mysqli("localhost", "root", "" , "door");
   mysqli_query($conn, 'SET NAMES utf8');


   $userID = $_POST["userID"];
   $userPassword = $_POST["userPassword"];
   
   $num=0;

   $sql = "SELECT * FROM room_information";
   $result = $conn->query($sql);
   $response = array();
   if (mysqli_num_rows($result) > 0) {
   while($row = mysqli_fetch_array($result)) {
       
   $response["number"]=$num;
   $numstr=(string)$num;
   $response["hotelName$numstr"]=$row["hotelName"];
   $response["hotelAddress$numstr"]=$row["hotelAddress"];
   $response["hotelNumber$numstr"]=$row["hotelNumber"];
   $response["hotelCapacity$numstr"]=$row["hotelCapacity"];
   $response["hotelCheckIn$numstr"]=$row["hotelCheckIn"];
   $response["hotelCheckOut$numstr"]=$row["hotelCheckOut"];
   $response["hotelPrice$numstr"]=$row["hotelPrice"];
   $response["hotelUserID$numstr"]=$row["hotelUserID"];
   $num++;
   
            }

   }
   else
   {
   echo "테이블에 데이터가 없습니다.";
   }

     header('Content-Type: application/json; charset=utf8');
    echo json_encode($response, JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
   
?>
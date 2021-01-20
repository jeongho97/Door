<?php 

$conn = mysqli_connect("localhost", "root", "", "door");
$sql = "SELECT * FROM room_information";
$result = mysqli_query($conn, $sql);


    if (mysqli_num_rows($result) > 0)
    {
        $data = array(); 

        while($row = mysqli_fetch_assoc($result))
        {
            extract($row);
    
            array_push($data, 
                array('hotelName'=>$hotelName,
                'hotelAddress'=>$hotelAddress,
                'hotelNumber'=>$hotelNumber,
                'hotelCapacity'=>$hotelCapacity,
                'hotelCheckIn'=>$hotelCheckIn,
                'hotelCheckOut'=>$hotelCheckOut,
                'hotelPrice'=>$hotelPrice,
                'hotelUserID'=>$hotelUserID
            ));
        }

        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
        echo $json;
    }

?>
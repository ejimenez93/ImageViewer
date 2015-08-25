<?php
/*
	Filename: index.php
    Author: Jimenez, Edison
    Description: Image to JSON Script developed for Crossway Mobile Developer Position coding challenge
    for Android Image Viewer App
    
    Looks within the current directory for files (images) and converts the names into an array
    then converts the array into a JSON object to use for our Android app
 */

	
// Set the domain of the website
$sDomain = 'http://users.wpi.edu/~ejimenez/crossway/';
$aFiles[] = array();

// Get a listing of all the files in this directory
// and put them into an array excluding the '.', '..', and 'index' references
$aImages = array_diff(scandir("."), array('.', '..', 'index.php'));

// Generate an associative array
foreach ($aImages as $index => $file) {
  $aFiles[] = array('title' => $file, 'link' => $sDomain.$file);
}

// Pop the empty element at index 0
array_shift($aFiles);


// Convert our associative array into JSON and output
// to browser
header('Content-Type: application/json');
echo json_encode(array('items'=>$aFiles));
	
?>
<?php
	include_once('script.php');
	
	//print_r($_REQUEST);
	
	$option=$_REQUEST['option'];
	
	$resp=array();
	try{
		switch($option)
		{
			case "getallrec":
				$sql=$_REQUEST['sql'];
				$allrec=get_all($sql);
				$resp['result']=$allrec;
				break;
			case "getbyid":
				$sql=$_REQUEST['sql'];
				$allrec=get_by_id($sql);
				
				if(!isset($allrec)){
					throw new Exception("Unknown contact no...",111);
				}
				
				$resp['result']=json_encode($allrec);
				break;	
			case "addrec":
				$rs=add_rec($_REQUEST['table'],$_REQUEST,"table,option");
				if($rs==true){
					$resp['result']='success';
				}else{
					throw new Exception("Insertion failed...",222);	
				}							
				break;			
		}
		$resp['errcode']=0;
	}catch(Exception $ex){
		$resp['errcode']=$ex->getCode();
		$resp['errmsg']=$ex->getMessage();
	}
	
	echo json_encode($resp);
?>
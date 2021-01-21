<?php
	ob_start();
	session_start();
	
	function connect(){
		//$con=mysqli_connect('localhost','root','root','chatapps');
		$con=mysqli_connect('mysql.hostinger.in','u829512096_chat','tb9830289049*','u829512096_chdb');
		return $con;
	}
	
	function msg_box($msg,$type='success'){
		echo '
			<div class="alert alert-dismissible alert-'.$type.'">
			  '.$msg.'
			</div><strong></strong>
		';	
	}
		
	function get_max_val($table,$colname){
		$con=connect();
		$sql="select max($colname) maxval from $table ";		
		$rs=mysqli_query($con,$sql);
		$row=mysqli_fetch_assoc($rs);
		
		return $row['maxval'];
	}
	
	function update_user($colname,$value,$id){
		$sql="update user set $colname='$value' where userid='$id'";
		
		$con=connect();		
		return $rs=mysqli_query($con,$sql) or die(mysqli_error($con)); 		
	}

	function get_all($sql)
	{
		$con=connect();		
		$rs=mysqli_query($con,$sql) or die(mysqli_error($con));
		$all=array();
		
		while(($row=mysqli_fetch_assoc($rs))!=NULL)
		{
			$all[]=$row;
		}
		return $all;
	}
	
	function get_by_id($sql)
	{
		$con=connect();
		$rs=mysqli_query($con,$sql);
		
		if(mysqli_num_rows($rs)<=0)
		{
			return NULL;
		}
		
		$row=mysqli_fetch_assoc($rs);		
		return $row;
	}
	
	function login($userid,$password){
		if($userid=='admin' and $password=='admin'){
			$_SESSION['adminid']='admin';
			header('location:home.php');
		}else{
			msg_box('Invalid ID/Password...','danger');
		}
	}
	
	function login_check(){
		if(!isset($_SESSION['adminid'])){
			header('location:index.php');
		}
	}
	
	function rec_exists($table,$condition=NULL)
	{
		$con=connect();
		$sql="select * from $table ";
		if(isset($condition)){
			$sql.="where $condition";
		}
		$rs=mysqli_query($con,$sql);
		
		$count=mysqli_num_rows($rs);
		
		return $count>0;
	}
	
	function add_rec($table,$data,$remove_keys=NULL)
	{
		$con=connect();
		
		if($remove_keys!=NULL)
		{
			foreach(explode(',',$remove_keys) as $k)
			{
				unset($data[$k]);
			}
		}
		$vals=array_values($data);
		for($i=0;$i<count($vals);$i++)
		{
			$vals[$i]="'{$vals[$i]}'";
		}
		
		$cols=implode(',',array_keys($data));
		$vals=implode(',',$vals);
		$sql="insert into $table($cols) values($vals)";
		
		//echo $sql;
		
		$rs=mysqli_query($con,$sql) or  die(mysqli_error($con));
		
		return $rs;
	}
	
	function uploadfile($fileinfo,$uploadfolder,$extra=NULL)
	{
		if($extra==NULL)
		{
			$fname=time().$fileinfo['name'];
		}
		else{
			$fname=$extra.substr($fileinfo['name'],-4);
		}
		move_uploaded_file($fileinfo['tmp_name'],$uploadfolder.'/'.$fname);
		return $fname;
	}
?>
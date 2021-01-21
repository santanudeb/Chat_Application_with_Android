<?php include_once('header.php'); include_once('script.php'); login_check(); ?>
  <!-- page content -->
  <div class="right_col" role="main">
    <div class="">

      <div class="page-title">
        <div class="title_left">
          <h3>
                User Details
                <small>
                    
                </small>
            </h3>
        </div>

        
      </div>
      <div class="clearfix"></div>


      <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
          <table class="table table-responsive table-bordered">
          	<tr>
            	<th>ID</th>
            	<th>Name</th>
                <th>Contact No</th>
            </tr>
            <?php
				if(isset($_GET['blockuser'])){
					update_user('block',1,$_GET['blockuser']);
				}else if(isset($_GET['unblockuser'])){
					update_user('block',0,$_GET['unblockuser']);
				}else if(isset($_GET['deluser'])){
					update_user('deleted',1,$_GET['deluser']);
				}
			
				$users=get_all("select * from user where deleted=0");
				
				foreach($users as $u){
					echo '
					<tr>
						<td>'.$u['userid'].'</td>
						<td>'.$u['name'].'</td>
						<td>'.$u['contact'].'</td>
					';
					if($u['block']==0){
						echo '<td align="center"><a href="?blockuser='.$u['userid'].'" class="btn btn-warning">Block</a></td>';
					}else{
						echo '<td align="center"><a href="?unblockuser='.$u['userid'].'" class="btn btn-success">Unblock</a></td>';
					}
					echo '	
						<td align="center"><a href="?deluser='.$u['userid'].'" class="btn btn-danger">Delete</a></td>
					</tr>
					';
				}
			?>
          </table>
        </div>
      </div>

    </div>

    <!-- footer content -->
    <footer>
      <div class="copyright-info">
        <p class="pull-right">Chit Chat - Android Application Admin by  <a href="https://colorlib.com">TIRTHANKAR</a>
        </p>
      </div>
      <div class="clearfix"></div>
    </footer>
    <!-- /footer content -->

  </div>
  <!-- /page content -->
<?php include_once('footer.php') ?>
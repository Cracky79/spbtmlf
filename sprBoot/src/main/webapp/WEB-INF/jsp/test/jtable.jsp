<%--
	@cracky  25.06.10
	/test/jtable.do     tiles3 layout sample
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script>
	$(document).ready(function(){
		jTable.bind();	/** dataTable */
	});
	/** tt event  테이블아이디 , jsonRow */
	var trnim = function ( tableId , rowJson) {
		alert(tableId + ":" + rowJson.crm_no)
	}
	
	/** td event */
	var lolnim = function (idx , value , rowJson) {
		alert(idx + ":" + value + ":" + rowJson.crm_no)
	}
	/** callback */
	var edt_callback = function (resp) {
		alert('callback!')
	}
	/** custom renderer function */ 
	var renderFunc = function (tdIdx , value , jsonRow) {
		return "["+value+"]";
	}
	/** 등록 레이어 호출 */
	var insPage = function () {
		gfn_openInsertLayer('jtb1' , {songbagang : "sec"});
	}
	
	var mySubm = function() {
		alert();
		$('#frm').attr('action','fileUpload.do').submit();
		//$('#frm').action('attr','fileUpload.do').submit();
		
	}
</script>
</head>
<body>
<div class="container">
	<table  id='jtb1' class="table table-striped table-bordered table-hover jtable"
			url="jtableAjax.do" 
			event='s'
			edit-url='true'
			theme-color='#848894'
			theme-text-color='#fff' >
		<thead>
			<tr>
				<th data-id='sn' 
					data-align='center' 
					width='8%'>NO</th>
				<th data-id='bbs_se' 
					data-align='center' 
					data-edit='true'
					data-require='true' 
					width='10%' 
					data-event='lolnim'>이름</th>
				<th data-id='sj' 
					data-edit='true' 
					data-align='center'
					width='40%'>제목</th>
				<th data-id='rdcnt' 
					data-edit='false' 
					width='10%'>조회수</th>
				<th data-id='f_regist_dt' 
				    data-align='center' 
				    width='13%'>등록일자</th>
				<th data-id='regist_id' 
					data-align="center" 
					data-edit='false' >등록자</th>
			</tr>
		</thead>
	</table>
</div>

<form name = 'frm' id = 'frm' method = 'post' enctype="multipart/form-data">
	<input type = 'text' id = 'ello' name = 'ello'/>
	<input type = 'file' id = 'sx' name = 'sx'/>
	<a href= 'javascript:mySubm();'>aaa</a>
</form>
</body>
</html>
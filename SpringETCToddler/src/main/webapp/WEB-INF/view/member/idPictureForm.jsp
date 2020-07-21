<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
td {f on t-family:"돋움";
	font-size: 9pt;
	line-height: 16px;
	color: #818181;
	letter-spacing: 0px;
}

td a {
	font-family: "돋움";
	font-size: 9pt;
	line-height: 16px;
	color: #818181;
	letter-spacing: 0px;
	text-decoration: none;
}

td a:hover {
	font-family: "돋움";
	font-size: 9pt;
	line-height: 16px;
	color: #818181;
	letter-spacing: 0px;
	text-decoration: underline;
}

#hiddenFileInput{
	position : relative;
	width : 80px; 
	height:30px; 
	overflow:hidden; 
	cursor:pointer; 
	background-image:url('${pageContext.request.contextPath}/image/bt_search.gif');
	background-repeat: no-repeat;
}
#idPic{
	width:80px; 
	height:30px; 
	filter:alpha(opacity=0); 
	opacity:0; 
	-moz-opacity:0; 
	cursor:pointer;
	vertical-align: middle;
}
.bar {
    height: 18px;
    background: green;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<!-- http://malsup.com/jquery/form/#download 에서 jquery.form.js 다운로드 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript">
var idPicName;
var idPic_seq;
function idPicInput(upfile){
	var filePath = upfile.split("\\");
	
	// 파일명 취득
    file_name = filePath[filePath.length-1]; 
    $('#fileName').val(file_name);
	
  	// 첨부파일 확장자 취득
    ext_name  = file_name.substring(file_name.length-3, file_name.length);
    ext = ext_name.toLowerCase(); 
    // 확장자 검사
    if(ext == 'jpg' || ext == 'gif' || ext == 'png'){
    	$('#idPicForm').ajaxForm({
            dataType : 'json', 
			success : function(data) {
				idPicName = data.fileName.substring(data.fileName.indexOf(':')+1);
				idPic_seq = data.file_seq;
				var htmlStr='<div><img src="${pageContext.request.contextPath }/image/bt_submit.gif" alt="적용" class="btn" title="사진 적용"' + 
					'style="cursor: pointer;" onclick="idPicConfirm();"/></div><br/>' + 
					'<img src="${pageContext.request.contextPath}/upload/'+idPicName+'"' + 
					'style="width:235px; height:315px;"/>';
				$('#viewTable').html(htmlStr);
	        }
		});
    	$('#idPicForm').submit();
    } else {
        $('#fileName').val('');
        $('#fileName').focus();
        alert('사용가능한 이미지파일이 아닙니다.');
        return;
    }   
}
function idPicConfirm(){
	opener.document.getElementById("viewTable").innerHTML = 
		'<input type="image" src="${pageContext.request.contextPath}/upload/'+idPicName+'"' + 
			'style="width:130px; height:150px;" name="mem_idPic"/>';		
	opener.document.getElementById("file_seq").value = idPic_seq;
	self.close();
}
</script>
</head>
<body>
	<table width="354" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" style="text-align: center;"><font color="red" size="5">증명 사진 업로드</font></td>
		</tr>
		<tr>
			<td><img src="../image/open_table01.gif" width="354" height="10"></td>
		</tr>
		<tr>
			<td height="10" background="../image/open_table02.gif" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td height="300" align="center" valign="top"
				background="../image/open_table02.gif">
				<table width="300" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="25">
							<div align="center">등록하려는 사진(gif | jpg | jpeg | png)를 선택해주세요.</div>
						</td>
					</tr>
					<tr>
						<td height="38" background="../image/open_tt.gif" align="center">
							<!-- input type="file"에서 value 속성을 제어할수 없으므로 '찾아보기' value를 버튼 이미지로 처리. 
								span과 input type="file" 폭/높이 동기화 처리 및 실제 type="file" 투명도 처리
							-->
							<form id="idPicForm" action="${pageContext.request.contextPath }/member/idPicFileupload.do" method="post" enctype="multipart/form-data">
								<input type="text" id="fileName" name="fileName"/>
								<span id="hiddenFileInput">
	    							<input type="file" id="idPic" name="idPic" onchange="idPicInput(this.value);"/>
								</span>
							</form>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div style="overflow: auto; white-space: nowrap; overflow-X: hidden; height: 200px;" id="viewTable"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><img src="../image/open_table03.gif" width="354" height="10"></td>
		</tr>
	</table>
</body>
</html>
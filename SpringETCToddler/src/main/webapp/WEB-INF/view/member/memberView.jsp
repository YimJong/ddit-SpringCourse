<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<style>
.fieldName {text-align: center; background-color: #f4f4f4;}
.tLine {background-color: #d2d2d2; height: 1px;}
.btnGroup { text-align: right; }
td {text-align: left; }
</style>
<script type="text/javascript">
function zipSearch(){
	var url = "${pageContext.request.contextPath}/popup/zipcodeSearch.jsp";
	var options = "toolbar=no,location=no,directories=no,status=no," +
	          "menubar=no,scrollbars=no,resizable=no,copyhistory=no," +
	          "width=375,height=400";
	window.open(url, 'zipSearch',options);
}
function idPicBtnClick(){
	var url = "${pageContext.request.contextPath}/member/idPictureForm.do";
	var options = "toolbar=no,location=no,directories=no,status=no," +
	          "menubar=no,scrollbars=no,resizable=no,copyhistory=no," +
	          "width=375,height=400";
	window.open(url, '증명사진업로드',options);
}
function selectOptionSelected(){
	var mem_hometelOpts = document.memberForm.mem_hometel1;
	selectBox(mem_hometelOpts, '${fn:substringBefore(memberInfo.mem_hometel,"-")}');

	var mem_comtelOpts = document.memberForm.mem_comtel1;
	selectBox(mem_comtelOpts, '${fn:substringBefore(memberInfo.mem_comtel,"-")}');
	
	var mem_hpOpts = document.memberForm.mem_hp1;
	selectBox(mem_hpOpts, '${fn:substringBefore(memberInfo.mem_hp,"-")}');
	
	var mem_mailOpts = document.memberForm.mem_mail2;
	selectBox(mem_mailOpts, '${fn:substringAfter(memberInfo.mem_mail,"@")}');
}
function selectBox(sel, val){
    var selOptions = sel.options;
    var size = selOptions.length;
    for(var i = 0; i<size; i++){
		if(selOptions[i].value == val){
	        selOptions[i].selected = true;
	        break;
		}
    }
}
// 타일즈 적용시 put-attribute로 정의된 jsp내에서 body onload 이벤트가 적용되지 않을때
// 동적으로 이벤트를 등록해 활용함.
window.onload=function(){
	var db = document.body;
	db.addEventListener("onload",selectOptionSelected());
}

function updateDataCheck(frm){
	var regMail = /\w+@[a-z]+(\.[a-z]+){1,2}/; // test@test.com or test@test.co.kr 
	
	if(frm.mem_zip1.value == '' || frm.mem_zip2.value == ''){
		alert('우편번호를 입력해주세요.');
		frm.mem_zip1.value = '';
		frm.mem_zip2.value = '';
		frm.mem_zip1.focus();
		return false;
	}else{
		frm.mem_zip.value = frm.mem_zip1.value+'-'+frm.mem_zip2.value;
	}
	if(frm.mem_add1.value == '' || frm.mem_add2.value ==''){
		alert('주소를 입력해주세요.');
		frm.mem_add1.value = '';
		frm.mem_add2.value = '';
		frm.mem_add1.focus();
		return false;
	}
	if(frm.mem_hp2.value == '' || frm.mem_hp3.value == ''){
		alert('휴대전화번호를 바르게 입력해주세요.');
		frm.mem_hp2.value = '';
		frm.mem_hp3.value = '';
		frm.mem_hp2.focus();
		return false;
	}else{
		frm.mem_hp.value = frm.mem_hp1.value+'-'+
		                 frm.mem_hp2.value+'-'+
		                 frm.mem_hp3.value;
	}
	if(frm.mem_hometel2.value == '' || frm.mem_hometel2.value == ''){
		alert('집 전화번호를 입력해주세요.');
		frm.mem_hometel2.value = '';
		frm.mem_hometel3.value = '';
		frm.mem_hometel1.focus();
		return false;
	}else{
		frm.mem_hometel.value = frm.mem_hometel1.value+'-'+
		                 frm.mem_hometel2.value+'-'+
		                 frm.mem_hometel3.value;
	}
	
	if(frm.mem_comtel2.value == '' || frm.mem_comtel2.value == ''){
		alert('회사 전화번호를 입력해주세요.');
		frm.mem_comtel2.value = '';
		frm.mem_comtel3.value = '';
		frm.mem_comtel1.focus();
		return false;
	}else{
		frm.mem_comtel.value = frm.mem_comtel1.value+'-'+
		                 frm.mem_comtel2.value+'-'+
		                 frm.mem_comtel3.value;
	}
	
	
	if(frm.mem_mail1.value == ''){
		alert('이메일을 바르게 입력해주세요.');
		frm.mem_mail1.value = '';
		frm.mem_mail1.focus();
		return false;
	}else{
		frm.mem_mail.value = frm.mem_mail1.value+'@'+frm.mem_mail2.value;
		if(regMail.test(frm.mem_mail.value) == false){
			alert('이메일을 올바르게 입력해주세요.');
			frm.mem_mail1.value = '';
			frm.mem_mail1.focus();
			return false;
		}
	}
	return true;	
}
</script>
<body>
<form name="memberForm" method="post" onsubmit="return updateDataCheck(this);" 
      action="${pageContext.request.contextPath }/member/memberUpdate.do">
<input type="hidden" name="file_seq" id="file_seq">
<input type="hidden" name="mem_id" value="${memberInfo.mem_id}">	
<table width="600" border="0" cellpadding="0" cellspacing="0">
	<tr><td class="tLine" colspan="2"></td></tr>
	<tr>
		<td rowspan="13" class="pic" colspan="2" style="vertical-align: bottom; width: 150px; text-align: center;">
			<div style="overflow: auto; white-space: nowrap; overflow-X: hidden; height: 100%; width: 100%;" id="viewTable">
				<img src="${pageContext.request.contextPath }/upload/${memberInfo.idPicture.file_name}" alt="증명사진"
					style="width:130px; height:150px;"/>
			</div>
			<img src="${pageContext.request.contextPath }/image/btn_pic.gif" alt="사진올리기" class="btn" title="인적사항에 올릴 증명사진을 검색합니다." 
				style="cursor: pointer;" onclick="idPicBtnClick();"/><br/>
			<div style="width: 100%" align="center">
				size : 235x315 이하
			</div>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	<tr>
		<td class="fieldName" width="100px" height="25">성 명</td>
		<td>
			<input type="text" name="mem_name" value="${memberInfo.mem_name }" disabled="disabled"/>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	<tr>
		<td class="fieldName" width="100px" height="25">주민등록번호</td>
		<td>
			<input type="text" name="mem_regno1" size="6" value="${memberInfo.mem_regno1}" disabled="disabled"/>
  			<input type="text" name="mem_regno2" size="7" value="${memberInfo.mem_regno2}" disabled="disabled"/>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">생년월일</td>
		<td>
				<input type="hidden" name="mem_bir" />
				<input type="text" name="mem_bir1" size="4" value="${fn:substringBefore(memberInfo.mem_bir,'-') }" disabled="disabled"/>년
				<input type="text" name="mem_bir2" size="2" value="${fn:substringBefore(fn:substringAfter(memberInfo.mem_bir,'-'),'-') }" disabled="disabled"/>월
				<input type="text" name="mem_bir3" size="2" value="${fn:substringBefore(fn:substringAfter(fn:substringAfter(memberInfo.mem_bir,'-'),'-'),' ') }" disabled="disabled"/>일
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">아이디</td>
		<td>
			<input type="text" name="mem_id" value="${memberInfo.mem_id }" disabled="disabled">
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">비밀번호</td>
		<td>
			<input type="password" name="mem_pass" value="${memberInfo.mem_pass }" disabled="disabled"/> 8 ~ 20 자리 영문자 및 숫자 사용
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
</table>
<table width="600" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
	<tr>
		<td class="fieldName" width="100px" height="25">전화번호</td>
		<td>
			<div>
			<input type="hidden" name="mem_hometel"/>
			<select name="mem_hometel1">
				<option value="02">02</option>
				<option value="031">031</option>
				<option value="032">032</option>								        	
				<option value="033">033</option>				        	
				<option value="041">041</option>
				<option value="042">042</option>				        	
				<option value="043">043</option>				        	
				<option value="051">051</option>				        	
				<option value="052">052</option>
				<option value="053">053</option>				        					        	
				<option value="061">061</option>
				<option value="062">062</option>
				<option value="063">063</option>				        					        					        	
				<option value="064">064</option>				        					        					        	
				<option value="070">070</option>				        					        					        	
			</select>	- 	
			<input type="text" name="mem_hometel2" size="4" value="${fn:substringBefore(fn:substringAfter(memberInfo.mem_hometel,'-'),'-') }" /> - 
			<input type="text" name="mem_hometel3" size="4" value="${fn:substringAfter(fn:substringAfter(memberInfo.mem_hometel,'-'),'-') }" />
			</div>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">회사 전화번호</td>
		<td>
			<div>
			<input type="hidden" name="mem_comtel"/>
			<select name="mem_comtel1">
				<option value="02">02</option>
				<option value="031">031</option>
				<option value="032">032</option>								        	
				<option value="033">033</option>				        	
				<option value="041">041</option>
				<option value="042">042</option>				        	
				<option value="043">043</option>				        	
				<option value="051">051</option>				        	
				<option value="052">052</option>
				<option value="053">053</option>				        					        	
				<option value="061">061</option>
				<option value="062">062</option>
				<option value="063">063</option>				        					        					        	
				<option value="064">064</option>				        					        					        	
				<option value="070">070</option>				        					        					        	
			</select>	- 	
			<input type="text" name="mem_comtel2" size="4" value="${fn:substringBefore(fn:substringAfter(memberInfo.mem_comtel,'-'),'-') }" /> - 
			<input type="text" name="mem_comtel3" size="4" value="${fn:substringAfter(fn:substringAfter(memberInfo.mem_comtel,'-'),'-') }" />
			</div>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">핸드폰</td>
		<td>
			<input type="hidden" name="mem_hp"/>
			<select name="mem_hp1">
				<option value="010">010</option>
				<option value="016">016</option>				        	
				<option value="017">017</option>				        	
				<option value="019">019</option>				        	
			</select>	-
			<input type="text" name="mem_hp2" size="4" value="${fn:substringBefore(fn:substringAfter(memberInfo.mem_hp,'-'),'-') }" /> - 
			<input type="text" name="mem_hp3" size="4" value="${fn:substringAfter(fn:substringAfter(memberInfo.mem_hp,'-'),'-') }" />
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr>
		<td class="fieldName" width="100px" height="25">이메일</td>
		<td>
			<input type="hidden" name="mem_mail" />
			<input type="text" name="mem_mail1" value="${fn:substringBefore(memberInfo.mem_mail,'@')}" /> @
			<select name="mem_mail2">
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="gmail.com">gmail.com</option>				
			</select>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
		
	<tr>
		<td class="fieldName" width="100px" height="25">주소</td>
		<td>
			<input type="hidden" name="mem_zip" />
			<input type="text" name="mem_zip1" id="mem_zip1" size="3" value="${fn:substringBefore(memberInfo.mem_zip,'-')}" /> - 
			<input type="text" name="mem_zip2" id="mem_zip2" size="3" value="${fn:substringAfter(memberInfo.mem_zip,'-')}" />
			<input type="button" value="우편번호검색" onclick="zipSearch();"/><br>
			<input type="text" name="mem_add1" id="mem_add1" value="${memberInfo.mem_add1 }" style="width: 200px;"/>
			<input type="text" name="mem_add2" id="mem_add2" value="${memberInfo.mem_add2 }" style="width: 200px;"/>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	<tr>
		<td class="fieldName" width="100px" height="25">직 업</td>
		<td>
			<input type="text" name="mem_job" value="${memberInfo.mem_job }"/>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	<tr>
		<td class="fieldName" width="100px" height="25">취 미</td>
		<td>
			<input type="text" name="mem_like" value="${memberInfo.mem_like }"/>
		</td>
	</tr>
	<tr><td class="tLine" colspan="2"></td></tr>
	
	<tr><td colspan="2" height="20"></td></tr>
	
	<tr>
		<td class="btnGroup" colspan="2" >
			<input type="submit" value="수정"/>
			<input type="reset" value="취소"/>
			<input type="button" value="삭제" onclick="javascript:location.href='${pageContext.request.contextPath}/member/memberDelete.do?mem_id=${memberInfo.mem_id }'"/>
			<input type="button" value="목록" onclick="javascript:location.href='${pageContext.request.contextPath}/member/memberList.do'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>









<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function idCheck(){
	if($('input[name=mem_id]').val() == ''){
		alert('아이디를 입력해주세요');
		$('input[name=mem_id]').focus();
		return;
	}
	$.ajax({
		url:'${pageContext.request.contextPath}/member/idCheck.do',
		dataType:'json',
		data:{ mem_id : $('input[name=mem_id]').val() },
		error:function(result){
				alert(result);
			},
		success:function(result){
				if(result.flag == 'true'){
					alert('사용할수있는 아이디입니다.');
					$('input[name=mem_pass]').focus();
					return;
				}else{
					alert('사용할수없는 아이디입니다.');
					$('input[name=mem_id]').val('');
				}
			}
	});
}

function zipSearch(){
	// 스크린의 가로, 세로 사이즈 취득
	var screenWidth=screen.availWidth;
	var screenHeight=screen.availHeight;

	// 데스크탑에 띄운 브라우저 창의 전체 크기(IE제외)
// 	var windowWidth = window.outerWidth;
// 	var windowHeight = window.outerHeight;
	
	// 데스크탑에 띄운 브라우저 창의 위치(IE제외)
// 	var window = window.screenX;
// 	var window = window.screenY;

	// HTML 문서가 표시되는 화상 표시 영역인 뷰포트(viewport)의 크기
	// 이것은 브라우저 창 크기에서 메뉴바, 툴바, 스크롤바 등의 크기를 뺀 나머지
	var viewportWidth = window.innerWidth;
	var viewportHeight = window.innerHeight;

	// HTML 문서가 표시되는 화상 표시 영역인 뷰포트(viewport)의 크기 
	var clientWidth = document.body.clientWidth;
	var clientHeight = document.body.clientHeight;

	// 다음의 값들은 수평, 수직 스크롤바의 위치를 나타내며, 문서 좌표와 창
	// 좌표를 상호 변환하는데 사용된다. 이 값들은 화면의 좌측 상단
	// 모서리에 문서의 어느 부분이 위치하는지 나타낸다.
	var horizontalScroll = window.pageXOffset;
	var verticalScroll = window.pageYOffset;

	
	// document.all['tag id속성명']==document.all.tag id속성명==document.getElementsById('tag id속성명')
	// 삼항 연산자
	var winwidth=document.all?document.body.clientWidth:window.innerWidth;
	var winHeight=document.all?document.body.clientHeight:window.innerHeight;

	var left = winwidth/2-(375/2);
	var top = winHeight/2;

	var url = "${pageContext.request.contextPath}/zipcode/zipcodeSearchForm.do";
	var options = "toolbar=no,location=no,directories=no,status=no,left=" + left +",top=" + top + "," +
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
</script>
</head>
<style>
.fieldName {text-align: center; background-color: #f4f4f4;}
.tLine {background-color: #d2d2d2; height: 1px;}
.btnGroup { text-align: right; }
td {text-align: left; }
</style>
<body>
<jsp:include page="${pageContext.request.contextPath }/inc/header.jsp"></jsp:include>
<div class="wrap">	
	<table width="1000" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td width="180"><jsp:include page="/WEB-INF/view/member/memberMenu.jsp"></jsp:include></td>
			<td width="30">&nbsp;</td>
			<td width="790">
				<div id="container">
					<div class="title">회원가입</div>
					<form name="memberForm" method="post"
						action="${pageContext.request.contextPath}/formTag/memberView_formTag.do">
						<input type="hidden" name="file_seq" id="file_seq">	
						<table width="600" border="0" cellpadding="0" cellspacing="0">
							<tr><td class="tLine" colspan="2"></td></tr>
							<tr>
								<td rowspan="13" class="pic" colspan="2" style="vertical-align: bottom; width: 150px; text-align: center;">
									<div style="overflow: auto; white-space: nowrap; overflow-X: hidden; height: 100%; width: 100%;" id="viewTable"></div>
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
									<input type="text" name="mem_name" value=""/>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							<tr>
								<td class="fieldName" width="100px" height="25">주민등록번호</td>
								<td>
									<input type="text" name="mem_regno1" size="6" value=""/>
						  			<input type="text" name="mem_regno2" size="7" value=""/>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr>
								<td class="fieldName" width="100px" height="25">생년월일</td>
								<td>
										<input type="hidden" name="mem_bir" />
										<input type="text" name="mem_bir1" size="4" value="" />년
										<input type="text" name="mem_bir2" size="2" value="" />월
										<input type="text" name="mem_bir3" size="2" value="" />일
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr>
								<td class="fieldName" width="100px" height="25">아이디</td>
								<td>
									<input type="text" name="mem_id" value="">&nbsp;&nbsp;<b><a href="javascript:idCheck();">[ID 중복검사]</a></b>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr>
								<td class="fieldName" width="100px" height="25">비밀번호</td>
								<td>
									<input type="text" name="mem_pass" value="" /> 8 ~ 20 자리 영문자 및 숫자 사용
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr>
								<td class="fieldName" width="100px" height="25">비밀번호확인</td>
								<td>
									<input type="text" name="mem_pass_confirm" value="" /> 8 ~ 20 자리 영문자 및 숫자 사용
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
						</table>
						<table width="600" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
							<tr>
								<td class="fieldName" width="100px" height="25">집 전화번호</td>
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
									<input type="text" name="mem_hometel2" size="4" value="" /> - 
									<input type="text" name="mem_hometel3" size="4" value="" />
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
									<input type="text" name="mem_comtel2" size="4" value="" /> - 
									<input type="text" name="mem_comtel3" size="4" value="" />
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
									<input type="text" name="mem_hp2" size="4" value="" /> - 
									<input type="text" name="mem_hp3" size="4" value="" />
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr>
								<td class="fieldName" width="100px" height="25">이메일</td>
								<td>
									<input type="hidden" name="mem_mail" />
									<input type="text" name="mem_mail1" value="" /> @
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
									<input type="text" name="mem_zip1" id="mem_zip1" size="3" value="" /> - 
									<input type="text" name="mem_zip2" id="mem_zip2" size="3" value="" />
									<input type="button" value="우편번호검색" onclick="zipSearch();"/><br>
									<input type="text" name="mem_add1" id="mem_add1" value="" style="width: 200px;"/>
									<input type="text" name="mem_add2" id="mem_add2" value="" style="width: 200px;"/>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							<tr>
								<td class="fieldName" width="100px" height="25">직 업</td>
								<td>
									<input type="text" name="mem_job" value=""/>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							<tr>
								<td class="fieldName" width="100px" height="25">취 미</td>
								<td>
									<input type="text" name="mem_like" value=""/>
								</td>
							</tr>
							<tr><td class="tLine" colspan="2"></td></tr>
							
							<tr><td colspan="2" height="20"></td></tr>
							
							<tr>
								<td class="btnGroup" colspan="2" >
									<input type="submit" value="가입하기"/>
									<input type="reset" value="취소"/>
									<input type="button" value="목록" onclick="javascript:location.href='${pageContext.request.contextPath}/member/memberList.do'"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
</div>						
</body>
</html>









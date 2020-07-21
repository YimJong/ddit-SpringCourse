<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원리스트</title>
<script type="text/javascript" 
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" 
    src="${pageContext.request.contextPath }/js/commons.js"></script>
<script type="text/javascript">
$(function(){
	$('select[name=search_sido]').append(getSido());
	$('select[name=search_sido]').change(function(){
		var gugunArray =
			getGugun($(this).find('option:selected').val());
		$('select[name=search_gugun]').empty().append('<option selected="selected">선택하세요</option>');
		for(var i=1; i<gugunArray.length; i++){
			$('select[name=search_gugun]').append('<option value="'+ gugunArray[i] + '">' 
					                               + gugunArray[i] +
					                               '</option>');
		}
	});
	$('select[name=search_hp1]').append(settingDisernNum());
	$('select[name=search_year]').append(getYearsOptions(new Date()));
	$('select[name=search_month]').append(getMonthOptions());
	$('select[name=search_month]').change(function(){
		var year = $('select[name=search_year] option:selected').val();
		var month = $('select[name=search_month] option:selected').val();
		var lastDay = getLastDate(new Date(year, month-1, 1));
		$('select[name=search_day]').empty().append('<option selected="selected">선택하세요</option>');
		for(var i=1; i<lastDay; i++){
			if(i<10){
				$('select[name=search_day]').append('<option value="0' + i + '">0' +
						                            i + 
						                            '</option>');
			}else{
				$('select[name=search_day]').append('<option value="' + i + '">' +
							                        i + 
							                        '</option>');
			}
		}
	});

	$('input[value=회원가입]').click(function(){
		$(location).attr('href', '${pageContext.request.contextPath}/member/memberForm.do');
	});
	
	$('input[value=회원가입_formTag]').click(function(){
		$(location).attr('href', '${pageContext.request.contextPath}/formTag/memberForm_formTag.do');
	});
	
	$('input[value=검색하기]').click(function(){
		search();
	});
	
	// 동적으로 추가되는 태그 이벤트 설정
	// table class='memberList' 하위 tr 태그들에 출력.
	$('.memberList').on('click', 'tr', function(){
// 		alert('text : ' + $(this).find('td:eq(2)').text() + ' / mem_id : ' + $(this).find('td:eq(1)').text());
		$(location).attr('href', '${pageContext.request.contextPath}/member/memberView.do?mem_id=' + 
				$(this).find('td:eq(1)').text());
	});
});

function search(){
	// form 내 모든 필드의 json data 변경
	var params = {};
	$('form[name=search_form]').serializeArray().map(function(element){ params[element.name] = element.value;}); 
	
// 	params = {search_base : $('select[name=search_base] option:selected').val(),
// 			  search_base_keyword : $('input[name=search_base_keyword]').val(),
// 			  search_sido : $('select[name=search_sido] option:selected').val(),
// 			  search_gugun : $('select[name=search_gugun] option:selected').val(),
// 			  search_hp1 : $('select[name=search_hp1] option:selected').val(),
// 			  search_hp2 : $('input[name=search_hp2]').val(),
// 			  search_hp3 : $('input[name=search_hp3]').val(),
// 			  search_year : $('select[name=search_year] option:selected').val(),
// 			  search_month : $('select[name=search_month] option:selected').val(),
// 			  search_day : $('select[name=search_day] option:selected').val(),
// 			  search_count : $('select[name=search_count] option:selected').val()
// 	         };
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/member/memberList.do",
		data:params,
		error:function(result){
				alert(result);
			},
		success:function(result){
				var htmls;
				for(var i=0; i<result.length; i++){
					htmls +='<tr>'+
					            '<td align="center"><input type="checkbox" name="chk'+ i +'"/></td>' +
					            '<td align="center">' + result[i].mem_id+ '</td>' +
					            '<td align="center">' + result[i].mem_name+ '</td>' +
					            '<td align="center">' + result[i].mem_bir.split(' ')[0]+ '</td>' +
					            '<td align="center">' + result[i].mem_mail+ '</td>' +
					        '</tr>';
				}
				$('.memberList').empty().append(htmls);
			}
	});
}
</script>    
</head>
<body>
<jsp:include page="${pageContext.request.contextPath }/inc/header.jsp"></jsp:include>
<div class="wrap">	
	<table width="1000" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td width="180"><jsp:include page="memberMenu.jsp"></jsp:include></td>
			<td width="30">&nbsp;</td>
			<td width="790">
				<div id="container">
					<div class="title">회원관리</div>
					<form name="search_form">
						<table border="0" cellspacing="1" cellpadding="1">
							<tr>
								<th width="120">기본검색</th>
								<td>
									<select style="width: 130px;" name="search_base">
											<option selected="selected">선택하세요</option>
											<option value="search_name">이름</option>
											<option value="search_id">아이디</option>
									</select>
								<input name="search_base_keyword" type="text" size="20" /></td>
							</tr>
							<tr>
								<th>거주지</th>
								<td>
									<select style="width: 130px;" name="search_sido">
											<option selected="selected">선택하세요</option>
									</select> 
									<select style="width: 130px;" name="search_gugun">
										<option selected="selected">선택하세요</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>
									<select style="width: 100px;" name="search_hp1">
										<option selected="selected">선택하세요</option>
									</select> - 
									<input name="search_hp2" type="text" id="search_hp2" size="20" /> - 
									<input name="search_hp3" type="text" id="search_hp3" size="20" />
								</td>
							</tr>
							<tr>
								<th>생년월일</th>
								<td><input type="radio" name="search_calendar" value="solar" checked="checked"/>양력
									<input type="radio" name="search_calendar" value="lunar" />음력 
									<select style="width: 130px;" name="search_year">
										<option selected="selected">선택하세요</option>
									</select> 
									<select style="width: 130px;" name="search_month">
										<option selected="selected">선택하세요</option>
									</select> 
									<select style="width: 130px;" name="search_day">
										<option selected="selected">선택하세요</option>
									</select>
								</td>
							</tr>
						</table>
						<div style="padding-top: 5px; text-align: right;">
							<input class="btn1" type="reset" value="초기화"/>
							<input class="btn1" type="button" value="검색하기" />
						</div>
						<div style="padding-top: 5px;" id="search_totalMembers">
							<label class="totalmembers"></label>
							<select style="width: 50px; float: right;" name="search_count">
								<option selected="selected" value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
							</select>
						</div>
					</form>
					<div style="height: 15px;"></div>
					<table border="0" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<th width="10%">
								    <input type="checkbox" name="selectTotal" class="checkbox_align"/>&nbsp;선택
								</th>
								<th width="20%">아이디</th>
								<th>이름</th>
								<th>생일</th>
								<th>메일</th>
							</tr>
						</thead>
						<tbody class="memberList">
							<c:forEach items="${memberList }" var="memberInfo">
								<tr>
									<td><input type="checkbox" name="delete_member" class="checkbox_align"/></td>
									<td>${memberInfo.mem_id }</td>
									<td>${memberInfo.mem_name }</td>
									<td>${fn:split(memberInfo.mem_bir, ' ')[0] }</td>
									<td>${memberInfo.mem_mail }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div align="center">
						<input class="publishingCoupon" type="submit" value="선택회원 메일발송" style="float: left;"/>
						<span class="pagingHtml"></span>
						<input class="btn2" type="button" value="Excel작성" style="float: right;"/>
						<input class="btn2" type="button" value="PDF작성" style="float: right; margin-right: 5px;"/>
						<input class="btn2" type="button" value="회원가입" style="float: right;"/>
						<input class="btn2" type="button" value="회원가입_formTag" style="float: right;"/>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
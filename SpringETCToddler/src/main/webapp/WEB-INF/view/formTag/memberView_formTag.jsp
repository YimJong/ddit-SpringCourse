<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
	<div id="form" style="text-align: left; margin-left: 20px;">
		<form:form name="memberForm" modelAttribute="memberInfo" 
		    action="${pageContext.request.contextPath }/formTag/updateMemberInfo_formTag.do"
			method="get">
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><form:input path="mem_id"/></td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><form:password path="mem_pass" showPassword="true"/></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><form:input path="mem_name"/></td>
					</tr>
					<tr>
						<th>성별1</th>
						<td>
							<!-- items속성값으로 checkbox를 작성하고 checkbox의 value와 path속성값과 일치시 체크 -->
							<form:checkboxes path="mem_gender" items="${memberInfo.mem_genderes}"/>
						</td>
					</tr>
					<tr>
						<th>성별2</th>
						<td>
							<!-- items속성값으로 radiobuttons를 작성하고 radiobutton의 value와 path속성값과 일치시 체크 -->
							<form:radiobuttons path="mem_gender" items="${memberInfo.mem_genderes}"/>
						</td>
					</tr>
					<tr>
						<th>생일</th>
						<td><form:input path="mem_bir1"/>-<form:input path="mem_bir2" />-<form:input path="mem_bir3" />
						</td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td><form:input path="mem_regno1"/>-<form:input path="mem_regno2"/></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><form:input path="mem_zip1"/>-<form:input path="mem_zip2"/></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><form:input path="mem_add1"/><br/><form:input path="mem_add2"/></td>
					</tr>
					<tr>
						<th>집전화</th>
						<td>
							<form:select id="mem_hometelMap" path="mem_hometel1" multiple="false">
								<form:options items="${memberInfo.mem_hometelMap}"/>
						    </form:select>-<form:input path="mem_hometel2"/>-<form:input path="mem_hometel3"/>
						</td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td>
							<form:select id="mem_hpMap" path="mem_hp1" multiple="false">
								<form:options items="${memberInfo.mem_hpMap}"/>
						    </form:select>-<form:input path="mem_hp2"/>-<form:input path="mem_hp3"/>
						</td>
					</tr>
					<tr>
						<th>회사전화</th>
						<td>
							<form:select id="mem_comtelMap" path="mem_comtel1" multiple="false">
								<form:options items="${memberInfo.mem_comtelMap}"/>
						    </form:select>-<form:input path="mem_comtel2"/>-<form:input path="mem_comtel3"/>
						</td>
					</tr>
					<tr>
						<th>메일</th>
						<td>
							<form:input path="mem_mail1"/>@
							<form:select id="mem_mailMap" path="mem_mail2" multiple="false" >
								<form:options items="${memberInfo.mem_mailMap}"/>
						    </form:select>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="width: 580px; text-align: right;">
				<input type="submit" value="수정"> 
				<input type="button" value="삭제" onclick=""> 
				<input type="reset"	value="취소"> 
				<input type="button" value="목록" onclick="">
			</div>
		</form:form>
	</div>
</body>
</html>






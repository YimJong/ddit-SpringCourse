<%@ page language="JAVA" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시글 목록</title>
</head>
<body>
<div id="freeboardList_content">
	<div class="panel panel-blue">
    	<div class="panel-heading">게시판 목록</div>
		<table class="table table-bordered table-hover" id="freeboardTable">
			<thead>
				<tr>
					<th scope="col" width="5%">No</th>
					<th scope="col" width="65%">제목</th>
					<th scope="col" width="10%">작성자</th>
					<th scope="col" width="10%">작성일</th>
					<th scope="col" width="10%">조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${freeboardList}" var="freeboardInfo">
				<tr>
					<td><input type="hidden" value="${freeboardInfo.bo_no }"/>${freeboardInfo.rnum}</td>
					<td align="left">
						<c:forEach begin="1" end="${freeboardInfo.bo_depth }" varStatus="stat">
                	        &nbsp;&nbsp;&nbsp;
	                        <c:if test="${stat.last }">
	                           <i class="fa fa-commenting"></i>
	                        </c:if>
		                </c:forEach>
		                ${freeboardInfo.bo_title }</td>
					<td>${freeboardInfo.bo_writer }</td>
					<td>${freeboardInfo.bo_reg_date }</td>
					<td>${freeboardInfo.bo_hit }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div >
${paginationHtml }
<form action="${pageContext.request.contextPath }/user/freeboard/freeboardList.do" method="post" class="form-inline pull-right">
		<input id="search_keyword" name="search_keyword" type="text" placeholder="검색어 입력..." class="form-control" />
		<select class="form-control" name="search_keycode" >
			<option>검색조건</option>
			<option value="TOTAL">전체</option>
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
			<option value="WRITER">작성자</option>
		</select>
	    <button type="submit" class="btn btn-primary form-control">검색</button>
	    <button type="button" class="btn btn-info form-control" name="registBtn">게시글 등록</button>
</form>
</div>	
</body>
<script>
$(document).ready(function() {
	if(eval('${!empty insertMessage}')) {
		infoMsg('${insertMessage}');
	}
	if(eval('${!empty deleteMessage}')) {
		infoMsg('${deleteMessage}');
	}
	
	$('button[name=registBtn]').on('click', function() {
		location.href = '${pageContext.request.contextPath}/user/freeboard/freeboardForm.do';
	});
	
	$('#freeboardTable tr:gt(0)').on('click', function() {
		const bo_no = $(this).find('td:eq(0) > input').val();
		$(location).attr('href', '${pageContext.request.contextPath}/user/freeboard/freeboardView.do?bo_no=' + bo_no);
	});
	
});

function infoMsg(value) {
	BootstrapDialog.show({
	    title: '알림창',
	    message: value
	});
};

</script>
</html>
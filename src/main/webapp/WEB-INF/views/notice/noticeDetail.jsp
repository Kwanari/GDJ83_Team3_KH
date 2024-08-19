<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>NOTICE</title>
<c:import url="../template/header.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/boardSY.css">
</head>
<body id="reportsPage" class="font">
		<c:import url="../template/topbar.jsp"></c:import>

    <div class="container tm-mt-big tm-mb-big">
      <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
          <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
            <div class="row">
              <div class="col-12">
                <h1 class="tm-block-title d-inline-block">공지사항 상세정보</h1>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-12 col-lg-12 col-md-12">
              <form action="noticeAdd" class="tm-edit-product-form" method="post">
                <input type="hidden" value="${users_info.user_id}" name="user_id">
                <div>
                  <div id="noticetitle"class="form-group mb-10">
                    <label
                      for="name"
                      >제목
                    </label>
                    <input size= "50" id="notice_title" name="notice_title" type="text" class="form-control validate" value="${detail.notice_title }" readonly style="background-color:#4e657a"/>
                  </div> 
                  
                  <div id="noticehit" class="form-group mb-3">
			        <label
			          for="name"
			          >조회수
			        </label>
			        <input size= "10"id="notice_hit" name="notice_hit" type="text" class="form-control validate" value="${detail.notice_hit}"readonly style="background-color:#4e657a"/>
			      </div> 
                  </div>
                  <div>
                  <div id="noticename" class="form-group mb-3">
                    <label
                      for="name"
                      >작성자
                    </label>
                    <input size= "30"id="user_id" name="user_id" type="text" class="form-control validate" value="${detail.user_id}"readonly style="background-color:#4e657a"/>
                  </div>
                  
                  <div id="noticedate" class="form-group mb-3">
                    <label
                      for="name"
                      >등록일
                    </label>
                    <input size= "30"id="notice_create_date" name="notice_create_date" type="text" class="form-control validate" value="${detail.notice_create_date}"readonly style="background-color:#4e657a"/>
                  </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="contents">내용</label>
                    <textarea 
                      class="form-control validate" name="notice_contents" id="notice_contents"rows="10" readonly style="background-color:#4e657a">${detail.notice_contents}</textarea>
                  </div>
                  
                  
                  
                  
                  
              
              <div class="col-12" id="detail_back">
                <a href="./noticeList" class="btn btn-primary btn-block text-uppercase">목록</a>
              </div>
              
              <c:if test="${users_info.user_id eq detail.user_id}">
              <a href="./noticeUpdate?notice_num=${detail.notice_num}" class="btn btn-outline-success mb-3">수정</a>
              <a href="./delete?notice_num=${detail.notice_num}" id="deletea" class="btn btn-outline-delete mb-3">삭제</a>
              </c:if>
            </form>
            </div>
          </div>
        </div>
      </div>
    </div>
   
	
	<c:import url="../template/footer.jsp"></c:import>
	<c:import url="../template/boot-footer.jsp"></c:import>
</body>
</html>
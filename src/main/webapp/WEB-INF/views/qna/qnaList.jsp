<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>QUESTION</title>
<c:import url="../template/header.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/boardSY.css">
</head>
<body id="reportsPage" class="font">
		<c:import url="../template/topbar.jsp"></c:import>
<div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-9 col-xl-9 tm-block-col">
				<h1 class="text-white mt-2 mb-2">문의 게시판</h1>
          <div class="tm-bg-primary-dark tm-block tm-block-products">
          
         <div class="form-group mb-3">
						<!-- 검색기능 태그 -->
						<!-- 검색기능 태그 -->
					<form method="get">
							<div class="row">
							<div class="col-auto">
								<select id="kind" name="kind" class="custom-select mr-sm-2">
									<option value="user_id" ${pager.kind eq 'user_id' ? 'selected' : ''}>작성자</option>
	              					<option value="question_title" ${pager.kind eq 'question_title' ? 'selected' : ''}>제목</option>
	              					<option value="question_contents" ${pager.kind eq 'question_contents' ? 'selected' : ''}>내용</option>
								</select>
							</div>
							
							<div class="col-sm-5">
								<input name="search" size="20" type="text" class="form-control" id="search">
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-primary" >검색</button>
							</div>
							<!-- 검색기능 태그 -->
							<!-- 검색기능 태그 -->
							<!-- 캐릭터, 무기 분류별로 조회하기 위한 셀렉트 태그 -->
							<!-- 캐릭터, 무기 분류별로 조회하기 위한 셀렉트 태그 -->
							<div class="col-auto">
							<label style="margin-top: 14px;" for="category">정렬</label>
							</div>
							<div class="col-sm-2">
							<select class="custom-select" name="order" id="category" data-cat-num="">
								<option  value="1">조회수순</option>
								<option value="2">최신순</option>
							</select>
							</div>
							</div>
					</div>
 					</form>
  
            <div class="tm-product-table-container">
            
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                  <tr>
                    <th scope="col">&nbsp;</th>
                    <th scope="col">글 번호</th>
                    <th scope="col">작성자</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
				
					<c:forEach var="list" items="${list}">
                  <tr>
                    <th scope="row"></th>
                    <td>${list.question_num}</td>
                    <td>${list.user_id}</td>
                    <td><a id= "aquestion" href="questionDetail?question_num=${list.question_num}" class="btn" >${list.question_title}</a></td>
                    <td>${list.question_create_date}</td>
                    <td>${list.question_hit}</td>
                    
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
   <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <li class="page-item ${pager.pre ? '' : 'disabled'}"><a class="page-link" href="./questionList?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">Previous</a></li>
            <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
              <li class="page-item"><a class="page-link ${pager.page == i ? 'active' : ''  }" href="./questionList?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
            </c:forEach>
            <li class="page-item ${pager.next ? '':'disabled'}"><a class="page-link" href="./questionList?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">Next</a></li>
          </ul>
        </nav>         
            <a
              href="./questionAdd"
              class="btn btn-primary btn-block text-uppercase mb-3">문의사항 글쓰기</a>
   
          </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
            <h2 class="tm-block-title">게시판 카테고리</h2>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <tbody>
                  <tr>
                    <td class="tm-notice-notice">공지사항</td>
                    <td class="text-center">
                    </td>
                  </tr>
                  <tr>
                    <td class="tm-notice-user">유저 게시판</td>
                    <td class="text-center">

                    </td>
                  </tr>
                  <tr>
                    <td class="tm-notice-lank">랭킹 게시판</td>
                    <td class="text-center">

                    </td>
                  </tr>
                  <tr>
                    <td class="tm-notice-qna">문의 게시판</td>
                    <td class="text-center">

                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
       
          </div>
        </div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
    
    $(function() {
        $(".tm-notice-notice").on("click", function() {
          window.location.href = "../notice/noticeList";
        });
      });</script>
      
      
        <script type="text/javascript">
    
    $(function() {
        $(".tm-notice-user").on("click", function() {
          window.location.href = "../user/userList";
        });
      });</script>
      
         <script type="text/javascript">
    
    $(function() {
        $(".tm-notice-lank").on("click", function() {
          window.location.href = "../lank/lankList";
        });
      });</script>
      
      
         <script type="text/javascript">
    
    $(function() {
        $(".tm-notice-qna").on("click", function() {
          window.location.href = "../qna/qnaList";
        });
      });</script>

   

	
	<c:import url="../template/footer.jsp"></c:import>
	<c:import url="../template/boot-footer.jsp"></c:import>
</body>
</html>
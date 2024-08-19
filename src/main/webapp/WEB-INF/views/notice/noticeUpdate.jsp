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
                <h2 class="tm-block-title d-inline-block">공지사항 수정</h2>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-12 col-lg-6 col-md-12">
              <form action="./noticeUpdate" class="tm-edit-product-form" method="post">
                <input type="hidden" value="${detail.notice_num}" name="notice_num">
                  <div class="form-group mb-3">
                    <label
                      for="name"
                      >제목
                    </label>
                    <input  value="${detail.notice_title}" id="notice_title" name="notice_title" type="text" class="form-control validate" required/>
                  </div>
                  <div class="form-group mb-3">
                    <label for="contents">내용</label>
                    <textarea 
                      class="form-control validate" name="notice_contents" id="notice_contents"rows="10"required>${detail.notice_contents}</textarea>
                  </div>
                  <div class="form-group mb-3"></div>
			</div>
              <div class=" col-md-12 mx-auto mb-4">
                <div style="height:60% " class="tm-product-img-dummy mx-auto">
                  <i
                    class="fas fa-cloud-upload-alt tm-upload-icon"
                    onclick="document.getElementById('fileInput').click();"
                  ></i>
                </div>
                <div class="custom-file mt-3 mb-3">
                  <input id="fileInput" type="file" style="display:none;" />
                  <input
                    type="button"
                    class="btn btn-primary btn-block mx-auto"
                    value="파일 업로드"
                    onclick="document.getElementById('fileInput').click();"
                  />
                </div>
              </div>
              
              <div id="updatebtn2" class="col-6">
                <button onclick="location.href='noticeList.jsp'" class="btn btn-primary btn-block text-uppercase">뒤로가기</button>
              </div>
              <div id="updatebtn1" class="col-6">
                <button type="submit" class="btn btn-primary btn-block text-uppercase">수정</button>
              </div>
              
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Home</title>
	<c:import url="../template/header.jsp"></c:import>
</head>
<body id="reportsPage" class="font">
		<c:import url="../template/topbar.jsp"></c:import>

		<div class="container">
			<!-- row -->
			<div class="container mt-5">
				<!-- row -->
				<div class="row tm-content-row">
					<div class="tm-block-col tm-col-avatar">
						<div class="tm-bg-primary-dark tm-block tm-block-avatar">
							<h2 class="tm-block-title">Change Avatar</h2>
							<div class="tm-avatar-container">
								<img src="img/avatar.png" alt="Avatar"
									class="tm-avatar img-fluid mb-4" /> <a
									href="#" class="tm-avatar-delete-link"> <i
									class="far fa-trash-alt tm-product-delete-icon"></i>
								</a>
							</div>
							<button class="btn btn-primary btn-block text-uppercase">
								Upload New Photo</button>
						</div>
					</div>
					<div class="tm-block-col tm-col-account-settings">
						<div class="tm-bg-primary-dark tm-block tm-block-settings">
							<h2 class="tm-block-title">내 정보</h2>
							<form action="/users/update" method="post" class="tm-signup-form row" id="form">
								<div class="form-group col-lg-6">
									<label for="user_id">유저 아이디</label>
									<input id="user_id" name="user_id" value="${usersDTO.user_id }"
									type="text" class="form-control validate" readonly style="background-color:#4e657a"/>
								</div>
								<div class="form-group col-lg-6">
									<label for="user_name">유저 이름</label>
									<input id="user_name" name="user_name" value="${usersDTO.user_name }"
									type="text" class="form-control validate" />
								</div>
								<div class="form-group col-lg-6">
									<label for="user_pw">비밀번호</label>
									<input id="user_pw" name="user_pw"
										type="password" class="form-control validate" />
								</div>
								<div class="form-group col-lg-6">
									<label for="user_pw2">비밀번호 재확인</label>
									<input id="user_pw2" name="user_pw2"
										type="password" class="form-control validate" />
										<div id="password-check"></div>
								</div>
								<div class="form-group col-lg-6">
									<label for="user_nickname">닉네임</label>
									<input id="user_nickname" name="user_nickname" value="${usersDTO.user_nickname }"
									type="text" class="form-control validate" />
								</div>
								<div class="form-group col-lg-6">
									<label for="user_phone">전화번호</label>
									<input id="user_phone" name="user_phone" value="${usersDTO.user_phone }"
									type="tel" class="form-control validate" />
								</div>
								<div class="form-group col-lg-6">
									<label for="user_email">이메일</label>
									<input id="user_email" name="user_email" value="${usersDTO.user_email }"
									type="email" class="form-control validate" />
								</div>
								<div class="form-group col-lg-6">
									<label class="tm-hide-sm">&nbsp;</label>
									<a type="button" class="btn btn-danger btn-block text-uppercase"
									href="/users/deleteAccount">
										회원 탈퇴</a>
								</div>
								<div class="col-12">
									<button type="button" id="check"
									class="btn btn-primary btn-block text-uppercase">회원 정보 수정</button>
								</div>
								<div class="form-group col-lg-6"></div>
								<div class="col-12">
									<a href="/users/tradeList" class="btn btn-success btn-block text-uppercase">거래 내역 조회</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		<c:import url="../template/footer.jsp"></c:import>
		<c:import url="../template/boot-footer.jsp"></c:import>
		<script src="/resources/js/users/mypageUpdate.js"></script>
</body>
</html>
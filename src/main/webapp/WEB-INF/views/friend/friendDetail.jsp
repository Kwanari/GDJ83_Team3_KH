<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
<c:import url="../template/header.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/friend.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body id="reportsPage" class="font">
	<div class="" id="home">
		<c:import url="../template/topbar.jsp"></c:import>

<div class="container">
			<!-- row -->
			<div class="container mt-5">
				<!-- row -->
				<div class="row tm-content-row">
					<div class="tm-block-col tm-col-avatar">
						<div class="tm-bg-primary-dark tm-block tm-block-avatar">
							<h2 id="friendStatus" data-friend-id="${friendInfoDTO.user_id}" data-user-id="${myDTO.user_id}" class="tm-block-title">부재중</h2>
							<div id="chatParent">
							<a id="startChat" type="button" class="btn btn-outline-warning btn-block text-uppercase mb-3"
									data-bs-toggle="modal" data-bs-target="#commentModal"
									href="chat?friend_num=${friendInfoDTO.friendDTO[0].friend_num}">
										대화하기</a>
															
							<!-- 대화 모달 창 -->
								<div class="modal fade" id="commentModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-dialog-scrollable">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="modal-title fs-5" id="exampleModalLabel">To. ${friendInfoDTO.user_id}</h1>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
								      </div>
								      <div class="modal-body">
								       <div class="form-floating" id="modalContent">
										  
										  
										  
										</div>
								      </div>
								      <div class="modal-footer">
										<div class="input-group mb-3">
											<input type="text" class="form-control" id="chatContent" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
											<button class="btn btn-outline-secondary" type="button" id="sendBtn">보내기</button>
											
										  </div>
								        
								      </div>
								    </div>
								  </div>
								</div>
		
							</div>
							<div class="tm-avatar-container">
								<img src="/resources/img/friendImage/chick.png" alt="Avatar"
									class="tm-avatar img-fluid mb-4" /> 
							</div>
							
						</div>
					</div>
					<div class="tm-block-col tm-col-account-settings">
						<div class="tm-bg-primary-dark tm-block tm-block-settings">
							<h2 class="tm-block-title">친구 정보</h2>
							<form action="/users/update" method="post" class="tm-signup-form row" id="form">
								<div class="form-group col-lg-6">
									<label for="user_id">유저 아이디</label>
									<input id="user_id" name="user_id" value="${friendInfoDTO.user_id}"
									type="text" class="form-control validate" readonly style="background-color:#4e657a"/>
								</div>
								
								<div class="form-group col-lg-6">
									<label for="user_nickname">닉네임</label>
									<input id="user_nickname" name="user_nickname" value="${friendInfoDTO.user_nickname}"
									type="text" class="form-control validate" />
								</div>
								
								<div class="form-group col-lg-6">
									<label class="tm-hide-sm">&nbsp;</label>
									<a type="button" id="sendGiftBtn" class="btn btn-secondary btn-block text-uppercase chatBtn"
									href="sendGift?user_id=${myDTO.user_id}" data-bs-toggle="modal" data-bs-target="#sendGiftModal">
										선물보내기</a>
								</div>
								
								<!-- 내 인벤토리 모달창 -->
								
									<div class="modal fade" id="sendGiftModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="sendGiftModal" aria-hidden="true">
										<div class="modal-dialog modal-lg modal-dialog-scrollable">
										  <div class="modal-content">
					
											<div class="modal-header">
											  <h1 class="modal-title fs-5" id="invenHeader">내 인벤토리</h1>
											  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
											</div>
					
											<div class="modal-body">
											 <!-- 인벤토리 리스트 -->
											 <div class="tm-block-col tm-col-account-settings" id="inventoryList">
												<div class="row tm-bg-primary-dark tm-block tm-block-settings">
													<h2 class="tm-block-title col-sm-12 col-md-12 col-lg-12 col-xl-12">선물을 골라주세요</h2>
													<c:forEach items="${inventoryList}" var="invenItem">
														<!-- 브라우저 사이즈에 따라 column 길이 변화 -->
														<div class=" col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
															<!-- 카드형 태그 -->
															<div class="card">
																<!-- 카드형 상부 이미지 -->
																<img src="/resources/img/character/4.gif" class="card-img-top"
																	alt="...">
																<div class="card-body">
																	<!-- 카드 이미지 하단 아이템명, 가격 -->
																	<h5 class="card-title">${invenItem.item_name }</h5>
																	<h5 class="card-title">${invenItem.item_description }</h5>
																	<h5 class="card-title">체력 증가 : ${invenItem.item_hp }</h5>
																	<h5 class="card-title">공격력 증가 : ${invenItem.item_atk }</h5>
																	<h5 class="card-title">회피력 증가 : ${invenItem.item_dod }</h5>
																	<a href="./sendGift?item_num=${invenItem.item_num}&user_id=${friendInfoDTO.user_id}" 
																	id="sendSelectedGift" type="button" class="btn btn-primary chatBtn">선물 보내기</a>	
																</div>
															</div>
														</div>
													</c:forEach>
												</div>
											  </div>
											</div>
											
										  </div>
										</div>
									  </div>
								   
								
								<div class="form-group col-lg-6">
									<label class="tm-hide-sm">&nbsp;</label>
									<a type="button" class="btn btn-danger btn-block text-uppercase"
									href="deleteFriend?friend_num=${friendInfoDTO.friendDTO[0].friend_num}">
										친구 끊기</a>
								</div>
								
								<div class="form-group col-lg-12">
									<label class="tm-hide-sm">&nbsp;</label>
									<a type="button" class="btn btn-secondary btn-block text-uppercase"
									href="./friendList">
										돌아가기</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		
		
		
	</div>

		<footer class="tm-footer row tm-mt-small fixed-bottom">
            <div class="col-12 font-weight-light">
                <p class="text-center text-white mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved. 
                    
                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                </p>
            </div>
        </footer>
        
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

<script type="text/javascript">
	
	
</script>
<script src="/resources/js/friend/chat.js"></script>
<c:import url="../template/boot-footer.jsp"></c:import>
</body>
</html>

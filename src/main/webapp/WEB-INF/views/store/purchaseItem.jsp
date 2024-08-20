<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>purchase</title>
	<c:import url="../template/header.jsp"></c:import>
</head>
<body id="reportsPage" class="font">
	<div class="" id="home">
	<c:import url="../template/topbar.jsp"></c:import>
	
	<div class="container">
        <div class="row">
            <div class="col">
                <h1 class="text-white mt-5 mb-5">WISHLIST</h1>
            	<div class="container mt-5">
					<div class="row tm-content-row">
					  <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 tm-block-col">
						<form action="purchaseItem" method="get" id="frm">
						<div class="tm-bg-primary-dark tm-block tm-block-products">
							<div class="tm-product-table-container" id="wishdiv">
							<table class="table table-hover tm-table-small tm-product-table">
							  <thead>
								<tr>
								  <th scope="col"></th>
								  <th scope="col">이름</th>
								  <th scope="col">설명</th>
								  <th scope="col">체력</th>
								  <th scope="col">공격력</th>
								  <th scope="col">회피력</th>
								  <th scope="col">가격</th>
								</tr>
							  </thead>
							  <tbody id="checkdiv">
									<c:forEach items="${purchaseList}" var="list">
										<tr>
											<td><div class="tm-gray-circle"><img src="/resources/img/item/${list.itemFileDTO.file_name}" width="80" alt="Avatar Image" class="rounded-circle"></div></td>
											<td class="tm-product-name itemName" id="${list.item_name}" data-inum="${list.item_num}">${list.item_name}</td>
											<td>${list.item_description}</td>
											<td>${list.item_hp}</td>
											<td>${list.item_atk}</td>
											<td>${list.item_dod}</td>
											<td class="itemPrice" id="${list.item_price}">${list.item_price}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						  </div>
						  <!-- table container -->
						  <button type="button" id="kakaobtn">카카오페이</button>
                		<button type="button" id="kakaodelbtn">취소</button>
						</div>
					</form>
					  </div>
					  </div>
					</div>



                
            </div>
        </div>
            <!-- row -->
    </div>
	<c:import url="../template/footer.jsp"></c:import>
	<c:import url="../template/boot-footer.jsp"></c:import>
	</div>
    <script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
    <script src="/resources/js/store/purchaseItem.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>tradeList</title>
	<c:import url="../template/header.jsp"></c:import>
</head>
<body id="reportsPage" class="font">
	<c:import url="../template/topbar.jsp"></c:import>
	
	<div class="container">
        <div class="row">
            <div class="col">
				<h1 class="text-white mt-5 mb-5">거래 내역 조회</h1>
            	<div class="container mt-5">
					<div class="row tm-content-row">
					  <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 tm-block-col">
						<div class="tm-bg-primary-dark tm-block tm-block-products">
							<div class="tm-product-table-container">
							<table class="table table-hover tm-table-small tm-product-table">
							  <thead>								 
								<tr>
								  <th scope="col"></th>
								  <th scope="col">아이템 명</th>
								  <th scope="col">거래 날짜</th>
								  <th scope="col">
								  	<select id="category-amount" class="custom-select">
								  		<option>거래 금액</option>
								  		<option value="1">높은 순</option>
								  		<option value="2">낮은 순</option>
								  	</select>
								  </th>
								  <th scope="col">
								  	<select id="category-type" class="custom-select">
								  		<option value="0">거래타입</option>
								  		<option value="구입">구입</option>
								  		<option value="환불">환불</option>
								  	</select>
								  </th>
								  <th scope="col"></th>
								</tr>
							  </thead>
							  <tbody id="list">
									<c:forEach items="${list}" var="dto">
										<tr>
											<td></td>
											<td>${dto.itemDTO.item_name }</td>
											<td>${dto.transaction_date }</td>
											<td>${dto.transaction_amount }</td>
											<td>${dto.transaction_type }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						  </div>
						  <!-- table container -->
						</div>
					  </div>
					  </div>
					</div>
				  </div>         	
    		</div>
		</div>
	<c:import url="../template/footer.jsp"></c:import>
	<c:import url="../template/boot-footer.jsp"></c:import>
	<script src="/resources/js/users/tradeList.js"></script>
</body>
</html>

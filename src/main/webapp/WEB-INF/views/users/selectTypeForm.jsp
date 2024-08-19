<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${list}" var="dto">
	<tr>
		<td></td>
		<td>${dto.itemDTO.item_name }</td>
		<td>${dto.transaction_date }</td>
		<td>${dto.transaction_amount }</td>
		<td>${dto.transaction_type }</td>
	</tr>
</c:forEach>

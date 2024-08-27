<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Chuyến Xe</h1>

<c:url value="/trips" var="action" />
<table class="table table-striped mt-1">
    <tr>
        <th>Id</th>
        <th>Tên Chuyến Xe</th>
        <th>Thời gian đi</th>
        <th>Thời gian đến</th>
        <th>Giá vé</th>
        <th>Tuyến đường</th>
        <th>Xe khách</th>
        <th>Nhà xe</th>
    </tr>
    <c:forEach items="${trips}" var="t">
        <tr>
            <td>${t.tripID}</td>
            <td>${t.name}</td>
            <td>${t.departureTime}</td>
            <td>${t.arrivalTime}</td>
            <td>${t.ticketPrice}</td>
            <td>${t.routeID.name}</td>
            <td>${t.busID.name}</td>
            <td>${t.companyID.companyName}</td>
            <td>
                <c:url value="/api/trips/${t.tripID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/trips/${t.tripID}"/>" >Cập nhật</a>
                <button onclick="deleteCategory('${url}',${c.categoryID})" class="btn btn-danger">Xóa</button>
            </td>

        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






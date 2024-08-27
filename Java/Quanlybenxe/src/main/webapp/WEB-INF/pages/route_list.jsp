<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Tuyến Ðường</h1>

<c:url value="/routes" var="action" />
<table class="table table-striped mt-1">
    <tr>
        <th>Id</th>
        <th>Tên tuyến dường</th>
        <th>Điểm di</th>
        <th>Điểm đến</th>
        <th>Khoảng cách</th>
        <th>Mô tả</th>
        <th>Giá vé</th>
        <th>Thời gian di chuyển dự kiến</th>
    </tr>
    <c:forEach items="${routes}" var="r">
        <tr>
            <td>${r.routeID}</td>
            <td>${r.name}</td>
            <td>${r.startLocation}</td>
            <td>${r.endLocation}</td>
            <td>${r.distance}</td>
            <td>${r.description}</td>
            <td>${r.ticketPrice}</td>
            <td>${r.estimatedDuration}</td>
            <td>
                <c:url value="/api/routes/${r.routeID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/routes/${r.routeID}"/>" >Cập nhật</a>
                <button onclick="deleteCategory('${url}',${c.categoryID})" class="btn btn-danger">Xóa</button>
            </td>

        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Xe Khách</h1>


<table class="table table-striped mt-1">
    <tr>
        <th>Avatar</th>
        <th>Id</th>
        <th>Biển số xe</th>
        <th>Sức chứa</th>
        <th>Loại xe</th>
        <th>Tác vụ</th>
    </tr>
    <c:forEach items="${buses}" var="b">
        <tr>
            <td> <img class="card-img-top" src="${b.avatar}"  style="width:200px;"></td>
            <td>${b.busID}</td>
            <td>${b.plateNumber}</td>
            <td>${b.capacity}</td>
            <td>${b.categoryID.name}</td>
            <td>
                <c:url value="/api/buses/${b.busID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/buses/${b.busID}"/>" >Cập nhật</a>
                <button onclick="deleteBus('${url}',${b.busID})" class="btn btn-danger">Xóa</button>
            </td>

        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






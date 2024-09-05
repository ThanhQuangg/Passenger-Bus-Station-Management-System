<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Nhà Xe</h1>

<c:url value="/companies" var="action" />
<table class="table table-striped mt-1">
    <tr>
        <th>Avatar</th>
        <th>Id</th>
        <th>Tên nhà xe</th>
        <th>Địa chỉ</th>
        <th>Email</th>
        <th>Số điện thoại</th>
    </tr>
    <c:forEach items="${companies}" var="c">
        <tr>
            <td> <img class="card-img-top" src="${c.avatar}"  style="width:200px;"></td>
            <td>${c.companyID}</td>
            <td>${c.companyName}</td>
            <td>${c.address}</td>
            <td>${c.email}</td>
            <td>${c.phoneNumber}</td>
            <td>
                <c:url value="/companies/${c.companyID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/companies/${c.companyID}"/>" >Cập nhật</a>
                <button onclick="deleteCompany('${url}',${c.companyID})" class="btn btn-danger">Xóa</button>
            </td>

        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






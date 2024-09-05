<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Loại Xe</h1>

<c:url value="/categories" var="action" />
<table class="table table-striped mt-1">
    <tr>
       
        <th>Id</th>
        <th>Tên loại xe</th>
        <th>Mô tả</th>
    </tr>
    <c:forEach items="${categories}" var="c">
        <tr>
            <td>${c.categoryID}</td>
            <td>${c.name}</td>
            <td>${c.description}</td>
            <td>
                <c:url value="/categories/${c.categoryID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/categories/${c.categoryID}"/>" >Cập nhật</a>
                <button onclick="deleteCategory('${url}',${c.categoryID})" class="btn btn-danger">Xóa</button>
            </td>

        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






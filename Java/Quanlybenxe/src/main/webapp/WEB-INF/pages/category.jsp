<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ LOẠI XE</h1>

<c:url value="/categories" var="action" />
<form:form method="post" action="${action}" modelAttribute="category" >
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="name"  placeholder="Tên loại xe" path="name" />
        <label for="name">Tên loại xe</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="description"  placeholder="Mô tả" path="description" />
        <label for="name">Mô tả</label>
    </div>  

    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${categoryID != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="categoryID" />
    </div>
</form:form>




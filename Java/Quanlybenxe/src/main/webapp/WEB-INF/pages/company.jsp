<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ NHÀ XE</h1>

<c:url value="/companies" var="action" />
<form:form method="post" action="${action}" modelAttribute="company" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="companyName"  placeholder="Tên nhà xe" path="companyName" />
        <label for="name">Tên nhà xe</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="address"  placeholder="Địa chỉ nhà xe" path="address" />
        <label for="name">Địa chỉ nhà xe</label>
    </div>  
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="email"  placeholder="Email của nhà xe" path="email" />
        <label for="name">Email của nhà xe</label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="phoneNumber"  placeholder="Số điện thoại nhà xe " path="phoneNumber" />
        <label for="name">Số điện thoại nhà xe</label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <!--        <form:input class="form-control"  id="isShippingAvailable"  placeholder="Có nhận giao hàng không" path="isShippingAvailable" />
                <label for="name">Có nhận giao hàng không</label>-->
        <form:select path="isShippingAvailable" class="form-select" placeholder="Có nhận giao hàng không">
            <option value="true">Có</option>
            <option value="false">Không</option>
        </form:select>
        <label for="name">Có nhận giao hàng không</label>
    </div> 

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control"  id="avatar" path="file" />
        <label for="image">Ảnh nhà xe</label>
    </div>
    <!-- Hidden input for isActive field -->
    <form:hidden path="isActive" />



    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${companyID != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="companyID" />
    </div>
</form:form>




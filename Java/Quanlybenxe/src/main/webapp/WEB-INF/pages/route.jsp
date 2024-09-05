<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ XE KHÁCH</h1>

<c:url value="/routes" var="action" />
<form:form method="post" action="${action}" modelAttribute="route" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Tên tuyến đường" path="name" />
        <label for="name">Tên tuyến đường</label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="startLocation" placeholder="Điểm bắt đầu" path="startLocation" />
        <label for="startLocation">Điểm bắt đầu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="endLocation" placeholder="Điểm kết thúc" path="endLocation" />
        <label for="endLocation">Điểm kết thúc</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="distance" placeholder="Khoảng cách" path="distance" />
        <label for="distance">Khoảng cách</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="ticketPrice" placeholder="Giá vé" path="ticketPrice" />
        <label for="ticketPrice">Giá vé</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="estimatedDuration" placeholder="Thời gian di chuyển dự kiến" path="estimatedDuration" />
        <label for="estimatedDuration">Thời gian di chuyển dự kiến</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="description" placeholder="Mô tả" path="description" />
        <label for="description">Mô tả</label>
    </div>

        <!--    <div class="form-floating">
                <button class="btn btn-info mt-1" type="submit">
                    Thêm
                </button>
        <form:hidden path="routeID" />
    </div>-->
        <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${routeID != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="routeID" />
    </div>
</form:form>

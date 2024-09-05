<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ CHUYẾN XE</h1>

<c:url value="/trips" var="action" />
<form:form method="post" action="${action}" modelAttribute="trip" enctype="multipart/form-data" >
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Tên chuyến xe" path="name" />
        <label for="name">Tên chuyến xe</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="departureTime" placeholder="Thời gian khởi hành" path="departureTime" />
        <label for="departureTime">Thời gian khởi hành</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="arrivalTime" placeholder="Thời gian đến dự kiến" path="arrivalTime" />
        <label for="arrivalTime">Thời gian đến dự kiến</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="ticketPrice" placeholder="Giá vé" path="ticketPrice" />
        <label for="ticketPrice">Giá vé</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="status" placeholder="Trang thái" path="status" />
        <label for="status">Trang thái</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="busID" path="busID">
            <c:forEach items="${buses}" var="b">
                <c:choose>
                    <c:when test="${b.busID == route.bus.busID}">
                        <option value="${b.busID}" selected>${b.name}</option> 
                    </c:when>
                    <c:otherwise>
                        <option value="${b.busID}">${b.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="busID" class="form-label">Xe Buýt:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="routeID" path="routeID">
            <c:forEach items="${routes}" var="r">
                <c:choose>
                    <c:when test="${r.routeID == route.routeID.routeID}">
                        <option value="${r.routeID}" selected>${r.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${r.routeID}">${r.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="routeID" class="form-label">Tuyến đường:</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="companyID" path="companyID">
            <c:forEach items="${companies}" var="cp">
                <c:choose>
                    <c:when test="${cp.companyID == bus.companyID.companyID}">
                        <option value="${cp.companyID}" selected>${cp.companyName}</option> 
                    </c:when>
                    <c:otherwise>
                        <option value="${cp.companyID}">${cp.companyName}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="categoryID" class="form-label">Nhà xe:</label>
    </div>
 
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${tripID != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="tripID" />
    </div>
</form:form>

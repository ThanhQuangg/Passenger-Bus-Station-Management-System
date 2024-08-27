<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ XE KHÁCH</h1>

<c:url value="/buses" var="action" />
<form:form method="post" action="${action}" modelAttribute="bus" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="name"  placeholder="Tên xe khách" path="name" />
        <label for="name">Tên xe khách</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="plateNumber"  placeholder="Biển số xe khách" path="plateNumber" />
        <label for="plateNumber">Biển số xe khách</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="capacity"  placeholder="Sức chứa xe khách" path="capacity" />
        <label for="capacity">Sức chứa xe khách</label>
    </div>  

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control"  id="avatar" path="file" />
        <label for="image">Ảnh xe khách</label>
        <c:if test="${bus.avatar != null}">
            <img src="${bus.avatar }" width="200" />
        </c:if>
    </div>


    <div class="form-floating">
        <form:select class="form-select" id="categoryID" path="categoryID">
            <c:forEach items="${categories}" var="c">
                <c:choose>
                    <c:when test="${c.categoryID == bus.categoryID.categoryID}">
                        <option value="${c.categoryID}" selected>${c.name}</option> 
                    </c:when>
                    <c:otherwise>
                        <option value="${c.categoryID}">${c.name}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="categoryID" class="form-label">Danh mục:</label>
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
                <c:when test="${bus.busID > 0}">Cập nhật xe</c:when>
                <c:otherwise>Thêm xe</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="busID" />
    </div>
</form:form>




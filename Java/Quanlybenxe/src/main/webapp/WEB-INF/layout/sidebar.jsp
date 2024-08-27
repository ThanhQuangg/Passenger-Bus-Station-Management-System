<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="sidebar">
    <div>
        <a class="btn btn-info" href="<c:url value="/buses/add"/>">Thêm xe khách</a>
    </div>
    <div>
        <a class="btn btn-info" href="<c:url value="/routes/add"/>">Thêm tuyến đường</a>
    </div>
    <div>
        <a class="btn btn-info" href="<c:url value="/companies/add"/>">Thêm nhà xe</a>
    </div>
    <div>
        <a class="btn btn-info" href="<c:url value="/categories/add"/>">Thêm loại xe</a>
    </div>
    <div>
        <a class="btn btn-info" href="<c:url value="/trips/add"/>">Thêm chuyến xe</a>
    </div>
    <div>
        <a class="btn btn-info" href="<c:url value="/stats"/>">Thống kê báo cáo</a>
    </div>
</div>

<style>
    .sidebar {
        width: 250px;
        position: fixed;
        top: 56px; /* Adjust this value according to the height of your header */
        left: 0;
        height: calc(100% - 56px); /* Adjust this value according to the height of your header */
        background-color: #f8f9fa;
        padding: 20px;
        overflow-y: auto;
    }

    .sidebar .btn {
        display: block;
        margin-bottom: 10px;
    }
</style>

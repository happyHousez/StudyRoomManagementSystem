<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>热门推荐</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title">热门推荐</h3>
</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
    <!-- 数据表格 -->
    <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
        <thead>
        <tr>
            <th class="sorting_asc">自习室名称</th>
            <th class="sorting">位置</th>
            <th class="sorting">可容纳人数</th>
            <th class="sorting">电话</th>
            <th class="sorting">状态</th>
            <th class="sorting">开放时间</th>
            <th class="sorting">关闭时间</th>
            <th class="sorting">详情描述</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageResult.rows}" var="room">
            <tr>
                <td> ${room.name}</td>
                <td>${room.location}</td>
                <td>${room.capacity}</td>
                <td>${room.phone}</td>
                <td>
                    <c:if test="${room.status ==0}">开放</c:if>
                    <c:if test="${room.status ==1}">位置已满</c:if>
                    <c:if test="${room.status ==2}">维修中</c:if>
                </td>
                <td>${room.openTime}</td>
                <td>${room.closeTime}</td>
                <td>${room.description}</td>
                <td class="text-center">
                    <c:if test="${room.status ==0}">
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#borrowModal"
                                onclick="findBookById(${room.id},'borrow')"> 预约
                        </button>
                    </c:if>
                    <c:if test="${room.status ==1 ||room.status ==2}">
                        <button type="button" class="btn bg-olive btn-xs" disabled="true">预约</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
<%--        <tbody>--%>
<%--        <c:forEach items="${pageResult.rows}" var="book">--%>
<%--            <tr>--%>
<%--                <td> ${book.name}</td>--%>
<%--                <td>${book.author}</td>--%>
<%--                <td>${book.press}</td>--%>
<%--                <td>${book.isbn}</td>--%>
<%--                <td>--%>
<%--                    <c:if test="${book.status ==0}">可借阅</c:if>--%>
<%--                    <c:if test="${book.status ==1}">借阅中</c:if>--%>
<%--                    <c:if test="${book.status ==2}">归还中</c:if>--%>
<%--                </td>--%>
<%--                <td>${book.borrower}</td>--%>
<%--                <td>${book.borrowTime}</td>--%>
<%--                <td>${book.returnTime}</td>--%>
<%--                <td class="text-center">--%>
<%--                    <c:if test="${book.status ==0}">--%>
<%--                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#borrowModal"--%>
<%--                                onclick="findBookById(${book.id},'borrow')"> 借阅--%>
<%--                        </button>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${book.status ==1 ||book.status ==2}">--%>
<%--                        <button type="button" class="btn bg-olive btn-xs" disabled="true">借阅</button>--%>
<%--                    </c:if>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
    </table>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/room_modal.jsp"></jsp:include>
</body>
</html>

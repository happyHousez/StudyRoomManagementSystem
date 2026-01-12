<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>自习室管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>

<body class="hold-transition skin-red sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">自习室预约</h3>

</div>
<div class="box-body">
    <%--新增按钮：如果当前登录用户是管理员，页面展示新增按钮--%>
    <c:if test="${USER_SESSION.role =='ADMIN'}">
        <div class="pull-left">
            <div class="form-group form-inline">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" title="新建" data-toggle="modal"
                            data-target="#addOrEditModal" onclick="resetFrom()"> 新增
                    </button>
                </div>
            </div>
        </div>
    </c:if>
    <%--新增按钮 /--%>
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/room/searchRooms" method="post">
                自习室名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp

                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <!--工具栏 数据搜索 /-->
    <!-- 数据列表 -->
        <div class="table-box">
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
                                <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                        data-target="#borrowModal" onclick="findBookById(${room.id},'borrow')"> 预约
                                </button>
                                <c:if test="${USER_SESSION.role =='ADMIN'}">
                                    <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                            data-target="#addOrEditModal" onclick="findBookById(${room.id},'edit')"> 编辑
                                    </button>
                                </c:if>
                            </c:if>
                            <c:if test="${room.status ==1 ||room.status ==2}">
                                <button type="button" class="btn bg-olive btn-xs" disabled="true">预约</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!--数据表格/-->
            <%--分页插件--%>
            <div id="pagination" class="pagination"></div>
        </div>

    <!--数据列表/-->
</div>
<!-- /.box-body -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/room_modal.jsp"></jsp:include>

<!-- 添加和编辑图书的模态窗口 -->
<div class="modal fade" id="addOrEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">自习室信息</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditBook">
                    <span><input type="hidden" id="ebid" name="id"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <%--图书的id,不展示在页面--%>
                        <tr>
                            <td  width="100px" >自习室名</td>
                            <td><input class="form-control" placeholder="自习室名称" name="name" id="ebname"></td>
                            <td>地址</td>
                            <td><input class="form-control" placeholder="地址" name="location" id="eblocation"></td>
                        </tr>
                        <tr>
                            <td>人数</td>
                            <td><input class="form-control" placeholder="人数" name="capacity" id="ebcapacity"></td>
                            <td>电话</td>
                            <td><input class="form-control" placeholder="电话" name="phone" id="ebphone"></td>
                        </tr>
                        <tr>
                            <td width="100px">开放时间</td>
                            <td><input class="form-control" placeholder="开放时间"  type="time" name="openTime" id="ebopenTime"></td>
                            <td width="100px">关闭时间<br/></td>
                            <td><input class="form-control" placeholder="关闭时间"  type="time"  name="closeTime" id="ebcloseTime"></td>
                        </tr>
                            <tr>
                                <td>介绍</td>
                                <td><input class="form-control" placeholder="介绍" name="description" id="ebdesc"></td>
                                <td>上架状态</td>
                                <td>
                                    <select class="form-control" id="ebstatus" name="status" >
                                        <option value="0">开放</option>
                                        <option value="2">维修</option>
                                    </select>
                                </td>
                            </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" disabled onclick="addOrEdit()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    /*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
    /*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
    /*分页插件页码变化时将跳转到的服务器端的路径*/
    pageargs.gourl = "${gourl}"
    /*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    bookVO.name = "${search.name}"
    <%--bookVO.author = "${search.author}"--%>
    <%--bookVO.press = "${search.press}"--%>
    /*分页效果*/
    pagination(pageargs);
</script>
</html>
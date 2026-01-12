<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 图书借阅信息的模态窗口，默认是隐藏的 -->
<div class="modal fade" id="borrowModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">自习室信息</h3>
            </div>
            <div class="modal-body">
                <form id="borrowBook">
                    <table class="table table-bordered table-striped" width="800px">
                        <%--图书的id，不展示在页面--%>
                        <span><input type="hidden" id="bid" name="id"></span>
                            <tr>
                                <td width="100rpx">自习室名</td>
                                <td><input class="form-control" readonly name="name" id="bname"></td>
                                <td>地址</td>
                                <td><input class="form-control" readonly name="location" id="blocation"></td>
                            </tr>
                            <tr>
                                <td>人数</td>
                                <td><input class="form-control" readonly name="capacity" id="bcapacity"></td>
                                <td>电话</td>
                                <td><input class="form-control"  readonly name="phone" id="bphone"></td>
                            </tr>
                            <tr>
                                <td>开放时间</td>
                                <td><input class="form-control"  readonly name="openTime" id="bopenTime"></td>
                                <td width="100rpx">预约时间<br/><span style="color: red">*</span></td>
                                <%--时间控件中的内容改变时，调用js文件中的cg()方法--%>
                                <td><input class="form-control" type="date" name="appointtime" id="time" onchange="cg()">
                                </td>
                            </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <%--点击保存按钮时，隐藏模态窗口并调用js文件中的borrow()方法--%>
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="borrow()"
                        disabled="true" id="savemsg">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

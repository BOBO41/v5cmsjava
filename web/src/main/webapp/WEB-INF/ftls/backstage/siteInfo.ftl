<#include "fragment/head.ftl">
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            站点管理
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li>站点设置</li>
            <li class="active">站点管理</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <div class="row">
            <div class="box box-danger">
                <div class="box-header">
                    <!-- tools box -->
                    <div class="pull-right box-tools">
                        <button id="addSite" class="btn btn-success btn-sm" data-toggle="tooltip" title="添加站点">
                            <i class="fa fa-plus"></i> 添加站点</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-table"></i>
                    <h3 class="box-title">站点信息表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <colgroup>
                            <col class="col-xs-1">
                            <col class="col-xs-2">
                            <col class="col-xs-1">
                            <col class="col-xs-2">
                            <col class="col-xs-1">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>名称</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if sites?size != 0>
                            <#list sites as site>
                            <tr>
                                <td>${site.tbId}</td>
                                <td>${site.siteName}</td>
                                <td>${(site.isclosesite==1)?string("正常","关闭")}</td>
                                <td>${site.createDate?string("yyyy-MM-dd")}</td>
                                <td>
                                    <a href="<@spring.url '/manager/addsite/${site.tbId}'/>">修改</a>
                                    <a href="javascript:;" class="deletesite" data-id="${site.tbId}">删除</a>
                                </td>
                            </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="5"><h3>还没有站点数据！</h3></td>
                            </tr>
                        </#if>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.row -->

    </section>
    <!-- /.content -->
</aside><!-- /.right-side -->
<#include "fragment/footer.ftl">
<script type="text/javascript">
    $(function(){
        $("#nav_siteSetting").imitClick();
        $("#addSite").click(function(){
            location.href="<@spring.url '/manager/addsite'/>";
        });
        $(".deletesite").click(function(){
            var tbId = $(this).attr("data-id");
            $.v5cms.confirm({icon:"question",content:"您确定要删除站点信息吗？",ok:function(){
                var url = "<@spring.url '/manager/deletesite/'/>" + tbId;
                $.get(url,function(data){
                    if(data.status == "1"){
                        $.v5cms.tooltip({icon:"succeed","content":data.message},function(){
                            location.reload();
                        });
                    }else{
                        $.v5cms.tooltip({icon:"error","content":data.message},function(){
                            location.reload();
                        });
                    }
                },"json");
            }});
        })
    });
</script>
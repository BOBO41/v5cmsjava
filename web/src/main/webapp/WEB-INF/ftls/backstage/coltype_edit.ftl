<#include "fragment/head.ftl">
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            栏目类型
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="<@spring.url '/manager/index'/>"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li>栏目管理</li>
            <li><a href="<@spring.url '/manager/coltype/list/1'/>">栏目类型</a></li>
            <li class="active">${page_title!"添加栏目类型"}</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <div class="row">
            <div class="box box-success">
                <div class="box-header">
                    <!-- tools box -->
                    <div class="pull-right box-tools">
                        <button id="saveColTypeForm" class="btn btn-success btn-sm" data-toggle="tooltip" title="保存">
                            <i class="fa fa-save"></i> 保存</button>
                        <button id="backColTypeList" class="btn btn-default btn-sm" data-toggle="tooltip" title="返回">
                            <i class="fa fa-mail-forward"></i> 返回</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-globe"></i>
                    <h3 class="box-title">${page_title!"添加栏目类型"}</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form id="colTypeForm" action="<@spring.url '/manager/coltype/edit'/>" class="form-horizontal" role="form" method="POST">
                        <input type="hidden" value="${columnType.colTypeId!""}" name="colTypeId">
                        <div class="form-group">
                            <label for="colTypeName" class="col-sm-2 control-label">栏目类型名称 <span style="color: #ff0000">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="colTypeName" id="colTypeName"
                                       placeholder="栏目类型名称" value="${columnType.colTypeName!""}">
                                <span class="help-block">设置版位的名称，方便日后管理。</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="coltpl" class="col-sm-2 control-label">栏目模板</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="coltpl" name="coltpl">
                                    <#list templates as template>
                                        <option value="${template.b!""}" <#if (columnType.coltpl!"")==(template.b!"")>selected</#if>>${template.a!""}</option>
                                    </#list>
                                </select>
                                <span class="help-block">设置栏目页面模板。</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="contenttpl" class="col-sm-2 control-label">栏目内容模板</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="contenttpldd" name="contenttpl">
                                    <#list templates as template>
                                        <option value="${template.b!""}" <#if (columnType.contenttpl!"")==(template.b!"")>selected</#if>>${template.a!""}</option>
                                    </#list>
                                </select>
                                <span class="help-block">设置栏目内容模板。</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isDisabled" class="col-sm-2 control-label">单页</label>
                            <div class="col-sm-3">
                                <label class="checkbox-inline" style="padding-left:0;">
                                    <input type="radio" name="hasContent" value="1" id="hasContent1" <#if columnType.hasContent==1>checked</#if>>
                                    <label for="available1">是</label>
                                    &nbsp;&nbsp;
                                    <input type="radio" name="hasContent" value="0" id="hasContent0" <#if columnType.hasContent==0>checked</#if>>
                                    <label for="available0">否</label>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isDisabled" class="col-sm-2 control-label">类型状态</label>
                            <div class="col-sm-3">
                                <label class="checkbox-inline" style="padding-left:0;">
                                    <input type="radio" name="isDisabled" value="1" id="isDisabled1" <#if columnType.isDisabled==1>checked</#if>>
                                    <label for="available1">可用</label>
                                    &nbsp;&nbsp;
                                    <input type="radio" name="isDisabled" value="0" id="isDisabled0" <#if columnType.isDisabled==0>checked</#if>>
                                    <label for="available0">禁用</label>
                                </label>
                            </div>
                        </div>
                    </form>
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
        $("#nav_columns").imitClick();
        $("#backColTypeList").click(function(){
            location.href="<@spring.url '/manager/coltype/list/1'/>"
        });

        $("#coltpl").chosen({disable_search_threshold: 10,width:'100%'});
        $("#contenttpldd").chosen({disable_search_threshold: 10,width:'100%'});

        $('#colTypeForm').ajaxForm({
            dataType : 'json',
            success : function(data) {
                if(data.status == "1"){
                    $.v5cms.tooltip({icon:"succeed",content:data.message},function(){
                        location.href="<@spring.url '/manager/coltype/list/1'/>";
                    });
                }else{
                    $.v5cms.tooltip({icon:"error",content:data.message},function(){});
                }
            },
            error:function(xhr, status, error){
                $.v5cms.tooltip({icon:"error",content:"错误代码：" + status + " 错误消息：" + error},function(){});
            }
        });

        $("#saveColTypeForm").click(function(){
            var result = $("#colTypeName").nonEmpty({content:"栏目类型名称不能为空！"});
            if(result) $("#colTypeForm").submit();
        });
    });
</script>
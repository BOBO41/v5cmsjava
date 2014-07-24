<#include "fragment/head.ftl">
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            广告管理
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="<@spring.url '/manager/index'/>"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li>站点设置</li>
            <li><a href="<@spring.url '/manager/sitelist'/>">广告管理</a></li>
            <li class="active">${page_title!"添加广告"}</li>
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
                        <button id="saveSiteForm" class="btn btn-success btn-sm" data-toggle="tooltip" title="保存">
                            <i class="fa fa-save"></i> 保存</button>
                        <button id="backAdvList" class="btn btn-default btn-sm" data-toggle="tooltip" title="返回">
                            <i class="fa fa-mail-forward"></i> 返回</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-globe"></i>
                    <h3 class="box-title">${page_title!"添加广告"}</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form id="siteForm" action="<@spring.url '/manager/ausite'/>" class="form-horizontal" role="form" method="POST">
                        <input type="hidden" value="" name="siteId">
                        <div class="form-group">
                            <label for="advName" class="col-sm-2 control-label">广告名称 <span style="color: #ff0000">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="advName" id="advName"
                                       placeholder="广告名称" value="">
                                <span class="help-block">设置广告名称。</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="advPosId" class="col-sm-2 control-label">广告版位 <span style="color: #ff0000">*</span></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="advPosId" id="advPosId"
                                       placeholder="广告版位" value="">
                                <#--<span class="help-block">设置站点的副标题，可以显示成title | 副标题的形式。</span>-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="advStartEndTime" class="col-sm-2 control-label">开始结束时间</label>
                            <div class="col-sm-4 input-group" style="padding-left: 15px;">
                                <input type="text" class="form-control" name="advStartEndTime" id="advStartEndTime"
                                       placeholder="开始结束时间" value="">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">启用 <span style="color: #ff0000">*</span></label>
                            <div class="col-sm-3">
                                <select class="form-control" id="startUsing" name="startUsing">
                                    <option value="1" selected>是</option>
                                    <option value="0">否</option>
                                <#--<#if (site.isclosesite!0) == 1>
                                    <option value="1" selected>是</option>
                                    <option value="0">否</option>
                                <#else>
                                    <option value="1">是</option>
                                    <option value="0" selected>否</option>
                                </#if>-->
                                </select>
                                <#--<span class="help-block">用于设置网站的运行状态，运行或者停止。</span>-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">类型 <span style="color: #ff0000">*</span></label>
                            <div class="col-sm-8">
                                <!-- Custom tabs (Charts with tabs)-->
                                <div class="nav-tabs-custom">
                                    <input type="hidden" id="hidden_advType" name="advType" value="1">
                                    <!-- Tabs within a box -->
                                    <ul class="nav nav-tabs" id="advType">
                                        <li class="active">
                                            <a href="#image-type" data-advtype="1" data-toggle="tab">图片</a>
                                        </li>
                                        <li><a href="#flash-type" data-advtype="2" data-toggle="tab">Flash</a></li>
                                        <li><a href="#text-type" data-advtype="3" data-toggle="tab">文字</a></li>
                                        <li><a href="#code-type" data-advtype="4" data-toggle="tab">代码</a></li>
                                    </ul>
                                    <div class="tab-content no-padding">
                                        <!-- Morris chart - Sales -->
                                        <div class="chart tab-pane active" id="image-type" style="position: relative;">
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">图片上传</label>
                                            <div class="col-sm-5" style="padding: 5px 0;">
                                                <div id="advImageUpload">图片上传</div>
                                                <#--<input type="button" id="advImageUpload" class="btn btn-success" value="图片上传">-->
                                            </div>
                                        </div>
                                        </div>
                                        <div class="chart tab-pane" id="flash-type" style="position: relative; height: 300px;"></div>
                                        <div class="chart tab-pane" id="text-type" style="position: relative; height: 300px;"></div>
                                        <div class="chart tab-pane" id="code-type" style="position: relative; height: 300px;"></div>
                                    </div>
                                </div><!-- /.nav-tabs-custom -->
                            </div>
                        </div>

                        <#--<div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">统计代码</label>
                            <div class="col-sm-8">
                                <!-- Custom tabs (Charts with tabs)-->
                                <#--<div class="nav-tabs-custom">
                                    <input type="hidden" id="hidden_advType" name="advType" value="1">
                                    <!-- Tabs within a box &ndash;&gt;
                                    <ul class="nav nav-tabs" id="advType">
                                        <li class="active">
                                            <a href="#image-type" data-advtype="1" data-toggle="tab">图片</a>
                                        </li>
                                        <li><a href="#flash-type" data-advtype="2" data-toggle="tab">Flash</a></li>
                                        <li><a href="#text-type" data-advtype="3" data-toggle="tab">文字</a></li>
                                        <li><a href="#code-type" data-advtype="4" data-toggle="tab">代码</a></li>
                                    </ul>
                                    <div class="tab-content no-padding">
                                        <!-- Morris chart - Sales &ndash;&gt;
                                        <div class="chart tab-pane active" id="image-type" style="position: relative; height: 300px;"></div>
                                        <div class="chart tab-pane" id="flash-type" style="position: relative; height: 300px;"></div>
                                        <div class="chart tab-pane" id="text-type" style="position: relative; height: 300px;"></div>
                                        <div class="chart tab-pane" id="code-type" style="position: relative; height: 300px;"></div>
                                    </div>
                                </div><!-- /.nav-tabs-custom &ndash;&gt;
                            </div>
                        </div>-->
                        <#--<div class="form-group">-->
                            <#--<label for="inputPassword3" class="col-sm-2 control-label">底部信息</label>-->
                            <#--<div class="col-sm-5">-->
                                <#--<textarea class="form-control" name="siteFooterInfo" id="siteFooterInfo" rows="3">${site.siteFooterInfo!""}</textarea>-->
                                <#--<span class="help-block">用于保存网站的底部信息，支持HTML。</span>-->
                            <#--</div>-->
                        <#--</div>-->

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
        $("#nav_siteSetting").imitClick();
        $("#backAdvList").click(function(){
            location.href="<@spring.url '/manager/advlist'/>"
        });

        $("#advStartEndTime").daterangepicker({
            format: 'YYYY年MM月DD日',
        });

        $("#advType").on("shown.bs.tab",function(e){
            var advType = $(e.target).data("advtype");
            $("#hidden_advType").val(advType);
        });

        var uploadImage = WebUploader.create({

            // swf文件路径
            swf: "<@spring.url '/r/js/Uploader.swf'/>",
            auto: true,
            // 文件接收服务端。
            server: '<@spring.url '/manager/advupload'/>',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#advImageUpload',

            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploadImage.on( 'uploadSuccess', function( file,response ) {
            $( '#'+file.id ).find('p.state').text('已上传');
        });

        uploadImage.on( 'uploadError', function( file,reason  ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });
        $('#siteForm').ajaxForm({
            dataType : 'json',
            success : function(data) {
                if(data.status == "1"){
                    $.v5cms.tooltip({icon:"succeed",content:data.message},function(){
                        location.href="<@spring.url '/manager/siteList'/>";
                    });
                }else{
                    $.v5cms.tooltip({icon:"error",content:data.message},function(){});
                }
            }
        });

        $("#saveSiteForm").click(function(){
            var result = $("#siteName").nonEmpty({content:"网站名称不能为空！"});
            if(result) $("#siteForm").submit();
        });
    });
</script>
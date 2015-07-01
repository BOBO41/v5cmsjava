<#include "../fragment/head.ftl">
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            模板管理
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">模板管理</li>
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
                        <button id="addColType" class="btn btn-success btn-sm" data-toggle="tooltip" title="添加模板">
                            <i class="fa fa-plus"></i> 添加模板</button>
                        <button id="advPosBatchDelete" class="btn btn-warning btn-sm" data-toggle="tooltip" title="批量删除">
                            <i class="fa fa-trash-o"></i> 批量删除</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-table"></i>
                    <h3 class="box-title">模板列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="thumbnail thumbnail-style">
                                <a class="fancybox-button zoomer" data-rel="fancybox-button" title="模板图片" href="assets/img/main/9.jpg">
                                <span class="overlay-zoom">
                                    <img class="img-responsive" data-src="<@spring.url '/r/js/holder.js'/>/100%x220/text:模板图片" alt="" />
                                    <span class="zoom-icon"></span>
                                </span>
                                </a>
                                <div class="caption">
                                    <h3><a class="hover-effect" href="#">模板1</a></h3>
                                    <p class="v5-p-word-wrap"><strong>简介：</strong>家居装修行业整站企业网站模板，全套模板，DIV+CSS布局，包含404.html，archives.html，article.html。</p>
                                </div>
                                <div class="caption text-center">
                                    <button class="btn btn-warning tpl-edit" data-tplname="" style="margin-right: 10px;">
                                        <i class="fa fa-edit"></i>
                                        编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <i class="fa fa-check-square-o"></i>
                                        启用
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="thumbnail thumbnail-style">
                                <a class="fancybox-button zoomer" data-rel="fancybox-button" title="模板图片" href="assets/img/main/9.jpg">
                                <span class="overlay-zoom">
                                    <img class="img-responsive" data-src="<@spring.url '/r/js/holder.js'/>/100%x220/text:模板图片" alt="" />
                                    <span class="zoom-icon"></span>
                                </span>
                                </a>
                                <div class="caption">
                                    <h3><a class="hover-effect" href="#">模板2</a></h3>
                                    <p class="v5-p-word-wrap"><strong>简介：</strong>家居装修行业整站企业网站模板，全套模板，DIV+CSS布局，包含404.html，archives.html，article.html。</p>
                                </div>
                                <div class="caption text-center">
                                    <button class="btn btn-warning tpl-edit" data-tplname="" style="margin-right: 10px;">
                                        <i class="fa fa-edit"></i>
                                        编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <i class="fa fa-check-square-o"></i>
                                        启用
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="thumbnail thumbnail-style">
                                <a class="fancybox-button zoomer" data-rel="fancybox-button" title="模板图片" href="assets/img/main/9.jpg">
                                <span class="overlay-zoom">
                                    <img class="img-responsive" data-src="<@spring.url '/r/js/holder.js'/>/100%x220/text:模板图片" alt="" />
                                    <span class="zoom-icon"></span>
                                </span>
                                </a>
                                <div class="caption">
                                    <h3><a class="hover-effect" href="#">模板3</a></h3>
                                    <p class="v5-p-word-wrap"><strong>简介：</strong>家居装修行业整站企业网站模板，全套模板，DIV+CSS布局，包含404.html，archives.html，article.html。</p>
                                </div>
                                <div class="caption text-center">
                                    <button class="btn btn-warning tpl-edit" data-tplname="" style="margin-right: 10px;">
                                        <i class="fa fa-edit"></i>
                                        编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <i class="fa fa-check-square-o"></i>
                                        启用
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="thumbnail thumbnail-style">
                                <a class="fancybox-button zoomer" data-rel="fancybox-button" title="模板图片" href="assets/img/main/9.jpg">
                                <span class="overlay-zoom">
                                    <img class="img-responsive" data-src="<@spring.url '/r/js/holder.js'/>/100%x220/text:模板图片" alt="" />
                                    <span class="zoom-icon"></span>
                                </span>
                                </a>
                                <div class="caption">
                                    <h3><a class="hover-effect" href="#">模板4</a></h3>
                                    <p class="v5-p-word-wrap"><strong>简介：</strong>家居装修行业整站企业网站模板，全套模板，DIV+CSS布局，包含404.html，archives.html，article.html。</p>
                                </div>
                                <div class="caption text-center">
                                    <button class="btn btn-warning tpl-edit" data-tplname="" style="margin-right: 10px;">
                                        <i class="fa fa-edit"></i>
                                        编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <i class="fa fa-check-square-o"></i>
                                        启用
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="thumbnail thumbnail-style">
                                <a class="fancybox-button zoomer" data-rel="fancybox-button" title="模板图片" href="assets/img/main/9.jpg">
                                <span class="overlay-zoom">
                                    <img class="img-responsive" data-src="<@spring.url '/r/js/holder.js'/>/100%x220/text:模板图片" alt="" />
                                    <span class="zoom-icon"></span>
                                </span>
                                </a>
                                <div class="caption">
                                    <h3><a class="hover-effect" href="#">模板5</a></h3>
                                    <p class="v5-p-word-wrap"><strong>简介：</strong>家居装修行业整站企业网站模板，全套模板，DIV+CSS布局，包含404.html，archives.html，article.html。</p>
                                </div>
                                <div class="caption text-center">
                                    <button class="btn btn-warning tpl-edit" data-tplname="" style="margin-right: 10px;">
                                        <i class="fa fa-edit"></i>
                                        编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <i class="fa fa-check-square-o"></i>
                                        启用
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<#include "../fragment/footer.ftl">
<script type="text/javascript">
    $(function(){
        $.v5cms.initFancybox();
        $(".tpl-edit").click(function(){
            var tplName = $(this).data('tplname');
            location.href="<@spring.url '/manager/tpl/edit'/>"
        });
        <#--$("#addColType").click(function(){-->
            <#--location.href="<@spring.url '/manager/coltypeaup'/>";-->
        <#--});-->

        /*function deleteSites(siteIds) {
             $.v5cms.confirm({icon:"question",content:"您确定要删除站点信息吗，删除后将不能恢复？",width:350,ok:function(){
                 <#--var url = "<@spring.url '/manager/deletesite'/>";-->
                $.post(url,{siteIds:siteIds},function(data){
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
        }

        $(".deletesite").click(function(){
            var siteId = $(this).data("siteid");
            deleteSites(siteId);
        });

        $("#thcheckbox").on('ifChecked', function(event){
            $('.table-cb').iCheck('check');
        });
        $("#thcheckbox").on('ifUnchecked', function(event){
            $('.table-cb').iCheck('uncheck');
        });

        $("#siteBatchDelete").click(function(){
            var $chs = $(":checkbox[checked=checked]");
            if($chs.length == 0){
                $.v5cms.modalDialog({icon:'warning',content:"您还没有选中要操作的数据项！",width:250});
                return;
            }
            var siteIds = [];
            for(var i=0;i<$chs.length;i++){
                var v = $($chs[i]).val();
                if(v == "on") continue;
                siteIds.push(v);
            }
            deleteSites(siteIds.join());
        });*/
    });
</script>
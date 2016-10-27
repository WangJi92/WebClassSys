/**
 * Created by JetWang on 2016/10/16.
 */

define(basePath + "/views/usermanage/index/index",
    [basePath + "/views/usermanage/dialog/add/js/user_add",
        basePath + "/views/usermanage/service/userService",
        basePath + "/views/usermanage/dialog/edit/js/user_edit"
    ],
    function(require, exports,module) {
        var $tableUserManage;
        var $addDialog = require(basePath + "/views/usermanage/dialog/add/js/user_add");
        var $editDialog =require(basePath + "/views/usermanage/dialog/edit/js/user_edit");
        var $userService =require(basePath + "/views/usermanage/service/userService");
        /**
         * 这个事件要写在前面一点
         * @type {{click .like: Function}}
         */
        var operateEvents = {
            'click .like': function (e, value, row, index) {
                $editDialog.editUserDialog($tableUserManage,row);
            }
        };
        //设置 bootstrap-tablb 配置信息
        var options ={
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [10, 15, 20, 25],  //记录数可选列表
            search: true,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            toolbar:"#toolbar",//设置旁边的框框的属性
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url:basePath+"/UserInfo/userInfoPage",
            responseHandler:function(res){
                //远程数据加载之前,处理程序响应数据格式,对象包含的参数: 我们可以对返回的数据格式进行处理
                //在ajax后我们可以在这里进行一些事件的处理
                return res.data;
            },
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    //这里是在ajax发送请求的时候设置一些参数
                    pageNo: params.pageNumber,
                    pageSize: params.pageSize,
                    search:params.searchText
                };
                return param;
            },
            onLoadSuccess: function(data){  //加载成功时执行

            },
            onLoadError: function(){  //加载失败时执行

            },
           // height: getHeight(),
            formatSearch:function(){
                return "登录名密码手机.."
            },
            columns: [
                {
                    field: 'state',
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '登录名',
                    field: 'userName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'userTypeStr',
                    title: '用户类型',
                    align: 'center'
                }, {
                    field: 'password',
                    title: '密码',
                    align: 'center'
                },
                {
                    field: 'phone',
                    title: '电话号码',
                    align: 'center'
                },{
                    field: 'operate',
                    title: '编辑',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
        };
        function operateFormatter(value, row, index) {
            return [
                '<a class="like"  href="javascript:void(0)" title="编辑">',
                '编辑',
                '</a>  '
            ].join('');
        }
        function getHeight() {
            return $(window).height() - 20;
        }
        function initEvent(){
            $tableUserManage = $("#table").bootstrapTable(options);

            $(".dic_add").click(function(){//添加
                $addDialog.opendAddDialog($tableUserManage);
            });
            $(".dic_delete").click(function () {//删除
                var ids = getIdSelections();
                console.log("删除的ids"+ids)
               if(ids.length>0){
                   $userService.deleteByIds(ids,function(data){
                 if(data.success ==true){
                     var dialogTip = new BootstrapDialog({
                         message: "删除成功",
                         cssClass: "width200-dialog",
                         onshow: function (diaRef) {
                             setTimeout(function () {
                                 diaRef.close();
                             }, 1500);
                         }
                     });
                     dialogTip.realize();
                     dialogTip.getModalHeader().hide();
                     dialogTip.getModalFooter().hide();
                     dialogTip.open();
                     $tableUserManage.bootstrapTable('refresh');//刷新
                 }else{
                     var dialogTip = new BootstrapDialog({
                         message: data.message,
                         cssClass: "width200-dialog",
                         onshow: function (diaRef) {
                             setTimeout(function () {
                                 diaRef.close();
                             }, 1500);
                         }
                     });
                     dialogTip.realize();
                     dialogTip.getModalHeader().hide();
                     dialogTip.getModalFooter().hide();
                     dialogTip.open();
                 }
                 });
                 }
            });

            $(".dic_export").click(function(){//导出excel
                window.open(basePath+"/UserInfo/exportUserInfo");
            });
        }
        function getIdSelections() {
            return $.map($tableUserManage.bootstrapTable('getSelections'), function (row) {
                console.log(row);
                return row.id
            });
        }
        initEvent();

    });
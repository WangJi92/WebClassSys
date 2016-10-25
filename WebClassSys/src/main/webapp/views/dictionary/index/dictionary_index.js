/**
 * Created by JetWang on 2016/10/12.
 */
define(basePath + "/views/dictionary/index/dictionary_index",
    ["mustache",
        basePath+"/views/dictionary/dialog/add/js/dic_add",
        basePath+"/views/dictionary/dialog/edit/js/dic_edit",
        basePath+"/views/dictionary/service/dictionaryService"
    ],
    function(require, exports,module) {

        var $dicService = require(basePath+"/views/dictionary/service/dictionaryService");
        var $dicAddDialog = require(basePath+"/views/dictionary/dialog/add/js/dic_add");
        var $dicEditDialog =require(  basePath+"/views/dictionary/dialog/edit/js/dic_edit");
         var $tableDic;

        /**
         * 这个事件要写在前面一点
         * @type {{click .like: Function}}
         */
        var operateEvents = {
            'click .like': function (e, value, row, index) {
                $dicEditDialog.openEditDialog(row,$tableDic);
            }
        };
        //设置 bootstrap-tablb 配置信息
        var options ={
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 15,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [10, 15, 20, 25],  //记录数可选列表
            search: true,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            toolbar:"#toolbar",//设置旁边的框框的属性
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url:basePath+"/dictionary/findPage",
            formatSearch:function(){
                return "分类类型名称.."
            },
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
            height: getHeight(),
            columns: [
                {
                    field: 'state',
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    formatter: stateFormatter
                }, {
                    title: '分类类型',
                    field: 'classfiyType',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'name',
                    title: '字典名称',
                    align: 'center'
                }, {
                    field: 'value',
                    title: '字典值',
                    align: 'center'
                },
                {
                    field: 'fixed',
                    title: '是否固定',
                    align: 'center',
                    formatter:function(value,row,index){
                         if(value ==1){
                             return "是";
                         }
                        return "否";
                    }
                },{
                    field: 'fatherState',
                    title: '是否根节点',
                    sortable: true,
                    align: 'center',
                    formatter:function(value,row,index){
                        if(value ==1){
                            return "是";
                        }
                        return "否";
                    }
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
            if(row.fatherState ==1 || row.fixed==1){
                return "";
            }
            return [
                '<a class="like"  href="javascript:void(0)" title="编辑">',
                '编辑',
                '</a>  '
            ].join('');
        }
        function getHeight() {
            return $(window).height() - 20;
        }
        function stateFormatter(value, row, index) {
            if(row.fixed == 1 || row.fatherState){
                return {
                    disabled:true
                }
            }
        }



        function initEvent(){
            $tableDic = $("#table").bootstrapTable(options);

            $(".dic_add").click(function(){
                $dicAddDialog.openAddDialog($tableDic);
            });
            $(".dic_delete").click(function () {
                var ids = getIdSelections();
                if(ids.length>0){
                    $dicService.deleteByIds(ids,function(data){
                        if(data.success ==true){
                            $tableDic.bootstrapTable('refresh');//刷新
                        }else{
                            console.log("error");
                        }
                    });
                }

            });
            $(".dic_export").click(function(){
                window.open(basePath+"/dictionary/exportdic");
            });
        }
        function getIdSelections() {
            return $.map($tableDic.bootstrapTable('getSelections'), function (row) {
                return row.id
            });
        }
       initEvent();

    });
/**
 * Created by JetWang on 2016/10/30.
 */

define(basePath + "/views/buildingmanage/addBuilding/dialog/userListByType/js/userList_index",
    [basePath + "/views/buildingmanage/service/buildingservice",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function (require, exports, module) {
        var buildingService = require(basePath + "/views/buildingmanage/service/buildingservice");
        var $uerTable;
        var options = {
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 5,  //每页显示的记录数
            pageNumber: 1, //当前第几页
            pageList: [3, 4,5, 6],  //记录数可选列表
            search: true,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url: basePath + "/UserInfo/userInfoPage",
            responseHandler: function (res) {
                //远程数据加载之前,处理程序响应数据格式,对象包含的参数: 我们可以对返回的数据格式进行处理
                //在ajax后我们可以在这里进行一些事件的处理
                return res.data;
            },
            queryParamsType: "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    //这里是在ajax发送请求的时候设置一些参数
                    pageNo: params.pageNumber,
                    pageSize: params.pageSize,
                    search: params.searchText
                };
                return param;
            },
            onLoadSuccess: function (data) {  //加载成功时执行

            },
            onLoadError: function () {  //加载失败时执行

            },
            // height: getHeight(),
            formatSearch: function () {
                return "姓名手机.."
            },
            columns: [
                {
                    field: 'state',
                    radio: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '姓名',
                    field: 'userName',
                    align: 'center',
                    valign: 'middle'
                },

                {
                    field: 'phone',
                    title: '电话号码',
                    align: 'center'
                }
            ]
        };

        function initEvent() {
            var value =$("#usertype").val();
            options = $.extend(options,{
                queryParams:function queryParams(params) {   //设置查询参数
                    var param = {
                        //这里是在ajax发送请求的时候设置一些参数
                        pageNo: params.pageNumber,
                        pageSize: params.pageSize,
                        search: params.searchText,
                        userType:value
                    };
                    return param;
                }
            });
            $uerTable = $("#table_user").bootstrapTable(options);

        }

        function getIdSelections() {
            return $.map($uerTable.bootstrapTable('getSelections'), function (row) {
                console.log(row);
                return row.id
            });
        }
        function getSelectOneInfo(){
            return $uerTable.bootstrapTable('getSelections');
        }
        module.exports = {
            initEvent: initEvent,
            getSelect: getSelectOneInfo

        }
    });

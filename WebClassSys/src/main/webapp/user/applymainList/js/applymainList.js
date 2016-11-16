/**
 * Created by JetWang on 2016/11/16.
 */

define(basePath + "/user/applymainList/js/applymainList",
    [
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table-locale-all",
        basePath + "/views/dictionary/service/dictionaryService",
    ],
    function (require, exports, module) {
        var $dictionaryService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $applymaintaintable;
        var options = {
                striped: true,  //表格显示条纹
                pagination: true, //启动分页
                pageSize: 10,  //每页显示的记录数
                pageNumber: 1, //当前第几页
                pageList: [10, 15, 20, 25],  //记录数可选列表
                search: false,  //是否启用查询
                showColumns: false,  //显示下拉框勾选要显示的列
                showRefresh: false,  //显示刷新按钮
                sidePagination: "server", //表示服务端请求
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                url: basePath + "/applyMaintainAction/getpage",
                responseHandler: function (res) {
                    //远程数据加载之前,处理程序响应数据格式,对象包含的参数: 我们可以对返回的数据格式进行处理
                    //在ajax后我们可以在这里进行一些事件的处理
                    return res.data;
                },
                queryParamsType: "undefined",
                queryParams: function queryParams(params) {   //设置查询参数
                    /**
                     * todo applyPeople 这里还没有写啊！
                     * @type {{pageNo: *, pageSize: *, applyPeople: (*|jQuery), state: (*|jQuery), search: (*|jQuery)}}
                     */
                    var param = {
                        //这里是在ajax发送请求的时候设置一些参数
                        pageNo: params.pageNumber,
                        pageSize: params.pageSize,
                        applyPeople: $("select[name='applyPeople']").attr("value"),
                        state: $("select[name='state']").attr("value"),
                        search: $(".search_text").val(),
                    };
                    return param;
                },
                onLoadSuccess: function (data) {  //加载成功时执行

                }
                ,
                onLoadError: function () {  //加载失败时执行

                },
                columns: [
                    {
                        field: 'createTimeStr',
                        title: '报修日期',
                        align: 'center'
                    },
                    {
                        field: 'applyDetail',
                        title: '报修详情',
                        align: 'center'
                    },
                    {
                        field: 'stateStr',
                        title: '状态',
                        align: 'center'
                    },
                    {
                        field: 'classRoomName',
                        title: '教室名称',
                        align: 'center'
                    },
                    {
                        field: 'maintainInfo',
                        title: '维修人员信息',
                        align: 'center',
                        formatter: maintainInfoFormatter
                    },
                    {
                        field: 'adminAddrice',
                        title: '备注信息',
                        align: 'center'
                    }
                ]
            };
        function maintainInfoFormatter(value, row, index) {
            if((row.maintainPeople == "" && row.maintainPeoplePhone =="")||(row.maintainPeople ==null && row.maintainPeoplePhone ==null)){
                return "信息为空";
            }
            return "姓名:" + row.maintainPeople + " |电话:" + row.maintainPeoplePhone;
        }
        function initEvent() {
            $applymaintaintable = $("#table_apply").bootstrapTable(options);
            $(".applymaintain_btn_search").click(function () {
                $applymaintaintable.bootstrapTable("refresh")
            });
            $(".search_text").keydown(function(e){
                if(e.keyCode ==13){//触发键盘事件enter 防止冒泡产生
                    $applymaintaintable.bootstrapTable("refresh")
                    return false;
                }
            });
        }
        module.exports = {
            initEvent:initEvent
        }
    })
;
/**
 * Created by JetWang on 2016/11/18.
 */

define(basePath + "/views/applyclassroommanage/applyclassroomindex/applyclassroomindex",
    [
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table-locale-all",
        basePath + "/views/applyclassroommanage/service/applyclassroomservice",
        basePath + "/views/applyclassroommanage/dialog/editApplyInfo/js/editApplyDialog"
    ],
    function (require, exports, module) {
        var $applyclassroomservice = require(basePath + "/views/applyclassroommanage/service/applyclassroomservice");
        var $editDialog = require(basePath + "/views/applyclassroommanage/dialog/editApplyInfo/js/editApplyDialog");
        var $applyclasroomstable;
        var operateEvents = {
            'click .approve': function (e, value, row, index) {
                $editDialog.openDialog(row,$applyclasroomstable);
            },//审批
            'click .edit': function (e, value, row, index) {
                $editDialog.openDialog(row,$applyclasroomstable);
            },//编辑
        };
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
            url: basePath + "/applyClassRoomAction/getPage",
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
                    title: '申请日期',
                    align: 'center'
                },
                {
                    field: 'applyInfo',
                    title: '申请人信息',
                    align: 'center',
                    formatter: applyInfoFormatter
                },
                {
                    field: 'applyReason',
                    title: '申请原因',
                    align: 'center'
                },
                {
                    field: 'purposeStr',
                    title: '用途',
                    align: 'center'
                },
                {
                    field: 'classRoomName',
                    title: '教室名称',
                    align: 'center'
                },
                {
                    field: 'whichLesson',
                    title: '申请使用时间',
                    align: 'center',

                },
                {
                    field: 'realLessonTime',
                    title: '实际时间',
                    align: 'center',
                    formatter: realLessonTimeFormatter
                },
                {
                    field: 'stateStr',
                    title: '申请状态',
                    align: 'center',
                },
                {
                    field: 'handleAddvice',
                    title: '处理意见',
                    align: 'center',
                },
                {
                    field: 'operate',
                    title: '处理申请',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }

            ]
        };
        function operateFormatter(value,row,index){
            if(row.state == 4){
                return "撤销成功";
            }//撤销申请
           else if(row.state ==0){
                return [
                    '<a class="approve"  href="javascript:void(0)" title="审批">',
                    '审批',
                    '</a>  '
                ].join('');
            } //申请中
            else if(row.state == 1 || row.state == 2){
                return [
                    '<a class="edit"  href="javascript:void(0)" title="编辑">',
                    '编辑',
                    '</a>  '
                ].join('');
            }//申请成功 失败 编辑

        }
        function realLessonTimeFormatter(value, row, index){
            return "【"+ row.realLessonTime+"】";
        }
        function applyInfoFormatter(value, row, index) {
            if((row.applicant == "" && row.phone =="")||(row.applicant ==null && row.phone ==null)){
                return "信息为空";
            }
            return "【姓名:" + row.applicant + " 电话:" + row.phone+"】";
        }
        function initEvent() {
            $applyclasroomstable = $("#table_apply").bootstrapTable(options);
            $(".applyclassroom_btn_search").click(function () {
                $applyclasroomstable.bootstrapTable("refresh")
            });
            $(".apply_export").click(function(){
                var str="state="+$("select[name='state']").val()+"&search="+$(".search_text").val();
                window.open(basePath + "/applyClassRoomAction/exportExcell?" + str);
            });
            $(".search_text").keydown(function(e){
                if(e.keyCode ==13){//触发键盘事件enter 防止冒泡产生
                    $applyclasroomstable.bootstrapTable("refresh")
                    return false;
                }
            });
            $applyclassroomservice.applyState(function(result){
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='state']").append("<option value='" + value.value + "'>" + value.key + "</option>");

                    });
                }
            })
        }
        module.exports = {
            initEvent:initEvent
        }
    })
;
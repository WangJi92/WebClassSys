/**
 * Created by JetWang on 2016/11/16.
 */

define(basePath + "/user/applyclassroom/applyclassroomindex/applyclassroom",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table-locale-all",
        basePath + "/user/applyclassroom/dialog/applyclassroomdialog/js/applyclassroomdialog"
    ],
    function (require, exports, module) {
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $applyDialog = require(basePath + "/user/applyclassroom/dialog/applyclassroomdialog/js/applyclassroomdialog");
        var $applyclassroomtable;
        var operateEvents = {
            'click .apply': function (e, value, row, index) {
                $applyDialog.openDialog(row,$applyclassroomtable);
            },
            'click .openClassRoomDetail': function (e, value, row, index) {
                window.location.href = basePath+"/user/classroomtableInfo/classroomtableInfo.jsp?classRoomName="+row.classRoomName;
            }
        };
        var timetableoptions = {
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber: 1, //当前第几页
            pageList: [10, 15, 20, 25],  //记录数可选列表
            search: false,  //是否启用查询
            showColumns: false,  //显示下拉框勾选要显示的列
            showRefresh: false,  //显示刷新按钮
            // toolbar:"#toolbar_timetable",//设置旁边的框框的属性
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url: basePath + "/timeTableAction/pageInfo",
            responseHandler: function (res) {
                //远程数据加载之前,处理程序响应数据格式,对象包含的参数: 我们可以对返回的数据格式进行处理
                //在ajax后我们可以在这里进行一些事件的处理
                return res.data;
            },
            queryParamsType: "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var re = /^[0-9]+.?[0-9]*$/;
                var setNo=0;
                if(!re.test($("*[name='seatNo']").attr("value"))|| $("*[name='seatNo']").attr("value") == ""){
                    setNo =0;
                }else{
                    setNo =$("*[name='seatNo']").attr("value");
                }
                var param = {
                    //这里是在ajax发送请求的时候设置一些参数
                    pageNo: params.pageNumber,
                    pageSize: params.pageSize,
                    whichWeek: $("*[name='whichWeek']").attr("value"),
                    whichDay: $("*[name='whichDay']").attr("value"),
                    whichLesson: $("*[name='whichLesson']").attr("value"),
                    classRoomIndexcode: $("*[name='indexCode']").attr("value"),
                    classType:$("*[name='classType']").attr("value"),
                    seatNo:setNo,
                    timetableType:$("*[name='timetableType']").attr("value"),
                    classRoomName:$("*[name='classRoomName']").attr("value")
                };
                return param;
            },
            onLoadSuccess: function (data) {  //加载成功时执行

            },
            onLoadError: function () {  //加载失败时执行

            },
            columns: [
               {
                    title: '教室名称',
                    field: 'classRoomName',
                    align: 'center',
                    valign: 'middle',
                   events: operateEvents,
                   formatter: classRoomFormatter
                },
                {
                    title: '课程时间',
                    field: 'lessontime',
                    align: 'center',
                    valign: 'middle',
                    formatter: lessontimeFormatter
                },
                {
                    field: 'classTypeName',
                    title: '教室内型',
                    align: 'center'
                }, {
                    field: 'seatNo',
                    title: '座位数',
                    align: 'center'
                },
                {
                    field: 'typeStr',
                    title: '使用状态',
                    align: 'center'
                }, {
                    field: 'operate',
                    title: '编辑',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
        };
        function classRoomFormatter(value,row,index){
            return [
                '<a class="openClassRoomDetail"  href="javascript:void(0)" title="点击查看教室详情">',
                 row.classRoomName,
                '</a>  '
            ].join('');
        }
        function lessontimeFormatter(value, row, index) {
          return "【"+row.weekStr+"*"+row.dayStr+"*"+row.lessonStr+"】";
        }

        function operateFormatter(value, row, index) {
            if (row.type != 1) {
                return "不能申请";
            }
            return [
                '<a class="apply"  href="javascript:void(0)" title="申请教室">',
                '申请教室',
                '</a>  '
            ].join('');
        }

        function initEvent() {
            $applyclassroomtable = $("#timetable").bootstrapTable(timetableoptions);
            $(".col-sm-12").on("click", ".timetable_btn_search", function () {
                $applyclassroomtable.bootstrapTable("refresh");
            });
            $dicService.getDicSelectByName("DIC_CLASSSTATE", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='timetableType']").append("<option value='" + value.value + "'>" + value.key + "</option>");

                    });
                }
            });
            $dicService.getDicSelectByName("DIC_CLASSROOM", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='classType']").append("<option value='" + value.value + "'>" + value.key + "</option>");

                    });
                }
            });
        }

        module.exports = {
            initEvent: initEvent
        }


    });
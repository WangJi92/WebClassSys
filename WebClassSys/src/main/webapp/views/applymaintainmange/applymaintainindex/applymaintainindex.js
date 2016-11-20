/**
 * Created by JetWang on 2016/11/15.
 */

define(basePath + "/views/applymaintainmange/applymaintainindex/applymaintainindex",
    [basePath + "/views/applymaintainmange/service/applymaintainservice",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table-locale-all",
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/views/applymaintainmange/dialog/assign/js/assign",
        basePath + "/views/applymaintainmange/dialog/dealsuccess/js/dealsuccess"
    ],
    function (require, exports, module) {
        var $assignDialog = require(basePath + "/views/applymaintainmange/dialog/assign/js/assign");
        var $dictionaryService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $applymaintainservice = require(basePath + "/views/applymaintainmange/service/applymaintainservice");
        var $dealSuccessDialog  = require(basePath + "/views/applymaintainmange/dialog/dealsuccess/js/dealsuccess");
        var operateEvents = {
            'click .assign': function (e, value, row, index) {//分派人员信息
                $assignDialog.opendDialog(row,$applymaintaintable);
            },
            'click .ok': function (e, value, row, index) {//修复完成
                $dealSuccessDialog.opendDialog(row,$applymaintaintable);
            },
        };

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
                    var param = {
                        //这里是在ajax发送请求的时候设置一些参数
                        pageNo: params.pageNumber,
                        pageSize: params.pageSize,
                        type: $("select[name='type']").attr("value"),
                        uergencyState: $("select[name='uergencyState']").attr("value"),
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
                formatSearch: function () {
                    return "名称品牌简介.."
                }
                ,
                columns: [
                    {
                        title: '申请者信息',
                        field: 'applyInfo',
                        align: 'center',
                        valign: 'middle',
                        formatter: applyFormatter
                    },
                    {
                        field: 'applyDetail',
                        title: '报修详情',
                        align: 'center'
                    },
                    {
                        field: 'createTimeStr',
                        title: '报修日期',
                        align: 'center'
                    },
                    {
                        field: 'typeStr',
                        title: '维修类型',
                        align: 'center'
                    }, {
                        field: 'uergencyStateStr',
                        title: '紧急程度',
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
                    },
                    {
                        field: 'operate',
                        title: '编辑',
                        align: 'center',
                        events: operateEvents,
                        formatter: operateFormatter
                    }
                ]
            }
            ;

        function operateFormatter(value, row, index) {
            if(row.state== 1){
                return [
                    '<a class="assign"  href="javascript:void(0)" title="分派维修人员">',
                    '分派维修人员',
                    '</a>',
                    '&nbsp;&nbsp;&nbsp;',
                    '<a class="ok"  href="javascript:void(0)" title="已处理完成">',
                    '已处理完成',
                    '</a>  '
                ].join('');
            }else if(row.state == 2){
               return ['<a class="ok"  href="javascript:void(0)" title="已处理完成">',
                         '已处理完成',
                        '</a>  '].join('');
            }else{
                return "处理完成"+ '&nbsp;&nbsp;&nbsp;'+
                     '<a class="ok"  href="javascript:void(0)" title="修改备注">'+
                       '修改备注'+
                      '</a>  ';
            }

        }

        function applyFormatter(value, row, index) {
            if((row.applyName == "" && row.applyPhone =="")||(row.applyName == null && row.applyPhone ==null) ){
                return "信息为空";
            }
            return "【姓名:" + row.applyName + " 电话:" + row.applyPhone+"】";
        }

        function maintainInfoFormatter(value, row, index) {
            if((row.maintainPeople == "" && row.maintainPeoplePhone =="")||(row.maintainPeople ==null && row.maintainPeoplePhone ==null)){
                return "信息为空";
            }
            return "【姓名:" + row.maintainPeople + " 电话:" + row.maintainPeoplePhone+"】";
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
            $(".applymaintain_btn_export").click(function () {
                var type = $("select[name='type']").attr("value");
                var uergencyState = $("select[name='uergencyState']").attr("value");
                var state = $("select[name='state']").attr("value");
                var search = $(".search_text").val();
                var str = "type=" + type + "&uergencyState=" + uergencyState + "&state=" + state + "&search=" + search;
                window.open(basePath + "/applyMaintainAction/exportMainTain?" + str);
            });
            initSelect();
        }

        function initSelect() {
            $dictionaryService.getDicSelectByName("DIC_URGENCYREPAIR", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='uergencyState']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
            $dictionaryService.getDicSelectByName("DIC_REPAIRCLASSFIY", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
        }

        initEvent();
    })
;
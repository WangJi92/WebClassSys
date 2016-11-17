/**
 * Created by JetWang on 2016/11/17.
 */

define(basePath + "/user/buildingInfo/buildingInfoIndex/buildingInfoIndex",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        basePath+"/libs/fancybox/source/jquery.fancybox",
        basePath+"/libs/fancybox/source/helpers/jquery.fancybox-buttons",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function (require, exports, module) {
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $buildingInfoTable;
        var operateEvents = {
            'click .lookbuildingpic': function (e, value, row, index) {
                lookBuildingFancyPic(row);
            },
            'click .lookclassroom': function (e, value, row, index) {
                window.location.href = basePath+"/user/classroomtableInfo/classroomtableInfo.jsp?buildingIndexcode="+row.indexCode;
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
            url: basePath+"/buildingInfoAction/findPage",
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
                    search:$("*[name='buildingName']").attr("value")
                };
                return param;
            },
            onLoadSuccess: function (data) {  //加载成功时执行

            },
            onLoadError: function () {  //加载失败时执行

            },
            columns: [
                {
                    title: '教学楼名称',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '楼层数',
                    field: 'floorTotal',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'duty',
                    title: '值班人员信息',
                    align: 'center',
                    formatter: dutyFormatter
                }, {
                    field: 'maintain',
                    title: '维修人员信息',
                    align: 'center',
                    formatter: maintainFormatter
                },
                {
                    field: 'introduceInfo',
                    title: '简介',
                    align: 'center'
                }, {
                    field: 'operate',
                    title: '教学楼照片',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                },
                {
                    field: 'operate3',
                    title: '当前教学楼教室',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter2
                }
            ]
        };
        function dutyFormatter(value,row,index){
            if((row.dutyRoomPeoplePhone == "" && row.dutyRoomPeopleName =="")||(row.dutyRoomPeoplePhone ==null && row.dutyRoomPeopleName ==null)){
                return "信息为空";
            }
            return "【姓名:" + row.dutyRoomPeopleName + " 电话:" + row.dutyRoomPeoplePhone+"】";
        }
        function maintainFormatter(value,row,index){
            if((row.maintancePeoplePhone == "" && row.maintancePeopleName =="")||(row.maintancePeoplePhone ==null && row.maintancePeopleName ==null)){
                return "信息为空";
            }
            return "【姓名:" + row.maintancePeopleName + " 电话:" + row.maintancePeoplePhone+"】";
        }//格式化显示的信息
        function operateFormatter(value, row, index) {
            return [
                '<a class="lookbuildingpic"  href="javascript:void(0)" title="查看照片">',
                '查看照片',
                '</a>  '
            ].join('');
        }//格式化显示的信息
        function operateFormatter2(value, row, index) {
            return [
                '<a class="lookclassroom"  href="javascript:void(0)" title="查看教室">',
                '查看教室',
                '</a>  '
            ].join('');
        }

        function lookBuildingFancyPic(row){
            var buildingPicList = JSON.parse(row.pictures);
            var picArray =[];
            for(var i =0;i<buildingPicList.length;i++){
                picArray.push({
                    href:basePath+"/image/"+buildingPicList[i]
                });
            }
            $.fancybox.open(picArray, {
                helpers : {
                    buttons	: {}
                }
            });


        }//将后台的图片信息展示出来，使用fancy
        function initEvent() {
            $buildingInfoTable = $("#buildingInfoTable").bootstrapTable(timetableoptions);
            $("#buildingName").keydown(function(e){
                if(e.keyCode ==13){//触发键盘事件enter 防止冒泡产生
                    $buildingInfoTable.bootstrapTable("refresh");
                    return false;
                }
            });//触发enter事件，重新搜索表格信息
            $(".col-sm-12").on("click", ".building_btn_search", function () {
                $buildingInfoTable.bootstrapTable("refresh");
            });
        }//初始化表格信息的显示
        module.exports = {
            initEvent: initEvent
        }
    });
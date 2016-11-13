/**
 * Created by JetWang on 2016/11/7.
 */
define(basePath + "/views/classroommanage/classroomindex/classroomindex",
    [basePath + "/views/classroommanage/service/classroomservice",
        "mustache",
        basePath + "/views/classroommanage/dialog/addEditDialog/js/addEditDialog",
        basePath + "/views/classroommanage/dialog/editTimeTable/js/editTimeTable",
        basePath + "/views/classroommanage/dialog/addEquipment/js/addEquipment",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function (require, exports, module) {

        var $classRoomService = require(basePath + "/views/classroommanage/service/classroomservice");
        var $addOrEditDialog =require(basePath + "/views/classroommanage/dialog/addEditDialog/js/addEditDialog");
        var $editTimeTableDialog = require(basePath + "/views/classroommanage/dialog/editTimeTable/js/editTimeTable");
        var $addEquipmentDialog = require(basePath + "/views/classroommanage/dialog/addEquipment/js/addEquipment");
        initPageInfo();
        function initPageInfo() {
            $.ajax({
                url: basePath+"/classRoomAction/findPage",
                data:{
                    pageSize:1,
                    pageNo:1,
                    name:$("#classRoom_search_text").val(),
                    buildingIndexCode:$(".buildingIndexcode").val()=='null'?"":$(".buildingIndexcode").val()//当前的建筑下面的教室信息
                },
                type: "Post",
                success: function (data) {
                    if (data != null) {
                        initPageItem(data);
                        var pageCount = data.data.totalPage; //取到pageCount的值(把返回数据转成object类型)
                        var currentPage =data.data.page //得到urrentPage
                        var options = {
                            bootstrapMajorVersion: 3, //版本
                            currentPage: currentPage, //当前页数
                            totalPages: pageCount, //总页数
                            itemTexts: function (type, page, current) {
                                switch (type) {
                                    case "first":
                                        return "首页";
                                    case "prev":
                                        return "上一页";
                                    case "next":
                                        return "下一页";
                                    case "last":
                                        return "末页";
                                    case "page":
                                        return page;
                                }
                            },//点击事件，用于通过Ajax来刷新整个list列表
                            onPageClicked: function (event, originalEvent, type, page) {
                                var url = basePath+"/classRoomAction/findPage";
                                var config ={
                                    url :  url,
                                    data:{
                                        pageSize:1,
                                        pageNo:page,
                                        name:$("#classRoom_search_text").val(),
                                        buildingIndexCode:$(".buildingIndexcode").val()=='null'?"":$(".buildingIndexcode").val()//当前的建筑下面的教室信息
                                    },
                                    success : initPageItem ,
                                    type: "Post",
                                };
                                $.ajax(config);
                            }
                        };
                        if(pageCount ==0){
                            $("#pagination").empty();
                            $(".classroomList").empty();
                            $(".classroomList").append("<h4>没有记录</h4>");
                        }else{
                            $('#pagination').bootstrapPaginator(options);
                        }
                    }
                }
            });

        }


        function initPageItem(data){
            var Rows = data.data.rows;
            $(".classroomList").empty();//不会删除当前的元素
            var template = $("#template").html();
            $.each(Rows, function (index, item) { //遍历返回的json
                var pictureJsonObjec = JSON.parse(item.pictures);
                var picArray =[];
                for(var i =0;i<pictureJsonObjec.length;i++){
                    picArray.push({
                        path:basePath+"/image/"+pictureJsonObjec[i]
                    });
                }
                Mustache.parse(template);
                var outputStr = Mustache.render(template, {
                    name:item.name,
                    typeName:item.typeName,
                    seatNo:item.seatNo,
                    floorNo:item.floorNo,
                    id:item.id,
                    indexCode:item.indexCode,
                    pictrues:picArray,
                    introduction:item.introduction
                });
                $(".classroomList").append(outputStr);
                $timetable = $("#timetable").bootstrapTable(timetableoptions);
                initEquipmentList();
            });
        }
        function initEquipmentList(){
            var classroomIndexcode = $("input[name='indexCode']").attr("value");
            var template_list_group = $("#equipment_list_template").html();
            Mustache.parse(template_list_group);
            $classRoomService.findAllByClassRoomIndexCode({
                classroomIndex:classroomIndexcode
            },function(result){
                console.log(result);
                $.each(result.data, function (index, item) {
                    Mustache.parse(template_list_group);
                    var outputStr = Mustache.render(template_list_group,item);
                    $(".equipmentList").append(outputStr);
                });
            });
        }
        function initEvent() {
            /**
             * 1.todo 根据是否有Indexcode pangduan是否需要去处理添加这个按钮
             */
            $('.fancybox').fancybox();

            if($(".buildingIndexcode").val()=="null"){
               $(".btn_add_calssroom").css('display','none');
            }
             $(".btn_add_calssroom").click(function(){//添加教室
                 $addOrEditDialog.openDialog();
             });
            $(".btn_search").click(function(){
                initPageInfo();
            });
            $(".col-sm-12").on("click",".classroom_edit",function(){
                var classroomIndexcode = $("input[name='indexCode']").attr("value");
                $addOrEditDialog.openDialog(2,classroomIndexcode);
            });//教室编辑
            $(".col-sm-12").on("click",".classroom_delete",function(){
                 $classRoomService.deleteByClassRoomIndexcode($("input[name='indexCode']").attr("value"),function(data){
                      if(data.success == true){
                          location.reload();
                      }
                 });
            })//教室的删除

            $(".col-sm-12").on("click",".timetable_btn_search",function(){
                $timetable.bootstrapTable("refresh",{
                    queryParams: function queryParams(params) {   //设置查询参数
                        var param = {
                            //这里是在ajax发送请求的时候设置一些参数
                            pageNo: params.pageNumber,
                            pageSize: params.pageSize,
                            whichWeek:$("*[name='whichWeek']").attr("value"),
                            whichDay:$("*[name='whichDay']").attr("value"),
                            whichLesson:$("*[name='whichLesson']").attr("value"),
                            classRoomIndexcode:$("*[name='indexCode']").attr("value")
                        };
                        return param;
                    }
                });
            });

            $(".col-sm-12").on("click",".equipment_add",function(){

                $addEquipmentDialog.initDialog();
            })
            //equipment_add
            $(".col-sm-12").on("click",".btn_equipment_delete",function(){
                var parent =$(this).parent();//找到子元素中的儿子的结点的id
                var id = parent.find("input[name='id']").val();
                console.log("id:"+id);
                $classRoomService.equipmentdeleteById(id,function(data){
                   if(data.success == true){
                       parent.remove();
                   }
                });

            })//equipment_delete
            $("#classRoom_search_text").keydown(function(e){
                if(e.keyCode ==13){//触发键盘事件enter 防止冒泡产生
                    initPageInfo();
                    return false;
                }
            });
        }
        initEvent();
        var $timetable;
        /**
         * 这个事件要写在前面一点
         * todo 这里是修改课表的弹出对话框然后进行处理 $row 是我们保存的数据信息
         * @type {{click .like: Function}}
         */
        var operateEvents = {
            'click .like': function (e, value, row, index) {
                $editTimeTableDialog.openDialog($timetable,row);
            }
        };
        //设置 bootstrap-tablb 配置信息
        var timetableoptions ={
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [10, 15, 20, 25],  //记录数可选列表
            search: false,  //是否启用查询
            showColumns: false,  //显示下拉框勾选要显示的列
            showRefresh: false,  //显示刷新按钮
           // toolbar:"#toolbar_timetable",//设置旁边的框框的属性
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url:basePath+"/timeTableAction/pageInfo",
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
                    whichWeek:$("*[name='whichWeek']").attr("value"),
                    whichDay:$("*[name='whichDay']").attr("value"),
                    whichLesson:$("*[name='whichLesson']").attr("value"),
                    classRoomIndexcode:$("*[name='indexCode']").attr("value")
                };
                return param;
            },
            onLoadSuccess: function(data){  //加载成功时执行

            },
            onLoadError: function(){  //加载失败时执行

            },
            columns: [
                {
                    field: 'state',
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '教室名称',
                    field: 'classRoomName',
                    align: 'center',
                    valign: 'middle'
                } ,
                {
                    title: '周次',
                    field: 'weekStr',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'dayStr',
                    title: '星期几',
                    align: 'center'
                }, {
                    field: 'lessonStr',
                    title: '课程',
                    align: 'center'
                },
                {
                    field: 'typeStr',
                    title: '使用状态',
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













    });


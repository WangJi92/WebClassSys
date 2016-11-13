/**
 * Created by JetWang on 2016/11/2.
 */
define(basePath + "/views/buildingmanage/all/buildingList/js/buildingList",
    [basePath + "/views/buildingmanage/service/buildingservice",
        "mustache"
    ],
    function (require, exports, module) {

       var $buildingService = require(basePath + "/views/buildingmanage/service/buildingservice");

        var pageInfo = '<li class="list-group-item ">\
                           <div class="row clearfix">\
                                <div class="col-md-12 column">\
                                  <div class="row clearfix">\
                                      <div class="col-md-12 column">\
                                        <div class="btn-group btn-group-md">\
                                           <button class="btn btn-default building_edit" type="button">\
                                           <i class="fa fa-pencil fa-fw"></i>编辑\
                                           </button>\
                                            <button class="btn btn-default building_delete" type="button">\
                                           <i class="fa fa-trash-o fa-fw"></i>删除\
                                          </button><button class="btn btn-default building_look" type="button">\
                                          <i class="fa fa-align-justify"></i>查看教室\
                                         </button>\
                                        </div>\
                                          <h3>\
                                          楼宇名称:{{name}}\
                                          </h3>\
                                       </div>\
                                  </div>\
        <div class="row clearfix">\
            <div class="col-md-6 column">\
            <ul class="list-group  list_picture_group">\
            <li class="list-group-item ">\
            楼层数量：{{floorTotal}}楼\
        </li>\
        <li class="list-group-item ">\
            值班人员:{{dutyRoomPeopleName}}\
        </li>\
        <li class="list-group-item ">\
            维修人员:{{maintancePeopleName}}\
        </li>\
        <li class="list-group-item ">\
            维修电话：{{maintancePeoplePhone}}\
        </li>\
        <li class="list-group-item ">\
            值班电话：{{dutyRoomPeoplePhone}}\
        </li>\
        </ul>\
        </div>\
        <input type="hidden" class="building_indexcode" value="{{indexCode}}"> \
        <div class="col-md-6 column">\
            <ul class="list-group  list_picture_group">{{#pcitrueList}}\
             <li class="list-group-item ">\
            <a class="fancybox" href="{{path}}">查看图片</a>\
            </li>\
            {{/pcitrueList}}</ul>\
         </div>\
        </div>\
    <div class="row clearfix">\
    <div class="col-md-12 column">\
    <h4>简介</h4>\
    <p>\
    {{introduceInfo}}\
</p>\
</div>\
</div>\
</div>\
</div>\
</li>';

        var pictureItem = ' <li class="list-group-item ">\
            <a class="fancybox" href="{{path}}">查看图片</a>\
            </li>';
        function initPageInfo() {
            $.ajax({
                url: basePath+"/buildingInfoAction/findPage",
                data:{
                    pageSize:1,
                    pageNo:1,
                    search:$("#buildingsearch").val()
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
                                var url = basePath+"/buildingInfoAction/findPage";
                                var config ={
                                    url :  url,
                                    data:{
                                        pageSize:1,
                                        pageNo:page,
                                        search:$("#buildingsearch").val()
                                    },
                                    success : initPageItem ,
                                    type: "Post",
                                };
                                $.ajax(config);
                            }
                        };
                        if(pageCount ==0){
                            $('#pagination').empty();
                            $(".buildingList").append("<h4>没有记录</h4>");
                        }else{
                            $('#pagination').bootstrapPaginator(options);
                        }
                    }
                }
            });

        }

       function initPageItem(data){
               var Rows = data.data.rows;
               $(".buildingList").empty();//不会删除当前的元素
               $.each(Rows, function (index, item) { //遍历返回的json
                   var pictureJsonObjec = JSON.parse(item.pictures);
                   var picArray =[];
                   for(var i =0;i<pictureJsonObjec.length;i++){
                       picArray.push({
                           path:basePath+"/image/"+pictureJsonObjec[i]
                       });
                   }
                   var outputString =Mustache.render(pageInfo,{
                       floorTotal:item.floorTotal,
                       dutyRoomPeopleName:item.dutyRoomPeopleName,
                       maintancePeopleName:item.maintancePeopleName,
                       maintancePeoplePhone:item.maintancePeoplePhone,
                       dutyRoomPeoplePhone:item.dutyRoomPeoplePhone,
                       introduceInfo:item.introduceInfo,
                       pcitrueList:picArray,
                       name:item.name,
                       indexCode:item.indexCode
                   });
                   $(".buildingList").append(outputString);
              });
       }
        function initEvent() {
            $('.fancybox').fancybox();
            $(".btn_search").click(function(){
                    initPageInfo();
            });
            $(".buildingList").on("click",".building_edit",function(){
                window.location.href = basePath+"/views/buildingmanage/addBuilding/addbuilding.jsp?indexcode="+$(".building_indexcode").val();
            });//删除教室
            $(".buildingList").on("click",".building_delete",function(){
                $buildingService.wheaherClassRoomEqualsZero($(".building_indexcode").val(),function(data){
                    if(data.success == true){
                        $buildingService.deleteByIndexCode($(".building_indexcode").val(),function(result){
                            if(result.success == true){
                                location.reload();
                            }else{
                                var dialogTip = new BootstrapDialog({
                                    message: result.message,
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
            });//编辑
            $(".buildingList").on("click",".building_look",function(){
               // alert(111);
                window.location.href = basePath+"/views/classroommanage/classroommanage.jsp?buildingIndexcode="+$(".building_indexcode").val();
            });//查看教室

            $("#buildingsearch").keydown(function(e){
                if(e.keyCode ==13){//触发键盘事件enter 防止冒泡产生
                    initPageInfo();
                    return false;
                }
            });

            initPageInfo();
        }
        function building_edit(){
            $(".building_indexcode").val();//唯一标识符
        }
        initEvent();
    });
    
  
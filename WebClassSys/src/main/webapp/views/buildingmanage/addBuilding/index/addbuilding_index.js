/**
 * Created by JetWang on 2016/10/30.
 */
define(basePath + "/views/buildingmanage/addBuilding/index/addbuilding_index",
    [basePath + "/views/buildingmanage/service/buildingservice",
        basePath + "/views/buildingmanage/addBuilding/dialog/userDialog/userdialog",
        basePath + "/views/buildingmanage/addBuilding/dialog/uploadpic/js/uploadpic"
        ,"mustache"
    ],
    function (require, exports, module) {
        var buildingService = require(basePath + "/views/buildingmanage/service/buildingservice");
        var opendialog = require(basePath + "/views/buildingmanage/addBuilding/dialog/userDialog/userdialog");
        var openUploadDialog = require(basePath + "/views/buildingmanage/addBuilding/dialog/uploadpic/js/uploadpic");
        init();
        function edit(indexcodeValue){
            /**
             * 1.首先跳转过来，Init方法被自动调用
             * 2.然后初始化我们的值得结果
             */
                buildingService.findByIndexCode(indexcodeValue,function(data){
                    if(data.success ==true &&data.data !=null){
                        var dataItem = data.data;
                        console.log(dataItem.id)
                        $("input[name='id']").prop("value",dataItem.id);
                        $("input[name='floorTotal']").attr("value",dataItem.floorTotal);
                        $("input[name='name']").attr("value",dataItem.name);
                        $("*[name='introduceInfo']").attr("value",dataItem.introduceInfo);
                        $("input[name='dutyRoomPeopleIndexcode']").attr("value",dataItem.dutyRoomPeopleIndexcode);
                        $("input[name='maintancePeopleIndexcode']").attr("value",dataItem.maintancePeopleIndexcode);
                        $(".input_duty_name").attr("value",dataItem.dutyRoomPeopleName);
                        $(".input_maintain_name").attr("value",dataItem.maintancePeopleName);
                        //照片的处理
                        var pictureJsonObjec = JSON.parse(dataItem.pictures);
                        var picArray =[];
                        for(var i =0;i<pictureJsonObjec.length;i++){
                            var ItemPic ={
                                path:basePath+"/image/"+pictureJsonObjec[i]
                            }
                            //使用模板方法把所有的图片信息放在一起处理
                            var ItemHtml = '<li class="list-group-item ">\
                        <a class="fancybox" href="{{path}}" >查看图片</a>\
                        <button type="button" class="btn btn-default btn_picture_delete"\
                            style="position: absolute;right: 10px;top:2px;" title="删除">\
                         <i class="glyphicon glyphicon-trash"></i></button>';
                            var output= Mustache.render(ItemHtml,ItemPic);
                            $(".list_picture_group").append(output);
                        }
                    }
                });
        }
        function init() {
            $(".btn_add_duty").click(function () {
                opendialog.openPeopleDialog("添加值班人员",1);
            });//添加值班人员
            $(".btn_add_maintain").click(function () {
                opendialog.openPeopleDialog("添加维修人员",2);
            });//添加维修人员
            $(".btn_add_picture").click(function () {
                openUploadDialog.openDialog();
            });//添加图片
            $(".btn_building_sava").click(function () {
               var errorMessage =null;
                console.log($("#building_form").serialize());
                var re = /^[0-9]+.?[0-9]*$/;
                if($("input[name='name']").val() ==""){
                    errorMessage ="请输入楼宇名称";
                }else if($("input[name='floorTotal']").val() =="" || !re.test($("input[name='floorTotal']").val()) ){
                    errorMessage="输入楼层数 例如：6";
                }else if($("*[name='introduceInfo']").val() ==""){
                    errorMessage="输入简介信息";
                }else if($(".input_duty_name").val()==""){
                    errorMessage="请选择值班人员";
                }else if($(".input_maintain_name").val() ==""){
                    errorMessage="请选择维修人员";
                }else if($(".fancybox").size()<1){
                    errorMessage="请上传一张图片";
                }
                if(errorMessage != null){
                    var dialogTip = new BootstrapDialog({
                        message: errorMessage,
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
                    return;
                }else{
                        //这里是查找图片的路径
                        var pictureList = [];
                        $(".fancybox").each(function(i){
                            var Item = $(this).prop("href").replace(basePath+"/image/","");
                            pictureList.push(Item);
                        });
                  var data={
                      pictures:JSON.stringify(pictureList),
                      floorTotal:$("input[name='floorTotal']").val(),
                      name:$("input[name='name']").val(),
                      introduceInfo: $("*[name='introduceInfo']").val(),
                      dutyRoomPeopleIndexcode:$("input[name='dutyRoomPeopleIndexcode']").val(),
                      maintancePeopleIndexcode:$("input[name='maintancePeopleIndexcode']").val()
                  }
                    if($("input[name='id']").val()!=""){
                        data.id=$("input[name='id']").val();
                    }
                    buildingService.saveOrUpdate(data,function(data){
                        if(data.success == true){
                            var dialogTip = new BootstrapDialog({
                                message: "保存成功",
                                cssClass: "width200-dialog",
                                onshow: function (diaRef) {
                                    setTimeout(function () {
                                        window.location.href = basePath+"/views/buildingmanage/all/buildingList/buildingList.jsp";
                                        diaRef.close();
                                    }, 1500);
                                }
                            });
                            dialogTip.realize();
                            dialogTip.getModalHeader().hide();
                            dialogTip.getModalFooter().hide();
                            dialogTip.open();

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
            });//保存图片的按钮
            $('.fancybox').fancybox();
            $(".list_picture_group").on("click", ".btn_picture_delete", function () {
                 $(this).parent().remove();
            });//动态绑定监听事件
            console.log($(".building_indexcode_edit").val());
            if($(".building_indexcode_edit").val() !="null"){
                console.log($(".building_indexcode_edit").val());
                edit($(".building_indexcode_edit").val());
            }

        }
    });

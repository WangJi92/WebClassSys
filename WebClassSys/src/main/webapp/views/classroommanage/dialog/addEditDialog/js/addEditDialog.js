/**
 * Created by JetWang on 2016/11/7.
 */


define(basePath + "/views/classroommanage/dialog/addEditDialog/js/addEditDialog",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/views/classroommanage/service/classroomservice",
        "mustache"
    ],
    function (require, exports, module) {
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $classroomService = require(basePath + "/views/classroommanage/service/classroomservice");
        var $addEditDialog;
        var $classroomindexcode;
        var options = {
            title: "添加图片",
            cssClass: "width700-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath")
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/classroommanage/dialog/addEditDialog/addEditDialog.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    $("#add_save").attr("disabled",true);
                    validate();
                }
            }, {
                id: "add_cancel",
                label: "取消",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }
            ],
            onshown: function (dialogRef) {
                initEvent();
                onShowText();
            }
        };

        function onShowText() {
            console.log("教师："+$("input[name='indexCode']").val());
            if ($classroomindexcode != undefined) {//教室Indexcode为空的时候为新增加
                $classroomService.findByIndexCode($("input[name='indexCode']").val(), function (classroom) {
                    var classroomdata = classroom.data;
                    $dicService.getDicSelectByName("DIC_CLASSROOM", function (result) {
                        if (result.success == true && result.data != null) {
                            $.each(result.data, function (index, value) {
                                if (value.value == classroomdata.type) {
                                    $("select[name='type']").append("<option selected value='" + value.value + "'>" + value.key + "</option>");
                                } else {
                                    $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                                }
                            });
                            $("input[name='name']").attr("value", classroomdata.name);
                            $("input[name='seatNo']").attr("value", classroomdata.seatNo);
                            $("input[name='buildingIndexCode']").attr("value", classroomdata.buildingIndexCode);
                            $("input[name='indexCode']").attr("value", classroomdata.indexCode);
                            $("input[name='floorNo']").attr("value", classroomdata.floorNo);
                            $("*[name='introduction']").attr("value", classroomdata.introduction);
                            $("input[name='id']").attr("value", classroomdata.id);
                            var pictureJsonObjec = JSON.parse(classroomdata.pictures);
                            var template = $("#item_picture").html();
                            for (var i = 0; i < pictureJsonObjec.length; i++) {
                                var ItemPic = {
                                    path: basePath + "/image/" + pictureJsonObjec[i]
                                }
                                Mustache.parse(template);
                                var output = Mustache.render(template,ItemPic);
                                $(".list_picture_group_dialog").append(output);
                            }
                        }

                    });
                });
            } else {//新增加信息
                $dicService.getDicSelectByName("DIC_CLASSROOM", function (result) {
                    if (result.success == true && result.data != null) {
                        $.each(result.data, function (index, value) {
                            if (value.value == 1) {
                                $("select[name='type']").append("<option selected value='" + value.value + "'>" + value.key + "</option>");
                            } else {
                                $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                            }
                        });
                    }

                });
            }
        }

        /**
         * 保存是教研信息
         * @returns {boolean}
         */
        function validate() {
            var errorMessage = null;
            var re = /^[0-9]+.?[0-9]*$/;
            if ($("input[name='name']").val() == "") {
                errorMessage = "请输入教室名称";
            } else if ($("input[name='seatNo']").val() == "" || !re.test($("input[name='seatNo']").val())) {
                errorMessage = "输入座位数 如56";
            } else if ($("input[name='floorNo']").val() == "" || !re.test($("input[name='floorNo']").val())) {
                errorMessage = "输入所在楼层数 如5";
            }
            else if ($("*[name='introduction']").val() == "") {
                errorMessage = "输入简介信息";
            } else if ($(".fancybox").size() < 1) {
                errorMessage = "请上传一张图片";
            }
            if (errorMessage != null) {
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
                $("#add_save").attr("disabled",false);
                return false;
            } else {
                //这里是查找图片的路径
                var pictureList = [];
                $(".fancybox").each(function (i) {
                    var Item = $(this).prop("href").replace(basePath + "/image/", "");
                    pictureList.push(Item);
                });
                var data = {
                    pictures: JSON.stringify(pictureList),
                    seatNo: $("input[name='seatNo']").val(),
                    name: $("input[name='name']").val(),
                    floorNo: $("*[name='floorNo']").val(),
                    introduction: $("*[name='introduction']").val(),
                    type: $("*[name='type']").prop("value"),
                    buildingIndexCode: $(".buildingIndexcode").val()
                }
                if ($("input[name='id']").val() != "" && $("input[name='indexcode']").val() != "") {
                    data.id = $("input[name='id']").val();
                    data.indexCode = $("input[name='indexcode']").val();
                }
                $classroomService.saveOrUpdate(data,function(result){
                    if(result.success == true){
                        var dialogTip = new BootstrapDialog({
                            message: "添加成功",
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
                        $addEditDialog.close();
                        $("#add_save").attr("disabled",false);
                        window.location.href = basePath+"/views/classroommanage/classroommanage.jsp?buildingIndexcode="+$(".buildingIndexcode").val();
                        return true;
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
                        $("#add_save").attr("disabled",false);
                        return false;
                    }


                });
            }
        }

        function initEvent() {
            $('.fancybox').fancybox();
            $(".list_picture_group_dialog").on("click", ".btn_picture_delete", function () {
                $(this).parent().remove();
            });//动态绑定监听事件
            $(".save_pic").click(function () {
                if ($(".upload_file").val() == "") {
                    var dialogTip = new BootstrapDialog({
                        message: "请选择一张图片",
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
                } else {
                    $.ajaxFileUpload({
                        url: basePath + '/test/upload',//用于文件上传的服务器端请求地址
                        secureuri: false,//一般设置为false
                        fileElementId: 'upload_file',//文件上传控件的id属性  <input type="file" id="upload" name="upload" />
                        dataType: 'json',//返回值类型 一般设置为json
                        success: function (data)  //服务器成功响应处理函数
                        {
                            // alert(data);
                            if (data.success == true) {
                                var template = $("#item_picture").html();
                                Mustache.parse(template);
                                var output = Mustache.render(template, {
                                    path: basePath + "/image/" + data.message
                                });
                                $(".list_picture_group_dialog").append(output);

                            }
                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {

                        }
                    });
                }
            })

        }

        module.exports = {
            openDialog: function (type, classroomIndexcode) {
                if (type == 2) {//编辑
                    $.extend(options, {
                        title: "编辑教室"
                    });
                    $classroomindexcode = classroomIndexcode;
                } else {//添加
                    $.extend(options, {
                        title: "添加教室"
                    });
                    $classroomindexcode =undefined;

                }

                $addEditDialog =  BootstrapDialog.show(options);
            }
        }
    });
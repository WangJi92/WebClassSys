/**
 * Created by JetWang on 2016/11/1.
 */

define(basePath + "/views/buildingmanage/addBuilding/dialog/uploadpic/js/uploadpic",
    [basePath + "/views/buildingmanage/service/buildingservice",
        basePath + "/libs/ajaxfileupload",
        "mustache"
    ],
    function (require, exports, module) {

        var options = {
            title: "添加图片",
            cssClass: "width400-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath")
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/buildingmanage/addBuilding/dialog/uploadpic/uploadpic.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    if($(".upload_pic").prop("src") ==""){
                        var dialogTip = new BootstrapDialog({
                            message: "请上传一张图片",
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
                    }
                    var data ={
                        path:$(".upload_pic").prop("src")
                    }
                    //使用模板方法把所有的图片信息放在一起处理
                     var ItemHtml = '<li class="list-group-item ">\
                        <a class="fancybox" href="{{path}}" >查看图片</a>\
                        <button type="button" class="btn btn-default btn_picture_delete"\
                            style="position: absolute;right: 10px;top:2px;" title="删除">\
                         <i class="glyphicon glyphicon-trash"></i></button>';
                    var output= Mustache.render(ItemHtml,data);
                    //alert(output)
                    $(".list_picture_group").append(output);
                    dialogRef.close();
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
            }
        };

        function initEvent() {
            $(".upload_pic").fancybox();

            $(".upload_file").click(function () {
                $(".look_pic").css({'display': 'none'});
                $(".picpath").val("value", "");
            });

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
                                $(".upload_pic").prop("src", basePath + "/image/" + data.message);
                                $(".look_pic").css({'display': 'block'});
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
            openDialog: function () {
                BootstrapDialog.show(options);
            },
            initEvent: initEvent
        }
    });
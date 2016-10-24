/**
 * Created by JetWang on 2016/10/23.
 */

define(basePath + "/views/usermanage/dialog/edit/js/user_edit",
    [basePath + "/views/usermanage/service/userService",
        basePath + "/views/dictionary/service/dictionaryService",
    ],
    function (require, exports, module) {
        //数据字典的信息
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $userInfoService = require(basePath + "/views/usermanage/service/userService");
        var $editDialog;
        var $tableUser;//表格对象的引用
        var $row;//修改行的引用；
        var options = {
            title: "修改用户信息",
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
                "removePath": basePath + "/UserInfo/userInfoAddView"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    console.log($("form[id='dialog_form']").serialize());
                    $userInfoService.saveOrUpdate($("form[id='dialog_form']").serialize(), function (data) {
                        console.log(data);
                        if (data.success == true) {
                            var dialogTip = new BootstrapDialog({
                                message: "修改成功",
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
                            $tableUser.bootstrapTable('refresh');//刷新
                            dialogRef.close();
                        } else {
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
            },
                {
                    id: "add_cancel",
                    label: "取消",
                    cssClass: 'btn-primary',
                    action: function (dialogRef) {
                        dialogRef.close();
                    }
                }
            ],
            onshown: function (dialogRef) {
                $dicService.getDicSelectByName("DIC_USER", function (result) {
                    if (result.success == true && result.data != null) {
                        console.log(result);
                        $.each(result.data, function (index, value) {
                            if(value.value == $row.userType){
                                $("select[name='userType']").append("<option selected value='" + value.value + "'>"+value.key+"</option>");
                            }else{
                                $("select[name='userType']").append("<option value='" + value.value + "'>"+value.key+"</option>");
                            }
                        });
                        $("input[name='userName']").attr("value",$row.userName);
                        $("input[name='password']").attr("value",$row.password);
                        $("input[name='phone']").attr("value",$row.phone);
                        $("input[name='id']").attr("value",$row.id);
                    }
                });
            }
        };

        module.exports = {
            editUserDialog: function (tableRef,row) {
                $tableUser = tableRef;
                console.log(row);
                $row = row;
                $editDialog = BootstrapDialog.show(options);
            }

        }

    })
;
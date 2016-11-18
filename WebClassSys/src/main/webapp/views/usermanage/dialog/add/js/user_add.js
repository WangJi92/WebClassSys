/**
 * Created by JetWang on 2016/10/23.
 */

define(basePath + "/views/usermanage/dialog/add/js/user_add",
    [basePath + "/views/usermanage/service/userService",
        basePath + "/views/dictionary/service/dictionaryService",
    ],
    function (require, exports, module) {
        //数据字典的信息
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $userInfoService = require(basePath + "/views/usermanage/service/userService");
        var $addDialog;
        var $tableUser;
        var $row = null;
        var options = {
            title: "用户信息",
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
                "removePath": basePath + "/views/usermanage/dialog/add/user_add.jsp"
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

            }
        };

        module.exports = {
            opendAddDialog: function (tableRef, row) {
                $tableUser = tableRef;
                $row = row;
                $addDialog = BootstrapDialog.show(options);
            },
            initEvent: initEvent

        }
        function initEvent() {
            if ($row == null) {//新建
                $dicService.getDicSelectByName("DIC_USER", function (result) {
                    if (result.success == true && result.data != null) {
                        console.log(result);
                        $.each(result.data, function (index, value) {
                            $("select[name='userType']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                        });
                    }
                });
            } else {//修改
                $dicService.getDicSelectByName("DIC_USER", function (result) {
                    if (result.success == true && result.data != null) {
                        console.log(result);
                        $.each(result.data, function (index, value) {
                            if (value.value == $row.userType) {
                                $("select[name='userType']").append("<option selected value='" + value.value + "'>" + value.key + "</option>");
                            } else {
                                $("select[name='userType']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                            }
                        });
                        $("input[name='userName']").attr("value", $row.userName);
                        $("input[name='password']").attr("value", $row.password);
                        $("input[name='phone']").attr("value", $row.phone);
                        $("input[name='id']").attr("value", $row.id);
                        $("input[name='loginAccount']").attr("value", $row.loginAccount);
                    }
                });
            }
        }
    })
;
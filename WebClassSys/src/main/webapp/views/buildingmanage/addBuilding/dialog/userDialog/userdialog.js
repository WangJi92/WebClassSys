/**
 * Created by JetWang on 2016/11/1.
 *
 * 这里使用了userListByType里面的信息
 */

define(basePath + "/views/buildingmanage/addBuilding/dialog/userDialog/userdialog",
    [basePath + "/views/buildingmanage/service/buildingservice",
        basePath + "/views/buildingmanage/addBuilding/dialog/userListByType/js/userList_index",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function (require, exports, module) {
        var buildingService = require(basePath + "/views/buildingmanage/service/buildingservice");
        var $userListInfo = require(basePath + "/views/buildingmanage/addBuilding/dialog/userListByType/js/userList_index");
        var $peopleDialog;
        var $type;//选择的是值班的 还是维修的
        var options = {
            title: "添加人员信息",
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
                "removePath": basePath + "/views/buildingmanage/addBuilding/dialog/userListByType/maintainList.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    var userInfoList = $userListInfo.getSelect();
                    if (userInfoList.length > 0) {
                        console.log(userInfoList);
                        var userInfo = userInfoList[0];
                        var inputVarName;
                        var inputVarPhone;
                        var indexcode_input_value;
                        if ($type == 1) {//值班人员
                            inputVarName = $(".input_duty_name");
                            inputVarPhone = $(".input_duty_phone");
                            indexcode_input_value = $(".input_duty_indexcode")
                        } else {//维修人员
                            inputVarName = $(".input_maintain_name");
                            inputVarPhone = $(".input_maintain_phone");
                            indexcode_input_value = $(".input_maintain_indexcode")
                        }
                        inputVarName.attr("value", userInfo.userName);
                        inputVarPhone.attr("value", userInfo.phone);
                        indexcode_input_value.attr("value", userInfo.indexcode);
                        dialogRef.close();
                    } else {
                        var dialogTip = new BootstrapDialog({
                            message: "请选择一个人员",
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
                $userListInfo.initEvent();
            }
        };
        module.exports = {
            openPeopleDialog: function (title, type) {
                $type = type;
                options = $.extend(options, {title: title});
                $peopleDialog = BootstrapDialog.show(options);
            }

        }

    });


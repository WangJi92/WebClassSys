/**
 * Created by JetWang on 2016/11/16.
 */

define(basePath + "/views/applymaintainmange/dialog/maintainpeople/js/peopledialog",
    [
        basePath + "/views/applymaintainmange/service/applymaintainservice",
        basePath + "/views/applymaintainmange/dialog/maintainpeople/js/peopletable"
    ],
    function (require, exports, module) {

        var $peopleTable = require(basePath + "/views/applymaintainmange/dialog/maintainpeople/js/peopletable");
        var $maintainService = require(basePath + "/views/applymaintainmange/service/applymaintainservice");
        var $dialogAssign
        var options = {
            title: "分派维修人员",
            cssClass: "width600-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: false,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath");
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/applymaintainmange/dialog/maintainpeople/maintainpeopletable.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    console.log($peopleTable.getSelect());
                    var arrayPeople =$peopleTable.getSelect();
                    if(arrayPeople.length>0){
                        var people = arrayPeople[0];
                        $("input[name='maintainPeople']").attr("value",people.userName);
                        $("input[name='maintainPeoplePhone']").attr("value",people.phone);
                        $("input[name='maintainIndexCode']").attr("value",people.indexcode);
                        dialogRef.close();
                    }else{
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
            opendDialog: function () {
                $dialogAssign = BootstrapDialog.show(options);
            }
        }
    });
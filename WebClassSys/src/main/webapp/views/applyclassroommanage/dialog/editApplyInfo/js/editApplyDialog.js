/**
 * Created by JetWang on 2016/11/18.
 */


define(basePath + "/views/applyclassroommanage/dialog/editApplyInfo/js/editApplyDialog",
    [
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/views/applyclassroommanage/service/applyclassroomservice"
    ],
    function (require, exports, module) {

        var $applyclassroomservice = require(basePath + "/views/applyclassroommanage/service/applyclassroomservice");

        module.exports = {
            initEvent: initEvent,
            openDialog: openDialog//传递当前table的row信息过来~~
        }
        var $row;// 当前行的信息
        var $applyclassroomtable;//刷新表格对象
        var optionsDialog = {
            title: "申请教室",
            cssClass: "width500-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath")
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/applyclassroommanage/dialog/editApplyInfo/editApplyDialog.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    saveApply(dialogRef);
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

            }
        };

        function initEvent() {
            $("*[name='applystate']").find("option[value='" + $row.state + "']").attr("selected", true);
            $("*[name='handleAddvice']").attr("value", $row.handleAddvice);
            $("input[name='whichLesson']").attr("value", $row.whichLesson).attr("disabled", true);
            $("input[name='realLessonTime']").attr("value", "【" + $row.realLessonTime + "】").attr("disabled", true);
            ;
            if ($row.state == 0) {
                $("*[name='handleAddvice']").attr("value","【同意使用】"+"【" + $row.realLessonTime + "】");
            }else{
                $("*[name='applystate']").find("option[value='0']").remove();
            }//审批的时候不要出现这个


        }

        function saveApply(dialogRef) {
            var data = {
                handleAddvice: $("*[name='handleAddvice']").val(),
                id: $row.id,
                state: $("*[name='applystate']").val()
            }
            if (data.handleAddvice == "") {
                var dialogTip = new BootstrapDialog({
                    message: "请输入备注信息",
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
                $applyclassroomservice.updataeState(data, function (result) {
                    if (result.success == true) {
                        var dialogTip = new BootstrapDialog({
                            message: "更新成功",
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
                        dialogRef.close();
                        $applyclassroomtable.bootstrapTable("refresh");
                    } else {
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
                        dialogRef.close();
                    }
                })
            }
        }

        function openDialog(row, applyclassroomtable) {
            $row = row;
            $applyclassroomtable = applyclassroomtable;
            BootstrapDialog.show(optionsDialog);
        }
    });
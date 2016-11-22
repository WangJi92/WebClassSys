/**
 * Created by JetWang on 2016/11/18.
 */

define(basePath + "/views/timetablemanage/dialog/editTimeTableState/js/editTimeTableDialog",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
        basePath + "/views/classroommanage/service/classroomservice",//classtimeservice中有这个功能

    ],
    function (require, exports, module) {
        var $classroomservice = require(basePath + "/views/classroommanage/service/classroomservice");
        var $dictionaryService = require(basePath + "/views/dictionary/service/dictionaryService");
        module.exports = {
            initEvent: initEvent,
            openDialog: openDialog//传递当前table的row信息过来~~
        }
        var $row;// 当前行的信息
        var $timetablestatetable;//刷新表格对象
        var optionsDialog = {
            title: "编辑",
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
                "removePath": basePath + "/views/timetablemanage/dialog/editTimeTableState/editTimeTableDialog.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    if(parseInt($("input[name='bath_process'][type='radio']:checked").val(),10) == 2){
                        $row.type = $("select[name='useType']").attr("value");
                        $classroomservice.editTimeTable($row, function (data) {
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
                                $timetablestatetable.bootstrapTable('refresh');//刷新
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
                    }else if(parseInt($("input[name='bath_process'][type='radio']:checked").val(),10) == 1){
                        bathUpate(dialogRef);
                    }else{
                        bathUpdataClassRoom(dialogRef);
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

            }
        };
        function bathUpdataClassRoom(dialogRef){
            var data = {
                classRoomIndexCode: $row.classRoomIndexCode,
                type: $("select[name='useType']").attr("value"),
                batch: 4,
            }
            var url = basePath + "/timeTableAction/bathUpdateClassRoom";
            var config = {
                url: url,
                data: data,
                success: function(result){
                    if(result.success ==true){
                        var dialogTip = new BootstrapDialog({
                            message: "操作成功",
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
                        $timetablestatetable.bootstrapTable('refresh');//刷新
                        dialogRef.close();
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
                }
            };
            $.ajax(config);
        }
        function bathUpate(dialogRef) {
            var errormessage = null;
            var re = /^[0-9]+.?[0-9]*$/;
            if (!re.test($('.beginWeek_input').val()) ) {
                errormessage = "请在开始周输入数字 ";
            } else if (!re.test($('.endWeek_input').val())) {
                errormessage = "请在结束周输入数字";
            }else if(parseInt($('.beginWeek_input').val(),10)<0 || parseInt($('.endWeek_input').val(),10)<0 )
                errormessage = "请输入开始结束周都大于0";
            else if ( parseInt($('.beginWeek_input').val(),10) >  parseInt($('.endWeek_input').val(),10)) {
                errormessage = "开始周不能大于结束周";
            }
            if (errormessage == null) {
                var data = {
                    classRoomIndexCode: $row.classRoomIndexCode,
                    whiichDay: $row.whiichDay,
                    whichLesson: $row.whichLesson,
                    type: $("select[name='useType']").attr("value"),
                    weekType: $("input[name='handlesetting'][type='radio']:checked").val(),
                    batch: 1,
                    beginWeek: $('.beginWeek_input').val(),
                    endWeek: $('.endWeek_input').val()
                }
                var url = basePath + "/timeTableAction/bathUpdate";
                var config = {
                    url: url,
                    data: data,
                    success: function(result){
                        if(result.success ==true){
                            var dialogTip = new BootstrapDialog({
                                message: "操作成功",
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
                            $timetablestatetable.bootstrapTable('refresh');//刷新
                            dialogRef.close();
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
                    }
                };
                $.ajax(config);

            } else {
                var dialogTip = new BootstrapDialog({
                    message: errormessage,
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

        function initEvent() {
            $dictionaryService.getDicSelectByName("DIC_CLASSSTATE", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='useType']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
            $("input[name='whichLesson']").attr("value", "【" + $row.weekStr + " " + $row.dayStr + " " + $row.lessonStr + "】").attr("disabled", true);
            $("*[name='useType']").find("option[value='" + $row.type + "']").attr("selected", true);
            $("input[name='classRoomNameEdit']").attr("value", $row.classRoomName).attr("disabled", true);
            ;
        }

        function openDialog(row, applyclassroomtable) {
            $row = row;
            console.log($row);
            $timetablestatetable = applyclassroomtable;
            BootstrapDialog.show(optionsDialog);
        }
    });
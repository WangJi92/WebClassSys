/**
 * Created by JetWang on 2016/11/17.
 */
define(basePath + "/user/applyclassroom/dialog/applyclassroomdialog/js/applyclassroomdialog",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        "mustache",
        basePath + "/user/applyclassroom/service/applyclassroomservice",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
    ],
    function (require, exports, module) {
        var $dictionaryService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $applyclassroomservice = require(basePath + "/user/applyclassroom/service/applyclassroomservice");

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
                "removePath": basePath + "/user/applyclassroom/dialog/applyclassroomdialog/applyclassroomdialog.jsp"
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
        
        function initEvent(){
            $dictionaryService.getDicSelectByName("DIC_CLASSUSEPOPURSE", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='purpose']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
            $("input[name='whichLesson']").attr("value","【"+$row.weekStr+" "+$row.dayStr+" "+$row.lessonStr+"】")
        }
        function saveApply(dialogRef){
            var data ={
                applicant:$("input[name='applicant']").val(),//姓名
                phone:$("input[name='phone']").val(),//电话
                purpose:$("*[name='purpose']").val(),//用途 数据字典
                applyReason:$("*[name='applyReason']").val(),//申请原因
                whichLesson:$("input[name='whichLesson']").val(),//占用时间
                timetableId:$row.id,//课程表的Id
                classRoomIndexCode:$row.classRoomIndexCode,//classroomindexcode
                classRoomName:$row.classRoomName//classroomName
            }
            var errormessage = null;
            if(data.applicant ==""){
                errormessage ="请输入姓名"
            }else if(data.phone ==""){
                errormessage ="请输入手机号码"
            }else if(data.whichLesson == ""){
                errormessage="请输入占用时间";
            }else if(data.applyReason == ""){
                errormessage ="请输入申请原因";
            }
            if(errormessage == null){
                $applyclassroomservice.save(data,function(data) {
                    if(data.success == true){
                        var dialogTip = new BootstrapDialog({
                            message: "提交成功",
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
            }else{
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
        function openDialog(row,applyclassroomtable) {
            $row = row;
            $applyclassroomtable =applyclassroomtable;
            BootstrapDialog.show(optionsDialog);
        }
    });
/**
 * Created by JetWang on 2016/11/9.
 */
define(basePath + "/views/classroommanage/dialog/editTimeTable/js/editTimeTable",
    [ "mustache",
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/views/classroommanage/service/classroomservice",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table.css",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function (require, exports, module) {
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $classroomservice =require(basePath + "/views/classroommanage/service/classroomservice");
        var $timetalbeRef;
        var $row;
        var options = {
            title: "修改课程表信息",
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
                "removePath": basePath + "/views/classroommanage/dialog/editTimeTable/editTimeTable.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    /**
                     * todo  $row 信息数据中有我们需要的数据信息
                     * 我们只需要修改这个type就好了。
                     * @type {*|jQuery}
                     */
                    $row.type = $("select[name='type']").attr("value");
                    $classroomservice.editTimeTable($row,function(data){
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
                            $timetalbeRef.bootstrapTable('refresh');//刷新
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
            openDialog: function (tableRef,row) {
                $timetalbeRef = tableRef;
                $row = row;
                 BootstrapDialog.show(options);
            },
            initEvent:initEvent

        }
        function initEvent(){
            var template = $("#timetable_templage").html();
            Mustache.parse(template);
            var outputStr = Mustache.render(template,$row);
            $(".edittimetable").append(outputStr);
            $dicService.getDicSelectByName("DIC_CLASSSTATE", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        if(value.value != $row.type){
                            $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                        }

                    });
                }
            });

        }

    });
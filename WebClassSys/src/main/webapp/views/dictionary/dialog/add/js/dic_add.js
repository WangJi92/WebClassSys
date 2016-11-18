/**
 * Created by JetWang on 2016/10/14.
 */
define(basePath + "/views/dictionary/dialog/add/js/dic_add",
    [basePath + "/views/dictionary/service/dictionaryService"
        , "jquery-validation"
    ],
    function (require, exports, module) {
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $addDialog;
        var $tableDic;
        var $row = null;
        var options = {
            title: "数据字典信息",
            /* size:BootstrapDialog.SIZE_SMALL,*/
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
                "removePath": basePath + "/views/dictionary/dialog/add/dic_add.jsp"
            },
            buttons: [{
                id: "dic_add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    $dicService.addOrUpdate($("#dicDialog").serialize(), function (data) {
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
                            $tableDic.bootstrapTable('refresh');//刷新
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
                    id: "dic_add_cancal",
                    label: "取消",
                    action: function (dialogRef) {
                        dialogRef.close();
                    }
                }
            ],
            onshown: function (dialogRef) {

            }
        };
        module.exports = {
            openAddDialog: open,
            initEvent: initEvent
        }
        function initEvent() {
            if ($row == null) {//新建
                $dicService.dicFatherSelectBean(function (result) {
                    if (result.success == true && result.data != null) {
                        $.each(result.data, function (index, value) {
                            // console.log(value.value+value.key);
                            $("select[name='classfiyType']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                        });
                    }
                });
            } else {
                $dicService.getDicTypeValue(function (dataSelect) {
                    console.log(dataSelect);
                    var SelectData = dataSelect.data;
                    var selectId;//找到当前的SelectId;
                    for (var i = 0; i < SelectData.length; i++) {
                        if (SelectData[i].key == $row.classfiyType) {
                            selectId = SelectData[i].value;
                            break;
                        }
                    }
                    $dicService.dicFatherSelectBean(function (result) {
                        if (result.success == true && result.data != null) {
                            $.each(result.data, function (index, value) {
                                if (value.value == selectId) {
                                    $("select[name='classfiyType']").append("<option selected value='" + value.value + "'>" + value.key + "</option>");
                                } else {
                                    $("select[name='classfiyType']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                                }
                            });
                            $("input[name='id']").attr("value", $row.id);
                            $("input[name='value']").attr("value", $row.value);
                            $("input[name='name']").attr("value", $row.name);
                        }
                    });
                });
            }
        }

        function open(table,row) {
            $tableDic = table;
            $row = row;
            $addDialog = BootstrapDialog.show(options);
        }

    });

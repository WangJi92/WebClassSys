/**
 * Created by JetWang on 2016/10/24.
 */
define(basePath + "/views/equipmentmanage/dialog/add/js/equipment_add",
    [basePath + "/views/equipmentmanage/service/equipment_service",
        basePath + "/views/dictionary/service/dictionaryService"
    ],
    function (require, exports, module) {
        //数据字典的信息
        var $dicService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $equipmentService = require(basePath + "/views/equipmentmanage/service/equipment_service");
        var $equipmentAddDialog;
        var $tableEquipment;
        var options = {
            title: "添加设备",
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
                "removePath": basePath + "/equipment/equipmentView"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    console.log($("form[id='dialog_form_eq']").serialize());
                    $equipmentService.saveOrUpdate($("form[id='dialog_form_eq']").serialize(), function (data) {
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
                            $tableEquipment.bootstrapTable('refresh');//刷新
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
                $dicService.getDicSelectByName("DIC_EQUIPMENT", function (result) {
                    if (result.success == true && result.data != null) {
                        console.log(result);
                        $.each(result.data, function (index, value) {
                            $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                        });
                    }
                });
            }
        };
        module.exports = {
            opendAddDialog: function (tableRef) {
                $tableEquipment = tableRef;
                $equipmentAddDialog = BootstrapDialog.show(options);
            }

        }

    })
;
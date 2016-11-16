/**
 * Created by JetWang on 2016/11/16.
 */

define(basePath + "/views/applymaintainmange/dialog/dealsuccess/js/dealsuccess",
    [
        basePath + "/views/applymaintainmange/service/applymaintainservice",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
    ],
    function (require, exports, module) {
        var $applymaintainservice = require(basePath + "/views/applymaintainmange/service/applymaintainservice");
        var $maintainService = require(basePath + "/views/applymaintainmange/service/applymaintainservice");
        var $dialogDealSuccess;
        var $row;
        var $applymaintainTable;
        var options = {
            title: "修改备注信息",
            cssClass: "width400-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath");
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/applymaintainmange/dialog/dealsuccess/dealsuccess.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    var errormessage = null;
                   if($("*[name='adminAddrice']").val() == ""){
                        errormessage ="请输入备注信息";
                    }
                    if(errormessage ==null){
                        $applymaintainservice.update({
                            adminAddrice:$("*[name='adminAddrice']").val(),
                            id:$row.id,
                            state:3
                        },function(result){
                            if(result.success == false){
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
                            }else{
                                dialogRef.close();
                                $applymaintainTable.bootstrapTable("refresh");
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
        function initData(){
            $("*[name='adminAddrice']").attr("value",$row.adminAddrice);
        }
        module.exports = {
            opendDialog: function (row,applymaintaintable) {
                $row = row;
                if($row.state == 2){
                    $.extend(options,{
                        title:"修改处理状态及备注信息"
                    })
                }
                $applymaintainTable = applymaintaintable;
                $dialogDealSuccess = BootstrapDialog.show(options);
            },
            initData:initData
        }
    });
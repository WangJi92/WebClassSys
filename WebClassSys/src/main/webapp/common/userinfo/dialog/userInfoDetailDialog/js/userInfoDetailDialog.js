/**
 * Created by JetWang on 2016/11/21.
 */


define(basePath + "/common/userinfo/dialog/userInfoDetailDialog/js/userInfoDetailDialog",
    [
    ],
    function (require, exports, module) {
      
        module.exports = {
            openDialog: openDialog
        }
        var $row;// 当前行的信息
        var $applyclassroomtable;//刷新表格对象
        var optionsDialog = {
            title: "个人信息",
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
                "removePath": basePath + "/common/userinfo/dialog/userInfoDetailDialog/userInfoDetailDialog.jsp"
            },
            buttons: [ {
                id: "add_cancel",
                label: "关闭",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }
            ],
            onshown: function (dialogRef) {

            },
            onhidden: function(dialogRef){
                location.reload();
            }
        };
        function openDialog() {
            BootstrapDialog.show(optionsDialog);
        }
    });
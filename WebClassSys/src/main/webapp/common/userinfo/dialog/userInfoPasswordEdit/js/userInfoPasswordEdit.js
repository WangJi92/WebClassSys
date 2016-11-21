/**
 * Created by JetWang on 2016/11/21.
 */


define(basePath + "/common/userinfo/dialog/userInfoPasswordEdit/js/userInfoPasswordEdit",
    [
    ],
    function (require, exports, module) {

        module.exports = {
            initEvent:initEvent,
            openDialog: openDialog//传递当前table的row信息过来~~
        }
        var optionsDialog = {
            title: "修改个人信息",
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
                "removePath": basePath + "/common/userinfo/dialog/userInfoPasswordEdit/userInfoPasswordEdit.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    saveUser(dialogRef);
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

            },
            onhidden: function(dialogRef){
                location.reload();
                //droup-menu 突然间不能点击了...
            }
        };

        function initEvent(){
            $(".edit_password_radio").on("click",":radio",function(){
                if($(this).val()==1){
                    $(".edit_password_content").css('display','block');
                }else{
                    $(".edit_password_content").css('display','none');
                }
            });//选择是否需要修改密码
        }
        function saveUser(dialogRef){
            var data ={
                phone:$("input[name='phone']").val(),
                password:"",
                newpassword:""
            }
            if($("input[type='radio']:checked").val() == 1){
                data.password =$("*[name='password']").val();
                data.newpassword=$("*[name='newpassword']").val()
            }
            var errormessage = null;
            if(data.phone ==""){
                errormessage ="请输入电话"
            }else if($("input[type='radio']:checked").val() == 1&&data.password ==""){
                errormessage ="请输入原密码"
            }else if($("input[type='radio']:checked").val() == 1&&data.nowpassword ==""){
                errormessage="请输入新密码";
            }
            if(errormessage == null){
                updata(data,function(result){
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
                    }
                })
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
            BootstrapDialog.show(optionsDialog);
        }

        /**
         * 更新手机号码和密码
         * @param data
         * @param callback
         */
        function updata(data, callback) {
            var url = basePath + "/UserInfo/editpassword";
            var config = {
                url: url,
                data: data,
                success: callback
            };
            $.ajax(config);
        }
    });
/**
 * Created by JetWang on 2016/11/16.
 * todo 保修管理中的人员分派信息
 */
define(basePath + "/views/applymaintainmange/dialog/assign/js/assign",
    [
        basePath + "/views/applymaintainmange/service/applymaintainservice",
        basePath + "/views/applymaintainmange/dialog/maintainpeople/js/peopledialog",
        basePath + "/libs/bootstrap-table/dist/bootstrap-table",
    ],
    function (require, exports, module) {
        var $applymaintainservice = require(basePath + "/views/applymaintainmange/service/applymaintainservice");
        var $peopleDialog = require(basePath + "/views/applymaintainmange/dialog/maintainpeople/js/peopledialog");
        var $dialogAssign;
        var $row;//行信息
        var $applymaintaintable;//table的引用
        var options = {
            title: "分派维修人员",
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
                "removePath": basePath + "/views/applymaintainmange/dialog/assign/assign.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    //console.log($("form[id='dialog_form_eq']").serialize());
                    validate(dialogRef);
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
        function initEvent(){
            $(".select_maintain_people").click(function(){
                $peopleDialog.opendDialog();
            });//选择维修人员的信息

            $(".send_message_radio").on("click",":radio",function(){
                if($(this).val()==1){
                    $(".send_message_text").css('display','block');
                }else{
                    $(".send_message_text").css('display','none');
                }
            });//选择是否需要发送短信的内容
             var MessageStr = '【申请者姓名】'+ $row.applyName+'\r\n'+
                 '【申请者电话】' + $row.applyPhone+'\r\n'+
                 '【报修类型】' + $row.typeStr+'\r\n'+
                 '【紧急程度】' + $row.uergencyStateStr+'\r\n'+
                 '【教室名称】' + $row.classRoomName+'\r\n'+
                 '【报修详情】' + $row.applyDetail+'\r\n'+
                 '【报修时间】' + $row.createTimeStr+'\r\n';
            $("*[name='message']").attr("value",MessageStr);
            $("*[name='adminAddrice']").attr("value","正在处理中...");

        }
        function validate(dialogRef){
            var errormessage = null;
            if($("input[name='maintainPeople']").val() ==""){
                errormessage ="请选择人员信息"
            }else if($("input[name='maintainPeoplePhone']").val() ==""){
                errormessage ="请输入手机号码"
            }else if($("input[type='radio']:checked").val() == 1 &&  $("*[name='message']").val() ==""){
                errormessage="请输入发送的短信内容";
            }else if($("*[name='adminAddrice']").val() == ""){
                errormessage ="请输入备注信息";
            }
            if(errormessage ==null){
                $applymaintainservice.update({
                    maintainPeople:$("input[name='maintainPeople']").val(),
                    maintainPeoplePhone:$("input[name='maintainPeoplePhone']").val(),
                    message:$("input[type='radio']:checked").val() == 1 ? $("*[name='message']").val():"",
                    adminAddrice:$("*[name='adminAddrice']").val(),
                    id:$row.id,
                    maintainIndexCode:$("input[name='maintainIndexCode']").val(),
                    state:2
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
                         $applymaintaintable.bootstrapTable("refresh");
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
        module.exports = {
            opendDialog: function (row,applymaintaintable) {
                $row = row;
                $applymaintaintable =applymaintaintable;
                $dialogAssign = BootstrapDialog.show(options);
            },
            initEvent:initEvent
        }
    })
;
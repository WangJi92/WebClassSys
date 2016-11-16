/**
 * Created by JetWang on 2016/11/16.
 */


define(basePath + "/user/applymaintain/js/applymaintain",
    [
        basePath + "/views/dictionary/service/dictionaryService",
        basePath + "/user/applymaintain/service/applymainservice"
    ],
    function (require, exports, module){
        var $dictionaryService = require(basePath + "/views/dictionary/service/dictionaryService");
        var $userApplyMainTainService = require(basePath + "/user/applymaintain/service/applymainservice");
        function initEvent(){
            $dictionaryService.getDicSelectByName("DIC_URGENCYREPAIR", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='uergencyState']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
            $dictionaryService.getDicSelectByName("DIC_REPAIRCLASSFIY", function (result) {
                if (result.success == true && result.data != null) {
                    $.each(result.data, function (index, value) {
                        $("select[name='type']").append("<option value='" + value.value + "'>" + value.key + "</option>");
                    });
                }
            });
            $(".col-xs-12").on("click",".btn_summit",function(){
                validate();
            });//提交表单事件
       }
        function validate(){
            var errormessage = null;
            if($("input[name='applyName']").val() ==""){
                errormessage ="请输入姓名"
            }else if($("input[name='applyPhone']").val() ==""){
                errormessage ="请输入手机号码"
            }else if($("input[name='classRoomName']").val() == ""){
                errormessage="教室名称不能为空";
            }else if($("*[name='applyDetail']").val() == ""){
                errormessage ="请输入详细描述信息";
            }
            console.log(errormessage);
            if(errormessage == null){
                var data ={
                    applyName:$("input[name='applyName']").val(),
                    applyPhone:$("input[name='applyPhone']").val(),
                    classRoomName:$("input[name='classRoomName']").val(),
                    applyDetail:$("*[name='applyDetail']").val(),
                    uergencyState:$("*[name='uergencyState']").val(),
                    type:$("*[name='type']").val()
                }
                $userApplyMainTainService.save(data,function(data){
                    var message ="提交成功"
                    if(data.success == false){
                        message = data.message;
                    }
                    var dialogTip = new BootstrapDialog({
                        message: message,
                        cssClass: "width200-dialog",
                        onshow: function (diaRef) {
                            setTimeout(function () {
                                if(data.success == true){
                                    window.location.href = basePath+"/user/applymaintain/applymaintain.jsp";
                                }
                                diaRef.close();
                            }, 1500);
                        }
                    });
                    dialogTip.realize();
                    dialogTip.getModalHeader().hide();
                    dialogTip.getModalFooter().hide();
                    dialogTip.open();

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
         initEvent:initEvent
        }

    });
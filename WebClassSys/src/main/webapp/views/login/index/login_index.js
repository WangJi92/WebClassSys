/**
 * Created by JetWang on 2016/10/4.
 */
define(basePath + "/views/login/index/login_index",
    ["backstretch"
    ],
    function(require, exports,module) {

        module.exports={
            init:init
        }
        function init(){
            $ .backstretch([
                basePath+"/views/login/img/school/bg1.jpg",
                basePath+"/views/login/img/school/bg2.jpg",
                basePath+"/views/login/img/school/bg3.jpg"
            ], {
                fade: 1000,
                duration: 8000
            });

            $(".login-form").on("click",".btn_login",function(){
                var  data={
                    name:$("input[name='name']").val(),
                    password:$("input[name='password']").val(),
                    save:0
                }
                if($("input[name='save']").prop("checked")){
                    data.save = 1;
                }
                validata(data);
            });
        }
        function validata(data){
             var errormessage =null;
            if(data.name == ""){
                errormessage="登录名不能为空";
            }else if(data.password== ""){
                errormessage="密码不能为空";
            }
            if(errormessage == null){
                Login(data,function(result){
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
                    }else if(result.data.type ==1){
                        window.location.href= basePath+"/views/classroommanage/classroommanage.jsp";
                    }else{
                        window.location.href= basePath+"/user/buildingInfo/buidlingInfo.jsp";
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

        function Login(data,callback){
            var url = basePath+"/UserInfo/login";
            var config ={
                url :  url,
                data:data,
                success : callback
            };
            $.ajax(config);
        }
    });

/**
 * Created by JetWang on 2016/10/14.
 */
define(basePath + "/views/dictionary/dialog/add/js/dic_add",
    [basePath+"/views/dictionary/service/dictionaryService"
    ,"jquery-validation"
    ],
    function(require, exports,module) {
        var $dicService = require(basePath+"/views/dictionary/service/dictionaryService");
        var $addDialog;
        var $tableDic;
        var options ={
            title:"添加数据字典",
           /* size:BootstrapDialog.SIZE_SMALL,*/
            cssClass:"width400-dialog",
            type:BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message:function(dialogRef){
                var $message =$("<div></div>");
                var dialogpath =  dialogRef.getData("removePath")
                $message.load(dialogpath);
                return $message;
            },
            data:{
                "removePath":basePath+"/dictionary/dicAdd"
            },
            buttons:[{
                id:"dic_add_save",
                label:"保存",
                cssClass: 'btn-primary',
                action:function(dialogRef){
                    $dicService.addOrUpdate($("#dicDialog").serialize(),function(data){
                        if(data.success == true){
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
                }},
                {
                    id:"dic_add_cancal",
                    label:"取消",
                    action:function(dialogRef){
                        dialogRef.close();
                    }
                }
            ],
            onshown:function(dialogRef){
                $dicService.dicFatherSelectBean(function(result){
                    if(result.success == true && result.data != null){
                        $.each(result.data,function(index,value){
                           // console.log(value.value+value.key);
                            $("select[name='classfiyType']").append("<option value='" + value.value + "'>"+value.key+"</option>");
                        });
                    }
                });
            }
        };
        module.exports={
            openAddDialog:open
        }
        function open(table){
            $tableDic = table;
            $addDialog = BootstrapDialog.show(options);
        }

    });

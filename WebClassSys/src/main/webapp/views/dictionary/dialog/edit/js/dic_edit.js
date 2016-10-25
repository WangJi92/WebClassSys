/**
 * Created by JetWang on 2016/10/15.
 */
define(basePath + "/views/dictionary/dialog/edit/js/dic_edit",
    [basePath+"/views/dictionary/service/dictionaryService"],
    function(require, exports,module) {
        var $dicService = require(basePath+"/views/dictionary/service/dictionaryService");
        var $row;
        var $tablbDicRef;
        var $editDialog;
        var options ={
            title:"修改数据字典",
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
                "removePath":basePath+"/dictionary/dicEdit"
            },
            buttons:[{
                id:"dic_add_save",
                label:"保存",
                cssClass: 'btn-primary',
                action:function(dialogRef){
                    $dicService.addOrUpdate($("#dicDialog").serialize(),function(data){
                        if(data.success == true){
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
                            $tablbDicRef.bootstrapTable('refresh');//刷新
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
                $dicService.getDicTypeValue(function(dataSelect){
                    console.log(dataSelect);
                    var SelectData = dataSelect.data;
                    var selectId;//找到当前的SelectId;
                    for(var i=0; i<SelectData.length;i++){
                          if(SelectData[i].key==$row.classfiyType){
                              selectId = SelectData[i].value;
                              break;
                          }
                    }
                    $dicService.dicFatherSelectBean(function(result){
                        if(result.success == true && result.data != null){
                            $.each(result.data,function(index,value){
                                if(value.value == selectId){
                                    $("select[name='classfiyType']").append("<option selected value='" + value.value + "'>"+value.key+"</option>");
                                }else{
                                    $("select[name='classfiyType']").append("<option value='" + value.value + "'>"+value.key+"</option>");
                                }
                            });
                            $("input[name='id']").attr("value",$row.id);
                            $("input[name='value']").attr("value",$row.value);
                            $("input[name='name']").attr("value",$row.name);
                        }
                    });
                });

            }
        };
        module.exports={
            openEditDialog:open
        }
        function open(row,tableRef){
            console.log(row);
            $row=row;
            $tablbDicRef =tableRef;
            BootstrapDialog.show(options);
        }
    });

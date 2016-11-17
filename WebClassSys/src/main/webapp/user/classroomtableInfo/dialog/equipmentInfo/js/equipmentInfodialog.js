/**
 * Created by JetWang on 2016/11/17.
 */

define(basePath + "/user/classroomtableInfo/dialog/equipmentInfo/js/equipmentInfodialog",
    [
        "mustache",
    ],
    function (require, exports, module) {
        module.exports = {
            initEvent: initEquipmentList,
            openDialog: openDialog//传递当前table的row信息过来~~
        }
        var $row;// 当前行的信息
        var optionsDialog = {
            title: "设备信息列表",
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
                "removePath": basePath + "/user/classroomtableInfo/dialog/equipmentInfo/equiipmentinfo.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "关闭",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }
            ],
            onshown: function (dialogRef) {

            }
        };

        /**
         * 加载设备信息列表
         */
        function initEquipmentList() {
            var template_list_group = $("#equipment_list_template").html();
            Mustache.parse(template_list_group);
            findAllByClassRoomIndexCode({
                classroomIndex: $row.indexCode
            }, function (result) {
                if (result.success == true && result.data.length > 0) {
                    $.each(result.data, function (index, item) {
                        Mustache.parse(template_list_group);
                        var outputStr = Mustache.render(template_list_group, item);
                        $(".equipmentList").append(outputStr);
                    });
                } else {
                    $(".equipmentList").append('<li class="list-group-item">设备信息为空</li>');
                }

            });//ajax请求通过教室的Indexcode, 获取所有的结果的信息
        }

        function findAllByClassRoomIndexCode(data, callback) {
            var url = basePath + "/classroomequipment/findAllByClassRoomIndexcode";
            var config = {
                url: url,
                data: data,
                success: callback
            };
            $.ajax(config);
        }//ajax请求当前教室的所有的设备的信息
        function openDialog(row) {
            $row = row;
            BootstrapDialog.show(optionsDialog);
        }
    });
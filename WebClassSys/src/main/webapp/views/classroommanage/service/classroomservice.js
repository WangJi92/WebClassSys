/**
 * Created by JetWang on 2016/11/7.
 */

define(basePath + "/views/classroommanage/service/classroomservice",
    function (require, exports, module) {
        module.exports = {
            /**
             * 保存或者更改信息
             * @param equipment
             * @param callback
             */
            saveOrUpdate: function (data, callback) {
                var url = basePath + "/classRoomAction/saveOrUpdate";
                var config = {
                    url: url,
                    async: false,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            findByIndexCode: function (indexcode, callback) {
                var url = basePath + "/classRoomAction/findByIndexCode";
                var config = {
                    url: url,
                    async: false,
                    data: {
                        indexCode:indexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
            findPage: function (indexcode, callback) {
                var url = basePath + "/classRoomAction/findByIndexCode";
                var config = {
                    url: url,
                    data: {
                        indexCode:indexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
            editTimeTable: function (data, callback) {
                var url = basePath + "/timeTableAction/saveOrUpdate";
                var config = {
                    url: url,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            saveEquipmentList: function (data, callback) {
                var url = basePath + "/classroomequipment/saveOrUpdate";
                var config = {
                    url: url,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            findAllByClassRoomIndexCode:function (data, callback) {
                var url = basePath + "/classroomequipment/findAllByClassRoomIndexcode";
                var config = {
                    url: url,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            equipmentdeleteById:function (id, callback) {
            var url = basePath + "/classroomequipment/deleteById";
            var config = {
                url: url,
                data: {
                    id:id
                },
                success: callback
            };
            $.ajax(config);
          },
            deleteByClassRoomIndexcode: function (classRoomIndexcode, callback) {
                var url = basePath + "/classRoomAction/deleteByClassRoomIndexcode";
                var config = {
                    url: url,
                    data: {
                        classRoomIndexcode:classRoomIndexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
        }
    });

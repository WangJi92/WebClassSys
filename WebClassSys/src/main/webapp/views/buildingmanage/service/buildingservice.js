/**
 * Created by JetWang on 2016/10/30.
 */
define(basePath + "/views/buildingmanage/service/buildingservice",
    function (require, exports, module) {
        module.exports = {
            /**
             * 保存或者更改信息
             * @param equipment
             * @param callback
             */
            saveOrUpdate: function (data, callback) {
                var url = basePath + "/buildingInfoAction/saveOrUpdate";
                var config = {
                    url: url,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            findByIndexCode: function (indexcode, callback) {
                var url = basePath + "/buildingInfoAction/findByIndexCode";
                var config = {
                    url: url,
                    data: {
                        indexCode:indexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
            wheaherClassRoomEqualsZero: function (buildingIndexcode, callback) {
                var url = basePath + "/classRoomAction/wheatherEx";
                var config = {
                    url: url,
                    data: {
                        buildingIndexcode:buildingIndexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
            deleteByIndexCode: function (buildingIndexcode, callback) {
                var url = basePath + "/buildingInfoAction/deleteByIndexCode";
                var config = {
                    url: url,
                    data: {
                        buildingIndexcode:buildingIndexcode
                    },
                    success: callback
                };
                $.ajax(config);
            },
        }
    });

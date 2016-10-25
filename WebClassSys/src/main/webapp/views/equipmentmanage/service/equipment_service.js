/**
 * Created by JetWang on 2016/10/24.
 */

define(basePath + "/views/equipmentmanage/service/equipment_service",
    function(require, exports,module) {
        module.exports= {
            /**
             * 删除通过Ids
             * @param ids
             * @param callback
             */
            deleteByIds: function (ids, callback) {
                var url = basePath + "/equipment/deleteByIds";
                var config = {
                    url: url,
                    data: {
                        ids: ids
                    },
                    success: callback
                };
                $.ajax(config);
            },
            /**
             * 保存或者更改信息
             * @param equipment
             * @param callback
             */
            saveOrUpdate: function (equipment, callback) {
                var url = basePath + "/equipment/saveOrUpdate";
                var config = {
                    url: url,
                    data: equipment,
                    success: callback
                };
                $.ajax(config);
            }
        }
    });
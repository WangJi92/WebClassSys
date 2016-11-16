/**
 * Created by JetWang on 2016/11/15.
 */

define(basePath + "/views/applymaintainmange/service/applymaintainservice",
    function (require, exports, module) {
        module.exports = {
            save: function (data, callback) {
                var url = basePath + "/applyMaintainAction/save";
                var config = {
                    url: url,
                    async: false,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            },
            findEntityById: function (id, callback) {
                var url = basePath + "/applyMaintainAction/findEntityById";
                var config = {
                    url: url,
                    async: false,
                    data: {
                        id: id
                    },
                    success: callback
                };
                $.ajax(config);
            },
            findDtoById: function (id, callback) {
                var url = basePath + "/applyMaintainAction/findDtoById";
                var config = {
                    url: url,
                    data: {
                        id: id
                    },
                    success: callback
                };
                $.ajax(config);
            },
            deleteById: function (id, callback) {
                var url = basePath + "/applyMaintainAction/deleteById";
                var config = {
                    url: url,
                    data: {
                        id: id
                    },
                    success: callback
                };
                $.ajax(config);
            },
            update: function (data, callback) {
                var url = basePath + "/applyMaintainAction/update";
                var config = {
                    url: url,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            }
        }
    });

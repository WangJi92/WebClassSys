/**
 * Created by JetWang on 2016/11/16.
 */

define(basePath + "/user/applymaintain/service/applymainservice",
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
            }
        }
    });

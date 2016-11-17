/**
 * Created by JetWang on 2016/11/17.
 */

define(basePath + "/user/applyclassroom/service/applyclassroomservice",
    function (require, exports, module) {
        module.exports = {
            save: function (data, callback) {
                var url = basePath + "/applyClassRoomAction/applyClassRoom";
                var config = {
                    url: url,
                    async: false,
                    data: data,
                    success: callback
                };
                $.ajax(config);
            }
        }});
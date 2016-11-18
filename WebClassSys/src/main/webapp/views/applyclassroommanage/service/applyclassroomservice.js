/**
 * Created by JetWang on 2016/11/18.
 */


define(basePath + "/views/applyclassroommanage/service/applyclassroomservice",
    function (require, exports, module) {
        module.exports = {
            updataeState: function (data, callback) {
                var url = basePath + "/applyClassRoomAction/update";
                var config = {
                    url: url,
                    data: data,
                    async: false,
                    success: callback
                };
                $.ajax(config);
            }//更新处理意见
            ,
            applyState: function (callback) {
                var url = basePath + "/applyClassRoomAction/applystate";
                var config = {
                    url: url,
                    async: false,
                    success: callback
                };
                $.ajax(config);
            },//获取select表中的选项
        }
    });
/**
 * Created by JetWang on 2016/11/18.
 */


define(basePath + "/user/myapplyclassroomtable/service/myapplyclassroomservice",
    function (require, exports, module) {
        module.exports = {
            applyState: function (callback) {
                var url = basePath + "/applyClassRoomAction/applystate";
                var config = {
                    url: url,
                    async: false,
                    success: callback
                };
                $.ajax(config);
            },//获取select表中的选项
            cancelApply: function (id,callback) {
                var url = basePath + "/applyClassRoomAction/cancel";
                var config = {
                    url: url,
                    data:{
                      id:id
                    },
                    async: false,
                    success: callback
                };
                $.ajax(config);
            }//取消申请教室，更新教室课程表的状态
        }});
/**
 * Created by JetWang on 2016/11/21.
 */


define(basePath + "/common/userinfo/userinfoIndex/userInfoIndex",
    [
        basePath + "/common/userinfo/dialog/userInfoDetailDialog/js/userInfoDetailDialog",
        basePath + "/common/userinfo/dialog/userInfoPasswordEdit/js/userInfoPasswordEdit"
    ],
    function (require, exports, module) {
        var $personInfoDialog = require(basePath + "/common/userinfo/dialog/userInfoDetailDialog/js/userInfoDetailDialog");
        var $personInfoEditDialog = require(basePath + "/common/userinfo/dialog/userInfoPasswordEdit/js/userInfoPasswordEdit");
        module.exports = {
            initEvent: initEvent
        }

        function initEvent() {
            $(".editPersonInfo").click(function () {
                $personInfoEditDialog.openDialog();
            });
            $(".personInfo").click(function (e) {
                $personInfoDialog.openDialog();
            });

        }
    });
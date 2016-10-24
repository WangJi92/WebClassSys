
define(basePath + "/views/usermanage/service/userService",
    function(require, exports,module) {
        module.exports={
            /**
             * 删除通过Ids
             * @param ids
             * @param callback
             */
            deleteByIds:function(ids,callback){
                var url = basePath+"/UserInfo/deleteUserByIds";
                var config ={
                    url :  url,
                    data:{
                        ids:ids
                    },
                    success : callback
                };
                $.ajax(config);
            },
            /**
             * 获得用户信息通过名字
             * @param name
             * @param callback
             */
            getUserInfoByName:function(name,callback){
                var url = basePath+"/UserInfo/getUserInfoByName";
                var config ={
                    url :  url,
                    data:{
                        userName:name
                    },
                    success : callback
                };
                $.ajax(config);
            },
            /**
             * 获得用户信息通过Id
             * @param id
             * @param callback
             */
            getUserInfoById:function(id,callback){
                var url = basePath+"/UserInfo/getUserInfoById";
                var config ={
                    url :  url,
                    data:{
                        id:id
                    },
                    success : callback
                };
                $.ajax(config);
            },
            /**
             * 保存或者更改信息
             * @param userinfo
             * @param callback
             */
            saveOrUpdate:function(userinfo,callback){
                var url = basePath+"/UserInfo/saveOrupdate";
                var config ={
                    url :  url,
                    data:userinfo,
                    success : callback
                };
                $.ajax(config);
            },
            exportExcel:function(callback){
                var url=basePath+"/UserInfo/exportUserInfo";
                var config = {
                    url:url,
                    success:callback
                };
                $.ajax(config);
            }
        }
    });
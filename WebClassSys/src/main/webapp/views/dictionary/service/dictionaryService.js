/**
 * Created by JetWang on 2016/10/12.
 */
define(basePath + "/views/dictionary/service/dictionaryService",
    function(require, exports,module) {
        module.exports={
            deleteByIndexcodes:function(indexcodes,callback){
                var url = basePath+"/dictionary/deleteByIndexcodes";
                var config ={
                    url :  url,
                    data:{
                        indexcodes:indexcodes
                    },
                    success : callback
                };
                $.ajax(config);
            },
            deleteByIds:function(ids,callback){
                var url = basePath+"/dictionary/deleteByIds";
                var config ={
                    url :  url,
                    data:{
                        ids:ids
                    },
                    success : callback
                };
                $.ajax(config);
            },
            addOrUpdate:function(dictionary,callback){
                var url = basePath+"/dictionary/addOrUpdate";
                var config = {
                    url:url,
                    data:dictionary,
                    success:callback
                };
                $.ajax(config);
            },
            findById:function(id,callback){
                var url=basePath+"/dictionary/findById";
                var config = {
                    url:url,
                    data:{
                        id:id
                    },
                    success:callback
                };
                $.ajax(config);
            },
            findByIndexcode:function(indexcode,callback){
                var url=basePath+"/dictionary/findByIndexcode";
                var config = {
                    url:url,
                    data:{
                        indexcode:indexcode
                    },
                    success:callback
                };
                $.ajax(config);
            },
            delById:function(id,callback){
                var url=basePath+"/dictionary/delById";
                var config = {
                    url:url,
                    data:{
                        id:id
                    },
                    success:callback
                };
                $.ajax(config);
            },
            delByIndexcode:function(indexcode,callback){
                var url=basePath+"/dictionary/delByIndexcode";
                var config = {
                    url:url,
                    data:{
                        indexcode:indexcode
                    },
                    success:callback
                };
                $.ajax(config);
            },
            getDicSelectByName:function(typeName,callback){
                var url=basePath+"/dictionary/getDicSelectByName";
                var config = {
                    url:url,
                    data:{
                        typeName:typeName
                    },
                    success:callback
                };
                $.ajax(config);
            },
            dicFatherSelectBean:function(callback){
                var url=basePath+"/dictionary/dicFatherSelectBean";
                var config = {
                    url:url,
                    success:callback
                };
                $.ajax(config);
            },
            getDicTypeValue:function(callback){
                var url=basePath+"/dictionary/getDicTypeValue";
                var config = {
                    url:url,
                    success:callback
                };
                $.ajax(config);
            }


        }


    });
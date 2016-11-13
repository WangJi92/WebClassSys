/**
 * Created by JetWang on 2016/11/10.
 */

define(basePath + "/views/classroommanage/dialog/addEquipment/js/addEquipment",
    [
        basePath + "/views/classroommanage/service/classroomservice",
        "mustache",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table.css",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table",
        basePath+"/libs/bootstrap-table/dist/bootstrap-table-locale-all"
    ],
    function(require, exports,module) {
        var $classroomService = require(basePath + "/views/classroommanage/service/classroomservice");
        //设置 bootstrap-tablb 配置信息
        var $equipmentTable ={
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 5,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [5, 10, 15, 20],  //记录数可选列表
            search: true,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            url:basePath+"/equipment/findPage",
            responseHandler:function(res){
                //远程数据加载之前,处理程序响应数据格式,对象包含的参数: 我们可以对返回的数据格式进行处理
                //在ajax后我们可以在这里进行一些事件的处理
                return res.data;
            },
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    //这里是在ajax发送请求的时候设置一些参数
                    pageNo: params.pageNumber,
                    pageSize: params.pageSize,
                    search:params.searchText
                };
                return param;
            },
            onLoadSuccess: function(data){  //加载成功时执行

            },
            onLoadError: function(){  //加载失败时执行

            },
            //  height: getHeight(),
            formatSearch:function(){
                return "名称品牌简介.."
            },
            columns: [
                {
                    field: 'state',
                    radio: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '设备名称',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'typeStr',
                    title: '设备类型',
                    align: 'center'
                }, {
                    field: 'brandName',
                    title: '品牌名称',
                    align: 'center'
                },
                {
                    field: 'introduce',
                    title: '简介',
                    align: 'center'
                }
            ]
        };
        function initEvent(){
            $tableEquipmentManage = $("#table_equipment").bootstrapTable($equipmentTable);

        }
        function getIdSelections() {
            return $.map($tableEquipmentManage.bootstrapTable('getSelections'), function (row) {
                console.log(row);
                return row.id
            });
        }
        module.exports = {
            initEvent:initEvent,
            initDialog:initDialog
        }


        var optionsDialog = {
            title: "添加设备",
            cssClass: "width700-dialog",
            type: BootstrapDialog.TYPE_DEFAULT,
            draggable: true,
            message: function (dialogRef) {
                var $message = $("<div></div>");
                var dialogpath = dialogRef.getData("removePath")
                $message.load(dialogpath);
                return $message;
            },
            data: {
                "removePath": basePath + "/views/classroommanage/dialog/addEquipment/addEquipmentTable.jsp"
            },
            buttons: [{
                id: "add_save",
                label: "保存",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    addEquipmentInToListGroup(dialogRef);
                }
            }, {
                id: "add_cancel",
                label: "取消",
                cssClass: 'btn-primary',
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }
            ],
            onshown: function (dialogRef) {

            }
        };

        /**
         * todo 添加设备信息的List列表中去
         * 先保存然后重新加载；
         */
        function addEquipmentInToListGroup(dialogRef){
         var rows = $tableEquipmentManage.bootstrapTable('getSelections');
            if(rows.length>0){
                var template_list_group = $("#equipment_list_template").html();
                Mustache.parse(template_list_group);
                var classroomIndexcode = $("input[name='indexCode']").attr("value");
                console.log("jiaoshi:"+classroomIndexcode);
                $classroomService.saveEquipmentList({
                    classRoomIndexCode:classroomIndexcode,
                    equipmentIndexCode:rows[0].indexCode
                },function(data){
                    /**
                     * todo 删除之前的所有的，然后从新加载
                     */
                    if(data.success == true){
                        $(".equipmentItem").remove();
                        $classroomService.findAllByClassRoomIndexCode({
                            classroomIndex:classroomIndexcode
                        },function(result){
                            console.log(result);
                            $.each(result.data, function (index, item) {
                                Mustache.parse(template_list_group);
                                var outputStr = Mustache.render(template_list_group,item);
                                $(".equipmentList").append(outputStr);
                            });
                            dialogRef.close();

                        });
                    }
                });
            }

        }
        function initDialog(){
            BootstrapDialog.show(optionsDialog);
        }

    });
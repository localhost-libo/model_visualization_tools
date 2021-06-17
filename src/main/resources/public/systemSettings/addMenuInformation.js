
function obtainUserData(){
    $.ajax({
        url: "/systemSettings/getMenuData.do",
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data){
            $("#addMenuInformationData").empty();
            setMenuDataList(data.menuDataList);
            prettyPrint();
        }
    })
}


function setMenuDataList(info){
    BUI.use(['bui/grid','bui/data'],function (Grid,Data) {
        //默认的数据
        data = JSON.parse(info),
            store = new Data.Store({
                data:data
            }),
            editing = new Grid.Plugins.CellEditing(),
            grid = new Grid.Grid({
                render : '#addMenuInformationData',
                columns : [
                    {title : '菜单ID',dataIndex :'id',editor : {xtype :  'text',rules:{required:true}}},
                    {title : '菜单名称',dataIndex :'name',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '排序',dataIndex :'sequence',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '所属分类',dataIndex :'category',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '所属分类ID',dataIndex :'category_sort',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '管理人员权限',dataIndex :'manag',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '操作人员权限',dataIndex :'operation',editor : {xtype : 'text',rules:{required:true}}},
                ],
                width : 1000,
                // loadMask : true, //遮罩效果
                forceFit : true,//自适应宽度
                store : store,
                plugins : [Grid.Plugins.CheckSelection,editing],
                tbar:{
                    items : [{
                        btnCls : 'button button-small',
                        text : '<i class="icon-plus"></i>添加',
                        listeners : {
                            'click' : addFunction
                        }
                    },
                        {
                            btnCls : 'button button-small',
                            text : '<i class="icon-remove"></i>删除',
                            listeners : {
                                'click' : delFunction
                            }
                        }]
                }

            });
        grid.render();

        function addFunction(){
            var newData = {id :'ID必须唯一'};
            store.add(newData);
            editing.edit(newData,'id'); //添加记录后，直接编辑
        }

        function delFunction(){
            var selections = grid.getSelection();
            store.remove(selections);
        }
        $('#btnSave').on('click',function(){
            if(editing.isValid()){
                var records = store.getResult();
                // console.log(records);
                // // var records = BUI.JSON.stringify(records)
                var formData = $("#addMenuInformationData").serializeArray();
                for ( var i in records){
                    formData.push({name:"id" , value:   records[i].id});
                    formData.push({name:"name" , value:   records[i].name});
                    formData.push({name:"sequence" , value:   records[i].sequence});
                    formData.push({name:"category" , value:   records[i].category});
                    formData.push({name:"category_sort" , value:   records[i].category_sort});
                    formData.push({name:"manag" , value:   records[i].manag});
                    formData.push({name:"operation" , value:   records[i].operation});
                }
                console.log(formData)
                $.ajax({
                    url: "/systemSettings/updateMenuDataList.do",
                    type: "post",
                    data: formData,
                    dataType: "json",
                    cache: false,
                    success: function (data){
                        if (data.sign){
                            alert("保存成功！");
                            window.history.back(-1);
                        }else{
                            alert("保存失败！");
                        }
                    }
                })
            }
        });
    });

}



function obtainUserData(){
    var formData = $("#userInfoformationForm").serializeArray();
    $.ajax({
        url: "/systemSettings/getUserData.do",
        type: "post",
        data: formData,
        dataType: "json",
        cache: false,
        success: function (data){
            $("#menuInformationData").empty();
            setMenuDataList(data.userDataList);
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
                render : '#userInfoformationData',
                columns : [{title : '用户名',dataIndex :'user_name'
                    //不可编辑则删除下行
                    ,editor : {xtype : 'text',rules:{required:true}}
                    },
                    {title : '真实姓名',dataIndex :'real_name',editor : {xtype :  'text',rules:{required:true}}},
                    {title : '手机号',dataIndex :'phone',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '性别',dataIndex :'gender',editor : {xtype : 'text',rules:{required:true}}},
                    {title : '组织机构',dataIndex :'name',editor : {xtype : 'select',items:{'管理人员':'管理人员','操作人员':'操作人员'}}},
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
                var formData = $("#userInfoformationForm").serializeArray();
                for ( var i in records){
                    formData.push({name:"user_name" , value:   records[i].user_name});
                    formData.push({name:"real_name" , value:   records[i].real_name});
                    formData.push({name:"phone" , value:   records[i].phone});
                    formData.push({name:"gender" , value:   records[i].gender});
                    formData.push({name:"name" , value:   records[i].name});
                }
                console.log(formData)
                $.ajax({
                    url: "/systemSettings/userDataOperation.do",
                    type: "post",
                    data: formData,
                    dataType: "json",
                    cache: false,
                    success: function (data){
                        if (data.sign){
                            alert("保存成功！");
                        }else{
                            alert("保存失败！");
                        }
                    }
                })
            }
        });
    });

}


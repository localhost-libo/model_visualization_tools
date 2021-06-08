function setSelectNameList(){
    $.ajax({
        url: "/systemSettings/getSelectNameData.do?category="+$("#category").val(),
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data){
            $("#name").empty();
            var nameList = data.nameList;
            for (var i in nameList){
                $("#name").append("<option value='"+nameList[i].name+"' >"+nameList[i].name+"</option>")
            }
            obtainSupMenuData();
        }
    })
}
function obtainSupMenuData(){
    var formData = $("#MenuInformationForm").serializeArray();
    $.ajax({
        url: "/systemSettings/getSupMenuData.do",
        type: "post",
        data: formData,
        dataType: "json",
        cache: false,
        success: function (data){
            $("#menuInformationData").empty();
            setMenuDataList(data.menuUtilList);
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
                render : '#menuInformationData',
                columns : [{title : 'ID',dataIndex :'id',editor : {xtype : "text",rules:{required:true}}},
                    {title : '名字',dataIndex :'text',editor : {xtype : 'text',rules:{required:true}}},
                    {title : 'URL地址',dataIndex :'href',width:250,editor : {xtype : 'text',rules:{required:true}}},
                    {title : '排序',dataIndex :'sequence',editor : {xtype : 'text'}},
                    {title : '管理人员权限',dataIndex :'manag',editor : {xtype : 'select',items:{1:'1',0:'0'}
                        }},
                    {title : '操作人员权限',dataIndex :'operation',editor : {xtype : 'select',items:{1:'1',0:'0'}}}
                ],
                width : 1000,
                // loadMask : true,
                forceFit : true,
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

    });
}

function a(){
    if(editing.isValid()){
        var records = store.getResult();
        // var records = BUI.JSON.stringify(records)
        var formData = $("#MenuInformationForm").serializeArray();
        for ( var i in records){
            // console.log(records[i].id);
            // console.log(records[i].text);
            // console.log(records[i].href);
            // console.log(records[i].sequence);
            formData.push({name:"id" , value:   records[i].id});
            formData.push({name:"text" , value:   records[i].text});
            formData.push({name:"href" , value:   records[i].href});
            formData.push({name:"sequence" , value:   records[i].sequence});
        }
        $.ajax({
            url: "/systemSettings/updateSupMenuData.do",
            type: "post",
            data:formData,
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
}

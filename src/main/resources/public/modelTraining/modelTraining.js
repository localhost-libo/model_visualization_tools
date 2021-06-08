var datas = null;
var infos = null;
var params = null;
var algorithm = null;

function getFeatureSettingInfo() {
    if (a()) {
        var formData = $("#modelTraining1Form").serializeArray();
        formData.push({name: "algorithm", value: $("#algorithm option:selected").val()});
        formData.push({name: "model_id", value: $("#model_id").val()});
        formData.push({name: "cross_validation", value: $("#cross_validation").val()});
        algorithm = $("#algorithm option:selected").val();
        $.ajax({
            url: "/getDataSheetInfo.do",
            data: formData,
            type: "post",
            dataType: "json",
            cache: false,
            success: function (data) {
                $("#a").show();
                $("#feature_setting_table tbody").empty();
                datas = data.ModelTrainingList;
                setFeatureSettingTable(data);
            }
        })
    }
}

function getFeatureProcessingInfo() {
    // if(b()){
    var formData = $("#featureSettingForm").serializeArray();
    var num = 0;
    for (var i in datas) {
        formData.push({name: "features_model_id", value: datas[i].features_model_id});
        formData.push({name: "field", value: datas[i].field});
        formData.push({name: "describes", value: datas[i].describes});
        formData.push({name: "field_type", value: datas[i].field_type});

        num++;
    }
    $.ajax({
        url: "/getFeatureProcessingData.do",
        data: formData,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (info) {
            $("#b").show();
            $("#feature_processing_table tbody").empty();
            infos = info.ModelTrainingList;
            setFeatureProcessingTable(info);
        }
    })
    // }
}

function yanzheng() {

    var formData = $("#featureProcessingForm").serializeArray();
    formData.push({name: "algorithm", value: algorithm});

    var num = 0;
    for (var i in infos) {
        formData.push({name: "features_model_id", value: infos[i].features_model_id});
        formData.push({name: "field", value: infos[i].field});
        formData.push({name: "describes", value: infos[i].data_sheet});
        formData.push({name: "field_type", value: infos[i].field_type});
        formData.push({name: "setting_type", value: infos[i].setting_type});
        $("#" + infos[i].field + "_handle_value").attr("class", "test1");
        $("#" + infos[i].field + "_handle_value").attr("placeholder", "");
        num++;
    }
    $.ajax({
        url: "/yanzheng.do",
        data: formData,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.sign == 2) {
                isnull(data.strList);
            }
            if (data.sign == 1) {
                $("#super_parameter_setting_table tbody").empty();
                getSuperParameterSettingData();
            }
        }
    })
}

function isnull(data) {
    var num = 0;
    for (var i in data) {
        $("#" + data[i]).addClass("test2");
        $("#" + data[i]).attr("placeholder", "不能为空");
    }
}

function getSuperParameterSettingData() {
    var formData = $("#featureProcessingForm").serializeArray();
    formData.push({name: "algorithm", value: algorithm});
    var num = 0;
    for (var i in infos) {
        formData.push({name: "features_model_id", value: infos[i].features_model_id});
        formData.push({name: "field", value: infos[i].field});
        formData.push({name: "describes", value: infos[i].data_sheet});
        formData.push({name: "field_type", value: infos[i].field_type});
        formData.push({name: "setting_type", value: infos[i].setting_type});
        num++;
    }
    $.ajax({
        url: "/getSuperParameterSettingData.do",
        data: formData,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data) {
            console.log(data)
            alert(data.info);
            if (data.sign) {
                $("#c").show();
                params = data.modelParamsList
                set(params);
            }
        }
    })

}

function setFeatureSettingTable(data) {
    var emp = data.ModelTrainingList;
    $.each(
        emp,
        function (index, item) {
            //     var features_model_id = $("<td></td>").append(
            //         "<input type='checkbox' value='"+item.features_model_id+"'>");
            var features_model_id = $("<td></td>").append(
                item.features_model_id);
            var fields = $("<td></td>").append(
                item.field
            );
            var describes = $("<td ></td>").append(
                item.describes);
            var field_type = $("<td></td>").append(
                item.field_type);
            var whether = $("<td></td>")
                .append(
                    "<select id='" + item.field + "' name='use' class='form-control' onclick='aaa(this.id,this.value)'>  <option value='1'>否</option> <option value='2'>是</option> </select>");
            var type = $("<td></td>")
                .append(
                    "<select id='" + item.field + "_setting_type' name='setting_type' class='form-control' disabled='disabled' ><option value='id'>id</option><option value='String'>String</option><option value='double'>double</option> <option value='int'>int</option> <option value='label'>label</option></select>");

            $("<tr></tr>").append(features_model_id).append(fields)
                .append(describes).append(field_type).append(whether).append(type)
                .appendTo(
                    "#feature_setting_table tbody");
        })
}

function setFeatureProcessingTable(info) {
    var emp = info.ModelTrainingList;
    $.each(
        emp,
        function (index, item) {
            // var inqut = $("<td></td>").append(
            //     "<input type='checkbox' >");
            var features_model_id = $("<td></td>").append(
                item.features_model_id);
            var field = $("<td></td>").append(
                item.field
            );
            var describes = $("<td ></td>").append(
                item.data_sheet);
            var field_type = $("<td></td>").append(
                item.field_type);
            var setting_type = $("<td></td>").append(
                item.setting_type);
            var whether = $("<td></td>")
                .append(
                    "<select id='" + item.field + "' name=\"handle\" class=\"form-control\" onchange='ddd(this.id,this.value)'>\n" +
                    "                                                    <option value=\"minmax\">minmax</option>\n" +
                    "                                                    <option value=\"stringindex\">stringindex</option>\n" +
                    "                                                    <option value=\"onehot\">onehot</option>\n" +
                    // "                                                    <option value=\"pca\">pca</option>\n" +
                    "                                                    <option value=\"qd\">qd</option>\n" +
                    "                                                    <option value=\"无\">无</option>\n" +
                    "                                                </select>");
            var type = $("<td></td>")
                .append(
                    "<input type=\"text\"  name=\"handle_value\" id='" + item.field + "_handle_value' class=\"test1\" disabled=\"disabled\">");

            $("<tr></tr>").append(features_model_id).append(field)
                .append(describes).append(field_type).append(setting_type).append(whether).append(type)
                .appendTo(
                    "#feature_processing_table tbody");
        })
}

function set(data) {
    // var emp = data.ModelTrainingList;
    $.each(
        data,
        function (index, item) {
            var alg_id = $("<td></td>").append(
                item.alg_id);
            var param_name = $("<td></td>").append(
                item.param_name
            );
            var use = $("<td></td>")
                .append(
                    "<select id='" + item.param_name + "' name='use' class='form-control' onclick='ccc(this.id,this.value)'>  <option value='1'>否</option> <option value='2'>是</option> </select>");
            var type = $("<td></td>")
                .append(
                    "<input type=\"text\"  name=\"handle_value\" id='" + item.param_name + "_handle_value'  class=\"test1\" disabled=\"disabled\">");
            // var tips = $("<td></td>").append("<font color=\"#d6d0c7\">我是提示信息</font>");
            $("<tr></tr>").append(alg_id).append(param_name)
                .append(use).append(type)
                .appendTo(
                    "#super_parameter_setting_table tbody");
        })
}

$(document).ready(function () {
    // $("#data_sheet").attr("placeholder","表名不能为空");
    $("#a").hide();
    $("#b").hide();
    $("#c").hide();
    //关闭
    // $("#changeImages").click(function(){
    // $("#image").hide();
    // $("#changeImages").hide();
    // $("#sign").val("2");
    // });
});

function a() {
    var sign = true;
    if ($("#data_sheet").val() == '') {
        $("#data_sheet").addClass("form-control is-invalid");
        $("#data_sheet").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#model_id").val() == '') {
        $("#model_id").addClass("form-control is-invalid");
        $("#model_id").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#cross_validation").val() == '') {
        $("#cross_validation").addClass("form-control is-invalid");
        $("#cross_validation").attr("placeholder", "不能为空");
        sign = false;
    }
    return sign;
}

function b() {
    var sign = true;
    if ($("#data_sheet").val() == '') {
        $("#data_sheet").addClass("form-control is-invalid");
        $("#data_sheet").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#model_id").val() == '') {
        $("#model_id").addClass("form-control is-invalid");
        $("#model_id").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#cross_validation").val() == '') {
        $("#cross_validation").addClass("form-control is-invalid");
        $("#cross_validation").attr("placeholder", "不能为空");
        sign = false;
    }
    return sign;
}

function c() {
    var sign = true;
    if ($("#data_sheet").val() == '') {
        $("#data_sheet").addClass("form-control is-invalid");
        $("#data_sheet").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#model_id").val() == '') {
        $("#model_id").addClass("form-control is-invalid");
        $("#model_id").attr("placeholder", "不能为空");
        sign = false;
    }
    if ($("#cross_validation").val() == '') {
        $("#cross_validation").addClass("form-control is-invalid");
        $("#cross_validation").attr("placeholder", "不能为空");
        sign = false;
    }
    return sign;
}

function aaa(id, value) {
    if (value == "2") {
        $("#" + id + "_setting_type").removeAttr("disabled");
    }
    if (value == "1") {
        $("#" + id + "_setting_type").attr("disabled", "disabled");
        // $("#"+id+"_setting_type").text("");
    }
}

function ddd(id, value) {
    if (value != "pca") {
        $("#" + id + "_handle_value").attr("disabled", "disabled");
        $("#" + id + "_handle_value").val("");
    }
    if (value != "qd") {
        $("#" + id + "_handle_value").attr("disabled", "disabled");
        $("#" + id + "_handle_value").val("");
    }
    if (value == "pca") {
        $("#" + id + "_handle_value").removeAttr("disabled");
    }
    if (value == "qd") {
        $("#" + id + "_handle_value").removeAttr("disabled");
    }
}

function ccc(id, value) {
    if (value == "2") {
        $("#" + id + "_handle_value").removeAttr("disabled");
        $("#" + id + "_handle_value").val("");
    }
    if (value == "1") {
        $("#" + id + "_handle_value").attr("disabled", "disabled");
        $("#" + id + "_handle_value").val("");
    }
}

function preservation() {
    var formData = $("#superParameterSettingForm").serializeArray();
    var num = 0;
    for (var i in params) {
        formData.push({name: "alg_id", value: params[i].alg_id});
        formData.push({name: "param_name", value: params[i].param_name});
        formData.push({name: "handle_value", value: $("#" + params[i].param_name + "_handle_value").val()});
        num++;
    }
    $.ajax({
        url: "/preservation.do",
        data: formData,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data) {
            alert(data.info);
            // if (data.sign){
            //     $("#c").show();
            //     params = data.modelParamsList
            //     set(params);
            // }
        }
    })
}
var schoolList = ["dwa_clue_params", "dwa_clue_abcd", "dwa_clue_qwe", "dwa_clue_abder", "dwa_clue_aabcd", "dwa_clue_qawe", "dwa_clue_aabder"];
var schoolLists = ["dwa_clue_params", "dwa_clue_abcd", "dwa_clue_qwe", "dwa_clue_abder"];
var schoolListss = ["dwa_clue_params", "dwa_clue_abcd", "dwa_clue_qwe", "dwa_clue_abder"];
function search(value) {
    $("#mylist").empty();
    for (i = 0; i < schoolList.length; ++i) {
        if (value != "" && schoolList[i].match(value + ".*") != null) {
            var option = "<option>" + schoolList[i] + "</option>";
            $("#mylist").append(option);
        }
    }
}
function searchs(value) {
    $("#fieldList").empty();
    for (i = 0; i < schoolLists.length; ++i) {
        if (value != "" && schoolLists[i].match(value + ".*") != null) {
            var option = "<option>" + schoolLists[i] + "</option>";
            $("#fieldList").append(option);
        }
    }
}

function searchss(value) {
    $("#labelList").empty();

    for (i = 0; i < schoolListss.length; ++i) {
        if (value != "" && schoolListss[i].match(value + ".*") != null) {
            var option = "<option>" + schoolListss[i] + "</option>";
            $("#labelList").append(option);
        }
    }
}
var sign = 0;
function addNextField(){
    $("<input type=\"text\" class=\"form-control\" autocomplete=\"on\" id='data_sheet_input_"+sign+"' list=\"fieldList\"\n" +
        "onkeyup=\"searchs(this.value)\"  name=\"data_sheet\"> <span class=\"glyphicon glyphicon-minus\" id='data_sheet_span_"+sign+"' onclick='removeField(this.id)'></span>").appendTo("#addNextField");
    sign++;
}

function removeField(span_id){
        $("#"+span_id).prev().remove();
        $("#"+span_id).remove()
}

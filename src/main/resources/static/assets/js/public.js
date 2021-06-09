/**
 * 处理请求路径
 * @param url
 * @returns {string|*}
 */
function handleRequestUrl(url) {
    url = url.indexOf("?") > 0 ? url + "&random_num=" + Math.random() : url + "?random_num=" + Math.random();
    return url;
}
/*
* 创建的模态框在网页的最外层打开
* */
function createModalDialog() {
    var mainBody = $(window.top.document.body);//找到父页面的body对象
    var dialog = mainBody.find("#mainModal");
    return dialog;
}
/**
 * 显示模态框的内容
 * @param dialog
 * @param url
 */
function modalDialogShow(dialog, url) {
    //从url加载页面到模态框
    dialog.load(url, function () {
        dialog.modal({
            backdrop: false
        });
        //显示模态框背景遮罩，遮罩位于index.html页面
        var mainBody = $(window.top.document.body);//找到父页面的body对象
        mainBody.find("#mainbackdrop").show();
    });
    dialog.on("show.bs.modal", function () {
        $(this).css("overflow", "hidden");
    });
    dialog.on("hide.bs.modal",
        function () {
            var mainBody = $(window.top.document.body);
            mainBody.find("#mainModal").removeData('bs.modal');
            mainBody.find("#mainbackdrop").hide();//隐藏模态框遮罩
        });
}
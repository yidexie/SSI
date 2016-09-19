//src : 弹出页面url
//width: 宽度
//height: 高度
//showfun: 打开页面时调用
//closefun: 关闭页面时的回调
//文件修改为utf-8编码避免在IE6下引入文件异常@2013-5-20
function OpenMyModal(src, width, height, showfun, closefun) {
    var frame = '<iframe width="' + width + '" height="' + height + '"src="' + src + '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>';
    var option = {
        escClose: true,
        close: true,
        minHeight: height,
        minWidth: width,
        autoResize: true
    };
    if (showfun != null) {
        option.onShow = showfun();
    }
    if (closefun != null) {
        option.onClose = function () { $.modal.close(); closefun(); }
    }

    $.modal(frame, option);
}

function CloseMyModal(){
	 $.modal.close(); 
}
function backLoading() {
    //显示提示
    function showTip(info) {
        $('#tipInfo').html(info);
        $('#tipDiv').show();
    }

    //初始加载提示
    showTip('内容正在加载...');

}
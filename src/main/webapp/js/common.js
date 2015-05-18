function getPath() {
    console.log(window.location);
    return window.location.host + "/" + window.location.pathname.split("/")[1] + "/";
}
function debug(data) {
    var $debug = $("#debug");
    if ($debug) {
        $debug.html(data);
    }
}
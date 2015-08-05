function getPath() {
    console.log(window.location);
    console.log(window.location.host);
    return window.location.host + "/";
}
function debug(data) {
    var $debug = $("#debug");
    if ($debug) {
        $debug.html(data);
    }
}

// google 分析
(function (i, s, o, g, r, a, m) {
    i['GoogleAnalyticsObject'] = r;
    i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
    a = s.createElement(o),
        m = s.getElementsByTagName(o)[0];
    a.async = 1;
    a.src = g;
    m.parentNode.insertBefore(a, m)
})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

ga('create', 'UA-63535140-1', 'auto');
ga('send', 'pageview');
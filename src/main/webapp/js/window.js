// Full list of configuration options available at:
// https://github.com/hakimel/reveal.js#configuration
Reveal.initialize({
    controls: true,
    progress: true,
    history: true,
    center: true,

    transition: 'slide', // none/fade/slide/convex/concave/zoom

    // Optional reveal.js plugins
    dependencies: [
        {
            src: 'reveal/lib/js/classList.js', condition: function () {
            return !document.body.classList;
        }
        },
        {
            src: 'reveal/plugin/markdown/marked.js', condition: function () {
            return !!document.querySelector('[data-markdown]');
        }
        },
        {
            src: 'reveal/plugin/markdown/markdown.js', condition: function () {
            return !!document.querySelector('[data-markdown]');
        }
        },
        {
            src: 'reveal/plugin/highlight/highlight.js', async: true, condition: function () {
            return !!document.querySelector('pre code');
        }, callback: function () {
            hljs.initHighlightingOnLoad();
        }
        },
        {src: 'reveal/plugin/zoom-js/zoom.js', async: true},
        {src: 'reveal/plugin/notes/notes.js', async: true}
    ]
});

Reveal.addEventListener('slidechanged', function (event) {
    if (newMsg) {
        newMsg = false;
    } else {
        var bean = new RemoteControl();
        bean.action = RemoteControl.INDEX;
        bean.index = [event.indexh, event.indexv];
        var json = JSON.stringify(bean);
        ws.send(json);
        console.log("send " + json);
    }
});

Reveal.addEventListener('fragmentshown', function (event) {
    if (newMsg) {
        newMsg = false;
    } else {
        var bean = new RemoteControl();
        bean.action = RemoteControl.FRAGMENTSHOWN;
        var json = JSON.stringify(bean);
        ws.send(json);
        console.log("send " + json);
    }
});
Reveal.addEventListener('fragmenthidden', function (event) {
    if (newMsg) {
        newMsg = false;
    } else {
        var bean = new RemoteControl();
        bean.action = RemoteControl.FRAGMENTHIDDEN;
        var json = JSON.stringify(bean);
        ws.send(json);
        console.log("send " + json);
    }
});

var ws;
var newMsg = false;
if (window.WebSocket) {
    ws = new WebSocket("ws://" + getPath() + "window.ws");
    ws.onmessage = function (obj) {
        console.log(obj);
        onMessage(JSON.parse(obj.data));
    };
    debug("Connection successful");
} else {
    debug("Connection failed");
}
/* Doc : https://github.com/hakimel/reveal.js  */
function onMessage(data) {
    newMsg = true;
    if (data.action == RemoteControl.UP) {
        Reveal.up();
    } else if (data.action == RemoteControl.DOWN) {
        Reveal.down();
    } else if (data.action == RemoteControl.LEFT) {
        Reveal.left();
    } else if (data.action == RemoteControl.RIGHT) {
        Reveal.right();
    } else if (data.action == RemoteControl.INDEX) {
        console.log(data);
        var dH = data.index[0];
        var dV = data.index[1];
        Reveal.slide(dH, dV);
    } else if (data.action == RemoteControl.FRAGMENTSHOWN) {
        Reveal.nextFragment();
    } else if (data.action == RemoteControl.FRAGMENTHIDDEN) {
        Reveal.prevFragment();
    } else if (data.action == RemoteControl.ID) {
        sessionId = data.id;
    }
}
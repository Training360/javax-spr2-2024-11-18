window.onload = function () {
    const socket = new SockJS("/websocket-endpoint")
    const client = Stomp.over(socket)

    document.querySelector("#message-button").onclick = function () {
        const text = document.querySelector("#message-input").value
        const json = JSON.stringify({"requestText": text})
        client.send("/app/messages", {}, json)
    }
}
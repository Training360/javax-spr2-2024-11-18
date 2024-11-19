window.onload = function () {
    console.log("start")
    const source = new EventSource("/api/employees/messages")
    source.addEventListener("message", function (event) {
        console.log("event");
        const data = event.data
        const json = JSON.parse(data)
        const text = json.text

        document.querySelector("#messages-div").innerHTML += `<p>${text}</p>`
    })

}
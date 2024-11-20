window.onload = function () {

    fetch("http://localhost:8080/api/employees")
        .then(response => response.json())
        .then(employees => {
            const ul = document.querySelector("#employees-ul")
            for (const employee of employees) {
                ul.innerHTML += `<li>${employee.name}</li>`
            }
        })

}
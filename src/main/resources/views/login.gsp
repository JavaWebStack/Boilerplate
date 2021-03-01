<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/style.css">
</head>
<body>
<div id="login">
    <h3>Register example</h3>
    <input id="username" type="text" placeholder="Username">
    <input id="password" type="text" placeholder="Password">
    <button class="button" id="submit-button">REGISTER</button>
    <script>
        document.getElementById("submit-button").addEventListener("click", function () {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            fetch("/auth/login", {
                method: "POST",
                body: JSON.stringify({
                    username: username,
                    password: password
                }),
                headers: {
                    "content-type": "application/json"
                }
            }).then(res => res.json())
                .then(res => {
                    if (res.success) {
                        alert("done!")
                    } else
                        alert("Please check if everything is correct")
                })
        })
    </script>
</div>
</body>
</html>
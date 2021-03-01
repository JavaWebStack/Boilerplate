<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/style.css">
</head>
<body>
<div id="login">
    <h3>Register example</h3>
    <input id="username" type="text" placeholder="Username">
    <input id="email" type="text" placeholder="EMail">
    <input id="password" type="text" placeholder="Password">
    <button class="button" id="submit-button">REGISTER</button>
    <script>
        document.getElementById("submit-button").addEventListener("click", function () {
            const username = document.getElementById("username").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            fetch("/auth/register", {
                method: "POST",
                body: JSON.stringify({
                    username: username,
                    email: email,
                    password: password
                }),
                headers: {
                    "content-type": "application/json"
                }
            }).then(res => res.json())
              .then(res => {
                  if (res.success) {
                      alert("done!")
                      // localStorage[""] = res.auth_key
                  } else
                      alert("Please give valid information")
              })

        })
    </script>
</div>
</body>
</html>
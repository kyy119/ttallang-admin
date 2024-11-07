const loginForm = document.querySelector("#loginForm");
const userName = document.querySelector("#username");
const userPassword = document.querySelector("#password");

loginForm.addEventListener("submit", handleLoginForm);

function handleLoginForm(event) {
    event.preventDefault();

    // Spring Security 는 application/x-www-form-urlencoded 타입의 값을 기대하고 있기 때문에 JSON 대신 아래 타입으로 보내야 함.
    const formData = new URLSearchParams();
    formData.append("username", userName.value);
    formData.append("password", userPassword.value);

    // 비공디 처리.
    fetch("/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded" // application/json 으로 보내면 안됨.
        },
        body: formData
    }).then(response => {
        if (!response.ok) {
            throw new Error("로그인 실패.");
        }
        return response.json();
    })
    .then(data => { // 이 data는 response.json()의 실제값.
        console.log(data)
        if (data.role === "admin") {
            window.location.href = "/admin/branch/main";
        } else if (data.role === "user") {
            // window.location.href = "/user/main";
            window.location.href = "/map/main"; // -> /user/** 형식으로 바꿔야 권한 적용 가능함.
        } else {
            throw new Error("로그인 실패.");
        }
    })
    .catch(error => {
        alert(error);
    });
}
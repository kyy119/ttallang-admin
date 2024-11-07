const userName = document.querySelector("#userName");
const userPassword = document.querySelector("#userPassword");

const form = document.querySelector("#signupForm");

// 폼 처리 관련.
form.addEventListener("submit", handleSignupAdminForm);

function handleSignupAdminForm (event) {
    event.preventDefault();

        const formData = new FormData(form);
        const data = Object.fromEntries(formData);

        fetch("/api/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data)
        }).then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("회원가입 실패.");
            }
        })
            .then(data => {
                if (data.status === "success") {
                    console.log(data.status);
                    alert("회원가입 성공.")
                    window.location.href = "/login/form";
                } else {
                    console.log(data.status)
                    console.log(data.message);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });

}


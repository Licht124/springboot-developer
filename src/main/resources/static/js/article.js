const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;

            function success() {
                alert("삭제 완료");
                location.replace("/articles");
            }

            function fail() {
                alert("삭제 실패");
                location.replace("/articles");
            }

            httpRequest("DELETE", "/api/articles/" + id, null, success, fail);


    });
}

const modifyButton = document.getElementById('modify-btn');

if(modifyButton) {
    modifyButton.addEventListener('click',event => {
    let params = new URLSearchParams(location.search);
    let id = params.get('id');

    body = JSON.stringify({
            title : document.getElementById('title').value,
            content : document.getElementById('content').value
        })

    function success() {
        alert("수정 완료");
        location.replace("/articles");
    }

    function fail() {
        alert("수정 실패");
        location.replace("/articles");
    }

    httpRequest("PUT", "/api/articles/" + id, body, success, fail);

 });
}

const createButton = document.getElementById('create-btn');

// 기존 jwt 방식 코드
//if(createButton) {
//    createButton.addEventListener('click',event => {
//    fetch("/api/articles", {
//        method : 'POST',
//        headers : {
//            "Content-Type" : "application/json",
//        },
//        // 자바데이터 -> JSON 이므로 직렬화
//        body : JSON.stringify({
//            title : document.getElementById('title').value,
//            content : document.getElementById('content').value,
//        }),
//    })
//    .then(() => {
//       alert('등록 완료');
//       location.replace("/articles");
//    });
//  });
//}

if(createButton) {
    createButton.addEventListener("click", (event) => {
    body = JSON.stringify({
        title : document.getElementById("title").value,
        content : document.getElementById("content").value,
    });

    function success() {
        alert("등록 완료");
        location.replace("/articles");
    }

    function fail() {
        alert("등록 실패");
        location.replace("/articles");
    }

    httpRequest("POST", "/api/articles", body, success, fail);
    });
}


    const commentCreateButton = document.getElementById('comment-create-btn');

    if(commentCreateButton) {
        commentCreateButton.addEventListener('click',event => {
            articleId = document.getElementById('article-id').value;

            body = JSON.stringify({
                articleId : articleId,
                content: document.getElementById('content').value
            });

                function success() {
                    alert("등록 완료");
                    location.replace("/articles/" + articleId);
                }

                function fail() {
                    alert("등록 실패");
                    location.replace("/articles/" + articleId);
                }

            httpRequest('POST', '/api/comments', body, success, fail)
        })
    }

function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(";");
    cookie.some(function(item) {
        item = item.replace(" ", "");

        var dic = item.split("=");

        if(key === dic[0]) {
        result = dic[1];
        return true;
        }
    });
    return result;
}

// http 요청 전달 함수
// 요청 시도 시 액세스 토큰 같이 전달. 에러발생 시
// 리프레시 토큰을 전달하면서 액세스 토큰을 요청, 전달받은 액세스 토큰으로 다시 api 요청
function httpRequest(method, url , body, success , fail) {
    fetch(url, {
    method : method,
    headers: {
        Authorization : "Bearer " + localStorage.getItem("access_token"),
        "Content-Type" : "application/json",
        },
        body : body,
    }).then((response) => {
        if(response.status === 200 || response.status === 201) {
            return success();
        }
        const refresh_token = getCookie("refresh_token");
        if(response.status === 401 && refresh_token) {
            fetch("/api/token", {
                method : "POST",
                headers : {
                    Authorization : "Bearer " + localStorage.getItem("access_token"),
                    "Content-Type" : "application/json",
                },
                body: JSON.stringify({
                refreshToken : getCookie("refresh_token"),
                }),

            })
            .then((res) => {
                if(res.ok) {
                return res.json();
                }
            })
            .then((result) => {
                //재발급 성공 시 로컬스토리지 새로운 액세스 토큰으로 교체
                localStorage.setItem("access_token", result.accessToken);
                httpRequest(method, url, body, success, fail);
            })
            .catch((error) => fail());
        } else {
            return fail();
        }
    })

}

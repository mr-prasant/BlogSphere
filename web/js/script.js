
function getCookie(name) {
    let cookies = document.cookie;
    let cookieval = null;

    cookies.split(";").forEach((cookie) => {
        cookie = cookie.trim();

        if (cookie.startsWith(name + "=")) {
            cookieval = cookie.substring((name + "=").length);
        }

    });

    return cookieval;
}

$(document).ready(() => {
    let userid = getCookie("blogsphere-user");

    $(".likeButton").click(function (event) {
        event.stopPropagation();
        console.log("working");

        let button = $(this);
        var blogid = button.data('blogid');

        console.log("Like button clicked!");
        console.log("bid: " + blogid);
        console.log("uid: " + userid);

        if (!userid) {
            button.children(".fa-heart").removeClass("fa-solid");
            button.children(".fa-heart").addClass("fa-regular");
            window.location.href = "signin.jsp";
            document.cookie = "blogsphere-page=post.jsp?bid=" + blogid + "; path=/";

            return null;
        }

        $.ajax({
            url: 'LikeServlet',
            type: 'POST',
            data: {
                blogID: blogid,
                userID: userid

            },
            success: (response) => {
                let classname = response.classname;
//                console.log(classname);
                button.children('.likecount').text(response.likecount);
//                console.log(response.likecount);

                if (classname === 'fa-regular') {
                    button.children(".fa-heart").removeClass("fa-solid");
                    button.children(".fa-heart").addClass("fa-regular");
                } else {
                    button.children(".fa-heart").removeClass("fa-regular");
                    button.children(".fa-heart").addClass("fa-solid");
                }

            },
            error: (error) => {
                button.children(".fa-heart").removeClass("fa-solid");
                button.children(".fa-heart").addClass("fa-regular");
            }
        });

    });
});

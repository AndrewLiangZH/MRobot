/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/26
 * Description:
 */

"use strict";

$(function () {
    $(".menu-list .menu-list-item").click(function () {
        var url = $(this).attr("url");
        $(".menu-list .menu-list-item").removeClass("active");
        $(this).addClass("active");
        $.ajax({
            url: url,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error: function() {
                alert("error");
            }
        });
    });

    $(".menu-list .menu-list-item:first").trigger("click");
})
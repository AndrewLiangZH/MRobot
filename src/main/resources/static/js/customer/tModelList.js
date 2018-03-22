/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/28
 * Description:
 */

"use strict"

$(function () {

    var _pageSize; // Store the page size for searching

    /**
     * List the tmodels by the name, page index and page size
     * @param pageIndex
     * @param pageSize
     */
    function getTModelsByName(pageIndex, pageSize) {
        $.ajax({
            url: "/tmodel",
            contentType: 'application/json',
            data: {
                "async": true,
                "pageIndex": pageIndex,
                "pageSize": pageSize,
                "name": $("#searchName").val()
            },
            success: function (data) {
                $("#rightMainContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    }

    /**
     * Paging
     */
 /*   $.tbpage("#rightMainContainer", function (pageIndex, pageSize) {
        getTModelsByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });*/

    /**
     * Searching
     */
    $("#searchNameBtn").click(function () {
        getTModelsByName(0, _pageSize);
    });

    /**
     * Get the page of creating the tmodel
     */
    $("#addTModel").click(function () {
        $.ajax({
            url: "/tmodel/add",
            success: function (data) {
                $("#tModelFormContainer").html(data);
            },
            error: function (data) {
                toastr.error("error!");
            }
        });
    });

    /**
     * Get the page of editing the tmodel
     */
    $("#rightContainer").on("click", ".editTmodel", function () {
        $.ajax({
            url: "/tmodel/edit/" + $(this).attr("tModelId"),
            success: function (data) {
                $("#tModelFormContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Submit the editing form and clear it
     */
    $("#submitEdit").click(function () {
        $.ajax({
            url: "/tmodel",
            type: 'POST',
            data: $('#tModelForm').serialize(),
            success: function (data) {
                $('#tModelForm')[0].reset();

                if (data.success) {
                    getTModelsByName(0, _pageSize);
                    toastr.success("Edit Success");
                } else {
                    toastr.error(data.message);
                }

            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Delete the tmodel
     */
    $("#rightContainer").on("click", ".deleteTmodel", function () {

        $.ajax({
            url: "/tmodel/" + $(this).attr("tModelId"),
            type: 'DELETE',
            success: function (data) {
                if (data.success) {
                    getTModelsByName(0, _pageSize);
                    toastr.success("Delete Success");
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Push the upload operate to the client
     */
    $("#rightContainer").on("click", ".tmodel-upload-client", function () {

        $.ajax({
            url: "/tmodel/client/upload/"+ $(this).attr("tName"),
            type: 'GET',
            success: function (data) {
                if (data.success) {
                    toastr.success("Client Upload Success!");
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Push the update operate to the client
     */
    $("#rightContainer").on("click", ".tmodel-update-client", function () {
        $.ajax({
            url: "/tmodel/client/update/"+$(this).attr("tName"),
            type: 'GET',
            success: function () {
                toastr.success("Client Apply Success!");
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Push the delete operate to the client
     */
    $("#rightContainer").on("click", ".tmodel-delete-client", function () {

        $.ajax({
            url: "/tmodel/client/delete/"+$(this).attr("tName"),
            type: 'GET',
            success: function (data) {
                if (data.success) {
                    toastr.success("Client Delete Success!")
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * Push the execute operate to the client
     */
    $("#rightContainer").on("click", ".tmodel-execute-client", function () {

        $.ajax({
            url: "/tmodel/client/execute",
            type: 'GET',
            success: function (data) {
                if (data.success) {
                    toastr.success("Client Execute!")
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });
});
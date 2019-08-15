var http = require("http");
var fs = require("fs");

http.createServer(function (request, response) {
    console.log(request.url);
    var filePath = request.url.substr(1);
    if(filePath === "")
        filePath = "indexx.html";
    console.log(filePath);
    fs.readFile(filePath, function (error, data) {
        response.end(data);
    });
    return;
}).listen(3000, function () {
    console.log("hello");
});
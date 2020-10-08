var stompClient=null;
function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame) {
        stompClient.subscribe('/user/queue/chat',function (chat) {
            showGreeting(JSON.stringify(chat.body)); // 会转成json字符串""
            //showGreeting(JSON.parse(chat.body));
        });
    });
}
function sendMSG() {
    stompClient.send("/app/chat",{},JSON.stringify({'content':$("#content").val(),
    'to':$("#to").val()}));
}
function showGreeting(message) {
    //json字符串转JSON对象  { dsadsd}
    var obj = eval('(' + message + ')');
    var obj1 = eval('(' + obj + ')');
    alert(obj1.from)
    $("#chatsContent")
        .append("<div>"+obj1.from+":"+obj1.content+"</div>")
}
$(document).ready(function () {
    connect();
    $("#send").click(function () {
        sendMSG();
    })
})
import * as StompJs from "@stomp/stompjs";

(function() {

    document.addEventListener("DOMContentLoaded", init);


    const userName = document.getElementById('username');
    const userMessage = document.getElementById('user-message-content');
    const responseBody = document.getElementById('responseBody');
    const chatBody = document.getElementById('chat');

    function init() {
        connect();
        chatBody.scrollTop = chatBody.scrollHeight;
        document.getElementById("send-message").addEventListener("click",sendMessage);
    }
    const stompClient = new StompJs.Client({
        brokerURL: '/chat'
    });

    stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', (greeting) => {
            showMessageOutput(JSON.parse(greeting.body));
        });
    };

    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };

    function connect() {
        stompClient.activate();
        console.log('Connected');
    }

    function disconnect() {
        stompClient.deactivate();
        console.log('Disconnected');
    }

    function sendMessage() {

        stompClient.publish({destination: '/app/chat', body: JSON.stringify(
                {'from': userName.value.trim(), 'text': userMessage.value.trim(), 'time': new Date().toLocaleTimeString()}
            )});
        userMessage.value = '';
    }

    function showMessageOutput(messageOutput) {

        let messageClass = messageOutput.from === userName ? 'incoming-message' : 'outgoing-message' ;

        responseBody.innerHTML +=
            `<div class="chat-message ${messageClass}">
                    <div><strong>${messageOutput.from}</strong> <span class="text-muted" style="font-size: smaller;">${messageOutput.time}</span></div>
                    <div>${messageOutput.text}</div>
                </div>`;
        chatBody.scrollTop = chatBody.scrollHeight;

    }


})();


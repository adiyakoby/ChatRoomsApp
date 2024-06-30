import * as StompJs from "@stomp/stompjs";

(function() {
    document.addEventListener("DOMContentLoaded", init);

    const userName = document.getElementById('username');
    const userMessage = document.getElementById('user-message-content');
    const responseBody = document.getElementById('responseBody');
    const chatBody = document.getElementById('chat');
    const chatId = document.getElementById('chat-room-id');

    function init() {
        connect();
        chatBody.scrollTop = chatBody.scrollHeight;
        document.getElementById("send-message").addEventListener("click", sendMessage);
    }

    let userColorMap = {}; // Object to store user colors dynamically

    // Array of predefined colors for users
    const USER_COLORS = [
        "#007bff", "#28a745", "#dc3545", "#ffc107", "#17a2b8", "#6610f2",
        "#e83e8c", "#20c997", "#6f42c1", "#fd7e14", "#343a40", "#007bff",
        "#28a745", "#dc3545", "#ffc107", "#17a2b8", "#6610f2", "#e83e8c",
        "#20c997", "#6f42c1", "#fd7e14", "#343a40", "#007bff", "#28a745",
        "#dc3545", "#ffc107", "#17a2b8", "#6610f2", "#e83e8c", "#20c997",
        "#6f42c1", "#fd7e14", "#343a40", "#007bff", "#28a745", "#dc3545",
        "#ffc107", "#17a2b8", "#6610f2", "#e83e8c", "#20c997", "#6f42c1",
        "#fd7e14", "#343a40", "#007bff", "#28a745", "#dc3545", "#ffc107",
        "#17a2b8", "#6610f2", "#e83e8c", "#20c997", "#6f42c1", "#fd7e14"
    ];

    const stompClient = new StompJs.Client({
        brokerURL: '/chat'
    });

    stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        const chatRoomId = chatId.value.trim(); // Assuming you have a hidden field with the current chat room ID
        stompClient.subscribe(`/topic/chatroom/${chatRoomId}`, (greeting) => {
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
        const content = userMessage.value.trim();
        if (content.length > 0) {
            stompClient.publish({destination: '/app/chat', body: JSON.stringify(
                    {'from': userName.value.trim(), 'text': content, chatId : chatId.value.trim()}
                )});
        }

        userMessage.value = '';
    }

    function showMessageOutput(messageOutput) {
        let messageClass = messageOutput.from === userName.value.trim() ? 'outgoing-message' : 'incoming-message';
        let alignClass = messageOutput.from === userName.value.trim() ? 'ms-auto' : 'me-auto';

        // Assign color based on whether the user is regular or not
        let userColor = userColorMap[messageOutput.from];
        if (!userColor) {
            // Generate a random color for non-regular users
            userColor = getRandomColor(messageOutput.from);
            userColorMap[messageOutput.from] = userColor;
        }

        responseBody.innerHTML +=
            `<div class="d-flex">
                <div class="chat-message ${messageClass} ${alignClass}" style="background-color: ${userColor}; color: #fff;">
                    <div><strong>${messageOutput.from}</strong> <span class="text-muted" style="font-size: smaller;">${messageOutput.time}</span></div>
                    <div>${messageOutput.text}</div>
                </div>
             </div>`;
        chatBody.scrollTop = chatBody.scrollHeight;
    }

    function getRandomColor(str) {
        let hash = 0;
        for (let i = 0; i < str.length; i++) {
            hash = str.charCodeAt(i) + ((hash << 5) - hash);
        }
        const index = Math.abs(hash) % USER_COLORS.length;
        return USER_COLORS[index];
    }
})();

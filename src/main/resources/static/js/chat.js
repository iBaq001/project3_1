let stompClient = null;

$(document).ready( () => {
    console.log("Index page is ready")
    connect()
})

const connect = () => {
    const socket = new SockJS('/ws')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, onConnected, onError)
}

const onConnected = () => {
    console.log('connected')

    stompClient.subscribe('/user/user/private-message')
}

const onError = () => {
    alert("Error")
}
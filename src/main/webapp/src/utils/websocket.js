const wsuri = `ws://localhost:8080/message`
// const wsuri = `ws://${window.location.host}/message`

let socket = null

function startWebSocket() {
  socket = new WebSocket(wsuri)
  //监听消息接收事件
  socket.addEventListener('message', onMessage)
  //监听WebSocket连接事件
  socket.addEventListener('open', onOpen)
  //监听WebSocket异常事件
  socket.addEventListener('error', onError)
  //监听WebSockt关闭事件
  socket.addEventListener('close', onClose)
}

function sendHeartBeat() {
  setTimeout(() => {
    console.log(socket.readyState)
    socket.send('ping')
  }, 5000)
}

function onOpen() {
  socket.send('Hello from client')
  sendHeartBeat()
}

function onError(e) {
  console.error(e)
  startWebSocket()
}

function onMessage(e) {
  if (!e) return
  if (e.data === 'pong') {
    sendHeartBeat()
    return
  }
}

function onClose(e) {
  console.log('WebSocket disconnected.', e)
}

function getReadyState() {
  return socket.readyState
}

startWebSocket()

export default {
  getReadyState,
}

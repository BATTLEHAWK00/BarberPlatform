const wsuri = `ws://localhost:8080/message`
// const wsuri = `ws://${window.location.host}/message`

let socket = null

function startWebSocket() {
  socket = new WebSocket(wsuri)
  socket.addEventListener('message', onMessage)
  socket.addEventListener('open', onOpen)
  socket.addEventListener('error', onError)
  socket.addEventListener('close', onClose)
}

function sendHeartBeat() {
  setTimeout(() => {
    console.log(socket.readyState)
    socket.send('ping')
  }, 1000)
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

startWebSocket()

export default {
  onMessage,
  onOpen,
  onError,
  onClose,
}

import childProcess, { ChildProcessWithoutNullStreams } from 'child_process';
import path from 'path';
import { findFreePort } from '../util/port';
import { setTimeout } from 'timers/promises';
import WebSocketServer, { CloseEvent } from 'ws';
import jvm from '../util/jvm';
import { protocol } from 'electron';

const jarPath = path.resolve(__dirname, '../../../electron/backend');
const jarName = 'backend.jar';
const jvmArgs: string[] = [
  '-XX:+UseSerialGC',
  '-Xms48M',
  '-Xmx64M',
  '-XX:NewRatio=1',
  // "-XX:+PrintGC"
];
const isDev = process.env.NODE_ENV === 'development';

function createWSConnection(
  url: string,
  onOpen: (event: WebSocketServer.Event) => void,
  onMessage: (event: WebSocketServer.MessageEvent) => void,
  onError: (event: WebSocketServer.ErrorEvent) => void,
  onClose: (event: CloseEvent) => void
) {
  const conn = new WebSocketServer(url);
  conn.onopen = onOpen;
  conn.onmessage = onMessage;
  conn.onerror = async (e) => {
    onError(e);
    await setTimeout(500);
    createWSConnection(url, onOpen, onMessage, onError, onClose);
  };
  conn.onclose = onClose;
}

class BarberShopBackend {
  private backend?: ChildProcessWithoutNullStreams;
  private port?: number;

  async start() {
    this.port = isDev ? 8080 : await findFreePort();
    const appArgs: string[] = [`--server.port=${this.port}`];
    const spawnArgs = jvm.buildArgs(jarName, jvmArgs, appArgs);

    if (!isDev) {
      this.backend = childProcess.spawn('java.exe', spawnArgs, { cwd: jarPath });
      this.backend.stdout.on('data', (data) => {
        // console.log(data.toString())
      });
      this.backend.stderr.on('data', (data) => {
        console.error(data.toString());
      });
      this.backend.once('exit', () => {
        throw new Error('后端进程异常退出，请重启系统');
      });
    }
    return new Promise<void>((resolve, reject) => {
      createWSConnection(
        `ws://localhost:${this.port}/ws`,
        () => {
          protocol.registerHttpProtocol('backend', ({ url, headers, referrer }, callback) => {
            const uri = url.replace('backend://', '');
            const newUrl = `http://localhost:${this.port}/${uri}`;
            callback({ headers, referrer, url: newUrl });
          });
          resolve();
        },
        (e) => {
          console.log(e);
        },
        (e) => {},
        () => {}
      );
    });
  }

  stop() {
    this.backend?.kill();
  }
}

export default new BarberShopBackend();

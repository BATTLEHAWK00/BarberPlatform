import childProcess, {ChildProcessWithoutNullStreams} from 'child_process';
import path from "path";
import {findFreePort} from "../util/port";
import {setTimeout} from 'timers/promises';
import WebSocketServer from "ws";

const jarPath = path.resolve(__dirname, "../../electron/backend/");

function createWSConnection(
    url: string,
    onOpen: (event: WebSocketServer.Event) => void,
    onMessage: (event: WebSocketServer.MessageEvent) => void,
    onError: (event: WebSocketServer.ErrorEvent) => void,
    onClose: (this: Window, ev: Event) => void
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
        this.port = await findFreePort();

        return new Promise<void>((resolve, reject) => {
            createWSConnection(
                `ws://localhost:${this.port}/ws`,
                () => resolve(),
                (e) => {
                    console.log(e);
                },
                (e) => {
                },
                () => {
                });
            this.backend = childProcess.spawn("D:/Runtimes/graalvm-ce-java17-22.2.0/bin/java.exe", [
                    "-Xms32M",
                    "-Xmx256M",
                    "-jar",
                    "backend.jar",
                    `--server.port=${this.port}`
                ],
                {
                    cwd: jarPath
                });
            this.backend.stdout.on('data', (data) => {
                // console.log(data.toString())
            });
        })
    }

    stop() {
        this.backend?.kill();
    }
}

export default new BarberShopBackend();
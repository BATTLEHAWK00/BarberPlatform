import childProcess, {ChildProcessWithoutNullStreams} from 'child_process';
import path from "path";
import {findFreePort} from "../util/port";
import {setTimeout} from 'timers/promises';
import WebSocketServer, {CloseEvent} from "ws";

const jarPath = path.resolve(__dirname, "../../electron/backend/");
const jvmArgs: string[] = [
    "-XX:+UseSerialGC",
    "-Xms48M",
    "-Xmx64M",
    "-XX:NewRatio=1",
    // "-XX:+PrintGC"
];

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
        this.port = await findFreePort();
        const appArgs: string[] = [
            `--server.port=${this.port}`
        ];

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
            this.backend = childProcess.spawn("java.exe", [
                    ...jvmArgs,
                    "-jar",
                    "backend.jar",
                    ...appArgs
                ],
                {cwd: jarPath});
            this.backend.stdout.on('data', (data) => {
                // console.log(data.toString())
            });
            this.backend.stderr.on('data', (data) => {
                console.error(data.toString())
            });
            this.backend.once('exit', () => {
                throw new Error("后端进程异常退出，请重启系统");
            })
        })
    }

    stop() {
        this.backend?.kill();
    }
}

export default new BarberShopBackend();
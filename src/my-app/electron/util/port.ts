import net from "net";

export async function findFreePort() {
    return new Promise<number>((resolve, reject) => {
        const server = net.createServer();
        server.listen(0, () => {
            const port = (server.address() as net.AddressInfo).port;
            server.close((e) => reject(e));
            resolve(port);
        }).on('error', (e) => reject(e));
    });
}


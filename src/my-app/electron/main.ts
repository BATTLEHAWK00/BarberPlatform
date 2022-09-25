import {app, BrowserWindow, dialog} from 'electron'
import backend from "./lib/backend";
import connectionWindow from "./window/connectionWindow";
import loginWindow from "./window/loginWindow";

function registerEvents() {
    process.on('uncaughtException', (e) => {
        console.log(e);
    })
    app.on('window-all-closed', () => {
        if (process.platform !== 'darwin') app.quit()
    }).on('before-quit', () => {
        backend.stop();
    }).on('activate', async () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            await loginWindow.create();
        }
    }).on('second-instance', () => {
        dialog.showMessageBoxSync({
            title: "理发店管理系统",
            message: "请勿重复运行本系统"
        });
        app.quit();
    });
}

(async () => {
    const startTime = Date.now();
    const backendPromise = backend.start()
    registerEvents();
    app.requestSingleInstanceLock();
    await app.whenReady();
    const timeout = setTimeout(() => connectionWindow.create(), Math.random() * 2000 + 1000);
    await backendPromise;
    clearTimeout(timeout);
    connectionWindow.close();
    await loginWindow.create();
    const endTime = Date.now();
    console.log(`Start time: ${endTime - startTime}ms`);
})()



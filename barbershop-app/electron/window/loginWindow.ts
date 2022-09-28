import {BrowserWindow} from "electron";
import {BaseWindow} from "./baseWindow";

class LoginWindow extends BaseWindow {
    async create() {
        this.window = new BrowserWindow({
            show: false,
            width: 800,
            height: 600,
            resizable: false,
            center: true,
            title: "登录 - 理发店管理系统",
            titleBarOverlay: false,
            webPreferences: {
                webSecurity: false,
                nodeIntegration: true
            },
        })
        this.window.setMenuBarVisibility(false);
        this.window.once('ready-to-show', () => this.window?.show());
        await this.window.loadURL("https://baidu.com");
    }
}

export default new LoginWindow();

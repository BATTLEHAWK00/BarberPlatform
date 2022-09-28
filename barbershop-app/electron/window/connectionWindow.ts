import {BrowserWindow} from "electron";
import {BaseWindow} from "./baseWindow";

class ConnectionWindow extends BaseWindow {
    private titleAnimation() {
        if (!this.window) throw new Error("Window not created.");
        let repeat = 0;
        const interval = setInterval(() => {
            if (!this.window) throw new Error("Window not created.");
            this.window.title = `启动中${'.'.repeat(repeat)}（理发店管理系统）`;
            repeat = (repeat + 1) % 10;
        }, 200);
        this.window.once('close', () => clearInterval(interval));
    }

    async create() {
        this.window = new BrowserWindow({
            width: 400,
            height: 300,
            frame: false,
            // transparent: true,
            webPreferences: {
                devTools: false,
                webSecurity: false,
                nodeIntegration: true
            }
        })
        // this.window.setClosable(false);
        this.window.setProgressBar(50, {
            mode: 'indeterminate'
        })
        this.window.setResizable(false);
        this.window.setMenuBarVisibility(false);
        this.titleAnimation();
    }
}

export default new ConnectionWindow();
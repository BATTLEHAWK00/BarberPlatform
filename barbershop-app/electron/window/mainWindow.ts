import {BrowserWindow} from "electron";
import path from "path";

const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
        webSecurity: false,
        nodeIntegration: true
    }
})
mainWindow.setMenuBarVisibility(false);
mainWindow.title = "理发店管理系统";

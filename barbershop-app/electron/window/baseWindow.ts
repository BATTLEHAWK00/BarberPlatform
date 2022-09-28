import {BrowserWindow} from "electron";

export abstract class BaseWindow {
    protected window?: BrowserWindow;

    abstract create(): Promise<void>;

    close() {
        this.window?.close();
        this.window = undefined;
    }
}

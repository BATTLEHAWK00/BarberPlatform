import { BrowserWindow, BrowserWindowConstructorOptions } from 'electron';

export abstract class BaseWindow {
  protected window: BrowserWindow;

  close() {
    this.window.close();
    this.window = undefined as unknown as BrowserWindow;
  }

  constructor(options: BrowserWindowConstructorOptions) {
    this.window = new BrowserWindow({
      show: false,
      autoHideMenuBar: true,
      ...options,
    });
  }

  show() {
    this.window.show();
  }
}

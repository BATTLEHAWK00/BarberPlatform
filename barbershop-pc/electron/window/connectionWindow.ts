import { BaseWindow } from './baseWindow';

export class ConnectionWindow extends BaseWindow {
  constructor() {
    super({
      width: 400,
      height: 300,
      frame: false,
      resizable: false,
      // transparent: true,
      webPreferences: {
        devTools: false,
        webSecurity: false,
        nodeIntegration: true,
      },
    });
    this.window.setProgressBar(50, {
      mode: 'indeterminate',
    });
    this.window.setMenuBarVisibility(false);
  }

  private titleAnimation() {
    if (!this.window) throw new Error('Window not created.');
    let repeat = 0;
    const interval = setInterval(() => {
      if (!this.window) throw new Error('Window not created.');
      this.window.title = `启动中${'.'.repeat(repeat)}（理发店管理系统）`;
      repeat = (repeat + 1) % 10;
    }, 200);
    this.window.once('close', () => clearInterval(interval));
  }

  show() {
    super.show();
    this.titleAnimation();
  }
}

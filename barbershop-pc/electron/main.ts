import { app, Notification, dialog } from 'electron';
import backend from './lib/backend';
import { ConnectionWindow } from './window/connectionWindow';
import protocol from './protocol';
import MainWindow from './window/mainWindow';

function whenReady() {
  protocol.registerProtocol();
  //   protocol.registerInterceptors();
}

function registerEvents() {
  process.on('uncaughtException', (e) => {
    console.error(e);
    dialog.showErrorBox('错误', e.message);
    app.exit(1);
  });
  app
    .on('window-all-closed', () => {
      if (process.platform !== 'darwin') app.quit();
    })
    .on('before-quit', () => {
      backend.stop();
    })
    .on('activate', async () => {})
    .on('second-instance', () => {
      dialog.showMessageBoxSync({
        title: '理发店管理系统',
        message: '请勿重复运行本系统',
      });
      app.quit();
    });
}

(async () => {
  registerEvents();
  app.requestSingleInstanceLock();
  const startTime = Date.now();
  const backendPromise = backend.start();
  await app.whenReady();
  whenReady();
  const connectionWindow = new ConnectionWindow();
  const mainWindow = new MainWindow();
  const timeout = setTimeout(() => connectionWindow.show(), Math.random() * 2000 + 1000);
  await backendPromise;
  connectionWindow.close();
  clearTimeout(timeout);
  await mainWindow.show();
  const endTime = Date.now();
  console.log(`Start time: ${endTime - startTime}ms`);
})();

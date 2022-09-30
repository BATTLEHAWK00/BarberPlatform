import { protocol } from 'electron';
import path from 'path';

function registerInterceptors() {
  protocol.interceptHttpProtocol('http', (request, callback) => {
    console.log(request);
  });
}

function registerProtocol() {}

export default {
  registerInterceptors,
  registerProtocol,
};

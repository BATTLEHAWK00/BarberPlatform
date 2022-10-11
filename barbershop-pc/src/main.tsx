import { createRoot } from 'react-dom/client';
import React from 'react';

// 引入全局样式
import './main.less';

// 引入RemixIcon
import 'remixicon/fonts/remixicon.css';

import App from './app';

const rootNode = document.createElement('div');
document.body.append(rootNode);

createRoot(rootNode).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

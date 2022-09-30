import { ConfigProvider } from 'antd';
import React from 'react';
import { createHashRouter, RouterProvider } from 'react-router-dom';
import routes from '../routes';

const router = createHashRouter(routes, {});
function App() {
  return (
    <>
      <ConfigProvider>
        <RouterProvider router={router} />
      </ConfigProvider>
    </>
  );
}

export default App;

import { notification } from 'antd';
import Axios from 'axios';

const axios = Axios.create({
  baseURL: 'http://localhost:8080',
  //   baseURL: 'backend://main',
  timeout: 5000,
  withCredentials: true,
});

axios.interceptors.request.use(undefined, (err) => {
  console.error(err);

  notification.error({
    message: '请求错误',
    description: err,
  });
  return undefined;
});

axios.interceptors.response.use(
  (res) => {
    if (res.data.code !== 200) {
      console.error(res.data.msg);
      notification.error({
        message: '请求错误',
        description: res.data.msg,
      });
    }
  },
  (err) => {
    console.error(err);
    notification.error({
      message: '请求错误',
      description: err,
    });
  }
);

export default axios;

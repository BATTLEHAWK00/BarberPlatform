const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const EslintWebpackPlugin = require('eslint-webpack-plugin');
const { ESBuildMinifyPlugin } = require('esbuild-loader');
const { ProgressPlugin, BannerPlugin } = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

function fromRoot(...paths) {
  return path.resolve(__dirname, '../', ...paths);
}

function fromSrc(...paths) {
  return fromRoot('src/', ...paths);
}

module.exports = {
  mode: 'development',
  entry: fromSrc('main.tsx'),
  output: {
    path: fromRoot('dist/frontend'),
    clean: true,
  },
  target: 'web',
  resolve: {
    alias: {
      '@': fromSrc(),
      '@components': fromSrc('components/'),
      '@pages': fromSrc('pages/'),
      '@util': fromSrc('util/'),
      '@api': fromSrc('api/'),
    },
    extensions: ['.ts', '.tsx', '.css', '...'],
  },
  devServer: {
    port: 8888,
    compress: true,
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'esbuild-loader',
            options: {
              loader: 'tsx',
              target: 'es2015',
            },
          },
        ],
      },
      {
        test: /\.less$/,
        use: [
          MiniCssExtractPlugin.loader,
          {
            loader: 'css-loader',
          },
          {
            loader: 'less-loader',
            options: {
              lessOptions: {
                modules: true,
                javascriptEnabled: true,
              },
            },
          },
        ],
      },
      {
        test: /\.css$/,
        use: [MiniCssExtractPlugin.loader, 'css-loader'],
      },
      {
        test: /\.(png|jpg|gif|svg)$/i,
        type: 'asset/resource',
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin(),
    new EslintWebpackPlugin({
      extensions: ['js', 'ts', 'tsx'],
      threads: true,
    }),
    new ProgressPlugin(),
    new HtmlWebpackPlugin({
      title: '理发店管理系统',
    }),
  ],
  optimization: {
    minimize: false,
    minimizer: [
      new ESBuildMinifyPlugin({
        target: 'es2015',
        css: false,
      }),
    ],
  },
};

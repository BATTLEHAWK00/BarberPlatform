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

const regex = {
  nodeModules: /[\\/]node_modules[\\/]/,
  antd: /[\\/]node_modules[\\/](antd|@ant-design|@antv)[\\/]/,
  icons: /[\\/]node_modules[\\/](@icons|remixicon|@ant-design[\\/]icons(-svg)?)[\\/]/,
  imageFile: /\.(png|jpg|gif|svg)$/i,
  fontFile: /\.(eot|woff|woff2|ttf)$/i,
};

module.exports = {
  mode: 'development',
  entry: fromSrc('main.tsx'),
  output: {
    filename: 'js/[name].[contenthash].js',
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
        generator: {
          outputPath: 'js/',
        },
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
        test: regex.imageFile,
        type: 'asset/resource',
        generator: {
          outputPath: 'img/[hash][ext]',
        },
      },
      {
        test: regex.fontFile,
        type: 'asset/resource',
        generator: {
          filename: 'font/[hash][ext]',
        },
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: 'css/[name].[contenthash].css',
    }),
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
    splitChunks: {
      automaticNameDelimiter: '-',
      name: '[name].[hash]',
      cacheGroups: {
        default: {
          minChunks: 2,
          priority: -30,
          reuseExistingChunk: true,
        },
        vendor: {
          name: 'vendor.chunk',
          chunks: 'all',
          test: regex.nodeModules,
          reuseExistingChunk: true,
          priority: -20,
        },
        antd: {
          name: 'antd.chunk',
          chunks: 'all',
          test: regex.antd,
          priority: -10,
        },
        icons: {
          name: 'icons.chunk',
          chunks: 'all',
          priority: -10,
          test: regex.icons,
        },
      },
    },
  },
};

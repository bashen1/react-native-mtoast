# React-Native-mToast

[![npm version](https://badge.fury.io/js/react-native-mtoast.svg)](https://badge.fury.io/js/react-native-mtoast)

react native native toast

针对 react native 中 toast 无法覆盖 Modal 的问题，新增了此库解决上述问题。

Android 基于 [GrenderG/Toasty](https://github.com/GrenderG/Toasty)

iOS 基于 [scalessec/Toast](https://github.com/scalessec/Toast)

感谢 [react-native-toasty](https://github.com/prscX/react-native-toasty)

## 安装

```sh
npm i react-native-mtoast -E
```

## 使用

```js
import Toast from "react-native-mtoast";

Toast.show(options: {
    /**
     * 消息内容
     */
    title: string;
    /**
     * 消息字体大小
     */
    titleSize?: number;
    /**
     * 消息字体颜色
     */
    titleColor?: string;
    /**
     * 显示时长
     * 0: SHORT; 1: LONG
     */
    duration?: 0 | 1;
    /**
     * 吐司背景色
     */
    tintColor?: string;
    /**
     * 显示位置
     */
    position?: 'top' | 'center' | 'bottom';
    /**
     * Android only
     */
    offsetX?: number;
    /**
     * Android only
     */
    offsetY?: number;
})
```

## License

MIT

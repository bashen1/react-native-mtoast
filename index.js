import {NativeModules} from 'react-native';

const {Mtoast} = NativeModules;

const defaultValue = {
    title: '',
    titleSize: 0,
    titleColor: '#ffffff',

    duration: 0,
    tintColor: '',

    position: 'bottom',
    offsetX: 0,
    offsetY: 50,
}

class Toast {
    static show(options = {}) {
        if (options.title === undefined) options.title = defaultValue.title;
        if (options.titleSize === undefined) options.titleSize = defaultValue.titleSize;
        if (options.titleColor === undefined) options.titleColor = defaultValue.titleColor;
        if (options.duration === undefined) options.duration = defaultValue.duration;
        if (options.tintColor === undefined) options.tintColor = defaultValue.tintColor;
        if (options.position === undefined) options.position = defaultValue.position;
        if (options.offsetX === undefined) options.offsetX = defaultValue.offsetX;
        if (options.offsetY === undefined) options.offsetY = defaultValue.offsetY;
        Mtoast.show(options);
    }
}

export default Toast;
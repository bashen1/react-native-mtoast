type Options ={
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
}

export default class Toast {
    static show(params: Options): void;
}
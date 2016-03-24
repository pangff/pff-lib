# pff-lib
总结一些自己做过的Android项目预研

## VectorPathView使用

#### 要实现一个地铁线路的路径绘制问题
* [具体预研思路文章](http://www.pffair.com/blog/2016/03/11/svg-android/)
* [详细实现思路文章](http://www.pffair.com/blog/2016/03/24/shi-liang-tu-lu-jing-wen-ti-de-pathviewshi-xian-fang-an/)
* 本项目是基于android-pathview的改动方案，
对[android-pathview项目](https://github.com/geftimov/android-pathview)进行了部分修改。
* 个人认为实现方式不是很好，而且要求svg规则有序,有想法的同学欢迎指正

#### 已经实现
* svg路线图到pathview的渲染
* 移动、放大缩小（后面会做成手势操作）
* 指定路线的高亮绘制
* 节点点击事件的处理


####效果图如下

![image](http://www.pffair.com/images/48.png)
![image](http://www.pffair.com/images/49.png)

#### 参考
[androidsvg](https://github.com/BigBadaboom/androidsvg) 

[android-pathview](https://github.com/geftimov/android-pathview)

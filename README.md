# FunnyDraw
绘画天赋不行，编码技术来补，猜画小歌小程序辅助工具。

```diff
-本项目需要广大人民群众支持，赶紧用Pull Request来砸我吧！
```

### 效果演示
![](https://github.com/MegatronKing/FunnyDraw/blob/master/screenshot/sample.gif)


### 猜画小歌辅助
#### 第一步：运行安装App并启动
![](https://github.com/MegatronKing/FunnyDraw/blob/master/screenshot/a.png)
#### 第二步：启动server
```shell
adb shell
export CLASSPATH=/data/local/tmp/com.github.megatronking.funnydraw
exec app_process /system/bin com.github.megatronking.funnydraw.Main '$@'
```
#### 第三步：点击App中“猜画小歌测试”按钮，同意悬浮窗权限。

#### 第四步：打开微信启动猜画小歌小程序,选择右侧列表中图案进行自动绘制。
![](https://github.com/MegatronKing/FunnyDraw/blob/master/screenshot/b.png)

#### 注意事项
- 部分手机需要在开发者模式中打开模拟点击开关，比如小米手机等。


### 自定义开发及测试

#### 调试模拟器
每次修改代码后，在猜画小歌小程序中测试都需要重启server，为了简化此过程，可以直接在App内调试。点击首页“当前应用调试”。

![](https://github.com/MegatronKing/FunnyDraw/blob/master/screenshot/c.png)

#### 编写一个简单的Sample，比如圆形
使用CircleMotionDrawer开发，定义好圆心、半径、绘制时间即可。
```java
public class CircleSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        // 根据画布位置和尺寸，将圆置于画布中心
        return new CircleMotionDrawer(canvas.centerX, canvas.centerY, canvas.width / 4,
                DEFAULT_DURATION);
    }

}
```

#### 编写一个复杂的Sample，比如酒杯
复杂的图案，需要组合各种MotionDrawer，可以使用MotionDrawerSet来组合。
```java
public class WineGlassSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        // 杯口的椭圆
        int topOvalRadiusX = 200;
        int topOvalRadiusY = 100;
        int topOvalCenterX = canvas.centerX;
        int topOvalCenterY = canvas.centerY - 400;
        OvalMotionDrawer drawer1 = new OvalMotionDrawer(topOvalCenterX, topOvalCenterY,
                topOvalRadiusX, topOvalRadiusY, 0, 1000);

        // 杯底的椭圆
        int bottomOvalRadiusX = 100;
        int bottomOvalRadiusY = 50;
        int bottomOvalCenterX = canvas.centerX;
        int bottomOvalCenterY = canvas.centerY + 400;
        OvalMotionDrawer drawer2 = new OvalMotionDrawer(bottomOvalCenterX, bottomOvalCenterY,
                bottomOvalRadiusX, bottomOvalRadiusY, 0, 1000);

        // 杯身两侧的圆弧，画贝塞尔曲线
        int glassBottomX = canvas.centerX;
        int glassBottomY = canvas.centerY + 150;
        QuadBezierMotionDrawer drawer3 = new QuadBezierMotionDrawer(topOvalCenterX - topOvalRadiusX,
                topOvalCenterY, glassBottomX, glassBottomY, canvas.left, canvas.centerY, 1000);
        QuadBezierMotionDrawer drawer4 = new QuadBezierMotionDrawer(topOvalCenterX + topOvalRadiusX,
                topOvalCenterY, glassBottomX, glassBottomY, canvas.right, canvas.centerY, 1000);

        // 杯柄
        LineMotionDrawer drawer5 = new LineMotionDrawer(glassBottomX, glassBottomY,
                bottomOvalCenterX, bottomOvalCenterY - bottomOvalRadiusY, 500);

        // 按照绘制的顺序组合起来
        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5);
    }

}

```

#### 将开发好的Sample加入到浮窗列表
在assets目录的samples.xml文件中配置好sample的类路径和名称。
```xml
<?xml version="1.0" encoding="utf-8"?>
<samples package="com.github.megatronking.funnydraw.sample">
    <sample name="酒杯" class=".WineGlassSample"/>
    <sample name="圆形" class=".CircleSample"/>
    ...
</samples>
```

### API文档

#### LineMotionDrawer 
绘制直线
```java
// 从坐标(500,500)直线绘制到坐标(600, 600)，绘制时间1000ms
LineMotionDrawer drawer = new LineMotionDrawer(500, 500, 600, 600, 1000);
```

#### CircleMotionDrawer 
绘制圆形
```java
// 以坐标(500,500)为圆心，100为半径，按顺时针绘制，绘制时间1000ms
CircleMotionDrawer drawer = new CircleMotionDrawer(500, 500, 100, 1000);
```

#### OvalMotionDrawer 
绘制椭圆形
```java
// 以坐标(500,500)为圆心，100为x轴半径，50为y轴半径，按顺时针绘制，绘制时间1000ms
OvalMotionDrawer drawer = new OvalMotionDrawer(500, 500, 100, 50, 1000);
```

#### RectMotionDrawer 
绘制矩形
```java
// 以坐标(100,100)、(500,100)、(500,500)、(100,500)为四个矩形点，按顺时针绘制，绘制时间1000ms
Rect rect = new Rect(100, 100, 500, 500);
RectMotionDrawer drawer = new RectMotionDrawer(rect, 1000);
```

#### TriangleMotionDrawer 
绘制三角形
```java
// 以坐标(100,100)、(300,100)、(200,200)为三角形顶点，按顺时针绘制，绘制时间1000ms
TriangleMotionDrawer drawer = new TriangleMotionDrawer(100, 100, 300, 100, 200, 200, 1000);
```

#### QuadBezierMotionDrawer 
绘制二阶贝塞尔曲线
```java
// 以坐标(100,100)为曲线起点、坐标(300,300)为曲线终点、坐标(200,200)为控制点，绘制时间1000ms
QuadBezierMotionDrawer drawer = new QuadBezierMotionDrawer(100, 100, 300, 300, 200, 200, 1000);
```

#### CubicBezierMotionDrawer 
绘制三阶贝塞尔曲线
```java
// 以坐标(100,100)为曲线起点、坐标(300,300)为曲线终点、坐标(200,200)和(200,250)为控制点，绘制时间1000ms
CubicBezierMotionDrawer drawer = new CubicBezierMotionDrawer(100, 100, 300, 300, 200, 200, 200，450, 1000);
```

#### MotionDrawerSet 
图形组合器，可以将以上的多个MotionDrawer组合成一个

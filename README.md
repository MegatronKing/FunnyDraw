# FunnyDraw
绘画天赋不行，编码技术来补，猜画小歌小程序辅助工具

### 猜画小歌辅助
#### 第一步：运行安装App并启动

#### 第二步：启动server
```
adb shell
export CLASSPATH=/data/local/tmp/com.github.megatronking.funnydraw
exec app_process /system/bin com.github.megatronking.funnydraw.Main '$@'
```
#### 第三步：点击App中“猜画小歌测试”按钮，同意悬浮窗权限。

#### 第四步：打开微信启动猜画小歌小程序,选择右侧列表中图案进行自动绘制。

#### 注意事项
- 部分手机需要在开发者模式中打开模拟点击开关，比如小米手机等。

### 自定义开发及测试

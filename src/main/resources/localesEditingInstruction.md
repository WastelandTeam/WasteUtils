# 插件内部消息编写指南

## 本指南旨在帮助你轻松编写内部消息，以防你不会

在普通情况下，我们需要为插件编写一些输出\
但是只因 ~~小迪不想整理~~ java文件里面字符串太多，\
我们就需要做一个单独的文件\
文件在resources/locales.config ~~千万别删~~

现在我们尝试去编写一些简单的吧

例如说，一条消息说Hello world?\

```
Hello World
```

这就好啦！

再来个难点的！\
例如...你有个字符串想要插进去\
比如说这个字符串是 Powered by DiGame\
整个内容是 WasteUtils Powered by DiGame

```
WasteUtils [%s]
```

就好啦！

不过就算有无尽的想法\
也要排队哦(排队表在最后)

| Line | Text                                                                    |
|------|-------------------------------------------------------------------------|
| 1    | No vault detected! Is vault installed correctly?                        |
| 2    | Vault not found, skipping economy system.                               |
| 3    | Di Anti-Cheat not found! The protection to the server will be disabled. |
| 4    | We are strongly recommend to install Di-Anti Cheat.                     |
## Handler面试点
1. Handler的初始化
2. Handler的消息发送

Handler的初始化： ActivityThread 创建Handler开始，等待消息
Handler消息发送： 唤醒等待，分发消息

### Android中为什么主线程不会因为Looper.loop()里的死循环卡死
1. epoll机制，在空闲的时候阻塞。
2. 所有的事件，生命周期，点击事件，界面刷新都是通过Handler发送消息给主线程处理
3. loop() 死循环可以让应用进程一直存活，如果没有loop() 进程运行完就结束了，没有后续操作

### Android Handler之同步屏障机制（点击页面上的按钮后更新TextView的内容，谈谈你的理解？）
1. 在同步消息插入屏障
2. 对异步消息不限制
3. target为空的异步消息，为异步消息提供了优先级

一般来说，MessageQueue里面的所有Message是按照时间从前往后有序排列的。
同步屏障消息就是在消息队列中插入一个屏障，在屏障之后的所有普通消息都会被挡着，不能被处理。
不过异步消息却例外，屏障不会挡住异步消息，因此可以认为，屏障消息就是为了确保异步消息的优先级，
设置了屏障后，只能处理其后的异步消息，同步消息会被挡住，除非撤销屏障。


> 事件监听机制多用于图形界面编程，比如：点击按钮、在文本框输入内容等操作被称为事件，而当事件触发时，应用程序作出一定的响应则表示应用监听了这个事件，而在服务器端，事件的监听机制更多的用于异步通知以及监控和异常处理。
## Java提供了实现事件监听机制的两个基础类：
- 自定义事件类型扩展自java.util.EventObject
- 事件的监听器扩展自java.util.EventListener
- 事件发布者：MethodMonitorEventPublisher 负责发布事件

### MethodMonitorEventPublisher
#### MethodMonitorEventPublisher
1.  在合适的时机发布事件。此例中的methodMonitor()方法是事件发布的源头，其在方法执行之前和结束之后两个时间点发布MethodMonitorEvent事件，每个时间点发布的事件都会传给相应的监听器进行处理。（在具体实现时需要注意的是，事件发布是顺序执行，为了不影响处理性能，事件监听器的处理逻辑应尽量简单。）
2.  事件监听器的管理。publisher类中提供了事件监听器的注册与移除方法，这样客户端可以根据实际情况决定是否需要注册新的监听器或者移除某个监听器。（如果这里没有提供remove方法，那么注册的监听器示例将一直MethodMonitorEventPublisher引用，即使已经废弃不用了，也依然在发布者的监听器列表中，这会导致隐性的内存泄漏。）

### LocalDate
- Java 8引入了一套全新的时间日期API，位于java.time路径下。

- LocalDate :LocalDate类型包含了年月日信息
- LocalTime :LocalTime和LocalDate类似，区别在于LocalTime包含的是时分秒（毫秒）信息
- LocalDateTime: LocalDateTime是LocalDate和LocalTime的组合形式，包含了年月日时分秒信息。
- Duration : Duration用于计算两个LocalTime或者LocalDateTime的时间差
- Period:Period用于计算两个LocalDate之间的时长。

- LocalDate和下面要介绍的LocalTime，LocalDateTime之间共享了许多类似的方法，上面介绍的LocalDate修改、格式化等方法通用适用于LocalTime和LocalDateTime。
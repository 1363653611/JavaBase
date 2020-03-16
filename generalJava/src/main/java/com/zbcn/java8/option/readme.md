### 使用Optional取代null
- 在Java中对一个空对象进行操作时，便会抛出最常见的异常NullPointerException。为了改善这个问题，Java 8中提供了一个java.util.Optional<T>类型。Optional类的Javadoc描述如下：这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。

#### 创建Optional
- 创建一个空的Optional `Optional<Department> department = Optional.empty();`
- 根据非空值创建Optional `Optional<Employee> optEmployee = Optional.of(employee);`
    - 如果employee为空，这段代码会 __立即__ 抛出一个NullPointerException。
      
- 创建可以为null的Optional： `Optional<Employee> optEmployee = Optional.ofNullable(employee);`
    - 如果employee为空，对其 __调用get方法__ 将抛出NoSuchElementException。

- ### Optional方法
- isPresent: 如果值存在返回true，否则返回false。
    ```java
    Optional<Department> opt = Optional.ofNullable(department);
    if(opt.isPresent()){
        System.out.println(opt.get().getEmployee());
    }
    ```

- get 如果Optional有值则将其返回，否则抛出NoSuchElementException

- ifPresent 如果Optional实例有值则为其调用Consumer（函数描述符为T -> void），否则不做处理
`nullAbleEmployee.ifPresent( e -> System.out.println(e.getGirlFriend().getName()));`
- orElse
    - 如果Optional实例有值则将其返回，否则返回orElse方法传入的参数。
    - `System.out.println(Optional.empty().orElse("There is no value present!"));`
- orElseGet
    - orElseGet与orElse方法类似，orElse方法将传入的字符串作为默认值，而orElseGet方法可以接受Supplier（函数描述符为() -> T）来生成默认值
    
- orElseThrow 如果有值则将其返回，否则抛出Supplier接口创建的异常
    - ` Optional.empty().orElseThrow(NoSuchElementException::new);`
    
- map
    - 如果Optional有值，则对其执行调用Function函数描述符为（T -> R）得到返回值。如果返回值不为null，则创建包含Function回值的Optional作为map方法返回值，否则返回空Optional。
    ```
    Optional<String> upperName = name.map(String::toUpperCase);
    System.out.println(upperName.orElse("No value found"));
    ```

- flatMap
    - 如果有值，为其执行Function函数返回Optional类型返回值，否则返回空Optional。flatMap与map方法类似，区别在于flatMap中的Function函数返回值必须是Optional。调用结束时，flatMap不会对结果用Optional封装。
    - ```
      Girl lili1 = optEmployee.flatMap(e -> Optional.of(e.getGirlFriend()))//latMap与map方法类似，区别在于flatMap中的Function函数返回值必须是Optional
      				.orElse(new Girl("lili"));
      ```
- filter
    - filter个方法通过传入Predicate（函数描述符为T -> Boolean）对Optional实例的值进行过滤
    - ```
        Optional<String> name = Optional.of("Jane");
        Optional<String> LongName = name.filter((value) -> value.length() >= 3);
        System.out.println(LongName.orElse("名字长度小于3个字符"));
      ```



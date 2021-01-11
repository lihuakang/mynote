# Java8

### Lamda表达式

```
Comparator<User> userComparator = (User a, User b) -> {
    return a.getAge() - b.getAge();
};
```

（）->{}

左边括号是函数式接口内方法的参数，右面是方法体

#### 函数式接口

函数式接口：只定义一个抽象接口。

Comparator

Runnable

Callable

```
        List<String> str = Arrays.asList("a","b","A","B");
//        str.sort(String::compareTo);
        str.sort((s1,s2)->s1.compareTo(s2));
        System.out.println(str);
```

### Stream流

#### 流是什么

可以看成遍历集合的高级迭代器。流可以透明的并行处理。

```
List<String> lowName=menu.stream()
						.filter(d->d.getCalories<400)//过滤
						.sort(comparing(Dishes::getCalories)) //排序
						.map(Dish::getName) //提取name
						.collect(toList());
```

```
List<String> lowName=menu.parallelStream() //并行处理
						.filter(d->d.getCalories<400)//过滤
						.sort(comparing(Dishes::getCalories)) //排序
						.map(Dish::getName) //提取name
						.collect(toList());
```

//通过type进行分组

```
Map<Dish.Type, List<Dish>> dishesByType =
menu.stream().collect(groupingBy(Dish::getType));  
```

#### 流简介

**元素序列**——就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序值。集合讲的是数据，流讲的是计算。

**源**——流会使用一个提供数据的源，如集合、数组或输入/输出源。

**数据处理操作**——流的数据处理功能支持类似于数据库的操作，以及函数式编程语言中的常用操作，如filter、 map、 reduce、 find、 match、 sort等。流操作可以顺序执行，也可并行执行。  

**流水线**——很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线。      

**内部迭代**——与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的。  

![image-20210104151053915](\img\java8-1.png)

**filter**——接受Lambda，从流中排除某些元素。在本例中，通过传递lambda d ->d.getCalories() > 300，选择出热量超过300卡路里的菜肴  

**map**——接受一个Lambda，将元素转换成其他形式或提取信息。在本例中，通过传递方法引用Dish::getName，相当于Lambda d -> d.getName()，提取了每道菜的菜名  

**limit**——截断流，使其元素不超过给定数量  

**collect**——将流转换为其他形式。  

#### 流与集合

集合是一个内存中的数据结构，它包含数据结构中目前所有的值——集合中的每个元素都得先算出来才能添加到集合中。  

流则是在概念上固定的数据结构（你不能添加或删除元素），其元素则是按需计算的。   

##### 只能遍历一次

和迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了。你可以从原始数据源那里再获得一个新的流来重新遍历一遍，就像迭代器一样（这里假设它是集合之类的可重复的源，如果是I/O通道就没戏了）。  

##### 外部迭代和内部迭代

集合是外部迭代，

流是内部迭代。可以内部并行处理。

#### 流操作

filter、 map和limit可以连成一条流水线；  

collect触发流水线执行并关闭它。  

可以连接起来的流操作称为中间操作，关闭流的操作称为终端操作。  

![image-20210104170139970](\img\java8-2.png)

##### 中间操作

filter或sorted等中间操作会返回一个新的流。

除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒。
这是因为中间操作一般都可以合并起来，在终端操作时一次性全部处理  

##### 终端操作

终端操作会从流的流水线生成结果。其结果是任何不是流的值，比如List、 Integer，甚至void。例如，在下面的流水线中， forEach是一个返回void的终端操作，  

#### 流操作

总而言之，流的使用一般包括三件事：
 一个数据源（如集合）来执行一个查询；
 一个中间操作链，形成一条流的流水线；
 一个终端操作，执行流水线，并能生成结果。  

![image-20210104170742652](\img\java8-3.png)

### 使用流

#### 筛选和切片

如何选择流中的元素：用谓词筛选，筛选出各不相同的元素，忽略流
中的头几个元素，或将流截短至指定长度  

##### 用谓词筛选

streams接口支持filter方法。该操作接收一个谓词（一个返回值为boolean类型的函数）作为参数。并返回一个包括所有符合谓词的元素的流。  

![image-20210104171329181](D:\note\mynote\笔记\img\java8-4.png)

##### 去重元素  

```
 List<Integer> integers = Arrays.asList(1, 1, 2, 4, 2, 4, 5);
        integers.stream()
                .filter(i->i%2==0)
                .distinct()
                .forEach(System.out::println);
```

distinct()去重

##### 截断流

流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递给limit。如果流是有序的，则最多会返回前n个元素。  

```
List<Integer> integers = Arrays.asList(1, 1, 2, 4, 2, 4, 5);
        List<Integer> collect = integers.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
```

请注意limit也可以用在无序流上，比如源是一个Set。这种情况下， limit的结果不会以任何顺序排列。

##### 跳过元素

流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一个空流。请注意， limit(n)和skip(n)是互补的！    

```
List<Integer> integers = Arrays.asList(1, 1, 2, 4, 2, 4, 5);
        List<Integer> collect = integers.stream()
                .skip(3)  //扔掉前面三个元素
                .collect(Collectors.toList());
        System.out.println(collect.toString());
```

#### 映射

##### 对流中每一个元素应用函数

流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不去“修改”）。  

```
List<String> dishNames=menu.stream()
						.map(Dish::getName)
						.collect(toList());
```

因为getName方法返回一个String，所以map方法输出的流的类型就是Stream<String>  

```
List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);//打印每个元素的长度
```

##### 流的扁平化

```
List<String> words = Arrays.asList("Hello", "World");
        List<String> wordArr = words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(wordArr);//打印每个元素的长度
```

flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。  

![image-20210105103458492](\img\java8-5.png)

#### 查找和匹配

Stream API通过allMatch、 anyMatch、 noneMatch、 findFirst和findAny方法提供了这样的工具。  

##### 检查谓词是否至少匹配一个元素 

anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。比如，你可以用它来看看菜单里面是否有素食可选择：

anyMatch方法返回一个boolean，因此是一个终端操作。  

```
if(menu.stream().anyMatch(Dish::isVegetarian)){
System.out.println("The menu is (somewhat) vegetarian friendly!!");
}
```

anyMatch方法返回一个boolean，因此是一个终端操作。  

##### 检查谓词是否匹配所有元素

```
boolean isHealthy = menu.stream()
.allMatch(d -> d.getCalories() < 1000);
```

#####  noneMatch

和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。  



anyMatch、 allMatch和noneMatch这三个操作都用到了我们所谓的短路，这就是大家熟悉的Java中&&和||运算符短路在流中的版本。  

#### 查找元素

findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用  

```
 List<String> words = Arrays.asList("Hello", "World");
        Optional<String> wordArr = words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .findAny();
        String s = wordArr.get();
        System.out.println(wordArr);//打印容器类
        System.out.println(s);//H
```

Optional简介
Optional<T>类（ java.util.Optional）是一个容器类，代表一个值存在或不存在。在上面的代码中， findAny可能什么元素都没找到。 Java 8的库设计人员引入了Optional<T>，这样就不用返回众所周知容易出问题的null了。  

Optional里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形的方法也不错。
 isPresent()将在Optional包含值的时候返回true, 否则返回false。
 ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块。
 T get()会在值存在时返回值，否则抛出一个NoSuchElement异常。
 T orElse(T other)会在值存在时返回值，否则返回一个默认值。  

```
List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .findAny()
                .ifPresent(s-> System.out.println(s));//如果存在就执行下面代码
```

##### 查找第一个元素

findFirst

```
 //找到平方能被3整除的第一个数
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        words.stream()
                .map(x->x*x)
                .filter(x->x%3==0)
                .findFirst()
                .ifPresent(s-> System.out.println(s));//9
```

#### 归纳

你见到过的终端操作都是返回一个boolean（ allMatch之类的）、 void（ forEach）或Optional对象（ findAny等）。你也见过了使用collect来将流中的所有元素组合成一个List  

如何把一个流中的元素组合起来，使用reduce操作来表达更复杂的查询，比如“计算菜单中的总卡路里”或“菜单中卡路里最高的菜是哪一个”。

此类查询需要将流中所有元素反复结合起来，得到一个值，比如一个Integer。这样的查询可以被归类为归约操作
（将流归约成一个值）。用函数式编程语言的术语来说，这称为折叠（ fold），因为你可以将这个操
作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。  

##### 元素求和

```
//元素求和
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        Integer reduce = words.stream()
                .reduce(0, (a, b) -> a + b);
               //0是初始值 后面lambda表达式
        System.out.println(reduce);
```

![image-20210105110940163](D:\note\mynote\笔记\img\java8-6.png)

无初始值  

```
//元素求和
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        words.stream()
                .reduce( (a, b) -> a + b).ifPresent(x-> System.out.println(x));
```

##### 最大值和最小值

```
//元素最大值
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        Optional<Integer> reduce = words.stream()
                .reduce(Integer::max);
        reduce.ifPresent(x-> System.out.println(x));
```

![image-20210105111613646](D:\note\mynote\笔记\img\java8-7.png)

#### 数值流

```
List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
       
        int reduce = words.stream()
                .reduce(0, Integer::sum);
//这里面暗含了Integer的拆箱
```

##### 原始类型流特化

IntStream、 DoubleStream和LongStream，分别将流中的元素特化为int、 long和double，从而避免了暗含的装箱成本。  

##### 映射到数值流

mapToInt会将流转成一个IntStream

```
 List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        OptionalDouble average = words.stream()
                .mapToInt(Integer::intValue)
                .average();
        average.ifPresent(a-> System.out.println(a));
```

##### 转换回对象流

intStream.boxed()

```
List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        IntStream intStream = words.stream().mapToInt(Integer::intValue);
        Stream<Integer> boxed = intStream.boxed();
```

##### 数值范围

Java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成这种范围：range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但range是不包含结束值的，而rangeClosed则包含结束值。  

```
 IntStream intStream1 = IntStream.range(1, 100);
        int sum = intStream1.sum();
        System.out.println(sum);
```

#### 构建流

值序列、数组、文件来创建流，甚至由生成函数来创建无限流！  

##### 1 通过Collection系列的集合创建流

##### 2 通过Arrays的静态方法stream()

##### 3 通过Stream类中的静态方法of()

##### 4 创建无限流

Stream.iterate和Stream.generate。  创建无限流，流是无界的，有limit截取。

```
Stream.iterate(0,n->n+2)
                .limit(10)
                .forEach(System.out::println);
```

```
 Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
```

#### 用流收集数据

流支持两种类型的操作：中间操作（如filter或map）和终端操作（如count、 findFirst、 forEach和reduce）。中间操作可以链接起来，将一个流转换为另一个流。这些操作不会消耗流，其目的是建立一个流水线。与此相反，终端操作会消耗流，以产生一个最终结果 

collect()终端操作

##### 预定义收集器

预定义收集器的功能，也就是那些可以从Collectors类提供的工厂方法（例如groupingBy）创建的收集器。它们主要提供了三大功能：
 将流元素归约和汇总为一个值
 元素分组
 元素分区  

##### 汇总

```
List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        Long collect = words.stream().collect(Collectors.counting());
        System.out.println(collect);//8
  //等同于
  long count = words.stream().count();
```

##### 取最大值最小值

```
Optional<Integer> collect1 = words.stream().collect(minBy((a, b) -> a.compareTo(b)));
        collect1.ifPresent(x-> System.out.println(x));
```

```
Optional<Integer> collect1 = words.stream().collect(maxBy((a, b) -> a.compareTo(b)));
        collect1.ifPresent(x-> System.out.println(x));
```

求和

```
int collect2 = words.stream().collect(summingInt(Integer::intValue));
        System.out.println(collect2);//36
```

求平均数

```
 Double collect2 = words.stream().collect(averagingInt(Integer::intValue));
        System.out.println(collect2);
```

##### 连接字符串

把所有元素都链接到一起

```
String collect3 = words.stream().map(a -> a.toString()).collect(joining());
        System.out.println(collect3);//12345678
```

```
 String collect3 = words.stream().map(a -> a.toString()).collect(joining(", "));
        System.out.println(collect3);//1, 2, 3, 4, 5, 6, 7, 8
```

#### 分组

一个常见的数据库操作是根据一个或多个属性对集合中的项目进行分组  

通过苹果颜色进行分组

```
Map<String, List<Apple>> collect = list.stream().collect(groupingBy(Apple::getColor));
        System.out.println(collect);
```

结果：

```
{red=[Apple{color='red', weight=11}, Apple{color='red', weight=2}], green=[Apple{color='green', weight=13}, Apple{color='green', weight=14}], black=[Apple{color='black', weight=22}]}
```



根据苹果重量分等级

```
Map<String, List<Apple>> collect1 = list.stream().collect(groupingBy((a) -> {
            if (a.getWeight() > 20) {
                return "a";
            } else {
                return "b";
            }
        }));
        System.out.println(collect1);
```

结果：

```
{a=[Apple{color='black', weight=22}], b=[Apple{color='red', weight=11}, Apple{color='red', weight=2}, Apple{color='green', weight=13}, Apple{color='green', weight=14}]}
```

多级分组

```
Map<String, Map<String, List<Apple>>> collect2 = list.stream().collect(groupingBy(Apple::getColor, groupingBy((a) -> {
            if (a.getWeight() > 20) {
                return "a";
            } else {
                return "b";
            }
        })));
        System.out.println(collect2);
```

##### 按子组收集数据

```
Map<String, Long> collect3 = list.stream().collect(groupingBy(Apple::getColor, counting()));
        System.out.println(collect3);
```

结果：

```
{red=2, green=2, black=1}
```

求每组苹果中重量最大的

```
Map<String, Optional<Apple>> collect4 = list.stream().collect(groupingBy(Apple::getColor, maxBy(Comparator.comparingInt(Apple::getWeight))));
        System.out.println(collect4);
```

结果

```
{red=Optional[Apple{color='red', weight=11}], green=Optional[Apple{color='green', weight=14}], black=Optional[Apple{color='black', weight=22}]}
```



把收集器结果转成另一种结果

```
Map<String, Apple> collect4 = list.stream().collect(groupingBy(Apple::getColor, collectingAndThen(maxBy(Comparator.comparingInt(Apple::getWeight)), Optional::get)));
        System.out.println(collect4);
```

打印：

```
{red=Apple{color='red', weight=11}, green=Apple{color='green', weight=14}, black=Apple{color='black', weight=22}}
```

#### 分区

分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以分为两组——true是一组， false是一组  。

```
Map<Boolean, List<Apple>> collect = list.stream().collect(partitioningBy(x -> x.getWeight() > 10));
        System.out.println(collect);
```

打印：

```
{false=[Apple{color='red', weight=2}], true=[Apple{color='red', weight=11}, Apple{color='green', weight=13}, Apple{color='green', weight=14}, Apple{color='black', weight=22}]}
```

获取谓词为true的分组

```
List<Apple> list1 = collect.get(true);
```

![image-20210105161236734](\img\java8-8.png)

![image-20210105161330545](\img\java8-9.png)

![image-20210105161357264](\img\java8-10.png)

#### 收集器接口

Collector接口  

```
public interface Collector<T, A, R> {
Supplier<A> supplier();
BiConsumer<A, T> accumulator();
Function<A, R> finisher();
BinaryOperator<A> combiner();
Set<Characteristics> characteristics();
}
```

toList工厂方法，它会把流中的所有元素收集成一个List。  

##### 理解 Collector 接口声明的方法  

建立新的结果容器： supplier方法  

```
public Supplier<List<T>> supplier() {
return () -> new ArrayList<T>();
}

public Supplier<List<T>> supplier() {
return ArrayList::new;
}
```

将元素添加到结果容器： accumulator方法  

```
public BiConsumer<List<T>, T> accumulator() {
return (list, item) -> list.add(item);
}
```

```
public BiConsumer<List<T>, T> accumulator() {
return List::add;
}
```

对结果容器应用最终转换： finisher方法  

```
public Function<List<T>, List<T>> finisher() {
return Function.identity();
}	
```

#### 并行数据处理与性能

##### 并行流

通过对收集源调用parallelStream方法来把集合转换为并行流。 并行流就是一个把内容分成多个数据 块，并用不同的线程分别处理每个数据块的流。  

```
Long reduce = Stream.iterate(0L, i -> i + 1) //创建无限流
                .limit(100)
                .parallel()//将流转换成并行流
                .reduce(0L, Long::sum);
        System.out.println(reduce);
```

##### 并行流转顺序流

并行流调用sequential方法就可以转顺序流

```
stream.parallel()
.filter(...)
.sequential()
.map(...)
.parallel()
.reduce();
```

##### 并行流用的线程池

并行流内部默认使用了ForkJoinPool,默认处理线程数量就是处理器的数量。

#### 分支/合并框架

并行流基础框架就是jdk1.7的分支/合并 框架

是executorService的一个实现类，把子任务分配给线程池ForkJoinPool中的工作线程。

##### 使用RecursiveTask

```
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD=8;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length=end-start;
        if (length<=THRESHOLD){
            return computeSequentially(); //小于阈值顺序执行
        }
        ForkJoinSumCalculator leftTask=new ForkJoinSumCalculator(numbers,start,start+length/2);
        leftTask.fork(); //利用另一个FoekJoinPool线程异步执行新创建的子任务
        ForkJoinSumCalculator rightTask=new ForkJoinSumCalculator(numbers,start+length/2,end);
        Long rightResult = rightTask.compute(); //同步执行，可能允许进一步递归
        Long leftResult = leftTask.join();
        return leftResult+rightResult;
    }

    private Long computeSequentially() {
        long sum=0;
        for (int i=start;i<end;i++){
            sum+=numbers[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        long[] nums={1,2,3,4,5,6,7,8,9,10};
        ForkJoinSumCalculator forkJoinSumCalculator=new ForkJoinSumCalculator(nums,0, nums.length);
        Long compute = forkJoinSumCalculator.compute();
        System.out.println(compute);
    }
}

```

### 默认方法

Java 8引入了一个新功能，叫默认方法，通过默认方法你可以指定接口方法的默认实现。换句话说，接口能提供方法的具体实现。因此，实现接口的类如果不显式地提供该方法的具体实现，就会自动继承默认的实现。  

方法上加default关键字

冲突问题：

接口是可以多实现的，但是java中时单继承，default方法自带实现类，在多实现的时候会产生冲突。

三条规则：

(1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优
先级。
(2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更加具体。
(3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，  显式地选择使用哪一个默认方法的实现  。

如果平级无法选择，则编译异常、

### Optional

避免空指针。

变量存在时， Optional类只是对类简单封装。变量不存在时，缺失的值会被建模成一个“空”的Optional对象，由方法Optional.empty()返回。 Optional.empty()方法是一个静态工厂方法，它返Optional类的特定单一实例  

#### 声明一个空的Optional

```
Optional<Apple> optional=Optional.empty();
```

#### 依据一个非空值创建Optional

```
Apple apple=new Apple();
Optional<Apple> optApple=Optional.of(apple);
```

#### 可接受null的Optional

```
Optional<Apple> apple1 = Optional.ofNullable(apple);
```

#### 使用 map 从 Optional 对象中提取和转换值  

```
Optional<Apple> apple1 = Optional.ofNullable(apple);
        Optional<String> s = apple1.map(Apple::getColor);
```


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


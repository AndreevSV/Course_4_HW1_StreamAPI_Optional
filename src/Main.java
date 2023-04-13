/*
Задание 1
Напишите метод findMinMax, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.

Данный метод принимает на вход 3 элемента:

Stream<? extends T> stream,
Comparator<? super T> order,
BiConsumer<? super T, ? super T> minMaxConsumer

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

minMaxConsumer.accept(min, max);
Если стрим не содержит элементов, то вызовите minMaxConsumer.accept(null, null);

Критерии
Задача решена корректно
Реализация через коллекцию(Например List или Queue)
Логически верно определили результат
Корректное получение необходимых данных
Соблюден кодстайл

------------------------------------------------------------------------------------------------------------------------
Задание 2
Реализуйте метод, который принимает на вход Список целых чисел и определяет количество четных и выводит их в консоль.
Решать именно с применением Stream API

Критерии
Задача решена корректно
Задача решена одним из двух способов
Соблюден кодстайл
Правильно реализован предикат
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 10, 9, 8, 7, 6);

        findMinMax2(integerList.stream(),
                Comparator.naturalOrder(),
                (min, max) -> System.out.println(min + "- минимальное значение \n" + max + " максимальное значение \n"));

        findMinMax3(integerList.stream(),
                Comparator.naturalOrder(),
                (min, max) -> System.out.println(min + "- минимальное значение \n" + max + " максимальное значение \n"));

    }

    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {
        Object[] arr = stream.sorted(order).toArray();

        // 1-я задача ------------------------
        if (arr.length == 0) {
            minMaxConsumer.accept(null, null);
        } else {
            T min = (T) arr[0];
            T max = (T) arr[arr.length - 1];
            minMaxConsumer.accept(min, max);
        }

        //2-я задача -------------------------
//        Integer counter = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (((Integer) arr[i] % 2) == 0) {
//                System.out.print(arr[i] + ", ");
//                counter++;
//            }
//        }
//        System.out.println();
//        System.out.println(counter);
    }


    // 1-я задача 2-й вариант через List------------------------
    public static <T> void findMinMax2(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<? extends T> lists = stream.sorted(order).toList();


        if (lists.size() == 0) {
            minMaxConsumer.accept(null, null);
        } else {
            T min = lists.get(0);
            T max = lists.get(lists.size() - 1);
            minMaxConsumer.accept(min, max);
        }
    }

    //2-я задача 2-й вариант через Stream-------------------------
    public static <T> void findMinMax3(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<Integer> list = stream
                .sorted(order)
                .map(String::valueOf)
                .map(Integer::valueOf)
                .filter(x -> x % 2 == 0)
                .toList();

        list.forEach(x -> System.out.print(x + " "));

        System.out.println();
        System.out.println("Количество четных элементов: " + list.size());

    }


}
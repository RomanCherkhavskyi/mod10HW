package home.task3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String source = "src\\words.txt";               //path to source file words.txt
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(source));
        String[] array;                                                         //temp array
        String line;                                                       //value for temp save a line's

        //read file and write all words in collection list
        while ((line = reader.readLine())!=null){                               //while reading line not null
            array = line.split(" ");                                     //write reading line to temp array div by words
            Collections.addAll(list, array);
        }

        //search repeat words
        List<Integer> repeat = new ArrayList<>();
        List<String> words = new ArrayList<>();
        int count = 0;                  //repeat counter

        do {
        String word = list.get(0);                      //take first word
            for (String s : list) {         //cycle for rind repeats
                if (s.equals(word)) {              //if word repeat
                    count++;                                //repeat counter increment
                }
            }
        repeat.add(count);                              //save num of repeat
        words.add(word);                                //save word
        count = 0;                                      //clean counter
        list.removeAll(Collections.singleton(word));      //remove word

        } while (list.size()!=0);

        //show result
        int max = 0;
        String text = "";
        String result;
        int index = 0;

        while (repeat.size()!=0){
        for (int i = 0; i < repeat.size(); i++) {
            if (repeat.get(i)>max){
                max = repeat.get(i);
                index = i;
                text = words.get(i);
            }
        }

        result = text + " " + max;
        max=0;
        repeat.remove(index);
        words.remove(text);

        System.out.println(result);
        }
    }
}

/*
Завдання 3
Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.

Вважаємо що:

words.txt містить лише слова в нижньому регістрі, розділені пробілом
Кожне слово містить лише літери в нижньому регістрі
Слова розділені одним або декількома пробілами, або переносом рядка
Приклад:

Для файлу words.txt із вмістом:

the day is sunny the the
the sunny is is

Метод повинен повернути результат на кшталт:

the 4
is 3
sunny 2
day 1

УВАГА
Результат виводу в консоль повинен бути відсортований за частотою (спочатку йдуть слова, що зустрічаються найчастіше)
 */

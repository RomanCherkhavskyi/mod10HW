package home.task1;

import java.io.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)  {
        String filePath = "src\\file1.txt";
        File file = new File(filePath);
        String[] lines = getLinesFromFile(file);            //write file lines in array

        //validation
        String validator = "(^[(]\\d{3}[)]\\s\\d{3}-\\d{4})|(\\d{3}-\\d{3}-\\d{4})";    //regex to validating phone numbers

        for (String line : lines) {
            if (Pattern.matches(validator, line))
                System.out.println(line);
        }
    }

    private static String[] getLinesFromFile(File file)  {
        String[] lines;          //array of lines
        int count;     //count lines
        count = countLinesInFile(String.valueOf(file));
        lines = new String[count+1];          //create array with length = count lines
        FileReader fr;     //create file read stream
        try {
            fr = new FileReader(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String s = "";      //temp value for save one line
        int symbol;         //value for save one symbol
        int i = 0;          //count array elements

        while (true){
            try {
                if (!fr.ready()) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                symbol = fr.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if ((char)symbol!='\n'){
                s = s + (char)symbol;
            } else {
                lines[i] = s.substring(0,s.length()-1);
                s = "";
                i++;
            }
        }
        lines[i]=s;
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static int countLinesInFile(String filePath)  {
        int count = 0; // count lines in file

        FileReader fr;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        int symbol;

        while (true){
            try {
                if (!fr.ready()) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                symbol = fr.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if ((char)symbol=='\n') count++;
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

}
/*
Завдання 1
Дано текстовий файл file.txt, в якому є список номерів телефонів (один рядок - один телефон).

Необхідно написати метод, який буде читати файл, і виводити в консоль всі валідні номери телефонів.

Телефон вважається валідним, якщо він відповідає одному з двох форматів (x - це одна цифра):

(xxx) xxx-xxxx
xxx-xxx-xxxx
Приклад:

Для файлу file.txt з наступним змістом:

987-123-4567
123 456 7890
(123) 456-7890

Метод повинен вивести на екран:

987-123-4567
(123) 456-7890


 */
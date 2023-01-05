package home.task2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)  {
        String readFile = "src\\file2.txt";        //path to source file
        String jsonFile = "src\\user.json";        //path to distance json file
        List<User> users = new ArrayList<>();                       //collection to save pair name-age from source file

//read and correct data from file2.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String line = reader.readLine();     //first line read - skip first line
            String[] array;
            while ((line = reader.readLine()) != null) {
                array = line.split("\s");            //read and split the line
                users.add(new User(array[0],Integer.parseInt(array[1])));    //write data in collection
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println("users.toString() = " + users.toString());       //show rebuild data in console

        //json file write
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(jsonFile),users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
Є текстовий файл file.txt. Необхідно прочитати файл, перетворити його в список об'єктів типу User, і записати їх у новий файл user.json.

Формат файлу:

перший рядок - заголовок
кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом
Приклад:

Для файлу file.txt із вмістом:

name age
alice 21
ryan 30

необхідно створити наступний файл user.json:

[
    {
        "name": "alice",
        "age":21
    },
    {
        "name": "ryan",
        "age":30
    }
]
 */
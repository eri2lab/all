package Lab3.Demo;

import Lab3.CallMe;
import Lab3.Caller;
import Lab3.SynchronizedCaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sync {
    /**
     * Вывод стихотворения в многопоточном режиме.
     */
    public static void main(String[] args) throws Exception{
        // Считываем стихотворение из файла.
        File file = new File("src/Lab3/Demo/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> strings = new ArrayList<String>();
        String st;
        while ((st = br.readLine()) != null)
            strings.add(st);

        // Создаем потоки для каждой строки.
        CallMe target = new CallMe();
        List<Caller> callers = strings
                .stream()
                .map(x -> new SynchronizedCaller(target, x))
                .collect(Collectors.toList());

        // Ожидаем завершение потока.
        for (Caller caller : callers) {
            caller.t.join();
        }
    }
}

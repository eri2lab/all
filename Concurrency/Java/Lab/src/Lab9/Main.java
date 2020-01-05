package Lab9;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Лабораторная работа №9.
 */
public class Main {
    /**
     * Контроллер уже использованных слов.
     */
    AlreadyUsedWordsController alreadyUsedWordsController = new AlreadyUsedWordsController();

    /**
     * Формирование письма с достаточным для заполнения. количеством слов.
     *
     * @param args Параметры запуска.
     * @throws Exception Неожиданное исключение.
     */
    public static void main(String[] args) throws Exception {
        demo("src/Lab9/Data/words.txt");
    }

    /**
     * Формирование письма.
     *
     * @param pathToWords Путь к словам для заполнения..
     * @throws Exception Неожиданное исключение.
     */
    public static void demo(String pathToWords) throws Exception {
        String template = prepareBaseTemplate();
        List<String> words = Files.readAllLines(Paths.get(pathToWords));

        LetterMaker letterMaker = new LetterMaker(template, words);

        System.out.println("\n\nРезультат:\n\n" + letterMaker.MakeLetter());
    }

    /**
     * Подготовить шаблон (поменять _____ на #) из исходного.
     *
     * @return Шаблон. На места % нужно вставить слова.
     * @throws Exception Неожиданное исключение.
     */
    private static String prepareBaseTemplate() throws Exception {
        String template = new String(Files.readAllBytes(Paths.get("src/Lab9/Data/input.txt")));

        StringBuilder s = new StringBuilder();
        for (int i = 0, n = template.length(); i < n; i++) {
            char c = template.charAt(i);
            if (c == '_') {
                s.append("#");
                while (template.charAt(i) == '_') {
                    i++;
                }
                i--;
            } else {
                if (s.length() > 0 && c != ' ' && s.charAt(s.length() - 1) == '#')
                    s.append(" ");
                s.append(c);
            }
        }
        return s.toString();
    }
}

package Lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Многопоточный составитель письма.
 */
class LetterMaker {
    /**
     * Число потоков.
     */
    private static final int THREADS_COUNT = 4;

    /**
     * Контроллер использования слов.
     */
    private AlreadyUsedWordsController alreadyUsedWordsController;

    /**
     * Список слов.
     */
    private List<String> words;

    /**
     * Шаблон, в который нужно вставлять слова.
     */
    private String template;

    /**
     * Конструктор.
     *
     * @param template Шаблон.
     * @param words    Слова.
     */
    LetterMaker(String template, List<String> words) {
        this.template = template;
        this.words = words;
        this.alreadyUsedWordsController = new AlreadyUsedWordsController();
    }

    /**
     * Разбить шаблон на части для разных потоков.
     *
     * @param template Шаблон.
     * @return Массив частей шаблона.
     */
    private static String[] SplitTemplate(String template) {
        int length = (int) Math.ceil((float) template.length() / (float) THREADS_COUNT);
        return IntStream
                .range(0, (template.length() + length - 1) / length)
                .mapToObj(i -> template.substring(i *= length, Math.min(i + length, template.length())))
                .toArray(String[]::new);
    }

    /**
     * Подготовить письмо по заданному шаблону с использованием заданных слов. Слова не могут повторяться.
     *
     * @return Письмо.
     */
    String MakeLetter() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

        List<Future<String>> futures = new ArrayList<Future<String>>();

        String[] parts = SplitTemplate(template);
        for (int i = 0; i < THREADS_COUNT; i++) {
            Callable<String> filler = new LetterPartFiller(parts[i], words, alreadyUsedWordsController);
            futures.add(executor.submit(filler));
        }
        executor.shutdown();
        StringBuilder result = new StringBuilder();
        for (Future future : futures) {
            try {
                result.append(future.get());
            } catch (InterruptedException | ExecutionException e) {
                return e.getMessage();
            }
        }
        return result.toString();
    }
}


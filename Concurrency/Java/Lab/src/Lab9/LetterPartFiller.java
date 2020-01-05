package Lab9;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Наполнитель части шаблона словами.
 */
class LetterPartFiller implements Callable<String> {
    /**
     * Часть шаблона.
     */
    private String letterPart;

    /**
     * Слова.
     */
    private List<String> words;

    /**
     * Контроллер использования слов.
     */
    private AlreadyUsedWordsController alreadyUsedWordsController;

    /**
     * Конструктор.
     *
     * @param letterPart                 Часть шаблона.
     * @param words                      Слова.
     * @param alreadyUsedWordsController Котнроллер использования слов.
     */
    LetterPartFiller(String letterPart, List<String> words,
                     AlreadyUsedWordsController alreadyUsedWordsController) {

        this.letterPart = letterPart;
        this.words = words;
        this.alreadyUsedWordsController = alreadyUsedWordsController;
    }

    /**
     * Непосредственное формирование результата.
     *
     * @return Заполненная часть шаблона.
     * @throws Exception Исключение.
     */
    @Override
    public String call() throws Exception {
        StringBuilder result = new StringBuilder();
        for (char c : letterPart.toCharArray()) {
            if (c != '#') {
                result.append(c);
                continue;
            }

            String word = getRandomWord();
            while (alreadyUsedWordsController.wasUsed(word)) {
                int delta = words.size() - alreadyUsedWordsController.getUsedWordsCount();
                if (delta == 0)
                    throw new OutOfWordsException();
                System.out.printf(
                        "%s: Слово %s уже было использовано, но осталось еще %d слов. Ищу новое слово (сон 5с).\n",
                        Thread.currentThread().getName(), word, delta);
                Thread.sleep(5000);
                word = getRandomWord();
            }
            System.out.printf("%s: Использую слово %s.\n", Thread.currentThread().getName(), word);
            result.append(word);
        }
        return result.toString();
    }

    /**
     * Получить случайное слово из списка слов.
     *
     * @return Случайное слово.
     */
    private String getRandomWord() {
        int index = ThreadLocalRandom.current().nextInt(words.size());
        return words.get(index);
    }
}

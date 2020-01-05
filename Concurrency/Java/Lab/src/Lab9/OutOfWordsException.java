package Lab9;

import java.util.concurrent.ExecutionException;

/**
 * Исключение, говорящее о том, что закончились слова в списке слов.
 */
public class OutOfWordsException extends ExecutionException {
    /**
     * @return Сообщение исключения.
     */
    @Override
    public String getMessage() {
        return "Произошла ошибка! Закончились слова в списке.";
    }
}

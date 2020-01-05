package Lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Контроллер использования слов.
 */
class AlreadyUsedWordsController {
    /**
     * Слова, которые уже использовались.
     */
    private List<String> used = new ArrayList<>();

    /**
     * Замок, чтобы чтение и запись не осуществлялись одновременно.
     */
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * Проверить, использовалось ли ранее указанное слово.
     *
     * @param string Слово.
     * @return Флаг использованности.
     */
    boolean wasUsed(String string) {
        rwLock.writeLock().lock();
        try {
            if (used.contains(string))
                return true;
            used.add(string);
            return false;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * Получить количество использованных слов.
     *
     * @return Количество использованных слов.
     */
    int getUsedWordsCount() {
        try {
            rwLock.readLock().lock();
            return used.size();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

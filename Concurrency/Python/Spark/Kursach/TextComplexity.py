# coding=utf-8
import csv


class TextComplexity:
    def __init__(self):
        pass

    text_name = None
    author = None
    href = None
    type = None

    Flesch_index = None

    percent_of_unique_words = None
    avg_term_frequency = None

    nouns_percent = None
    verbs_percent = None
    adjectives_percent = None

    avg_paragraph_len_in_letters = None
    avg_paragraph_len_in_words = None
    avg_paragraph_len_in_sentences = None
    avg_sentence_len_in_letters = None
    avg_sentence_len_in_words = None
    avg_word_len = None

    def get_values(self):
        return [self.text_name,
                self.author,
                self.type,
                '%.2f' % self.Flesch_index,
                '%.2f' % self.percent_of_unique_words,
                '%.2f' % self.avg_term_frequency,
                '%.2f' % self.nouns_percent,
                '%.2f' % self.verbs_percent,
                '%.2f' % self.adjectives_percent,
                '%.2f' % self.avg_paragraph_len_in_sentences,
                '%.2f' % self.avg_paragraph_len_in_words,
                '%.2f' % self.avg_paragraph_len_in_letters,
                '%.2f' % self.avg_sentence_len_in_words,
                '%.2f' % self.avg_sentence_len_in_letters,
                '%.2f' % self.avg_word_len,
                self.href]

    @staticmethod
    def get_names():
        return ['Наименование текста',
                'Автор',
                'Тип текста',
                'Индекс Флеша',
                'Процент уникальных слов',
                'Средняя частота повторения слова',
                'Процент существительных',
                'Процент глаголов',
                'Процент прилагательных',
                'Средняя длина абзаца в предложениях',
                'Средняя длина абзаца в словах',
                'Средняя длина абзаца в буквах',
                'Средняя длина предложения в словах',
                'Средняя длина предложения в буквах',
                'Средняя длина слова',
                'Ссылка на интернет-ресурс']

    @staticmethod
    def save_to_csv(path, list):
        with open(path, 'wb', ) as csvfile:
            writer = csv.writer(csvfile)
            writer.writerow(TextComplexity.get_names())
            for item in list:
                writer.writerow(item.get_values())

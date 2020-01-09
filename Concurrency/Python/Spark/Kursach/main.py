# coding=utf-8
import nltk
import syllables
from nltk.tag import pos_tag, map_tag

import string
from pyspark.context import SparkContext
from nltk.tokenize import word_tokenize
from nltk.tokenize import sent_tokenize
from nltk.corpus import stopwords

from TextComplexity import TextComplexity

nltk.download("punkt")
nltk.download("stopwords")
nltk.download('averaged_perceptron_tagger')
nltk.download('wordnet')
nltk.download('universal_tagset')

sc = SparkContext()
stopwords = set(stopwords.words("english"))
punctuation = list(string.punctuation)


def main():
    # http://dostoevskiy-lit.ru/dostoevskiy/foreign/crime-and-punishment/1-chapter-one.htm
    crime_and_punishment_model = get_text_complexity(sc.textFile("data/crime_and_punishment.txt"))
    crime_and_punishment_model.text_name = "Crime and Punishment"
    crime_and_punishment_model.type = "Literature"
    crime_and_punishment_model.author = "F. Dostoevskiy"
    crime_and_punishment_model.href = "http://dostoevskiy-lit.ru/dostoevskiy/foreign/crime-and-punishment/1-chapter-one.htm"

    # http://dostoevskiy-lit.ru/dostoevskiy/foreign/crime-and-punishment/1-chapter-one.htm
    alice_in_wonderland_model = get_text_complexity(sc.textFile("data/alice_in_wonderland.txt"))
    alice_in_wonderland_model.text_name = "Alice's Adventures in Wonderland"
    alice_in_wonderland_model.type = "Literature"
    alice_in_wonderland_model.author = "L. Carroll"
    alice_in_wonderland_model.href = "https://www.gutenberg.org/files/11/11-h/11-h.htm"

    bbc_nasa_moon_rocket_model = get_text_complexity(sc.textFile("data/bbc_nasa_moon_rocket.txt"))
    bbc_nasa_moon_rocket_model.text_name = "Nasa Moon rocket core leaves for testing"
    bbc_nasa_moon_rocket_model.type = "News"
    bbc_nasa_moon_rocket_model.author = "P. Rincon"
    bbc_nasa_moon_rocket_model.href = "https://www.bbc.com/news/science-environment-51048986"

    time_is_one_of_the_great_mysteries = get_text_complexity(sc.textFile("data/time_is_one_of_the_great_mysteries.txt"))
    time_is_one_of_the_great_mysteries.text_name = "Time Is One of the Great Mysteries"
    time_is_one_of_the_great_mysteries.type = "Learning English podcast"
    time_is_one_of_the_great_mysteries.author = "J. Simms"
    time_is_one_of_the_great_mysteries.href = "https://learningenglish.voanews.com/a/time-clock-calendar/1819679.html"

    TextComplexity.save_to_csv("data/output.csv", [crime_and_punishment_model,
                                                   bbc_nasa_moon_rocket_model,
                                                   alice_in_wonderland_model,
                                                   time_is_one_of_the_great_mysteries])


def get_text_complexity(text):
    model = TextComplexity()

    paragraphs = text \
        .filter(not_linebreak) \
        .map(lambda str: str.lower())

    model.avg_paragraph_len_in_letters = avg_len_in_letters(paragraphs)
    model.avg_paragraph_len_in_words = avg_len_in_words(paragraphs)
    model.avg_paragraph_len_in_sentences = avg_len_in_sentences(paragraphs)

    sentences = paragraphs.flatMap(sent_tokenize)

    model.avg_sentence_len_in_letters = avg_len_in_letters(sentences)
    model.avg_sentence_len_in_words = avg_len_in_words(sentences)

    words = sentences \
        .flatMap(word_tokenize) \
        .filter(lambda word: word not in string.punctuation and word != "...")

    model.avg_word_len = avg_len_in_letters(words)
    model.percent_of_unique_words = percent_of_unique_words(words)
    model.avg_term_frequency = avg_term_frequency(words)

    parts_of_speech = part_of_speech_percent(words)
    model.nouns_percent = parts_of_speech["NOUN"]
    model.verbs_percent = parts_of_speech["VERB"]
    model.adverbs_percent = parts_of_speech["ADV"]
    model.adjectives_percent = parts_of_speech["ADJ"]

    model.Flesch_index = calculateFRE(sentences, words)
    return model


def calculateFRE(sentences, words):
    words_count = words.count()
    syllables_count = words.map(syllables.estimate) \
        .reduce(lambda a, x: a + x)
    return 206.835 - 1.015 * float(words_count) / sentences.count() - 84.6 * float(syllables_count) / words_count


def part_of_speech_percent(words):
    count = words.count()
    result = words \
        .map(part_of_speech) \
        .countByValue()
    for key, val in result.items():
        result[key] = float(val) / count
    return result


def part_of_speech(text):
    posTagged = pos_tag(text)
    simplifiedTags = [(word, map_tag('en-ptb', 'universal', tag)) for word, tag in posTagged]
    return simplifiedTags[0][1]


def percent_of_unique_words(words):
    unique_count = words.distinct().count()
    total_count = words.count()
    return float(unique_count) / total_count


def avg_term_frequency(words):
    term_frequency = words.countByValue()
    return float(reduce(sum, term_frequency.values())) / term_frequency.__len__()


def avg_len(text_array, transform_func):
    lengths = text_array \
        .map(transform_func) \
        .map(lambda x: x.__len__()) \
        .collect()

    return float(reduce(sum, lengths)) \
           / lengths.__len__()


def avg_len_in_letters(text_array):
    return avg_len(text_array, remove_non_letters)


def avg_len_in_words(text_array):
    return avg_len(text_array, word_tokenize)


def avg_len_in_sentences(text_array):
    return avg_len(text_array, sent_tokenize)


def not_linebreak(string):
    return string != "\n" and string != "\r\n" and string != ""


def remove_non_letters(str):
    return filter(lambda x: x in string.ascii_letters, str)


def sum(a, b):
    return a + b


if __name__ == "__main__":
    main()

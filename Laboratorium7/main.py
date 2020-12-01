import functools
import re
import pathlib
import string
from collections import Counter, defaultdict

x = [1, 2, 3, 4]


def increment(iterable):
    for i in range(len(iterable)):
        iterable[i] += 1


def increment2(iterable):
    return [x + 1 for x in iterable]


def product(numbers):
    return functools.reduce(lambda x, y: x * y, numbers)


def isPalindrome(s):
    return s == s[::-1]


def palindrome(text):
    word = list(filter(lambda c: c in string.ascii_letters, text))
    word = "".join(word)
    if isPalindrome(word.lower()):
        print("Yes")
    else:
        print("No")


def tokenize(text):
    return re.findall(r"[\w']+", text)


def tokenize2(text):
    return re.split(r'\W+', text)


def remove_stop_words(text):
    with open(r'./pl.stopwords.txt', 'r',
              encoding='UTF-8') as input_file:
        stopWords = tokenize(input_file.read())
        return list(filter(lambda word: word not in stopWords and len(word) > 2, tokenize(text)))


def read_file(file_name):
    with open(rf'./{file_name}', 'r',
              encoding='UTF-8') as inputFile:
        text = inputFile.read()
        return text


def count_most_frequent(text, n):
    new_text = remove_stop_words(text)
    counter_list = Counter(new_text)
    sorted_list = list(sorted(counter_list.items(), key=lambda item: item[1]))
    return sorted_list[-n:]


def is_anagram(str1, str2):
    str1_list = list(str1)
    str1_list.sort()
    str2_list = list(str2)
    str2_list.sort()

    return str1_list == str2_list


def check_anagram(text):
    token_text = tokenize(text)
    token_text = list(filter(lambda word: len(word) > 10, token_text))
    d = defaultdict(list)
    duplicates = []
    for x in token_text:
        for y in token_text:
            if x == y or x in duplicates: continue
            if is_anagram(x, y):
                d[x].append(y)
                duplicates.append(y)

    return d


if __name__ == '__main__':
    # Task 1
    increment2(x)
    print(x)

    # Task 2
    # y = product(x)
    # print(y)

    # Task 3
    # palindrome('Tolo ma samolot')

    # Task 4
    # tokenList = tokenize1("To be, or not to be - that is the question [...]")
    # print(tokenList)

    # Task 5
    # rlist = remove_stop_words(readFile("trurl.txt"))
    # print(rlist)

    # Task 6
    # count = count_most_frequent(readFile("trurl.txt"), 20)
    # print(count)

    # Task 7
    # sortedList = checkAnagram(readFile("unixdict.txt"))
    # print(sortedList)
    # showAnagram(sortedList, 10)

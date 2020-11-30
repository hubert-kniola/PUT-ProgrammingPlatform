import functools
import re
import pathlib

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


def filterFoo(letter):
    elements = range(1, 10)

    if letter in elements:
        return True
    else:
        return False


def palindrome(text):
    x = next(filter(text.isalpha(), text))
    yon = isPalindrome(x.lower())
    if yon:
        print("Yes")
    else:
        print("No")


def tokenize(text):
    return [re.findall(r"[\w']+", text)]


def tokenize2(text):
    return re.split('\W+', text)


# def remove_stop_words(text):
#    with open('C:\\Users\\hubkn\\AppData\\Local\\Temp\\PPLab6.py', encoding='UTF-8') as inputFile:
#        stopWords = tokenize(inputFile.read())
#            for x in range(len(text)):
#                if(text[x] == stopWords)

def isAnagram(str1, str2):
    str1_list = list(str1)
    str1_list.sort()
    str2_list = list(str2)
    str2_list.sort()

    return (str1_list == str2_list)

def readFile(fileName):
    with open('C:\\Users\\hubkn\\AppData\\Local\\Temp\\PPLab6\\' + fileName + '.txt', encoding='UTF-8') as inputFile:
        text = inputFile.read()
        return text

def checkAnagram(text):
    tokenText = tokenize(text)
    l = list()
    for x in range(len(tokenText)):
        for y in range(len(tokenText)):
            if isAnagram(tokenText[x], tokenText[y]):
                l.append(tokenText[x])

    return l

def showAnagram(l, number):
    sortedList = list(sorted(l, key=len))
    for x in reversed(sortedList):
        print(sortedList[x])


if __name__ == '__main__':
    # Task 1
    # increment(x)
    # print(x)
    # increment2(x)
    # print(x)

    # Task 2
    # y = product(x)
    # print(y)

    # Task 3
     palindrome('Tolo ma samolot')

    # Task 4
    # tokenList = tokenize1("To be, or not to be - that is the question [...]")
    # print(tokenList)

    # Task 5

    # Task 6

    # Task 7
    #text = readFile('unixdict')
    #sortedList = checkAnagram(text)
    #showAnagram(sortedList, 10)



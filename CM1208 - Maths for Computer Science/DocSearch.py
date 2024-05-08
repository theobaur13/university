import numpy as py
import math

def main():
    dict = buildIndex()
    queries = getQueries()
    print ("Words in dictionary:", len(dict))
    #print (dict)
    for query in queries:
        print ("Query:", query)
        list_of_document_IDs = ' '.join(map(str,relevantDocs(dict,query)))
        print ("Relevant documents:", list_of_document_IDs)
        for angle in angleCalc(dict,query):
            print (angle)

def getQueries():
    queries = []
    with open("queries.txt") as file:
        for line in file:
            queries.append(line.rstrip("\n"))
    return queries

def getDocs():
    docs = []
    with open("docs.txt") as file:
        for line in file:
            docs.append(line.rstrip("\n").replace("\t"," "))
    return docs

def buildIndex():
    dict = {}
    docs = getDocs()
    i = 1
    for line in docs:
        for word in line.split():
            if word in dict:
                dict[word].append(i)
            else:
                dict[word] = [i]
        i += 1
    return dict

def relevantDocs(dict, queries):
    relevantDoc = []
    listOfValues = []
    for query in queries.split(" "):
        if query in dict:
            listOfValues.append(removeDupes(dict[query]))
    relevantDoc = matches(listOfValues)
    return relevantDoc

def matches(lists):
    return list(set.intersection(*map(set, lists)))

def removeDupes(list):
    newList = []
    for ch in list:
        if ch not in newList:
            newList.append(ch)
    return newList

def angleCalc(dict, queries):
    angles = []
    queryArray = queryVector(dict,queries)
    for docNo in relevantDocs(dict, queries):
        docArray = docVector(dict, docNo)
        dotProduct = py.dot(queryArray,docArray)
        queryNorm = py.linalg.norm(queryArray)
        docNorm = py.linalg.norm(docArray)
        rad = math.acos(dotProduct/(queryNorm*docNorm))
        deg = math.degrees(rad)
        string = str("{:.2f}".format(round(deg, 2))) + " " + str(docNo)
        angles.append(string)
    angles.sort()
    x = 0
    for i in angles:
        string = i.split(" ")[1] + " " + i.split(" ")[0]
        angles[x] = string
        x+=1
    return angles

def docVector(dict, docNo):
    toArray = []
    for word in dict:
        if docNo in dict[word]:
            i = 0
            for element in dict[word]:
                if element == docNo:
                    i+=1
            toArray.append(i)
        else:
            toArray.append(0)
    x = py.array(toArray)
    return x

def queryVector(dict,queries):
    toArray = [0] * len(dict)
    for query in queries.split(" "):
        i = -1
        for word in dict:
            i += 1
            if word == query:
                toArray[i] = 1
    x = py.array(toArray)
    return x

main()


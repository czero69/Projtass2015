import csv
from decimal import Decimal

files = ['2014', '2013', '2012', '2011', '2010', '2009']
magDict = dict()
for file in files:
	csvfile = open(file+'_IEEE.csv', 'rb')
	fileReader = csv.reader(csvfile, delimiter=',')
	for row in fileReader:
		if(row[0] in magDict):
			magDict[row[0]]=(Decimal(magDict[row[0]][0])+Decimal(row[1]),magDict[row[0]][1]+1)
		else:
			magDict[row[0]]=(Decimal(row[1]),1)
	print magDict['IEEE TRANSACTIONS ON EVOLUTIONARY COMPUTATION']
	csvfile.close()

outCsvFile = open('ave_IEEE.csv', 'wb')
fileWriter = csv.writer(outCsvFile, delimiter=',')
for key, value in magDict.iteritems():
	average = "{0:.3f}".format(value[0]/value[1])
	fileWriter.writerow([key,average])
outCsvFile.close()

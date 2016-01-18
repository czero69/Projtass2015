import csv
files = ['2014', '2013', '2012', '2011', '2010', '2009']
for file in files:
	csvfile = open(file+'.csv', 'rb')
	fileReader = csv.reader(csvfile, delimiter=',')
	csvFileWrite = open(file+'_IEEE.csv', 'wb')
	fileWriter = csv.writer(csvFileWrite, delimiter=',')
	for row in fileReader:
		if(row[0].startswith('IEEE')):
			fileWriter.writerow(row)
	csvfile.close()
	csvFileWrite.close()

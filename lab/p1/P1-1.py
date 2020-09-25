from math import* 
import sys
import csv

def DistanciaEuclidiana(A, B):
    suma=0
    for i in range(len(A)):
        suma=suma + pow(A[i]-B[i],2)
    return sqrt(suma) 

results = []
with open("DatosLEC.csv") as csvfile:
    reader = csv.reader(csvfile, quoting=csv.QUOTE_NONNUMERIC) # change contents to floats
    for row in reader: # each row is a list
        results.append(row) 
              
#0= buenos 1= equix 2=malardos
centroides=[[4.7,63,8.5],[3.4,-44,9.1],[0.9,-427,8.5]]

grupos=[]

for i in range(len(results)):
    distanciaMasCercana = 100000000
    centroideMasCercano = 0
    for j in range(len(centroides)):
        distancia = DistanciaEuclidiana(results[i],centroides[j])
        if distancia < distanciaMasCercana:
            distanciaMasCercana = distancia
            centroideMasCercano = j
    grupos.append(centroideMasCercano)    

print(results[7][1])
print(grupos)
print(centroides)

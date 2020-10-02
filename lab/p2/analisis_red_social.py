import sys

m=0
grados=[]

try:
	archivo=open(sys.argv[1],'r')
	lineas=archivo.readlines()
	archivo.close()
except IOError:
	print('No se pudo abrir el archivo')
	sys.exit(1)
	
n=len(lineas)

for linea in lineas:
	linea=linea.strip()
	celdas=linea.split()
	grado=0
	for celda in celdas:
		m=m + int(celda)
		grado=grado + int(celda)
	grados.append(grado)
		
m=m/2

densidad=2*m/(n*(n-1))

print('n: ',n)
print('m: ',m)
print('Densidad:', densidad)

for i in range(len(grados)):
	centralidad=grados[i]/(n-1)
	print("Grado v"+str(i)+"="+str(grados[i])+ " Centralidad: "+str(centralidad))
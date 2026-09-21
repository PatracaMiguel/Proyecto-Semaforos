PROYECTO INTEGRADOR (ENTREGA 1) 

Módulo 1: Inicialización y Datos del Escenario 
• El equipo debe programar a la medida los datos de entrada de su problema (coordenadas, 
nodos, matriz de costos o elementos a optimizar según su investigación). 
• Al seleccionar esta opción, el sistema debe cargar los datos base e imprimir una breve 
descripción del escenario del mundo real que están simulando y qué representa su función 
de costo. 

Módulo 2: Búsqueda Local (Hill Climbing) 
• El programa genera un estado inicial (a partir de los datos del Módulo 1) y comienza a 
evaluar vecinos. 
• Condición estricta: Solo se mueve si el vecino representa una mejora directa en la función 
de costo. 
• Debe imprimir en consola los pasos y mostrar obligatoriamente el punto exacto y el costo 
donde se queda atorado (óptimo local). El sistema debe almacenar este costo final en una 
variable. 

Módulo 3: Búsqueda Estocástica (Simulated Annealing) 
• Parte de la misma solución inicial, incorporando variables de temperatura inicial y una tasa 
de enfriamiento. 
• Debe implementar obligatoriamente la probabilidad de aceptación de Boltzmann 
(Math.exp(deltaF / temperatura) combinada con un número aleatorio) para permitir 
aceptar peores soluciones al inicio. 
• Debe mostrar en consola cómo logra salir de los baches hasta llegar a la solución óptima 
global. Al igual que el anterior, almacena su costo final. 

Módulo 4: Comparación de Resultados 
Lee los datos guardados en las ejecuciones previas (Módulos 2 y 3). 
Muestra un resumen o tabla comparativa en consola con los siguientes datos: 
• Costo final obtenido por Hill Climbing (evidenciando su estancamiento). 
• Costo final obtenido por Simulated Annealing (mostrando la mejora lograda). 
• Número de iteraciones o pasos que le tomó a cada algoritmo.
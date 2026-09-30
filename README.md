# ArenaLegends

Partie 1 :
On utilise un do afin de mettre dans le while uniquement la condition de sortie.

Partie 2:
Lorsqu'on copie "int[] b = a;" l'objet a devient b et inversement. C'est-à-dire que lorsqu'on va modifier a ou b l'autre le sera également.
int[] a = {1,2,3,4}
int[] b = a
b[1] = 5 //a[1] devient également 5

Partie 3 : 
le seter permet de passer au dessus des règles, ça protéège certe des bornes définit mais pas des règles style un combattant KO ne peut pas etre soigné.

Partie 4:
On met le proctected afin que les class filles (ici Mage) puisse avoir accès a la méthode. Si on avait public n'importe qui aurait eu accès et private seulement Combattant.

# Outils & techniques de développement - Projet final
| Année     | Auteur           | Version                   |
| --------- | ---------------- | ------------------------- |
| 2020/2021 | Thomas Domingues | tdomingues@myges.fr 1.0.0 |
***

## Objectifs
Le but de ce projet est de faire un jeu vidéo à la croisée entre Tropico et Reigns. Vous incarnerez
un jeune dictateur en herbe sur une île tropicale, fraîchement
élu comme Président. Vous aurez la lourde tâche de faire prospérer cette nouvelle mini-
république.

# Introduction

Note technique rédigé dans le cadre du projet pédagogique d'Outils et techniques de development.

Cette note a pour but de détailler notre architecture, nos choix d’implémentation et décrire nos algorithmes & design patterns

# Architecture

## Packages

Pour ce projet nous avons adopté l'architecture suivante pour nos packages JAVA :

![arborescence](doc_files/arborescence.png "arborescence")

Nous avons donc décidé de regrouper nos classes dans différents packages, ce qui nous permet de rendre certaines classes visibles dans le package uniquement et d'autres publiques pour réduire les dépendances chaotiques.

## Classes

Concernant l'architecture des classes, nous avons rapidement identifié des systèmes qui devraient êtres gérés de manière indépendante à des endroits différents du code.

Par exemple pour l'argent, l'industrie pourra en ajouter au même titre que les évènements, nous sommes donc partie sur un système de controller afin de pouvoir centraliser les interactions entre les classes.

# Design Patterns

Pour ce projet nous allons détailler les design patterns que nous avons retenus en expliquant pourquoi et comment nous les avons mis en places.

## Singleton

Le singleton est un design pattern qui permet de s'assurer qu'il n'existe qu'une seule et unique instance d'une classe précise.

Dans notre cas, nous avons retenu ce design pattern pour le fonctionnement des classes `DifficultyController`, `EventController`, `FactionController`, `ConsumableController` et `ResourceController`.

L'avantage du singleton dans ce cas est de s'assurer que nous appellerons toujours la même instance de la classe et ce depuis n'importe quel endroit du code.

Cela nous évite de passer les bonnes instances des classes en paramètres via des setters/getters ou autre, déchargeant ainsi le reste du code et le rendant moins complexe.

Cependant, les Singletons ne doivent pas êtres utilisés de manière abusive sinon ils témoignent d'une mauvaise architecture, nous avons donc limité leur utilisation à des controllers qui vont servir de point de passage pour le traitement d'informations.

Ces controllers étant appelés à différents endroits du code, il est donc important de toujours récupérer la même instance de ces derniers, ce qui explique notre choix du Singleton.

## Contrôleur (Mediator)

Le Contrôleur est un design pattern qui a pour but de centraliser le traitement des informations, afin d'éviter que les dépendances chaotiques entre les objets.

Dans notre cas, nous avons retenu ce design pattern pour le fonctionnement des classes `DifficultyController`, `EndOfYearController`, `EventController`, `FactionController`, `ConsumableController`, `ResourceController` et `TurnController`.

D'un point de vue fonctionnel, cela nous a permis de réduire les dépendances dans notre code en passant par un controller plutôt que par les classes directement.

En couplant certains controller au design pattern Singleton, nous nous assurons d'avoir toujours la bonne instance de notre controller et ce n'importe où dans notre code.

Nous avons aussi profité de ce design pattern pour réduire la visibilité de certaines classes en ne les rendant visibles que par le controller, les protégeant ainsi de toute modification externe au package sans appel du controller.

## Monteur (Builder)

Le Monteur est un design pattern qui vas nous permettre de construire des objets étapes par étapes, ce qui permet d'initialiser plus tôt nos classes si nécessaire et réduire les dépendances de Constructeurs.

Dans notre cas nous utilisons le Monteur dans les classes `GameBuilder`, `LoadFromSaveBuilder` et `TurnBuilder`.

Pour `GameBuilder` et `TurnBuilder`, cela nous permet de construire avant de savoir ce que l'utilisateur veux faire ou d'exécuter des actions supplémentaires dans le code.

Pour `LoadFromSaveBuilder`, cette classe est implémentée dans tous les objets qui sont rechargés après une sauvegarde, nous pouvons donc les créer avant de les recharger et ensuite les traiter pour leur assigner une valeur ou non si l'utilisateur décide de créer une nouvelle partie.


# Répartition des partisans

Pour le bonus la répartition des partisans on a décidé d'utiliser un Singleton pour créer un Controller et ainsi pouvoir appeler la classe sans l'instancier au début du jeu et ainsi pouvoir tout au long du jeu avoir le même algorithme. En effet grace à l'interface SupportersDistribution on peut rajouter des algorithmes de répartition très facilement e, créant une classe implémentant SupportersDistribution puis en ajoutant dans SupportersDistributionController l'instanciation de notre nouvel sorte d'algorithme en fonction de la valeur dans le json de supporters_distribution.
Pour l'algorithme equilibrate on va distribuer les partisans en fonction du pourcentage de satisfaction de chaque factions et de son rapport avec le pourcentage de satisfaction global. Une faction ne contenant que 10 Partisans ne pourra avoir plus de 5 nouveaux partisans à la fin de l'année, une faction ayant 0 % de satisfaction ne pourra avoir de nouveaux partisans. Les factions ayant le plus de satisfaction seront les premières factions ayant de nouveaux partisans car plus heureuses.

# Pour tester la défaite

Vous pouvez tout simplement lors de l'événement ci-dessous
> Mort de Staline. La population se demande comment se place El-presidente face à ce (tragique ?) évènement.",


Choisir l'option ci-dessous qui appliquera assez de malus pour faire perdre el-presidente à la fin du tour.
> El-presidente n'est pas fait pour ce job, le suicide est la seule option (Vous perdez la partie voir le README)",

# Execution

Pour exécuter notre programme, il faut lancer se placer dans le meme repertoire que le `.jar` et lancer la commande suivante :

```shell
java -jar El-Presidente-1.0.0.jar
```

# Liens utiles

[le github du projet](https://github.com/JackAuxLanternes/El-Presidente/tree/master)
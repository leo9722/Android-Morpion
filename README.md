# Android-Morpion

Application réaliser en JAVA, sous Android Studio

## Introduction

L'objectif de ce projet est de programmer le jeu du Morpion sous réseau. La partie réseau doit être gérée en utilisant la plateforme Firebase avec l’utilisation de real time database.
De même, un accès à la ressource firebase sera demandé en utilisant l’authentification par téléphone.

## Le Projet

## Diagramme de Gantt

Ce projet se déroulant sur un peu plus de deux semaines nous nous sommes interrogés sur comment articuler ce projet et l’élaborer de manière efficace.
Ainsi le diagramme de Gantt ci-dessous illuistre la répartition du travail et l’élaboration de ce projet.

image

## Authentification

A la suite de l’élaboration de notre diagramme de Gantt, nous avons commencé le projet par la phase d’authentification et de prise en main de firebase.

### Firebase

Côté Firebase,  nous avons appliqué le tutoriel pour joindre notre projet à firebase pour avoir notre projet Morpion. L’authentification demandée étant une authentification par SMS, nous avons alors alloué ce service, créé notre real time database  et écrit des règles spécifiques d’utilisation de notre bdd.

### JAVA 

Pour la partie Jav nous avons pensé qu’il serait utile d’ajouter un Textfield pour entrer le numéro, un bouton pour l’authentification, un Textfield pour le code reçu, un bouton pour la vérification d'authentification et un bouton “Resend” si l’utilisateur en a besoin. Les méthodes utilisées sont celles fournis par firebase pour l’authentification.
nous avons rajouté une méthode permettant à l’utilisateur ( si il est connecté d’avoir cette information et de ne pas devoir s’authentifier à nouveau )
Enfin, si l'authentification est correcte, on accède à notre interface de multi users.

image


## Multi Players

Pour cette phase, nous nous sommes interrogés sur la manière la plus facile  pour un joueur de jouer avec un autre joueur.

En lisant plusieurs Forums, la solution était de permettre à un joueur de créer des “rooms” ou de rejoindre une “room” et ainsi de donner un accès à un autre utilisateur pour le rejoindre. 

Nous avions donc besoin de demander à l’utilisateur un username, puis de le renvoyer vers une interface où il pourrait choisir de rejoindre ou de créer une room.

Ainsi un Textfield, un bouton et une liste fait l’affaire.
Pour le code il fallait donc insérer au niveau de la base de donnée le nom du joueur ainsi que la room créer avec le nom du joueur associé. Puis il fallait ajouter en temps réel les rooms créer s' il y en avait. Enfin il fallait rediriger l’utilisateur vers le jeu. Lors de la création de cette partie, une problématique évidente  est apparue, l’utilisateur doit pouvoir supprimer une room si il le veut.
Ainsi la création d’une alerte fut la solution à ce problème.

image 

## Le Jeu

Enfin il s'agit d'un Tic tac Toe des plus simples, où deux joueurs s'affrontent pour remporter la victoire

image







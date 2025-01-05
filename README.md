# Projet Climax - Application de Rapports Statistiques

## Description du projet

Le projet a pour but de développer une application JavaFX permettant d'effectuer des rapports statistiques sur les clients de la société Climax. Les données des clients incluent l'identité, le niveau de revenu et la profession. L'application doit pouvoir lire plusieurs formats de fichiers (CSV, XML, JSON) et calculer la moyenne des salaires par profession.

## Fonctionnalités

- **Importation des fichiers** : L'application permet la lecture des fichiers au format CSV, XML et JSON,DOCX, PDF,TXT,XLSX,XLS.
- **Calcul des statistiques** : L'application calcule la moyenne des salaires par profession.
- **Interface utilisateur** : Une interface graphique simple et intuitive réalisée avec JavaFX.
- **Extensibilité** : Le code est conçu pour être modulaire, facilitant l'ajout de nouveaux formats de fichiers ou d'autres fonctionnalités.

## Technologies utilisées

- **Java 22 ou supérieur**
- **JavaFX** pour l'interface graphique
- **Maven** pour la gestion des dépendances

## Prérequis

Avant de pouvoir exécuter l'application, assurez-vous d'avoir installé :

- **JDK 17 ou supérieur** (téléchargeable [ici](https://adoptopenjdk.net/))
- **Maven** (téléchargeable [ici](https://maven.apache.org/download.cgi))


## Installation

### 1. Cloner le dépôt

Clonez ce projet avec la commande suivante :


git clone https://github.com/votre-utilisateur/climax-project.git
cd climax-project



### Explication des ajouts :

- **Installation des dépendances** : La section "Installer les dépendances" a été ajoutée pour expliquer comment utiliser Maven pour installer les dépendances nécessaires.
- **Démarrage** : Les sections de démarrage avec Maven et Docker ont été précisées. La commande `mvn clean install` est utilisée pour télécharger les dépendances, tandis que `mvn javafx:run` démarre l'application localement. Le Dockerfile permet de construire et d'exécuter l'application dans un conteneur Docker, facilitant ainsi le déploiement.

# TP Java RMI : Application Client-Serveur de Conversion

## 📋 Description
Ce projet est une mise en œuvre pratique du protocole **Java RMI (Remote Method Invocation)**. 
Il consiste en une application distribuée où un **Client** demande la conversion d'un montant financier à un **Serveur** distant. Le serveur exécute la logique métier et renvoie le résultat au client de manière transparente.

## 🛠️ Architecture
Le projet est divisé en deux modules principaux (ou projets Java distincts) :

1.  **RMIConversion_Server** (Côté Serveur) :
    *   Définit l'interface distante (`IConversion`).
    *   Implémente le service de conversion (`ConversionImpl`).
    *   Héberge le serveur qui lance le registre RMI et publie l'objet.
    
2.  **RMIConversion_Client** (Côté Client) :
    *   Utilise une copie de l'interface (via un fichier `.jar`).
    *   Recherche l'objet distant dans l'annuaire.
    *   Exécute la méthode de conversion à distance.

## ⚙️ Prérequis
*   **Java JDK** (version 8 ou supérieure).
*   Un IDE (Eclipse, IntelliJ IDEA, ou NetBeans).
*   Port **1099** libre sur la machine (port par défaut du RMI Registry).

## 🚀 Installation et Configuration

### 1. Configuration du Serveur
*   Ouvrir le projet `RMIConversion_Server`.
*   S'assurer que la classe `ConversionImpl` hérite bien de `UnicastRemoteObject`.
*   Compiler le projet.

### 2. Génération de la librairie commune (Stub)
Pour que le client connaisse les méthodes disponibles sans avoir accès au code source du serveur :
1.  Depuis le projet Serveur, exporter uniquement le package `rmiService` (contenant `IConversion.java`).
2.  Générer un fichier **`conversionClient.jar`**.
3.  **Attention :** Ne pas inclure `ConversionImpl.java` dans ce JAR.

### 3. Configuration du Client
*   Ouvrir le projet `RMIConversion_Client`.
*   Ajouter le fichier `conversionClient.jar` au **Build Path** (Classpath) du projet.
*   Ceci permet l'importation de `rmiService.IConversion`.

## ▶️ Comment exécuter le projet

**Étape 1 : Lancer le Serveur**
Exécutez la classe `rmiServer.ConversionServer` (Run as Java Application).
> *Console attendue :*
> ```text
> Serveur : Annuaire RMI démarré sur le port 1099.
> Serveur : Instanciation de l'objet distant.
> Serveur : Objet publié. En attente de clients...
> ```

**Étape 2 : Lancer le Client**
Une fois le serveur prêt, exécutez la classe `rmiClient.ConversionClient`.
> *Console attendue :*
> ```text
> Client : Recherche de l'objet distant...
> Client : Invocation de la méthode convertirMontant(500.0)
> ------------------------------------------
> Montant initial : 500.0
> Résultat de la conversion : 1650.0
> ------------------------------------------
> ```

## 💡 Points Techniques Clés & Résolution de Problèmes

### L'héritage UnicastRemoteObject
Une erreur `java.io.NotSerializableException` a été rencontrée initialement.
*   **Cause :** L'objet d'implémentation n'était pas configuré pour être exporté via RMI.
*   **Solution :** La classe `ConversionImpl` a été modifiée pour étendre `java.rmi.server.UnicastRemoteObject`. Cela permet à la JVM de créer un **Stub** (référence) au lieu de tenter de sérialiser l'objet entier.

### Découplage via JAR
L'utilisation de `conversionClient.jar` démontre comment partager un contrat (interface) entre deux systèmes distribués sans partager la logique métier (implémentation).

## 👤 Auteur
Réalisé dans le cadre du TP de Systèmes Distribués / Java RMI.

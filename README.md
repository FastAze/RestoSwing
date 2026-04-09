# RestoSwing 🍽️

Application desktop Java Swing pour la gestion des commandes restaurant (liste, détail, acceptation, refus, terminaison), connectée aux API de RestoWeb.

## 📋 Présentation

RestoSwing est un client lourd Java destiné à l’équipe de préparation.

### Objectifs

- Afficher les commandes en attente via API.
- Consulter le détail d’une commande.
- Changer l’état d’une commande (accepter, refuser, terminer).
- Fournir une interface simple et rapide avec Swing.

## 🧱 Architecture du projet

| Dossier/Fichier | Description |
|:----------------|:------------|
| `src/Restoswing.java` | Point d’entrée de l’application |
| `src/api/` | Appels API REST (liste et actions commande) |
| `src/DAO/` | Modèles de données (`Commande`, `Ligne`) |
| `src/ui/` | Fenêtres Swing et formulaires `.form` |
| `libs/` | Dépendances externes (JSON) |

### Arborescence

```text
RestoSwing/
├── README.md
├── RestoSwing.iml
├── libs/
├── src/
│   ├── Restoswing.java
│   ├── api/
│   │   ├── api_commande_accepter.java
│   │   ├── api_commande_refuser.java
│   │   ├── api_commande_terminer.java
│   │   └── api_liste_commandes.java
│   ├── DAO/
│   │   ├── Commande.java
│   │   └── Ligne.java
│   └── ui/
│       ├── Commande_liste.java
│       ├── Commande_liste.form
│       ├── Commande_details.java
│       └── Commande_details.form
```

## 📦 Dépendances

Ce projet n’utilise pas Maven/Gradle : les dépendances sont embarquées en jar.

- `libs/` : bibliothèque JSON (`json-20250517.jar`)
- `lib/` (si présent sur votre poste) : bibliothèques UI comme MigLayout

> Vérifiez que tous les jars sont bien ajoutés au classpath de votre IDE.

## 🚀 Installation et exécution

### 1) Prérequis

- Java JDK 17+ recommandé
- IntelliJ IDEA (ou VS Code avec extension Java)

### 2) Compilation et exécution (Windows PowerShell)

Depuis la racine du projet :

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -cp "libs/*;lib/*" -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp "out;libs/*;lib/*" Restoswing
```

### 3) Compilation et exécution (macOS/Linux)

```bash
mkdir -p out
javac -cp "libs/*:lib/*" -d out $(find src -name "*.java")
java -cp "out:libs/*:lib/*" Restoswing
```

## 💻 Lancement depuis IntelliJ IDEA

1. Ouvrir le dossier du projet.
2. Configurer un SDK Java (Project SDK).
3. Ajouter les jars de `libs/` (et `lib/` si utilisé) dans les dépendances du module.
4. Créer une configuration **Application** :
   - Main class : `Restoswing`
   - Use classpath of module : module principal
5. Exécuter.

## 🔌 API utilisées

Le dossier `src/api/` contient les opérations principales :

- `api_liste_commandes` : récupération des commandes
- `api_commande_accepter` : passage en préparation
- `api_commande_refuser` : refus de commande
- `api_commande_terminer` : marquage de fin de préparation

## 🧪 Vérification rapide

Après lancement de l’application :

1. Ouvrir la liste des commandes.
2. Sélectionner une commande pour afficher le détail.
3. Tester les actions accepter/refuser/terminer.
4. Vérifier qu’aucune exception Java n’apparaît dans la console.

## 📚 Générer la Javadoc

```powershell
New-Item -ItemType Directory -Force javadoc | Out-Null
javadoc -d javadoc -cp "libs/*;lib/*" -sourcepath src DAO ui api
```

## ℹ️ Informations du projet

- **Projet** : RestoWeb - AP.SLAM BTS SIO 2ème année
- **Institut** : LIMAYRAC
- **Responsable** : Christophe PUEL
- **Repository** : [https://github.com/FastAze/restoSwing](https://github.com/FastAze/restoSwing)
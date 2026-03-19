# RestoSwing

RestoSwing est une petite application Java Swing (interface graphique) destinée à gérer des commandes de restaurant de RestoWeb.

Ce README décrit :
- l'objectif du projet
- l'organisation du dépôt
- les dépendances et où elles se trouvent
- comment compiler, exécuter et générer la Javadoc (macOS / zsh)
- comment contribuer

----

## 1) Aperçu

RestoSwing contient une interface Swing minimale (package `testui`) avec des classes d'exemple et des utilitaires. Le point d'entrée principal se trouve dans `src/testui/Main.java`.

Objectifs :
- Démontrer une UI Swing simple
- Illustrer l'utilisation de MigLayout (dans `lib/`) et d'une petite gestion JSON (dans `libs/`)

## 2) Structure du dépôt

Arborescence principale (les éléments importants) :

- `src/` : sources Java. Le package UI principal est `testui`.
  - `src/testui/Main.java` : classe principale (point d'entrée)
  - autres classes UI dans `src/testui`
- `lib/` : bibliothèques tiers (principalement MigLayout *.jar)
  - `miglayout-core.jar`, `miglayout-swing.jar`, ...
- `libs/` : autres bibliothèques (ex. `json-20250517.jar`)
- `META-INF/MANIFEST.MF` : manifeste (si utile pour packaging)
- `RestoSwing.iml` : fichier de configuration IntelliJ (module)

----

## 3) Dépendances

Les dépendances sont fournies localement dans :

- `lib/` : contient MigLayout (swing et core)
- `libs/` : contient `json-20250517.jar`

Il n'y a pas de gestionnaire de dépendances (Maven/Gradle) dans ce projet — les jars sont inclus directement.

----

## 4) Compiler et exécuter (ligne de commande - macOS / zsh)

Instructions prêtes à copier pour macOS (zsh). Ces commandes vont compiler tous les .java trouvés dans `src/` et lancer la classe `testui.Main` :

```bash
# depuis la racine du projet
mkdir -p out
javac -cp "lib/*:libs/*" -d out $(find src -name "*.java")
java -cp "out:lib/*:libs/*" testui.Main
```

Remarques :
- Le classpath utilise `:` (séparateur macOS/Unix). Si vous exécutez sur Windows, utilisez `;`.
- Si votre shell ne supporte l'expansion `lib/*` comme attendu, précisez explicitement les jars.

----

## 5) Exécution depuis IntelliJ IDEA

1. Ouvrez le dossier du projet (`File > Open...` et choisissez le répertoire du projet).
2. Assurez-vous qu'un SDK Java est configuré (Project Structure > SDKs / Project SDK).
3. Ajoutez les jars externes (`lib/*.jar` et `libs/*.jar`) en tant que Libraries ou ajoutez-les au module (Project Structure > Modules > Dependencies).
4. Créez une configuration de type "Application":
   - Main class : `testui.Main`
   - Classpath: module classpath (les jars ajoutés seront pris en compte)
5. Lancez avec le bouton Run.

----

## 6) Générer la Javadoc

Commande pour générer la Javadoc dans le dossier `javadoc/` :

```bash
# depuis la racine du projet
mkdir -p javadoc
javadoc -d javadoc -cp "lib/*:libs/*" -sourcepath src $(find src -name "*.java" | sed -e 's#^src/##' -e 's#/#.#g' -e 's#\.java$##')
```

Le `find | sed` construit la liste des noms de classes (packages) à documenter. Vous pouvez aussi lister explicitement les packages (ex. `testui`) :

```bash
javadoc -d javadoc -cp "lib/*:libs/*" -sourcepath src testui
```

----

## 7) Tests / Vérification rapide

Il n'y a pas de suite de tests automatisés fournie. Pour vérifier rapidement :

- Compiler et exécuter (voir section 4) ;
- Ouvrir l'interface et vérifier que les fenêtres s'affichent sans exceptions liées au classpath.

----



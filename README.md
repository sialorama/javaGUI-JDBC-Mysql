Cette application est une interface graphique (GUI) de **saisie de données personnelles** avec la possibilité de sauvegarder des informations dans une base de données, ainsi que de charger et afficher une image. 

Elle est développée en **Java** à l'aide de la bibliothèque **Swing** pour l'interface utilisateur et utilise **JDBC** pour interagir avec une base de données MySQL.

### Fonctionnalités principales :  
1. **Saisie des informations** : L'application permet à l'utilisateur d'entrer le nom, prénom, et âge dans des champs de texte.
2. **Affichage des informations** : Les données saisies peuvent être affichées dans une zone de texte pour vérifier leur exactitude.
3. **Upload d'image** : L'utilisateur peut charger une image (JPEG, PNG, GIF), et celle-ci est redimensionnée pour s'afficher dans l'interface.
4. **Réinitialisation les champs** : Un bouton "Reset" permet de vider les champs de texte et de supprimer l'image affichée.
5. **Sauvegarde dans une base de données** : Les informations saisies (nom, prénom, âge) sont enregistrées dans une base de données via JDBC.
6. **Gestion d'erreurs** : L'application valide l'entrée des données, notamment pour l'âge, et informe l'utilisateur en cas de saisie incorrecte.

### Technologies utilisées :
- **Java (Swing)** : Pour créer l'interface graphique.
- **JDBC (Java Database Connectivity)** : Pour la connexion et l'interaction avec une base de données.
- **MySQL** : Pour stocker les informations saisies.

Cette application peut être étendue pour inclure des fonctionnalités supplémentaires, comme la gestion des utilisateurs ou l'amélioration des interactions avec la base de données.

![img](interface.png)

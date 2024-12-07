# PcAuth (Gestion des produits)

## Description du Projet

Ce projet est une API REST sécurisée qui permet la gestion des produits, des catégories et des utilisateurs. Les fonctionnalités sont adaptées aux rôles d'administrateurs et d'utilisateurs.

---

## Objectif Général de l'Application

L'objectif principal de cette application est de fournir une API REST robuste pour :

1. Gérer les produits et les catégories.
2. Gérer les utilisateurs et leurs rôles.
3. Renforcer la sécurité grâce à l'authentification et au contrôle d'accès.
4. Intégrer des outils pour le déploiement continu.

---

## Structure du Projet

- **Controllers** : Gère les requêtes HTTP REST.
- **Services** : Contient la logique métier.
- **Repositories** : Interagit avec la base de données.
- **DTOs et Mappers** : Transforment les entités pour les réponses API.
- **Exceptions** : Gère les erreurs spécifiques à l'API.

---

## Entités Principales

### Produit
- **designation** (String)
- **prix** (Double)
- **quantite** (Integer)

### Catégorie
- **nom** (String)
- **description** (String)

### Utilisateur
- **login** (String)
- **password** (String)
- **active** (Boolean)
- **role** (Enum)

---

## Relations

- Une catégorie peut contenir plusieurs produits.
- Un produit appartient à une seule catégorie.

---

## Fonctionnalités du Projet

### Gestion des Produits
- **Lister les produits** avec pagination (USER ou ADMIN).
- **Rechercher des produits** par désignation avec pagination et tri (USER ou ADMIN).
- **Rechercher des produits** par catégorie (USER ou ADMIN).
- **Filtrer les produits** par catégorie avec pagination et tri (USER ou ADMIN).
- **Ajouter un produit** (ADMIN uniquement).
- **Modifier un produit existant** (ADMIN uniquement).
- **Supprimer un produit** (ADMIN uniquement).

**Endpoints :**
- `/api/user/products/**`
- `/api/admin/products/**`

### Gestion des Catégories
- **Lister les catégories** avec pagination (USER ou ADMIN).
- **Rechercher des catégories** par nom avec pagination et tri (USER ou ADMIN).
- **Lister les produits** d'une catégorie avec pagination et tri (USER ou ADMIN).
- **Ajouter une catégorie** (ADMIN uniquement).
- **Modifier une catégorie existante** (ADMIN uniquement).
- **Supprimer une catégorie** (ADMIN uniquement).

**Endpoints :**
- `/api/user/categories/**`
- `/api/admin/categories/**`

### Gestion des Utilisateurs
- **Authentification** : `/api/auth/login`
- **Création de compte** : `POST /api/auth/register`
- **Lister les utilisateurs** : `GET /api/admin/users` (ADMIN uniquement).
- **Gestion des rôles** : `PUT /api/admin/users/{id}/roles` (ADMIN uniquement).

---

## Sécurité

- **Authentification Stateful** : Gestion via session (JdbcAuthentication).
- **Cryptage des mots de passe** : Utilisation de `BCryptPasswordEncoder`.
- **Restrictions d'accès** :
  - `/api/admin/*` nécessite le rôle ADMIN.
  - `/api/user/*` nécessite le rôle USER.
- **Profils** :
  - **Test** : Sécurité simplifiée pour le développement.
  - **Prod** : Sécurité activée avec `JdbcAuthentication`.

---

## Technologies Utilisées

- **Frameworks** : Spring Boot, Spring Security, Spring Data JPA.
- **Base de données** : MariaDB.
- **Langage** : Java 17+.
- **CI/CD** : Jenkins.
- **Conteneurisation** : Docker.
- **Tests** : JUnit, Mockito, Postman.
- **Documentation API** : Swagger.
- **Outils** : Git (branches), JIRA (Scrum), SonarLint.

---

## Guide d'Installation et d'Exécution

### Prérequis

- Java 8 ou supérieur
- Maven
- Docker et Docker Compose

### Étapes à suivre

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/Douaesb/Producia.git
   cd Producia
   ```

2. Lancez les conteneurs Docker :
   ```bash
   docker-compose up
   ```

3. Testez les endpoints via Postman ou autres outils.

---

## Liens Utiles

- **Planification JIRA** : [Lien JIRA](https://oneno9847.atlassian.net/jira/software/projects/TOUR/boards/2/backlog?atlOrigin=eyJpIjoiODhlZmZlYzMxYjM0NGM2MjhhNGNjNzIyYzM5ZjcyYTkiLCJwIjoiaiJ9)


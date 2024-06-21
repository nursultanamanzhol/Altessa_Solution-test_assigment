# Altessa_Solution test_assigment

Задача. Пользователи GitHub

Приложение должно содержать 2 экрана:

1) Главный экран:

Users (список всех Github пользователей). Использовать API https://developer.github.com/v3/users/#get-all-users
В элементе списка отрисовать avatar, login (title), id (subtitle)
По нажатию на элемент списка реализовать переход на UserDetails
Реализовать pagination и Pull-to-refresh

2) UserDetails (экран с информацией о пользователе):

Использовать API https://developer.github.com/v3/users/#get-a-single-user
Поля: Avatar, Name, Email, Organization (если есть), Following count, Followers count, Дата создания аккаунта


Требования:

Структурированный код (архитектурный паттерн на усмотрение кандидата), UI Framework - Jetpack Compose
Язык :Kotlin
Код поместить в репозиторий на GitHub/Bitbucket/GitLab

В приложении должна быть обработка ошибок, отображение состояний загрузки.
Неоднозначности задания трактуются на усмотрение разработчика

# GitHubUsersApiApp

## Description

An Application that takes on data from the GitHub Api. An Api key was needed as to authorize the usage of it. The purpose of this application is to hierarchically display the users from the "beginning times" of GitHub

## A short tour of the app

As soon as the User opens the application, a call to the Api is being made with the necessary headers as for the request to be authorized. If the Internet Connection is available, the response is to be successfull, fetching the users from the server and displaaying them as for the User to interact with.

As to not load too much the UI with private details of each Github user, the application's algorithm is configured that when the User presses one GitHub user that is displayed in the list retrieved from the server, he will be redirected to the Details Screen of that particular Github user.

Details such as the position the user is, timeline sensitive, is situated, the number of repositories he has, as well as followers and following.

In case of wanting to know more about the user, a button called "Profile" is in charge or redirecting you to the user's Github profile, where more information can be seen and accessed.

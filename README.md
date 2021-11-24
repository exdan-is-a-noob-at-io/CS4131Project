# Praeclarum - A "Brilliant" Ripoff

Part of The Final Year Project in the CS4131 Module at NUS High School.

This application is an app which makes Math Olympiad Questions more accessible, if it wasn’t accessible enough already. It essentially works like a forum, allowing users to upload questions, solve questions, et cetra. 

Here are some features I have implemented:

## Account System

You can create a profile with your name, email, and password. I unfortunately forgot that Firebase had authentication inbuilt into it, and coded mine manually. Sure, not as secure as Google's, but its still something. 

## Question Creation System

Users can create new questions and edit previously created questions. Due to the long amount of time taken to load the questions, using any sorts of animations seems to break what the user can see. 

The app supports the usage of latex, and renders it for the user. 

## Question Viewing System

Each question has a certain difficulty assigned to it by the question’s creator (In light of the MO system in Singapore, 5 difficulties). Then, by clicking on a difficulty, a question of that difficulty is pulled in from the firebase, and loaded in by random (Priority is given to unfinished questions first, then to all questions of that difficulty if the user has already attempted all the questions). Latex is supported, however usage of diagrams in the questions is not supported.

Furthermore, an answer verification system is in place. 

## Search Functionality

Users may search for questions to attempt; in 3 different ways:

1. By Question (fuzzy wuzzy libary used, substring checked) 
2. By Question id (exact)
3. By Tags (exact, separated by commas. Capitalisation is not important.)

## Profile / Settings Page

Users may view their own profile, containing some details. On this page, you may also view one's placement on the leader boards. Furthermore, you may change your profile picture on this page as well. 



*This project is still in development stage. Please do not use it until necessary.*

*More information may be found under the Writeup.*
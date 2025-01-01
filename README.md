# Submarines
Overview
The Submarines Game is an advanced implementation of the classic battleship game in Java. It challenges players against an AI opponent, incorporating strategic gameplay and dynamic mechanics. The project emphasizes professional-grade programming practices, efficient algorithms, and clear modularity for maintainability and scalability.

## **Features**
Dynamic Gameplay:
Submarine placement with rotation angles and custom lengths.
Real-time board updates for both players and AI.
AI-Driven Opponent:
Adaptive difficulty levels (1-7).
Uses randomness and logical targeting for strategic attacks.
Robust Input Validation:
Ensures valid user entries for submarine placement and attacks.
Victory Conditions:
Automated checks for sunken submarines and game-end scenarios.
Scalable Design:
Modular methods enable easy feature extension and debugging.

## **Methods and Programming Features**
## **Core Methods**
LevelChoosing:
Ensures valid difficulty selection with error handling.
printTable:
Dynamically renders the game board, concealing AI submarines during user turns.
Getlocation:
Handles submarine placement logic for both players and AI, validating coordinates and angles.
Attack:
Processes user and AI attacks with logic for hit detection and board updates.
GetCompAttack:
AI-driven attack logic based on difficulty level and random targeting.
SubmarineIsHit:
Detects whether an attack hits a submarine and checks if it's fully sunk.
SubmarineIsDown:
Confirms if a submarine is destroyed, updating game state and announcing progress.
Advanced Programming Features
Abstraction and Modularity:
Core game logic is separated into reusable and maintainable methods.
Error Handling:
Ensures robust user input validation and graceful handling of invalid actions.
Dynamic Data Structures:
Multidimensional arrays for real-time board representation and submarine tracking.
AI Logic:
Integrates randomness and adaptive strategies for competitive gameplay.
Scalability:
Modular design enables easy addition of new features like multiplayer or enhanced AI.
Randomization:
Utilizes java.util.Random for dynamic gameplay elements such as AI attack coordinates.
Interactive Console:
Prompts and feedback ensure an intuitive user experience.


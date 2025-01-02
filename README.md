## **Submarines**
## **Overview**
The Submarines Game is an advanced implementation of the classic battleship game in Java. It challenges players against an AI opponent, incorporating strategic gameplay and dynamic mechanics. The project emphasizes professional-grade programming practices, efficient algorithms, and clear modularity for maintainability and scalability.

## **Features**
- Dynamic Gameplay: Submarine placement with rotation angles and custom lengths.<br>
- Real-time board updates for both players and AI.<br>
- AI-Driven Opponent: Adaptive difficulty levels (1-7).<br>
- Uses randomness and logical targeting for strategic attacks.<br>
- Robust Input Validation: Ensures valid user entries for submarine placement and attacks.<br>
- Victory Conditions: Automated checks for sunken submarines and game-end scenarios.<br>
- Scalable Design: Modular methods enable easy feature extension and debugging.<br>

## **Methods and Programming Features**
## **Core Methods**
* LevelChoosing: Ensures valid difficulty selection with error handling.<br>
* printTable: Dynamically renders the game board, concealing AI submarines during user turns.<br>
* Getlocation: Handles submarine placement logic for both players and AI, validating coordinates and angles.<br>
* Attack: Processes user and AI attacks with logic for hit detection and board updates.<br>
* GetCompAttack: AI-driven attack logic based on difficulty level and random targeting.<br>
* SubmarineIsHit: Detects whether an attack hits a submarine and checks if it's fully sunk.<br>
* SubmarineIsDown: Confirms if a submarine is destroyed, updating game state and announcing progress.<br>

## ** Advanced Programming Features**
<ins>Abstraction and Modularity:</ins> Core game logic is separated into reusable and maintainable methods.<br>
<ins>Error Handling:</ins>
Ensures robust user input validation and graceful handling of invalid actions.<br>
<ins>Dynamic Data Structures:</ins>
Multidimensional arrays for real-time board representation and submarine tracking.<br>
<ins>AI Logic:</ins>
Integrates randomness and adaptive strategies for competitive gameplay.<br>
<ins>Scalability:</ins>
Modular design enables easy addition of new features like multiplayer or enhanced AI.<br>
<ins>Randomization:</ins>
Utilizes java.util.Random for dynamic gameplay elements such as AI attack coordinates.<br>
<ins>Interactive Console:</ins>
Prompts and feedback ensure an intuitive user experience.<br>


# Hyperlight Drifter

![LoadingScreen](/screenshots/LoadingScreen.png)

## Setup/Installation (for IntelliJ):

Compile the project:  
Step 1) Open the file "build.gradle" with IntelliJ.  
Step 2) Select "Auto-import" in the options.  
Step 3) Wait for gradle to sync.  
Step 4) Go to "desktop/src/com/hotlinedrifter/tests", press Alt-Enter in "import org.junit.Test;" and select "Add 'JUnit 4' to classpath".  
Step 4) Go to "desktop/src/com/hotlinedrifter/desktop/DesktopLauncher". Run project as DesktopLauncher.  
Step 5) Go to "Run" | "Edit Configurations" and set the "Working directory" as the folder $PROJ/core/assets".  
Step 6) Ready to run!  

Play the game:  
Open the .jar file in the folder "jar".

## Development documentation:

### Updated UML diagrams:

#### Basic diagram
![BasicDiagram](/uml/basic_uml.png)

#### Full diagram
![FullDiagram](/uml/full_uml.png)

### Design patterns:

#### Model-view-controller:
The entities' models and views are separated from one another, using the controller (GameController) as "glue" between both of them.

#### Observer:
Observer is used to notify the physic's controller (GameController) when a collision occurs.

#### Strategy:
Each entity has several actions available to them: sit still, walk in a direction, attack, shoot, etc. With Strategy, an entity can easily change their behaviour and act accorddingly to its current action.

#### State:
Although an entity has several actions at their disposal, the ability to do one of them depends on its current state.

#### Singleton:
Several singleton classes are used for controllers (input controller, game controller and AI controller), since they should have only one instance.

#### Factory:
A factory is used to make and return a specific view for each type of model.

#### Flyweight:
In order to minimize the number of textures and sprites used, we use Flyweight objects to share this data with common objects.  

#### Object Pool:
Object pools are used to reuse and recycle bullets and attacks.

#### Update:
Update is used to simulate one frame of a model's behaviour.

#### Game Loop:
Using this pattern, we can simulate a physic's simulation and render as often as we want.

### Other design decisions:
We decided to implement Dijkstra's algorithm to be used in path-finding for the AI. This made the

### Major difficulties:
Organizing the code overrall.

### Overall time spent:
We spent around 3/4 weeks working.

### Work distribution:
Equal between members.

## User Manual:

Kill as many spider as you can! Are you strong enough to defeat the boss?  

Movement:  
W, A, S, D to move. Space to dash.  

Attack:  
Right click to attack, left click to shoot.  

![GameScreen](/screenshots/GameScreen.png)
![GameScreen2](/screenshots/GameScreen2.png)
![DeathScreen](/screenshots/DeathScreen.png)


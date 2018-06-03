# Hyperlight Drifter

![LoadingScreen](/screenshots/LoadingScreen.png)

## Setup/Installation:

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








## User Manual:


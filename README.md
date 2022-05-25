# StarwarsApplication

The application follows Clean architecture guidelines, where each subpackage represents a layer in the architecture

* Dependency Inject was done using hilt
* Tests were written: Unit tests and Test for data layer
* Navigation component was used with a single activity
* The app flow provided in this Read Me
* Network Connectivity Check since application is currently only available online unless feature addition requires otherwise.

**Data Layer**

This contains my model of responses for Characters,Planet,Species and Films. 
The repository that conditions the data received from the network as well as the usecases for the
search implementation and character details requests.

**Presenter Layer**
This contains the viewmodels for each flow of the application(Search, Detail flow and a shared on for allowing the fragments to communicate) that mediates between the usecases and the views.

**DI**

The dependency injection package contains the module for providing required classes and in the case, just the network module since everything is performed over the network

**UI(View) Layer**

This Layer manages the screens which includes
* The Search Fragment
* The Detail Fragment
* and necessary set up(e.g Adapter) for displaying the data.

The App Flow:

The search triggers a request providing a list of characters in the Starwars Universe, queried by name of the character.
The selected character's data is exposed using a shared viewmodel to be observed by the detail screen
This response shared doesnt contain all the required detail screen data,
in some cases single URLs are provided as a link to the planet and species (planet name, population and language data)  
and in other cases as in the list of film, a list of url linking to the films is provided. In this lies the film title and opening_crawl data,
which is gotten by making a full request for films in the Starwars Universe and filtered by the user's url in the character list putting efficiency 
and space optimization into consideration.


**May the code be with you too**

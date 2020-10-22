# My Personal Project: C-Auction

## Car Auction Application

The application will be similar to services such as *Craiglist, eBay and Facebook Marketplace*. It will have different cars that are available for auction on the application that the user can view, and potentially
can place a bid on if they choose to. Some **features** will include a system to check if you won the bid, or if you want to put your own listing up for auction. 
<br>

Anyone who wants to **sell** their cars to people across the country can use this application, instead of getting lowballed for trade in value at a car dealership, or on a *classifieds page* where people are known to have sketchy and unreliable experiences.
<br>

I find existing services **incomplete** and have a **poor user interface**. For example, *Craiglist* still looks like its from the 1990s when the internet first began to become mainstream. There is also no concrete service that provides a clean car auction experience for the average person, unless you want to buy extremely high end or imported Japanese vehicles. 
<br>
<br>

## User Stories: <br>
- As a user, I want to be able to place a bid for a car that is listed in the system <br>
- As a user, I want to add my own car into the list of cars up for auction <br>
- As a user, I want to be able to delete my listing that was previously created <br>
- As a user, I want to be able to check if I won or lost the bid on a car <br>
- As a user, I want to be able to load a file that contains existing listings <br>
- As a user, I want to be able to have my listing, that was added, to be reloaded from a file and resume from where I left off if I quit <br>
#

# Instructions for Grader 
- You can add your own car listing by clicking on the 'View current listings' button and filling out the info on the next page, then clicking the 'Add car to listings' button
- You can remove your car listing by clicking on the 'Remove your listing' button
- You can locate an image on the main screen or on the 'View current listings' screen.
- You can also hear a sound effect being played if either one of the first three buttons are pressed, along with the 'Save listings file' button
- You can save the state of my application by clicking on the 'Save listings file' button
- You can reload the state of my application by clicking on the 'Load listings file' button

# Phase 4: Task 2
I chose to implement Option 1, where I added a IndexOutOfBoundsException to getCar() in the Cars class to make sure that the Cars object has been initialized before proceeding with anything further <br>
By making the following classes more robust, it will ensure that the program will cause less errors for the user and cause a much smoother experience: GUI | loadCarListings() / removeListing() / addCarToListings(), CarsTest | testGetCarExpectedException() & testGetListingsAndExceptionFails(), ReaderTest | testParseCarsFile(), WriterTest | testWriteCarListing()  

# Phase 4: Task 3
- The first change was to declare my main menu buttons as global and create a method to initialize them in my GUI constructor, as it was not following the single responsibility principle
- The second change was noticed while coding my GUI class, I realized that many repetitive tasks were occurring. I made a method that would take a JPanel, and a JButton as arguments, and would create the settings of the button and add it to the given panel. This was done in order to avoid cohesion, and to make the method reusable for other panels within the class. 
- The third change I did was make to make methods to initialize all panels in the GUI constructor to avoid duplication of the labels and buttons in them. This also helps for the single responsibility principle, and prevents coupling in later methods.  
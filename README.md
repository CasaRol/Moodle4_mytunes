# Spring web application

This is the 3rd assignment following Experis Academy.  

For this assignment I had to create a simple clone of iTunes.  
This is build using Spring Boot and Spring Initializer with Thymeleaf as frontend.  
All data used in this project is taken form the provided database made with SQLite.  

The frontend of this project can be found on Heroku at: https://casarol-mytunes.herokuapp.com/  
The deployment has been done using Docker containerization (The image can be found on Docker hub under "casarol/mytunes" (NOTE: Image does not work outside the git repo root due to path specification within the image)). 
I tried deploying the image using the GitHub repo every time so that the image could run by itself but this gave too much trouble for the assignment scope to be viable at this time.    

It's a very simple website with an index page containing a search field where you can enter a song name (Case insensitive).
When pressing submit you'll be taken to a new page which will show you the result of the search along with the search word itself.  

Parts of the application backend contains API calls with 6 different possible calls: 
<ol>
<li>Get all customers</li>
<li>Add a new customer</li>
<li>update an existing customer</li>
<li>Get the number of customers per country</li>
<li>Get the customers with highest spending</li>
<li>Find the top genre for a specific customer</li>
</ol>

All of these calls can be tested using Postman (https://www.postman.com/downloads/) and import the provided collection (https://github.com/CasaRol/Moodle4_mytunes/tree/master/Grading%20Resources).  

Please note: The target folder is usually a part of the .gitignore file, but I've manually removed it in order to create an image which could run by itself  by cloning the repo.  
But as mentioned above, this didn't pan out as expected and was put in the back of the priority queue.

